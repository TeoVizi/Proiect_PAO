package repository;

import config.DatabaseConfiguration;
import model.Sofer;
import service.AuditService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SoferRepository {

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS soferi" +
                "(id INT PRIMARY KEY AUTO_INCREMENT, " +
                "nume VARCHAR(50), " +
                "email VARCHAR(50), " +
                "username VARCHAR(50), " +
                "parola VARCHAR(50), " +
                "locatie VARCHAR(100), " +
                "disponibilitate BOOLEAN)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        AuditService.getInstance().logAction("create Table Soferi");

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int addSofer(Sofer sofer) {
        String insertSoferSql = "INSERT INTO soferi(nume, email, username, parola, locatie, disponibilitate) VALUES(?, ?, ?, ?, ?, ?)";
        AuditService.getInstance().logAction("create Soferi");

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement pstmt = connection.prepareStatement(insertSoferSql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, sofer.getNume());
            pstmt.setString(2, sofer.getEmail());
            pstmt.setString(3, sofer.getUsername());
            pstmt.setString(4, sofer.getParola());
            pstmt.setString(5, sofer.getLocatie());
            pstmt.setBoolean(6, sofer.getDisponibilitate());

            pstmt.executeUpdate();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1); // Return the generated sofer ID
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public Sofer getSoferById(int id) {
        String selectSql = "SELECT * FROM soferi WHERE id = ?";
        AuditService.getInstance().logAction("read Soferi");

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(selectSql);
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                Sofer sofer = new Sofer();
                sofer.setId(resultSet.getInt("id"));
                sofer.setNume(resultSet.getString("nume"));
                sofer.setEmail(resultSet.getString("email"));
                sofer.setUsername(resultSet.getString("username"));
                sofer.setParola(resultSet.getString("parola"));
                sofer.setLocatie(resultSet.getString("locatie"));
                sofer.setDisponibilitate(resultSet.getBoolean("disponibilitate"));
                return sofer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getIdByUsername(String username) {
        String selectSql = "SELECT id FROM soferi WHERE username = ?";
        AuditService.getInstance().logAction("read Soferi");

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


    public void updateSofer(Sofer sofer) {
        String updateSql = "UPDATE soferi SET nume = ?, email = ?, username = ?, parola = ?, locatie = ?, disponibilitate = ? WHERE id = ?";
        AuditService.getInstance().logAction("update Soferi");

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(updateSql);
            pstmt.setString(1, sofer.getNume());
            pstmt.setString(2, sofer.getEmail());
            pstmt.setString(3, sofer.getUsername());
            pstmt.setString(4, sofer.getParola());
            pstmt.setString(5, sofer.getLocatie());
            pstmt.setBoolean(6, sofer.getDisponibilitate());
            pstmt.setInt(7, this.getIdByUsername(sofer.getUsername()));

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSofer(int id) {
        String deleteSql = "DELETE FROM soferi WHERE id = ?";
        AuditService.getInstance().logAction("delete Soferi");

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(deleteSql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Sofer getSoferByUsernameAndPassword(String username, String password) {
        String selectSql = "SELECT * FROM soferi WHERE username = ? AND parola = ?";
        AuditService.getInstance().logAction("read Soferi");

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement pstmt = connection.prepareStatement(selectSql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                Sofer sofer = new Sofer();
                sofer.setId(resultSet.getInt("id"));
                sofer.setNume(resultSet.getString("nume"));
                sofer.setEmail(resultSet.getString("email"));
                sofer.setUsername(resultSet.getString("username"));
                sofer.setParola(resultSet.getString("parola"));
                sofer.setLocatie(resultSet.getString("locatie"));
                sofer.setDisponibilitate(resultSet.getBoolean("disponibilitate"));
                return sofer;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean usernameExists(String username) {
        String selectSql = "SELECT * FROM soferi WHERE username = ?";
        AuditService.getInstance().logAction("read Soferi");

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
    public boolean emailExists(String email) {
        String query = "SELECT * FROM soferi WHERE email = ?";
        AuditService.getInstance().logAction("read Soferi");
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
