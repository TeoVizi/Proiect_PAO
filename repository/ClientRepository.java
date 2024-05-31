package repository;

import config.DatabaseConfiguration;
import model.Client;
import service.AuditService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientRepository {

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS clients" +
                "(id INT PRIMARY KEY AUTO_INCREMENT, " +
                "nume VARCHAR(100), " +
                "email VARCHAR(100), " +
                "username VARCHAR(100), " +
                "parola VARCHAR(100), " +
                "strada VARCHAR(255), " +
                "numar INT(4), " +
                "oras VARCHAR(255), " +
                "isPremium BOOLEAN)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(createTableSql);
            AuditService.getInstance().logAction("creare Tabel Client");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean usernameExists(String username) {
        String selectSql = "SELECT * FROM clients WHERE username = ?";
        AuditService.getInstance().logAction("read Client");

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


    public int getIdByUsername(String username) {
        String selectSql = "SELECT id FROM clients WHERE username = ?";
        AuditService.getInstance().logAction("read Client");

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


    public int addClient(Client client) {
        String insertClientSql = "INSERT INTO clients(nume, email, username, parola, strada, numar, oras, isPremium) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        AuditService.getInstance().logAction("create Client");

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement pstmt = connection.prepareStatement(insertClientSql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, client.getNume());
            pstmt.setString(2, client.getEmail());
            pstmt.setString(3, client.getUsername());
            pstmt.setString(4, client.getParola());
            pstmt.setString(5, client.getStrada());
            pstmt.setString(6, client.getNumar());
            pstmt.setString(7, client.getOras());
            pstmt.setBoolean(8, client.getIsPremium());

            pstmt.executeUpdate();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int clientId = generatedKeys.getInt(1);
                client.setId(clientId);
                return clientId; // Return the generated client ID
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 if there was an error
    }

    public Client getClientById(int id) {
        String selectSql = "SELECT * FROM clients WHERE id = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        AuditService.getInstance().logAction("read Client");

        try {
            PreparedStatement pstmt = connection.prepareStatement(selectSql);
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setNume(resultSet.getString("nume"));
                client.setEmail(resultSet.getString("email"));
                client.setUsername(resultSet.getString("username"));
                client.setParola(resultSet.getString("parola"));
                client.setStrada(resultSet.getString("strada"));
                client.setNumar(resultSet.getString("numar"));
                client.setOras(resultSet.getString("oras"));
                client.setPremium(resultSet.getBoolean("isPremium"));
                return client;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Client getClientByUsernameAndPassword(String username, String password) {
        String selectSql = "SELECT * FROM clients WHERE username = ? AND parola = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        AuditService.getInstance().logAction("read Client");

        try {
            PreparedStatement pstmt = connection.prepareStatement(selectSql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setNume(resultSet.getString("nume"));
                client.setEmail(resultSet.getString("email"));
                client.setUsername(resultSet.getString("username"));
                client.setParola(resultSet.getString("parola"));
                client.setStrada(resultSet.getString("strada"));
                client.setNumar(resultSet.getString("numar"));
                client.setOras(resultSet.getString("oras"));
                client.setPremium(resultSet.getBoolean("isPremium"));
                return client;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void updateClient(Client client) {
        String updateSql = "UPDATE clients SET nume = ?, email = ?, username = ?, parola = ?, strada = ?, numar = ?, oras = ?, isPremium = ? WHERE id = ?";
        AuditService.getInstance().logAction("update Client");

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(updateSql);
            pstmt.setString(1, client.getNume());
            pstmt.setString(2, client.getEmail());
            pstmt.setString(3, client.getUsername());
            pstmt.setString(4, client.getParola());
            pstmt.setString(5, client.getStrada());
            pstmt.setString(6, client.getNumar());
            pstmt.setString(7, client.getOras());
            pstmt.setBoolean(8, client.getIsPremium());
            pstmt.setInt(9, client.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteClient(int id) {
        String deleteSql = "DELETE FROM clients WHERE id = ?";
        AuditService.getInstance().logAction("delete Client");

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(deleteSql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean emailExists(String email) {
        String query = "SELECT * FROM clients WHERE email = ?";
        AuditService.getInstance().logAction("read Client");

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
    public Client getClientByUsername(String username) {
        String selectSql = "SELECT * FROM clients WHERE username = ?";
        AuditService.getInstance().logAction("read Client");

        AuditService.getInstance().logAction("read Client");
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(selectSql);
            pstmt.setString(1, username);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return new Client(
                        resultSet.getString("nume"),
                        resultSet.getString("email"),
                        resultSet.getString("username"),
                        resultSet.getString("parola"),
                        resultSet.getInt("id"),
                        resultSet.getString("strada"),
                        resultSet.getString("numar"),
                        resultSet.getString("oras"),
                        resultSet.getBoolean("isPremium")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
