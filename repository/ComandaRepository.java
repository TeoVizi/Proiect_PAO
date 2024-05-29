package repository;

import config.DatabaseConfiguration;
import model.Comanda;
import model.ItemComanda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ComandaRepository {

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS comenzi" +
                "(id INT PRIMARY KEY AUTO_INCREMENT, " +
                "client_id INT, " +
                "restaurant_id INT, " +
                "status VARCHAR(50), " +
                "total_plata DOUBLE, " +
                "FOREIGN KEY (client_id) REFERENCES clients(id), " +
                "FOREIGN KEY (restaurant_id) REFERENCES restaurants(id))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addComanda(Comanda comanda) {
        String insertComandaSql = "INSERT INTO comenzi(client_id, restaurant_id, status, total_plata) VALUES(?, ?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement pstmt = connection.prepareStatement(insertComandaSql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, comanda.getClient().getId());
            pstmt.setInt(2, comanda.getRestaurant().getId());
            pstmt.setString(3, comanda.getStatus());
            pstmt.setDouble(4, comanda.getTotalPlata());

            pstmt.executeUpdate();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int comandaId = generatedKeys.getInt(1);
                ItemComandaRepository itemComandaRepository = new ItemComandaRepository();
                for (ItemComanda item : comanda.getListaItemiComandati()) {
                    itemComandaRepository.addItemComanda(item, comandaId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Comanda getComandaById(int id) {
        String selectSql = "SELECT * FROM comenzi WHERE id = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(selectSql);
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                ClientRepository clientRepository = new ClientRepository();
                RestaurantRepository restaurantRepository = new RestaurantRepository();
                ItemComandaRepository itemComandaRepository = new ItemComandaRepository();

                Comanda comanda = new Comanda(
                        clientRepository.getClientById(resultSet.getInt("client_id")),
                        restaurantRepository.getRestaurantById(resultSet.getInt("restaurant_id"))
                );
                comanda.setId(resultSet.getInt("id"));
                comanda.setStatus(resultSet.getString("status"));
                comanda.setTotalPlata(resultSet.getDouble("total_plata"));
                comanda.setListaItemiComandati(itemComandaRepository.getItemsForComanda(id));

                return comanda;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateComanda(Comanda comanda) {
        String updateSql = "UPDATE comenzi SET client_id = ?, restaurant_id = ?, status = ?, total_plata = ? WHERE id = ?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(updateSql);
            pstmt.setInt(1, comanda.getClient().getId());
            pstmt.setInt(2, comanda.getRestaurant().getId());
            pstmt.setString(3, comanda.getStatus());
            pstmt.setDouble(4, comanda.getTotalPlata());
            pstmt.setInt(5, comanda.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteComanda(int id) {
        String deleteSql = "DELETE FROM comenzi WHERE id = ?";

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
