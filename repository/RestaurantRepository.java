package repository;

import config.DatabaseConfiguration;
import model.Restaurant;
import service.AuditService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RestaurantRepository {

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS restaurants" +
                "(id INT PRIMARY KEY AUTO_INCREMENT, " +
                "nume VARCHAR(100), " +
                "adresa VARCHAR(255), " +
                "cost_livrare DOUBLE)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        AuditService.getInstance().logAction("create Table Restaurants");

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public int addRestaurant(Restaurant restaurant) {
        String insertRestaurantSql = "INSERT INTO restaurants(nume, adresa, cost_livrare) VALUES(?, ?, ?)";
        AuditService.getInstance().logAction("create Restaurants");

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement pstmt = connection.prepareStatement(insertRestaurantSql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, restaurant.getNume());
            pstmt.setString(2, restaurant.getAdresa());
            pstmt.setDouble(3, restaurant.getCostLivrare());

            pstmt.executeUpdate();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public Restaurant getRestaurantById(int id) {
        String selectSql = "SELECT * FROM restaurants WHERE id = ?";
        AuditService.getInstance().logAction("read Restaurants");

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
        AuditService.getInstance().logAction("update Restaurants");

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
        AuditService.getInstance().logAction("delete Restaurants");

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(deleteSql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Restaurant> getAllRestaurants() {
        String selectSql = "SELECT * FROM restaurants";
        AuditService.getInstance().logAction("read Restaurants");

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        List<Restaurant> restaurants = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next()) {
                Restaurant restaurant = new Restaurant();
                restaurant.setId(resultSet.getInt("id"));
                restaurant.setNume(resultSet.getString("nume"));
                restaurant.setAdresa(resultSet.getString("adresa"));
                restaurant.setCostLivrare(resultSet.getDouble("cost_livrare"));
                restaurants.add(restaurant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurants;
    }
}
