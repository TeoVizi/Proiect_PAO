package repository;

import config.DatabaseConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManagerRestaurantRepository {

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS manager_restaurants" +
                "(manager_id INT, " +
                "restaurant_id INT, " +
                "PRIMARY KEY (manager_id, restaurant_id), " +
                "FOREIGN KEY (manager_id) REFERENCES managers(id), " +
                "FOREIGN KEY (restaurant_id) REFERENCES restaurants(id))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addManagerRestaurant(int managerId, int restaurantId) {
        String insertSql = "INSERT INTO manager_restaurants(manager_id, restaurant_id) VALUES(?, ?)";

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

    public void displayManagerRestaurants() {
        String selectSql = "SELECT * FROM manager_restaurants";

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
}
