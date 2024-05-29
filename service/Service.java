//package service;
//
//import model.*;
//
//public class Service {
//    private static ClientService userService = new ClientService();
//    private static RestaurantService restaurantService = new RestaurantService();
//    private static ComandaService orderService = new ComandaService();
//    private static ManagerService managerService = new ManagerService();
//
//
//    public static void afiseazaTotalComanda(Comanda comanda) {
//        orderService.afiseazaTotalComanda(comanda);
//    }
//
//    public static void afiseazaDateManager(Manager manager) {
//        managerService.afiseazaDateManager(manager);
//    }
//
//    public static int[] logareUtilizator(String username, String parola) {
//        return userService.logareUtilizator(username, parola);
//    }
//
//    public static void adaugaClient(Client client) {
//        userService.adaugaClient(client);
//    }
//
//    public static void adaugaSofer(Sofer sofer) {
//        userService.adaugaSofer(sofer);
//    }
//
//    public static void adaugaManager(Manager manager) {
//        managerService.adaugaManager(manager);
//    }
//
//    public static Manager gasesteManager(String username) {
//        return managerService.gasesteManager(username);
//    }
//
//    public static void adaugaRestaurant(Manager manager, Restaurant restaurant) {
//        restaurantService.adaugaRestaurant(manager, restaurant);
//    }
//
//    public static boolean unicitateRestaurant(String numeRestaurant) {
//        return restaurantService.unicitateRestaurant(numeRestaurant);
//    }
//
//    public static void afiseazaRestauranteManager(Manager manager) {
//        restaurantService.afiseazaRestauranteManager(manager);
//    }
//
//    public static boolean restaurantExistent(Manager manager, String numeRestaurant) {
//        return restaurantService.restaurantExistent(manager, numeRestaurant);
//    }
//
//    public static boolean restaurantExistentClient(String numeRestaurant) {
//        return restaurantService.restaurantExistentClient(numeRestaurant);
//    }
//
//    public static void adaugaItemMeniuRestaurant(Restaurant restaurant, ItemMeniu item) {
//        restaurantService.adaugaItemMeniuRestaurant(restaurant, item);
//    }
//
//    public static Restaurant gasesteRestaurantManager(Manager manager, String numeRestaurant) {
//        return restaurantService.gasesteRestaurantManager(manager, numeRestaurant);
//    }
//
//    public static Client gasesteClientDupaUsername(String username) {
//        return userService.gasesteClientDupaUsername(username);
//    }
//
//    public static Restaurant gasesteRestaurant(String numeRestaurant) {
//        return restaurantService.gasesteRestaurant(numeRestaurant);
//    }
//
//    public static ItemMeniu gasesteItemMeniu(String numeItem) {
//        return restaurantService.gasesteItemMeniu(numeItem);
//    }
//
//    public static void afiseazaRestaurante() {
//        restaurantService.afiseazaRestaurante();
//    }
//
//    public static void afiseazaMeniuRestaurant(Restaurant restaurant) {
//        restaurantService.afiseazaMeniuRestaurant(restaurant);
//    }
//
//    public static boolean existaItemMeniuRestaurant(Restaurant restaurant, String numeItem) {
//        return restaurantService.existaItemMeniuRestaurant(restaurant, numeItem);
//    }
//
//    public static Sofer gasesteSoferDupaUsername(String username) {
//        return userService.gasesteSoferDupaUsername(username);
//    }
//}
