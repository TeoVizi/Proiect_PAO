package service;


import model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private static List<Client> clients = new ArrayList<>();

    public static void adaugaClient(Client client) {
        clients.add(client);
    }

    public static void devinePremium(Client client) {
        if (client != null) {
            client.setPremium(true);
            System.out.println("Felicitări! Clientul " + client.getNume() + " a devenit premium.");
        } else {
            System.out.println("Clientul nu există.");
        }
    }

    public static Client gasesteClientDupaUsername(String username) {
        for (Client client : clients) {
            if (client.getUsername().equals(username)) {
                return client;
            }
        }
        return null;
    }

    public void initializeData() {
    }
}
