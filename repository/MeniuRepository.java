package repository;

import config.DatabaseConfiguration;
import model.Meniu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MeniuRepository {

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS meniu" +
                "(id INT PRIMARY KEY AUTO_INCREMENT, " +
                "restaurant_id INT, " +
                "FOREIGN KEY (restaurant_id) REFERENCES restaurants(id))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMeniu(Meniu meniu, int restaurantId) {
        String insertMeniuSql = "INSERT INTO meniu(restaurant_id) VALUES(?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement pstmt = connection.prepareStatement(insertMeniuSql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, restaurantId);

            pstmt.executeUpdate();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int meniuId = generatedKeys.getInt(1);
                meniu.setId(meniuId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Meniu getMeniuById(int id) {
        String selectSql = "SELECT * FROM meniu WHERE id = ?";
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

    public void deleteMeniu(int id) {
        String deleteSql = "DELETE FROM meniu WHERE id = ?";

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
