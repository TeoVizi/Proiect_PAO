package service;

import model.Client;
import model.Comanda;
import model.ItemComanda;
import model.ItemMeniu;
import model.Manager;
import model.Meniu;
import model.Restaurant;
import model.Sofer;
import model.Utilizator;

public class LoginRegisterService {
    private ClientService clientService;
    private RestaurantService restaurantService;
    private MeniuService meniuService;
    private ItemMeniuService itemMeniuService;
    private ItemComandaService itemComandaService;
    private ComandaService comandaService;
    private SoferService soferService;
    private ManagerService managerService;

    public LoginRegisterService() {
        this.clientService = ClientService.getInstance(ClientService.class);
        this.restaurantService = RestaurantService.getInstance(RestaurantService.class);
        this.meniuService = MeniuService.getInstance(MeniuService.class);
        this.itemMeniuService = ItemMeniuService.getInstance(ItemMeniuService.class);
        this.itemComandaService = ItemComandaService.getInstance(ItemComandaService.class);
        this.comandaService = ComandaService.getInstance(ComandaService.class);
        this.soferService = SoferService.getInstance(SoferService.class);
        this.managerService = ManagerService.getInstance(ManagerService.class);
    }

    public void initialize() {
        clientService.createTable();
        restaurantService.createTable();
        meniuService.createTable();
        itemMeniuService.createTable();
        itemComandaService.createTable();
        comandaService.createTable();
        soferService.createTable();
        managerService.createTable();

        clientService.initialize();
        restaurantService.initialize();
        meniuService.initialize();
        itemMeniuService.initialize();
        itemComandaService.initialize();
        comandaService.initialize();
        soferService.initialize();
        managerService.initialize();
    }

    public Utilizator login(String username, String password) {
        Client client = clientService.getClientByUsernameAndPassword(username, password);
        if (client != null) {
            return client;
        }

        Sofer sofer = soferService.getSoferByUsernameAndPassword(username, password);
        if (sofer != null) {
            return sofer;
        }

        Manager manager = managerService.getManagerByUsernameAndPassword(username, password);
        if (manager != null) {
            return manager;
        }

        return null; // Return null if no matching user is found
    }

    public boolean emailExists(String email) {
        return clientService.emailExists(email) || soferService.emailExists(email) || managerService.emailExists(email);
    }

    public void registerClient(String nume, String email, String username, String parola, String strada, String numar, String oras, boolean isPremium) {
        if (emailExists(email)) {
            System.out.println("Email already exists. Please use a different email.");
            return;
        }
        Client client = new Client(nume, email, username, parola, strada, numar, oras, isPremium);
        clientService.create(client);
    }

    public void registerSofer(String nume, String email, String username, String parola, String locatie, boolean disponibilitate) {
        if (emailExists(email)) {
            System.out.println("Email already exists. Please use a different email.");
            return;
        }
        Sofer sofer = new Sofer(nume, email, username, parola, locatie);
        sofer.setDisponibilitate(disponibilitate);
        soferService.create(sofer);
    }

    public void registerManager(String nume, String email, String username, String parola) {
        if (emailExists(email)) {
            System.out.println("Email already exists. Please use a different email.");
            return;
        }
        Manager manager = new Manager(nume, email, username, parola);
        managerService.create(manager);
    }

}
