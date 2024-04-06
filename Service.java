import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

public class Service {
    private static ArrayList<Utilizator> utilizatori = new ArrayList<>() ;
    private static List<Restaurant> restaurante = new ArrayList<>();

    private static Map<Manager, List<Restaurant>> managerRestaurantMap = new HashMap<>();
    private static Map<Restaurant, Meniu> meniuri = new HashMap<>();

    private Service()
    {

    }
    public static void initializeData() {
        utilizatori.add(new Client("John Doe", "john.doe@example.com", "john", "parola1", "Str. Principală", "10", "Orasul X", true));
        utilizatori.add(new Sofer("Alice Smith", "alice.smith@example.com", "alice", "parola2", "12345"));
        Manager manager1 = new Manager("Bob Johnson", "bob.johnson@example.com", "bob", "parola3");

        Manager manager2 = new Manager("Alice Brown", "alice.brown@example.com", "aliceB", "parola4");

        utilizatori.add(manager1);
        utilizatori.add(manager2);

        Restaurant restaurant1 = new Restaurant("Restaurant1", "Str. A", 10.0);
        Restaurant restaurant2 = new Restaurant("Restaurant2", "Str. B", 15.0);
        restaurante.add(restaurant1);
        restaurante.add(restaurant2);

        managerRestaurantMap.put(manager1, new ArrayList<>(Arrays.asList(restaurant1, restaurant2)));

        Meniu meniu1 = new Meniu();
        meniu1.adaugaItemMeniu(new ItemMeniu("Pizza", "Pizza delicioasă", 25.0));
        meniu1.adaugaItemMeniu(new ItemMeniu("Paste", "Paste cu sos de roșii", 20.0));
        meniu1.adaugaItemMeniu(new ItemMeniu("Salată", "Salată verde cu legume", 15.0));
        meniuri.put(restaurant1, meniu1);

        Meniu meniu2 = new Meniu();
        meniu2.adaugaItemMeniu(new ItemMeniu("Burger", "Burger american clasic", 30.0));
        meniu2.adaugaItemMeniu(new ItemMeniu("Friptură", "Friptură de porc cu cartofi", 40.0));
        meniu2.adaugaItemMeniu(new ItemMeniu("Desert", "Cheesecake cu fructe de pădure", 15.0));
        meniuri.put(restaurant2, meniu2);
    }

    public static boolean unicitateUsername(String username)
    {
        for (Utilizator utilizator : utilizatori) {
            if (utilizator.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }
    public static void adaugaClient(Client client) {
        utilizatori.add(client);
    }

    public static void adaugaSofer(Sofer sofer) {
        utilizatori.add(sofer);
    }

    public static void adaugaManager(Manager manager) {
        utilizatori.add(manager);
    }
    public static Manager gasesteManager(String username) {
        for (Utilizator utilizator : utilizatori) {
            if (utilizator instanceof Manager && utilizator.getUsername().equals(username)) {
                return (Manager) utilizator;
            }
        }
        return null;
    }

    public static void adaugaRestaurant(Manager manager, Restaurant restaurant) {
        if (!unicitateRestaurant(restaurant.getNume())) {
            System.out.println("Restaurantul cu numele '" + restaurant.getNume() + "' exista deja în sistem.");
            return;
        }

        List<Restaurant> restauranteManager = managerRestaurantMap.get(manager);
        if (restauranteManager == null) {
            restauranteManager = new ArrayList<>();
        }
        restauranteManager.add(restaurant);
        restaurante.add(restaurant);
        managerRestaurantMap.put(manager, restauranteManager);
        System.out.println("Restaurantul " + restaurant.getNume() + " a fost adaugat cu succes pentru managerul " + manager.getNume() + ".");
    }


    public static boolean unicitateRestaurant(String numeRestaurant) {
        for (Restaurant restaurant : restaurante) {
            if (restaurant.getNume().equalsIgnoreCase(numeRestaurant)) {
                return false;
            }
        }
        return true;
    }
    public static void afiseazaRestauranteManager(Manager manager) {
        List<Restaurant> restaurante = managerRestaurantMap.get(manager);
        if (restaurante != null && !restaurante.isEmpty()) {
            System.out.println("Restaurantele asociate managerului " + manager.getNume() + " sunt:");
            for (Restaurant restaurant : restaurante) {
                System.out.println("- " + restaurant.getNume());
            }
        } else {
            System.out.println("Nu exista restaurante asociate managerului " + manager.getNume() + ".");
        }
    }

    public static boolean restaurantExistent(Manager manager, String numeRestaurant) {
        List<Restaurant> restauranteManager = managerRestaurantMap.get(manager);

        if (restauranteManager != null) {
            for (Restaurant restaurant : restauranteManager) {
                if (restaurant.getNume().equalsIgnoreCase(numeRestaurant)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void adaugaItemMeniuRestaurant(Restaurant restaurant, ItemMeniu item) {
        if (meniuri.containsKey(restaurant)) {
            Meniu meniu = meniuri.get(restaurant);

            meniu.adaugaItemMeniu(item);

            meniuri.put(restaurant, meniu);

            System.out.println("Preparatul '" + item.getNume() + "' a fost adaugat cu succes la meniul restaurantului '" + restaurant.getNume() + "'.");
        } else {
            System.out.println("Restaurantul '" + restaurant.getNume() + "' nu exista în sistem. Nu se poate adauga preparatul.");
        }
    }

    public static Restaurant gasesteRestaurant(Manager manager, String numeRestaurant) {
        // Verificați dacă managerul are asociate restaurante
        if (managerRestaurantMap.containsKey(manager)) {
            // Obțineți lista de restaurante asociate managerului
            List<Restaurant> restauranteManager = managerRestaurantMap.get(manager);

            // Parcurgeți lista de restaurante
            for (Restaurant restaurant : restauranteManager) {
                // Verificați dacă numele restaurantului corespunde cu cel căutat
                if (restaurant.getNume().equalsIgnoreCase(numeRestaurant)) {
                    // Returnați restaurantul găsit
                    return restaurant;
                }
            }
        }

        // Returnați null dacă restaurantul nu a fost găsit sau managerul nu are restaurante asociate
        return null;
    }



    public static int[] logareUtilizator(String username, String parola) {
        for (Utilizator utilizator : utilizatori) {
            if (utilizator.getUsername().equals(username) && utilizator.getParola().equals(parola)) {
                if (utilizator instanceof Client) {
                    return new int[]{1, 1};
                } else if (utilizator instanceof Sofer) {
                    return new int[]{1, 2};
                } else if (utilizator instanceof Manager) {
                    return new int[]{1, 3};
                }
            }
        }

        return new int[]{0, 0};
    }
}
