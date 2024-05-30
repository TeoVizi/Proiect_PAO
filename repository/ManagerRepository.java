package repository;

import config.DatabaseConfiguration;
import model.Manager;
import model.Restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ManagerRepository {

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS managers" +
                "(id INT PRIMARY KEY AUTO_INCREMENT, " +
                "nume VARCHAR(100), " +
                "email VARCHAR(100), " +
                "username VARCHAR(100), " +
                "parola VARCHAR(100))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getIdByUsername(String username) {
        String selectSql = "SELECT id FROM managers WHERE username = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(selectSql);
            pstmt.setString(1, username);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void addManager(Manager manager) {
        String insertManagerSql = "INSERT INTO managers(nume, email, username, parola) VALUES(?, ?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement pstmt = connection.prepareStatement(insertManagerSql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, manager.getNume());
            pstmt.setString(2, manager.getEmail());
            pstmt.setString(3, manager.getUsername());
            pstmt.setString(4, manager.getParola());

            pstmt.executeUpdate();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int managerId = generatedKeys.getInt(1);
                manager.setId(managerId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Manager getManagerById(int id) {
        String selectSql = "SELECT * FROM managers WHERE id = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(selectSql);
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                Manager manager = new Manager();
                manager.setId(resultSet.getInt("id"));
                manager.setNume(resultSet.getString("nume"));
                manager.setEmail(resultSet.getString("email"));
                manager.setUsername(resultSet.getString("username"));
                manager.setParola(resultSet.getString("parola"));
                return manager;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean usernameExists(String username) {
        String selectSql = "SELECT * FROM managers WHERE username = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(selectSql);
            pstmt.setString(1, username);
            ResultSet resultSet = pstmt.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void updateManager(Manager manager) {
        String updateSql = "UPDATE managers SET nume = ?, email = ?, username = ?, parola = ? WHERE id = ?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(updateSql);
            pstmt.setString(1, manager.getNume());
            pstmt.setString(2, manager.getEmail());
            pstmt.setString(3, manager.getUsername());
            pstmt.setString(4, manager.getParola());
            pstmt.setInt(5, this.getIdByUsername(manager.getUsername()));

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteManager(int id) {
        String deleteSql = "DELETE FROM managers WHERE id = ?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(deleteSql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Manager getManagerByUsernameAndPassword(String username, String parola) {
        String query = "SELECT * FROM managers WHERE username = ? AND parola = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, parola);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return new Manager(
                        resultSet.getString("nume"),
                        resultSet.getString("email"),
                        resultSet.getString("username"),
                        resultSet.getString("parola")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void assignRestaurantToManager(int managerId, int restaurantId) {
        String assignSql = "INSERT INTO manager_restaurant(manager_id, restaurant_id) VALUES(?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement pstmt = connection.prepareStatement(assignSql);
            pstmt.setInt(1, managerId);
            pstmt.setInt(2, restaurantId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Restaurant> getRestaurantsByManager(int managerId) {
        List<Restaurant> restaurants = new ArrayList<>();
        String selectSql = "SELECT r.* FROM restaurants r " +
                "INNER JOIN manager_restaurant mr ON r.id = mr.restaurant_id " +
                "WHERE mr.manager_id = ?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(selectSql);
            pstmt.setInt(1, managerId);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Restaurant restaurant = new Restaurant(
                        resultSet.getString("nume"),
                        resultSet.getString("adresa"),
                        resultSet.getDouble("cost_livrare")
                );
                restaurants.add(restaurant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurants;
    }

    public boolean emailExists(String email) {
        String query = "SELECT * FROM managers WHERE email = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, email);
            ResultSet resultSet = pstmt.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
