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

public class ManagerRestaurantRepository {

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS manager_restaurants" +
                "(manager_id INT, " +
                "restaurant_id INT, " +
                "PRIMARY KEY (manager_id, restaurant_id), " +
                "FOREIGN KEY (manager_id) REFERENCES managers(id) ON DELETE CASCADE, " +
                "FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        AuditService.getInstance().logAction("create Table ManagersRestaurants");

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addManagerRestaurant(int managerId, int restaurantId) {
        String insertSql = "INSERT INTO manager_restaurants(manager_id, restaurant_id) VALUES(?, ?)";
        AuditService.getInstance().logAction("create ManagersRestaurants");

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement pstmt = connection.prepareStatement(insertSql);
            pstmt.setInt(1, managerId);
            pstmt.setInt(2, restaurantId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeManagerRestaurantByRestaurantId(int restaurantId) {
        String deleteSql = "DELETE FROM manager_restaurants WHERE restaurant_id = ?";
        AuditService.getInstance().logAction("delete ManagersRestaurants");

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(deleteSql);
            pstmt.setInt(1, restaurantId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayManagerRestaurants() {
        String selectSql = "SELECT * FROM manager_restaurants";
        AuditService.getInstance().logAction("read ManagersRestaurants");

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSql);

            while (resultSet.next()) {
                System.out.println("Manager Id: " + resultSet.getInt("manager_id"));
                System.out.println("Restaurant Id: " + resultSet.getInt("restaurant_id"));
                System.out.println("------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Restaurant> getRestaurantsByManagerId(int managerId) {
        String selectSql = "SELECT r.id, r.nume, r.adresa, r.cost_livrare FROM restaurants r " +
                "INNER JOIN manager_restaurants mr ON r.id = mr.restaurant_id " +
                "WHERE mr.manager_id = ?";
        AuditService.getInstance().logAction("read ManagersRestaurants");

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        List<Restaurant> restaurants = new ArrayList<>();

        try {
            PreparedStatement pstmt = connection.prepareStatement(selectSql);
            pstmt.setInt(1, managerId);
            ResultSet resultSet = pstmt.executeQuery();

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
