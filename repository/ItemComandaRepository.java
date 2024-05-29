package repository;

import config.DatabaseConfiguration;
import model.ItemComanda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemComandaRepository {

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS item_comanda" +
                "(id INT PRIMARY KEY AUTO_INCREMENT, " +
                "item_meniu_id INT, " +
                "cantitate INT, " +
                "comanda_id INT, " +
                "FOREIGN KEY (item_meniu_id) REFERENCES item_meniu(id), " +
                "FOREIGN KEY (comanda_id) REFERENCES comenzi(id))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addItemComanda(ItemComanda itemComanda, int comandaId) {
        String insertItemComandaSql = "INSERT INTO item_comanda(item_meniu_id, cantitate, comanda_id) VALUES(?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement pstmt = connection.prepareStatement(insertItemComandaSql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, itemComanda.getItemMeniu().getId());
            pstmt.setInt(2, itemComanda.getCantitate());
            pstmt.setInt(3, comandaId);

            pstmt.executeUpdate();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int itemComandaId = generatedKeys.getInt(1);
                itemComanda.setId(itemComandaId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ItemComanda> getItemsForComanda(int comandaId) {
        String selectSql = "SELECT * FROM item_comanda WHERE comanda_id = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        List<ItemComanda> items = new ArrayList<>();
        try {
            PreparedStatement pstmt = connection.prepareStatement(selectSql);
            pstmt.setInt(1, comandaId);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                ItemMeniuRepository itemMeniuRepository = new ItemMeniuRepository();
                ItemComanda itemComanda = new ItemComanda(
                        itemMeniuRepository.getItemMeniuById(resultSet.getInt("item_meniu_id")),
                        resultSet.getInt("cantitate")
                );
                itemComanda.setId(resultSet.getInt("id"));
                items.add(itemComanda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public void updateItemComanda(ItemComanda itemComanda, int comandaId) {
        String updateSql = "UPDATE item_comanda SET item_meniu_id = ?, cantitate = ? WHERE id = ? AND comanda_id = ?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(updateSql);
            pstmt.setInt(1, itemComanda.getItemMeniu().getId());
            pstmt.setInt(2, itemComanda.getCantitate());
            pstmt.setInt(3, itemComanda.getId());
            pstmt.setInt(4, comandaId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteItemComanda(int id) {
        String deleteSql = "DELETE FROM item_comanda WHERE id = ?";

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
