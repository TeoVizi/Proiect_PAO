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
    private ItemComandaService itemComandaService;
    private ComandaService comandaService;
    private static SoferService soferService;
    private static ManagerService managerService;

    private LoginRegisterService() {
        this.clientService = ClientService.getInstance();
        this.restaurantService = RestaurantService.getInstance();
        this.meniuService = MeniuService.getInstance();
        this.itemMeniuService = ItemMeniuService.getInstance();
        this.itemComandaService = ItemComandaService.getInstance();
        this.comandaService = ComandaService.getInstance();
        this.soferService = SoferService.getInstance();
        this.managerService = ManagerService.getInstance();
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
        itemComandaService.createTable();
        soferService.createTable();
    }

    public void handleRegistration(Scanner scanner) {
        System.out.println("1. Înregistrare Client");
        System.out.println("2. Înregistrare Manager");
        System.out.println("3. Înregistrare Șofer");

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
                System.out.println("Opțiune invalidă.");
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
            System.out.println("Emailul există deja. Vă rugăm să folosiți un alt email.");
            return;
        }

        if (managerService.usernameExists(username)) {
            System.out.println("Username-ul există deja. Vă rugăm să folosiți un alt username.");
            return;
        }

        Client client = new Client(nume, email, username, password, strada,numar,oras, isPremium);
        clientService.create(client);
        System.out.println("Client înregistrat cu succes!");
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
            System.out.println("Emailul există deja. Vă rugăm să folosiți un alt email.");
            return;
        }

        if (managerService.usernameExists(username)) {
            System.out.println("Username-ul există deja. Vă rugăm să folosiți un alt username.");
            return;
        }

        Manager manager = new Manager(nume, email, username, password);
        managerService.create(manager);
        System.out.println("Manager înregistrat cu succes!");
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
            System.out.println("Emailul există deja. Vă rugăm să folosiți un alt email.");
            return;
        }

        if (soferService.usernameExists(username)) {
            System.out.println("Username-ul există deja. Vă rugăm să folosiți un alt username.");
            return;
        }

        Sofer sofer = new Sofer(nume, email, username, password, locatie);
        soferService.create(sofer);
        System.out.println("Șofer înregistrat cu succes!");
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
            System.out.println("Autentificare reușită!");
            clientService.handleClientActions(client, scanner);
        } else {
            System.out.println("Autentificare eșuată!");
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
            System.out.println("Autentificare reușită!");
            managerService.handleManagerActions(manager, scanner);
        } else {
            System.out.println("Autentificare eșuată!");
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
            System.out.println("Autentificare reușită!");
            soferService.handleSoferActions(sofer, scanner);
        } else {
            System.out.println("Autentificare eșuată!");
        }
    }


}
