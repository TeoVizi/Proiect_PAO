package repository;

import config.DatabaseConfiguration;
import model.Restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RestaurantRepository {

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS restaurants" +
                "(id INT PRIMARY KEY AUTO_INCREMENT, " +
                "nume VARCHAR(50), " +
                "adresa VARCHAR(100), " +
                "cost_livrare DOUBLE)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addRestaurant(Restaurant restaurant) {
        String insertRestaurantSql = "INSERT INTO restaurants(nume, adresa, cost_livrare) VALUES(?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement pstmt = connection.prepareStatement(insertRestaurantSql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, restaurant.getNume());
            pstmt.setString(2, restaurant.getAdresa());
            pstmt.setDouble(3, restaurant.getCostLivrare());

            pstmt.executeUpdate();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int restaurantId = generatedKeys.getInt(1);
                restaurant.setId(restaurantId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Restaurant getRestaurantById(int id) {
        String selectSql = "SELECT * FROM restaurants WHERE id = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(selectSql);
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                Restaurant restaurant = new Restaurant();
                restaurant.setId(resultSet.getInt("id"));
                restaurant.setNume(resultSet.getString("nume"));
                restaurant.setAdresa(resultSet.getString("adresa"));
                restaurant.setCostLivrare(resultSet.getDouble("cost_livrare"));
                return restaurant;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateRestaurant(Restaurant restaurant) {
        String updateSql = "UPDATE restaurants SET nume = ?, adresa = ?, cost_livrare = ? WHERE id = ?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(updateSql);
            pstmt.setString(1, restaurant.getNume());
            pstmt.setString(2, restaurant.getAdresa());
            pstmt.setDouble(3, restaurant.getCostLivrare());
            pstmt.setInt(4, restaurant.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRestaurant(int id) {
        String deleteSql = "DELETE FROM restaurants WHERE id = ?";

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
