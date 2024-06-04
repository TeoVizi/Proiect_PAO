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

import java.util.Scanner;

public class LoginRegisterService {

    private static LoginRegisterService loginRegisterService;
    private static ClientService clientService;
    private RestaurantService restaurantService;
    private MeniuService meniuService;
    private ItemMeniuService itemMeniuService;
    private ComandaService comandaService;
    private static SoferService soferService;
    private static ManagerService managerService;
    private static ManagerRestaurantService managerRestaurantService;

    private LoginRegisterService() {
        this.clientService = ClientService.getInstance();
        this.restaurantService = RestaurantService.getInstance();
        this.meniuService = MeniuService.getInstance();
        this.itemMeniuService = ItemMeniuService.getInstance();
        this.comandaService = ComandaService.getInstance();
        this.soferService = SoferService.getInstance();
        this.managerService = ManagerService.getInstance();
        this.managerRestaurantService = ManagerRestaurantService.getInstance();
    }

    public static LoginRegisterService getInstance(){
        if(loginRegisterService == null){
            loginRegisterService = new LoginRegisterService();
        }
       return loginRegisterService;
    }

    public void initialize() {
        clientService.createTable();
        managerService.createTable();
        restaurantService.createTable();
        meniuService.createTable();
        itemMeniuService.createTable();
        comandaService.createTable();
        soferService.createTable();
        managerRestaurantService.createTable();
    }

    public void handleRegistration(Scanner scanner) {
        System.out.println("1. Inregistrare Client");
        System.out.println("2. Inregistrare Manager");
        System.out.println("3. Inregistrare Sofer");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                registerClient(scanner);
                break;
            case 2:
                registerManager(scanner);
                break;
            case 3:
                registerSofer(scanner);
                break;
            default:
                System.out.println("Opțiune invalida.");
        }
    }

    private static void registerClient(Scanner scanner) {
        ClientService clientService = ClientService.getInstance();

        System.out.print("Nume: ");
        String nume = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Parola: ");
        String password = scanner.nextLine();
        System.out.print("Strada: ");
        String strada = scanner.nextLine();
        System.out.print("Numar: ");
        String numar = scanner.nextLine();
        System.out.print("Oras: ");
        String oras = scanner.nextLine();
        System.out.print("Este client premium? (true/false): ");
        boolean isPremium = Boolean.parseBoolean(scanner.nextLine());

        if (managerService.emailExists(email)) {
            System.out.println("Emailul exista deja. Va rugam sa folositi un alt email.");
            return;
        }

        if (managerService.usernameExists(username)) {
            System.out.println("Username-ul exista deja. Va rugam sa folositi un alt username.");
            return;
        }

        Client client = new Client(nume, email, username, password, strada,numar,oras, isPremium);
        clientService.create(client);
        System.out.println("Client inregistrat cu succes!");
    }

    private static void registerManager(Scanner scanner) {
        ManagerService managerService = ManagerService.getInstance();

        System.out.print("Nume: ");
        String nume = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Parola: ");
        String password = scanner.nextLine();

        if (managerService.emailExists(email)) {
            System.out.println("Emailul exista deja. Va rugam sa folositi un alt email.");
            return;
        }

        if (managerService.usernameExists(username)) {
            System.out.println("Username-ul exista deja. Va rugam sa folositi un alt username.");
            return;
        }

        Manager manager = new Manager(nume, email, username, password);
        managerService.create(manager);
        System.out.println("Manager inregistrat cu succes!");
    }

    private static void registerSofer(Scanner scanner) {
        SoferService soferService = SoferService.getInstance();

        System.out.print("Nume: ");
        String nume = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Parola: ");
        String password = scanner.nextLine();
        System.out.print("Locatie: ");
        String locatie = scanner.nextLine();

        if (soferService.emailExists(email)) {
            System.out.println("Emailul exista deja. Va rugam sa folositi un alt email.");
            return;
        }

        if (soferService.usernameExists(username)) {
            System.out.println("Username-ul exista deja. Vă rugam sa folositi un alt username.");
            return;
        }

        Sofer sofer = new Sofer(nume, email, username, password, locatie);
        soferService.create(sofer);
        System.out.println("Sofer inregistrat cu succes!");
    }


    public static void handleLogin(Scanner scanner) {
        System.out.println("1. Autentificare Client");
        System.out.println("2. Autentificare Manager");
        System.out.println("3. Autentificare Sofer");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                loginClient(scanner);
                break;
            case 2:
                loginManager(scanner);
                break;
            case 3:
                loginSofer(scanner);
                break;
            default:
                System.out.println("Opțiune invalida.");
        }
    }

    private static void loginClient(Scanner scanner) {
        ClientService clientService = ClientService.getInstance();

        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Parola: ");
        String password = scanner.nextLine();

        Client client = clientService.getClientByUsernameAndPassword(username, password);

        if (client != null) {
            System.out.println("Autentificare reusita!");
            clientService.handleClientActions(client, scanner);
        } else {
            System.out.println("Autentificare esuata!");
        }
    }

    private static void loginManager(Scanner scanner) {
        ManagerService managerService = ManagerService.getInstance();

        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Parola: ");
        String password = scanner.nextLine();

        Manager manager = managerService.getManagerByUsernameAndPassword(username, password);

        if (manager != null) {
            System.out.println("Autentificare reusita!");
            managerService.handleManagerActions(manager, scanner);
        } else {
            System.out.println("Autentificare esuata!");
        }
    }

    private static void loginSofer(Scanner scanner) {
        SoferService soferService = SoferService.getInstance();

        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Parola: ");
        String password = scanner.nextLine();

        Sofer sofer = soferService.getSoferByUsernameAndPassword(username, password);

        if (sofer != null) {
            System.out.println("Autentificare reusita!");
            soferService.handleSoferActions(sofer, scanner);
        } else {
            System.out.println("Autentificare esuata!");
        }
    }


}
