package service;

import model.*;
import repository.ManagerRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagerService implements CRUDService<Manager> {
    private ManagerRepository managerRepository;
    private static ManagerService instance;

    ManagerService() {
        this.managerRepository = new ManagerRepository();
    }

    public static ManagerService getInstance() {
        if (instance == null) {
            instance = new ManagerService();
        }
        return instance;
    }

    @Override
    public int create(Manager manager) {
        return managerRepository.addManager(manager);
    }

    @Override
    public Manager read(int id) {
        return managerRepository.getManagerById(id);
    }

    @Override
    public void update(Manager manager) {
        managerRepository.updateManager(manager);
    }

    @Override
    public void delete(int id) {
        managerRepository.deleteManager(id);
    }

    public void createTable() {
        managerRepository.createTable();
    }

    public Manager getManagerByUsernameAndPassword(String username, String password) {
        return managerRepository.getManagerByUsernameAndPassword(username, password);
    }

    public boolean usernameExists(String username) {
        return managerRepository.usernameExists(username);
    }

    public boolean emailExists(String email) {
        return managerRepository.emailExists(email);
    }

    public void assignRestaurantToManager(int managerId, int restaurantId) {
        ManagerRestaurantService.getInstance().addManagerRestaurant(managerId, restaurantId);
    }

    public List<Restaurant> getRestaurantsByManager(int managerId) {
        return managerRepository.getRestaurantsByManager(managerId);
    }

    private void deleteAccount(Manager manager, Scanner scanner) {
        ManagerService managerService = ManagerService.getInstance();

        System.out.print("Sunteti sigur ca doriti sa va stergeti contul? (da/nu): ");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("da")) {
            managerService.delete(managerRepository.getIdByUsername(manager.getUsername()));
            System.out.println("Contul a fost sters cu succes.");
        } else {
            System.out.println("Stergerea contului a fost anulata.");
        }
    }

    public void viewAccountInformation(Manager manager) {
        System.out.println("Informatii de Cont:");
        System.out.println("Name: " + manager.getNume());
        System.out.println("Username: " + manager.getUsername());
    }

    public void addRestaurantDB(String name, String address, double deliveryCost, int managerId) {
        Restaurant restaurant = new Restaurant(name, address, deliveryCost);
        int restaurantId = RestaurantService.getInstance().create(restaurant);
        assignRestaurantToManager(managerId, restaurantId);
    }

    public void addMenuToRestaurantDB(int restaurantId, List<ItemMeniu> items) {
        Meniu meniu = new Meniu();
        meniu.setRestaurantId(restaurantId);
        int meniuId = MeniuService.getInstance().create(meniu);

        for (ItemMeniu item : items) {
            item.setMeniuId(meniuId);
            ItemMeniuService.getInstance().create(item);
        }
    }

    public void removeRestaurantDB(int restaurantId) {
        ManagerRestaurantService.getInstance().removeManagerRestaurantByRestaurantId(restaurantId);
        RestaurantService.getInstance().delete(restaurantId);
    }

    public void viewManagerRestaurants(int managerId) {
        List<Restaurant> restaurants = ManagerRestaurantService.getInstance().getRestaurantsByManagerId(managerId);

        if (restaurants.isEmpty()) {
            System.out.println("Nu exista restaurante asociate acestui manager.");
        } else {
            System.out.println("Restaurante asociate managerului:");
            for (Restaurant restaurant : restaurants) {
                System.out.println("ID: " + restaurant.getId());
                System.out.println("Nume: " + restaurant.getNume());
                System.out.println("Adresa: " + restaurant.getAdresa());
                System.out.println("Cost livrare: " + restaurant.getCostLivrare());
                System.out.println("-----------");
            }
        }
    }

    private static void addRestaurant(Scanner scanner, Manager manager) {
        ManagerService managerService = ManagerService.getInstance();

        System.out.print("Nume restaurant: ");
        String nume = scanner.nextLine();
        System.out.print("Adresa: ");
        String adresa = scanner.nextLine();
        System.out.print("Cost livrare: ");
        double costLivrare = Double.parseDouble(scanner.nextLine());

        managerService.addRestaurantDB(nume, adresa, costLivrare, manager.getId());

        System.out.println("Restaurant adaugat cu succes!");
    }

    private static void addMenuToRestaurant(Scanner scanner) {
        ManagerService managerService = ManagerService.getInstance();

        System.out.print("Introduceți ID-ul restaurantului: ");
        int restaurantId = Integer.parseInt(scanner.nextLine());

        System.out.println("Introduceti produsele pentru meniu (tastați 'stop' pentru a termina):");

        List<ItemMeniu> items = new ArrayList<>();
        while (true) {
            System.out.print("Nume produs: ");
            String nume = scanner.nextLine();
            if ("stop".equalsIgnoreCase(nume)) {
                break;
            }
            System.out.print("Descriere: ");
            String descriere = scanner.nextLine();
            System.out.print("Pret: ");
            double pret = Double.parseDouble(scanner.nextLine());

            ItemMeniu item = new ItemMeniu(nume, descriere, pret, restaurantId);
            items.add(item);
        }

        managerService.addMenuToRestaurantDB(restaurantId, items);
        System.out.println("Meniu adaugat cu succes la restaurant!");
    }

    private static void removeRestaurant(Scanner scanner) {
        ManagerService managerService = ManagerService.getInstance();

        System.out.print("Introduceți ID-ul restaurantului: ");
        int restaurantId = Integer.parseInt(scanner.nextLine());

        managerService.removeRestaurantDB(restaurantId);
        System.out.println("Restaurant șters cu succes!");
    }


    private static void changePassword(Manager manager, Scanner scanner) {
        ManagerService managerService = ManagerService.getInstance();

        System.out.print("Introduceti noua parola: ");
        String newPassword = scanner.nextLine();

        manager.setParola(newPassword);
        managerService.update(manager);

        System.out.println("Parola modificata cu succes!");
    }

    public static void handleManagerActions(Manager manager, Scanner scanner) {
        ManagerService managerService = ManagerService.getInstance();

        while (true) {
            System.out.println("1. Vizualizare informatii cont");
            System.out.println("2. Vizualizare restaurantele tale");
            System.out.println("3. Adaugare restaurant");
            System.out.println("4. Adaugare meniu la restaurant");
            System.out.println("5. Stergere restaurant");
            System.out.println("6. Modificare parola");
            System.out.println("7. Stergere cont");
            System.out.println("8. Deconectare");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    managerService.viewAccountInformation(manager);
                    break;
                case 2:
                    managerService.viewManagerRestaurants(manager.getId());
                    break;
                case 3:
                    addRestaurant(scanner, manager);
                    break;
                case 4:
                    addMenuToRestaurant(scanner);
                    break;
                case 5:
                    removeRestaurant(scanner);
                    break;
                case 6:
                    changePassword(manager, scanner);
                    break;
                case 7:
                    managerService.deleteAccount(manager, scanner);
                    return;
                case 8:
                    return;
                default:
                    System.out.println("Optiune invalida.");
            }
        }
    }
}
