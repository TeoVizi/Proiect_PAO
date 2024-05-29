package repository;

import config.DatabaseConfiguration;
import model.Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManagerRepository {

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS managers" +
                "(id INT PRIMARY KEY AUTO_INCREMENT, " +
                "nume VARCHAR(50), " +
                "email VARCHAR(50), " +
                "username VARCHAR(50), " +
                "parola VARCHAR(50))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public void updateManager(Manager manager) {
        String updateSql = "UPDATE managers SET nume = ?, email = ?, username = ?, parola = ? WHERE id = ?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(updateSql);
            pstmt.setString(1, manager.getNume());
            pstmt.setString(2, manager.getEmail());
            pstmt.setString(3, manager.getUsername());
            pstmt.setString(4, manager.getParola());
            pstmt.setInt(5, manager.getId());

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

    public Manager getManagerByUsernameAndPassword(String username, String password) {
        String query = "SELECT * FROM managers WHERE username = ? AND parola = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
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
