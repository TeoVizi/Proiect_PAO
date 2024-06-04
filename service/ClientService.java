package service;

import model.*;
import repository.ClientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientService implements CRUDService<Client> {
    private ClientRepository clientRepository;
    private List<ItemComanda> currentCart;
    private static ClientService instance;

    private ClientService() {
        this.clientRepository = new ClientRepository();
        this.currentCart = new ArrayList<>();
    }

    public static ClientService getInstance() {
        if (instance == null) {
            instance = new ClientService();
        }
        return instance;
    }

    @Override
    public int create(Client client) {
        int id = clientRepository.addClient(client);
        return id;
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
        System.out.println("Informatii Cont:");
        System.out.println("Nume: " + client.getNume());
        System.out.println("Username: " + client.getUsername());
        System.out.println("Adresa: " + client.getAdresaCompleta());
        System.out.println("Premium: " + client.getIsPremium());
    }

    private void deleteAccount(Client client, Scanner scanner) {
        ClientService clientService = ClientService.getInstance();

        System.out.print("Sunteti sigur ca doriti sa va stergeti contul? (da/nu): ");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("da")) {
            clientService.delete(clientRepository.getIdByUsername(client.getUsername()));
            System.out.println("Contul a fost sters cu succes.");
        } else {
            System.out.println("Stergerea contului a fost anulata.");
        }
    }

    public void addItemToCart(ItemComanda item) {
        currentCart.add(item);
    }

    private static void handleAddItemToCart(Scanner scanner) {
        ClientService clientService = ClientService.getInstance();

        System.out.print("Introduceți ID-ul produsului: ");
        int itemId = Integer.parseInt(scanner.nextLine());
        System.out.print("Introduceti cantitatea: ");
        int cantitate = Integer.parseInt(scanner.nextLine());

        ItemMeniu item = MeniuService.getInstance().getItemMeniuById(itemId);
        ItemComanda itemComanda = new ItemComanda(item, cantitate);
        clientService.addItemToCart(itemComanda);

        System.out.println("Produs adaugat în cos.");
    }

    public List<ItemComanda> getCurrentCart() {
        return currentCart;
    }

    public void clearCart() {
        currentCart.clear();
    }

    public static void changePassword(Client client, Scanner scanner) {
        ClientService clientService = ClientService.getInstance();

        System.out.print("Introduceti noua parola: ");
        String newPassword = scanner.nextLine();

        client.setParola(newPassword);
        clientService.update(client);

        System.out.println("Parola modificata cu succes!");
    }

    public static void viewRestaurantMenu(Scanner scanner) {
        System.out.print("Introduceti ID-ul restaurantului: ");
        int restaurantId = Integer.parseInt(scanner.nextLine());

        Meniu meniu = MeniuService.getInstance().getMeniuByRestaurantId(restaurantId);

        if (meniu != null && !meniu.getListaItemiMeniu().isEmpty()) {
            System.out.println("Meniu restaurant:");
            for (ItemMeniu item : meniu.getListaItemiMeniu()) {
                System.out.println(item.getId()+": "+item.getNume() + ": " + item.getDescriere() + " - " + item.getPret());
            }
        } else {
            System.out.println("Nu exista meniu pentru acest restaurant.");
        }
    }

    public static void viewRestaurants() {
        RestaurantService restaurantService = RestaurantService.getInstance();
        List<Restaurant> restaurants = restaurantService.getAll();

        if (restaurants.isEmpty()) {
            System.out.println("Nu exista restaurante disponibile.");
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

        System.out.println("Produse în cos:");
        for (ItemComanda item : cart) {
            System.out.println(item.getItemMeniu().getNume() + ": " + item.getCantitate());
        }
    }

    private static void placeOrder(Client client, Scanner scanner) {
        ClientService clientService = ClientService.getInstance();

        System.out.print("Introduceți ID-ul restaurantului: ");
        int restaurantId = Integer.parseInt(scanner.nextLine());

        Restaurant restaurant = RestaurantService.getInstance().read(restaurantId);

        if (restaurant != null) {
            double totalPlata = clientService.getCurrentCart().stream()
                    .mapToDouble(item -> item.getItemMeniu().getPret() * item.getCantitate())
                    .sum();

            Comanda comanda = new Comanda(client, restaurant, "Pending", totalPlata);
            for (ItemComanda item : clientService.getCurrentCart()) {
                comanda.adaugaItemComanda(item);
            }

            ComandaService comandaService = ComandaService.getInstance();
            comandaService.create(comanda);

            clientService.clearCart();
            System.out.println("Comanda plasata cu succes!" + " Total plata: " + totalPlata);
        } else {
            System.out.println("Restaurant invalid.");
        }
    }

    public void handleClientActions(Client client, Scanner scanner) {
        ClientService clientService = ClientService.getInstance();

        while (true) {
            System.out.println("1. Vizualizare informatii cont");
            System.out.println("2. Vizualizare restaurante");
            System.out.println("3. Vizualizare meniu restaurant");
            System.out.println("4. Adaugare produs in cos");
            System.out.println("5. Vizualizare cos curent");
            System.out.println("6. Plasare comanda");
            System.out.println("7. Modificare parola");
            System.out.println("8. Stergere cont");
            System.out.println("9. Deconectare");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    clientService. viewAccountInformation(client);
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
                    return;
                case 9:
                    return;
                default:
                    System.out.println("Optiune invalida.");
            }
        }
    }
}
