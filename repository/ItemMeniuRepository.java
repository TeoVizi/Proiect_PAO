package repository;

import config.DatabaseConfiguration;
import model.ItemMeniu;
import service.AuditService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ItemMeniuRepository {

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS item_meniu (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "meniuId INT, " +
                "nume VARCHAR(255), " +
                "descriere VARCHAR(255), " +
                "pret DOUBLE, " +
                "FOREIGN KEY (meniuId) REFERENCES meniu(id) ON DELETE CASCADE)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        AuditService.getInstance().logAction("create Table ItemMeniu");

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public int addItemMeniu(ItemMeniu itemMeniu) {
        String insertItemMeniuSql = "INSERT INTO item_meniu(nume, descriere, pret, meniuId) VALUES(?, ?, ?, ?)";
        AuditService.getInstance().logAction("create ItemMeniu");
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement pstmt = connection.prepareStatement(insertItemMeniuSql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, itemMeniu.getNume());
            pstmt.setString(2, itemMeniu.getDescriere());
            pstmt.setDouble(3, itemMeniu.getPret());
            pstmt.setInt(4, itemMeniu.getMeniuId());

            pstmt.executeUpdate();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int itemMeniuId = generatedKeys.getInt(1);
                itemMeniu.setId(itemMeniuId);
                return itemMeniuId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


    public ItemMeniu getItemMeniuById(int id) {
        String selectSql = "SELECT * FROM item_meniu WHERE id = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        AuditService.getInstance().logAction("read ItemMeniu");

        try {
            PreparedStatement pstmt = connection.prepareStatement(selectSql);
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                return new ItemMeniu(
                        resultSet.getInt("id"),
                        resultSet.getString("nume"),
                        resultSet.getString("descriere"),
                        resultSet.getDouble("pret"),
                        resultSet.getInt("meniuId")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateItemMeniu(ItemMeniu itemMeniu) {
        String updateSql = "UPDATE item_meniu SET nume = ?, descriere = ?, pret = ? WHERE id = ?";
        AuditService.getInstance().logAction("update ItemMeniu");

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(updateSql);
            pstmt.setString(1, itemMeniu.getNume());
            pstmt.setString(2, itemMeniu.getDescriere());
            pstmt.setDouble(3, itemMeniu.getPret());
            pstmt.setInt(4, itemMeniu.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteItemMeniu(int id) {
        String deleteSql = "DELETE FROM item_meniu WHERE id = ?";
        AuditService.getInstance().logAction("delete ItemMeniu");

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
