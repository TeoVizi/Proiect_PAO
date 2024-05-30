package service;

import model.*;
import repository.ClientRepository;

import java.util.List;
import java.util.Scanner;

public class ClientService implements CRUDService<Client> {
    private ClientRepository clientRepository;
    private List<ItemComanda> currentCart;
    private static ClientService instance;


    private ClientService() {
        this.clientRepository = new ClientRepository();
    }

    public static ClientService getInstance() {
        if (instance == null) {
            instance = new ClientService();
        }
        return instance;
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

    public boolean usernameExists(String username) {
        return clientRepository.usernameExists(username);
    }
    public void viewAccountInformation(Client client) {
        System.out.println("Account Information:");
        System.out.println("Name: " + client.getNume());
        System.out.println("Email: " + client.getEmail());
        System.out.println("Username: " + client.getUsername());
        System.out.println("Address: " + client.getAdresaCompleta());
        System.out.println("Premium: " + client.getIsPremium());
    }

    private void deleteAccount(Client client, Scanner scanner) {
        ClientService clientService = ClientService.getInstance();

        System.out.print("Sunteți sigur că doriți să vă ștergeți contul? (da/nu): ");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("da")) {
            clientService.delete(clientRepository.getIdByUsername(client.getUsername()));
            System.out.println("Contul a fost șters cu succes.");
        } else {
            System.out.println("Ștergerea contului a fost anulată.");
        }
    }

    public void addItemToCart(ItemComanda item) {
        currentCart.add(item);
    }

    private static void handleAddItemToCart(Scanner scanner) {
        ClientService clientService = ClientService.getInstance();

        System.out.print("Introduceți ID-ul produsului: ");
        int itemId = Integer.parseInt(scanner.nextLine());
        System.out.print("Introduceți cantitatea: ");
        int cantitate = Integer.parseInt(scanner.nextLine());

        ItemMeniu item = MeniuService.getInstance().getItemMeniuById(itemId);
        ItemComanda itemComanda = new ItemComanda(item, cantitate);
        clientService.addItemToCart(itemComanda);

        System.out.println("Produs adăugat în coș.");
    }

    public List<ItemComanda> getCurrentCart() {
        return currentCart;
    }

    public void clearCart() {
        currentCart.clear();
    }

    public static void changePassword(Client client,Scanner scanner) {
        ClientService clientService = ClientService.getInstance();

        System.out.print("Introduceți noua parolă: ");
        String newPassword = scanner.nextLine();

        client.setParola(newPassword);
        clientService.update(client);

        System.out.println("Parolă modificată cu succes!");
    }

    public static void viewRestaurantMenu(Scanner scanner) {
        System.out.print("Introduceți ID-ul restaurantului: ");
        int restaurantId = Integer.parseInt(scanner.nextLine());

        Meniu meniu = MeniuService.getInstance().getMeniuByRestaurantId(restaurantId);

        if (meniu == null) {
            System.out.println("Nu există meniu pentru restaurantul cu ID-ul specificat.");
            return;
        }

        System.out.println("Meniu restaurant:");
        for (ItemMeniu item : meniu.getListaItemiMeniu()) {
            System.out.println(item);
        }
    }


    public static void viewRestaurants() {
        RestaurantService restaurantService = RestaurantService.getInstance();
        List<Restaurant> restaurants = restaurantService.getAll();

        if (restaurants.isEmpty()) {
            System.out.println("Nu există restaurante disponibile.");
        } else {
            System.out.println("Restaurante disponibile:");
            for (Restaurant restaurant : restaurants) {
                System.out.println("ID: " + restaurant.getId());
                System.out.println("Nume: " + restaurant.getNume());
                System.out.println("Adresa: " + restaurant.getAdresa());
                System.out.println("Cost livrare: " + restaurant.getCostLivrare());
                System.out.println("-----------");
            }
        }
    }


    private static void viewCurrentCart() {
        ClientService clientService = ClientService.getInstance();

        List<ItemComanda> cart = clientService.getCurrentCart();

        System.out.println("Produse în coș:");
        for (ItemComanda item : cart) {
            System.out.println(item);
        }
    }

    private static void placeOrder(Client client, Scanner scanner) {
        ClientService clientService = ClientService.getInstance();

        System.out.print("Introduceți ID-ul restaurantului: ");
        int restaurantId = Integer.parseInt(scanner.nextLine());

        Restaurant restaurant = RestaurantService.getInstance().read(restaurantId);

        if (restaurant != null) {
            Comanda comanda = new Comanda(client, restaurant);
            List<ItemComanda> cart = clientService.getCurrentCart();
            for (ItemComanda item : cart) {
                comanda.adaugaItemComanda(item);
            }

            ComandaService comandaService = ComandaService.getInstance();
            comandaService.create(comanda);

            clientService.clearCart();
            System.out.println("Comandă plasată cu succes!");
        } else {
            System.out.println("Restaurant invalid.");
        }
    }

     public void handleClientActions(Client client, Scanner scanner) {
        ClientService clientService = ClientService.getInstance();

        while (true) {
            System.out.println("1. Vizualizare informații cont");
            System.out.println("2. Vizualizare restaurante");
            System.out.println("3. Vizualizare meniu restaurant");
            System.out.println("4. Adăugare produs în coș");
            System.out.println("5. Vizualizare coș curent");
            System.out.println("6. Plasare comandă");
            System.out.println("7. Modificare parolă");
            System.out.println("8. Ștergere cont");
            System.out.println("9. Deconectare");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    clientService.viewAccountInformation(client);
                    break;
                case 2:
                    viewRestaurants();
                    break;
                case 3:
                    viewRestaurantMenu(scanner);
                    break;
                case 4:
                    handleAddItemToCart(scanner);
                    break;
                case 5:
                    viewCurrentCart();
                    break;
                case 6:
                    placeOrder(client, scanner);
                    break;
                case 7:
                    changePassword(client, scanner);
                    break;
                case 8:
                    deleteAccount(client, scanner);
                case 9:
                    return;
                default:
                    System.out.println("Opțiune invalidă.");
            }
        }
    }
}
