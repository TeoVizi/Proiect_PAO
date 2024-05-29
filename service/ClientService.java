package service;

import model.Client;
import repository.ClientRepository;

public class ClientService extends BaseService<Client> {
    private ClientRepository clientRepository;

    private ClientService() {
        this.clientRepository = new ClientRepository();
    }

    @Override
    public void create(Client client) {
        clientRepository.addClient(client);
    }

    @Override
    public Client read(int id) {
        return clientRepository.getClientById(id);
    }

    @Override
    public void update(Client client) {
        clientRepository.updateClient(client);
    }

    @Override
    public void delete(int id) {
        clientRepository.deleteClient(id);
    }

    public void createTable() {
        clientRepository.createTable();
    }

    public Client getClientByUsernameAndPassword(String username, String password) {
        return clientRepository.getClientByUsernameAndPassword(username, password);
    }

    public boolean emailExists(String email) {
        return clientRepository.emailExists(email);
    }

    public void initialize() {
        createTable();
        for (int i = 1; i <= 5; i++) {
            Client client = new Client("Client" + i, "client" + i + "@example.com", "username" + i, "password" + i, "Street" + i, "Number" + i, "City" + i, i % 2 == 0);
            create(client);
        }
    }
}
