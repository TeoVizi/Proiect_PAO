package repository;

import config.DatabaseConfiguration;
import model.ItemMeniu;
import model.Meniu;
import service.AuditService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MeniuRepository {

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS meniu" +
                "(id INT PRIMARY KEY AUTO_INCREMENT, " +
                "restaurantId INT, " +
                "FOREIGN KEY (restaurantId) REFERENCES restaurants(id) ON DELETE CASCADE)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        AuditService.getInstance().logAction("create Table Meniu");

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int addMeniu(Meniu meniu, int restaurantId) {
        String insertMeniuSql = "INSERT INTO meniu(restaurantId) VALUES(?)";
        AuditService.getInstance().logAction("create Meniu");

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement pstmt = connection.prepareStatement(insertMeniuSql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, restaurantId);

            pstmt.executeUpdate();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int meniuId = generatedKeys.getInt(1);
                meniu.setId(meniuId);

                for (ItemMeniu item : meniu.getListaItemiMeniu()) {
                    addItemMeniu(item, meniuId);
                }

                return meniuId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void addItemMeniu(ItemMeniu itemMeniu, int meniuId) {
        String insertItemMeniuSql = "INSERT INTO item_meniu(meniuId, nume, descriere, pret) VALUES(?, ?, ?, ?)";
        AuditService.getInstance().logAction("create Meniu");

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement pstmt = connection.prepareStatement(insertItemMeniuSql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, meniuId);
            pstmt.setString(2, itemMeniu.getNume());
            pstmt.setString(3, itemMeniu.getDescriere());
            pstmt.setDouble(4, itemMeniu.getPret());
            pstmt.executeUpdate();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int itemMeniuId = generatedKeys.getInt(1);
                itemMeniu.setId(itemMeniuId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Meniu getMeniuById(int id) {
        String selectSql = "SELECT * FROM meniu WHERE id = ?";
        AuditService.getInstance().logAction("read Meniu");

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(selectSql);
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                Meniu meniu = new Meniu();
                meniu.setId(resultSet.getInt("id"));
                return meniu;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateMeniu(Meniu meniu) {
        String updateSql = "UPDATE meniu SET restaurant_id = ? WHERE id = ?";
        AuditService.getInstance().logAction("update Meniu");

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(updateSql);
            pstmt.setInt(1, meniu.getRestaurantId());
            pstmt.setInt(2, meniu.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ItemMeniu> getItemsByMeniuId(int meniuId) {
        List<ItemMeniu> items = new ArrayList<>();
        String selectSql = "SELECT * FROM item_meniu WHERE meniuId = ?";
        AuditService.getInstance().logAction("read Meniu");

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(selectSql);
            pstmt.setInt(1, meniuId);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                ItemMeniu item = new ItemMeniu(
                        resultSet.getString("nume"),
                        resultSet.getString("descriere"),
                        resultSet.getDouble("pret")
                );
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public Meniu getMeniuByRestaurantId(int restaurantId) {
        String selectSql = "SELECT im.id as itemId, im.nume, im.descriere, im.pret " +
                "FROM meniu m " +
                "JOIN item_meniu im ON m.id = im.meniuId " +
                "WHERE m.restaurantId = ?";
        AuditService.getInstance().logAction("read Meniu");

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        Meniu meniu = new Meniu();
        meniu.setRestaurantId(restaurantId);

        try {
            PreparedStatement pstmt = connection.prepareStatement(selectSql);
            pstmt.setInt(1, restaurantId);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                ItemMeniu item = new ItemMeniu();
                item.setId(resultSet.getInt("itemId"));
                item.setNume(resultSet.getString("nume"));
                item.setDescriere(resultSet.getString("descriere"));
                item.setPret(resultSet.getDouble("pret"));
                meniu.adaugaItemMeniu(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return meniu;
    }


    public ItemMeniu getItemMeniuById(int id) {
        String selectSql = "SELECT * FROM item_meniu WHERE id = ?";
        AuditService.getInstance().logAction("read Meniu");

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(selectSql);
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return new ItemMeniu(
                        resultSet.getString("nume"),
                        resultSet.getString("descriere"),
                        resultSet.getDouble("pret")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteMeniu(int id) {
        String deleteSql = "DELETE FROM meniu WHERE id = ?";
        AuditService.getInstance().logAction("delete Meniu");

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(deleteSql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
