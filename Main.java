import model.*;
import service.Service;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean page1 = true;
        boolean page2;
        Service.initializeData();

        while (page1) {
            page2 = true;
            System.out.println("Bine ati venit in aplicatia de food delivery YumYumDelivery!");
            System.out.println("Va rugam sa alegeti o actiune de mai jos:");
            System.out.println("1. Creati un cont nou");
            System.out.println("2. Logare");

            int optiune1 = scan.nextInt();

            if (optiune1 == 1) {
                System.out.println("Alegeti tipul de cont pe care il doriti sau intoarceti-va la pagina anterioara:");
                System.out.println("1. model.Client");
                System.out.println("2. model.Sofer");
                System.out.println("3. model.Manager");
                System.out.println("4. Inapoi la pagina principala");

                int optiune2 = scan.nextInt();

                if (optiune2 == 1) {
                    System.out.println("Ati ales optiunea CLIENT:");
                    System.out.println("Nume:");
                    String nume = scan.next();
                    System.out.println("Email:");
                    String email = scan.next();
                    boolean unic = false;
                    String username = "";
                    while (!unic) {
                        System.out.println("Username:");
                        username = scan.next();
                        if (Service.unicitateUsername(username)) {
                            unic = true;
                        } else {
                            System.out.println("model.Utilizator deja existent! Introdu alt username!");
                        }
                    }
                    System.out.println("Parola:");
                    String parola = scan.next();
                    System.out.println("Strada:");
                    String strada = scan.next();
                    System.out.println("Numar:");
                    String numar = scan.next();
                    System.out.println("Oras:");
                    String oras = scan.next();
                    System.out.println("Premium? (true/ false):");
                    boolean premium = scan.nextBoolean();
                    Client client = new Client(nume, email, username, parola, strada, numar, oras, premium);
                    Service.adaugaClient(client);
                }

                if (optiune2 == 2) {
                    System.out.println("Ati ales optiunea SOFER:");
                    System.out.println("Nume:");
                    String nume = scan.next();
                    System.out.println("Email:");
                    String email = scan.next();
                    boolean unic = false;
                    String username = "";
                    while (!unic) {
                        System.out.println("Username:");
                        username = scan.next();
                        if (Service.unicitateUsername(username)) {
                            unic = true;
                        } else {
                            System.out.println("model.Utilizator deja existent! Introdu alt username!");
                        }
                    }

                    System.out.println("Parola:");
                    String parola = scan.next();
                    System.out.println("Locatia (orasul):");
                    String locatie = scan.next();
                    Sofer sofer = new Sofer(nume, email, username, parola, locatie);
                    Service.adaugaSofer(sofer);
                }

                if (optiune2 == 3) {
                    System.out.println("Ati ales optiunea MANAGER:");
                    System.out.println("Nume:");
                    String nume = scan.next();
                    System.out.println("Email:");
                    String email = scan.next();
                    boolean unic = false;
                    String username = "";
                    while (!unic) {
                        System.out.println("Username:");
                        username = scan.next();
                        if (Service.unicitateUsername(username)) {
                            unic = true;
                        } else {
                            System.out.println("model.Utilizator deja existent! Introdu alt username!");
                        }
                    }
                    System.out.println("Parola:");
                    String parola = scan.next();
                    Manager manager = new Manager(nume, email, username, parola);
                    Service.adaugaManager(manager);
                }

            }

            if (optiune1 == 2) {
                int[] rezultat = {0, 0};
                boolean logat = false;
                String username = "";
                String parola = "";
                while (!logat) {
                    System.out.println("Introduceti user-ul:");
                    username = scan.next();
                    System.out.println("Introduceti parola:");
                    parola = scan.next();
                    if (Service.logareUtilizator(username, parola)[0] == 0) {
                        System.out.println("Username sau parola incorecte!");
                    } else {
                        logat = true;
                        rezultat = Service.logareUtilizator(username, parola);
                    }
                }

                if (rezultat[1] == 1) {
                    System.out.println("CLIENT");
                    while (page2) {
                        System.out.println("Alegeti una din urmatoarele:");
                        System.out.println("1. Plaseaza o comanda");
                        System.out.println("2. Devino client premium");
                        System.out.println("3. Logout");

                        int optiune4 = scan.nextInt();

                        if(optiune4 == 1) {

                            System.out.println("Alegeti un restaurant din urmatoarea lista:");
                            System.out.println("Pentru logout, introduce-ti 'Logout'");
                            Service.afiseazaRestaurante();
                            String restaurant;
                            boolean exista = false;
                            while (!exista) {
                                restaurant = scan.next();
                                if (restaurant.equalsIgnoreCase("Logout")) {
                                    page2 = false;
                                    break;
                                }
                                if (Service.restaurantExistentClient(restaurant)) {
                                    Restaurant obiectRestaurant = Service.gasesteRestaurant(restaurant);
                                    Service.afiseazaMeniuRestaurant(obiectRestaurant);
                                    boolean comanda = true;
                                    System.out.println("Alege preparatele comenzii tale.");
                                    System.out.println("In cazul in care ai terminat comanda, scrie 'Calculeaza', iar totalul tau va fi calculat.");
                                    Comanda comandaClient = new Comanda(Service.gasesteClientDupaUsername(username), obiectRestaurant);
                                    while (comanda) {
                                        boolean existaItem = false;
                                        while (!existaItem) {
                                            String item = scan.next();
                                            if (!item.equalsIgnoreCase("Calculeaza")) {
                                                if (Service.existaItemMeniuRestaurant(obiectRestaurant, item)) {
                                                    System.out.println("Introduce cantitate:");
                                                    int cantitate;
                                                    cantitate = scan.nextInt();
                                                    ItemMeniu itemMeniu = Service.gasesteItemMeniu(item);
                                                    ItemComanda itemComanda = new ItemComanda(itemMeniu, cantitate);
                                                    comandaClient.adaugaItemComanda(itemComanda);
                                                    break;
                                                } else {
                                                    System.out.println("Item-ul nu exista in meniu!");
                                                }
                                            } else {
                                                Service.afiseazaTotalComanda(comandaClient);
                                                existaItem = true;
                                                comanda = false;
                                            }

                                        }
                                    }

                                } else {
                                    System.out.println("Restaurantul nu exista! Introduceti alt restaurant!");
                                }
                                exista = true;

                            }
                        } else if (optiune4 == 2) {
                            Service.devinePremium(Service.gasesteClientDupaUsername(username));
                        } else {
                            break;
                        }
                    }
                }

                    if (rezultat[1] == 2) {
                        System.out.println("SOFER");
                        Sofer sofer = Service.gasesteSoferDupaUsername(username);
                        boolean disponibil = false;
                        while(!disponibil) {
                            System.out.println("Doriti sa deveniti DISPONIBIL pentru preluarea comenzilor?");
                            String decizie = scan.next();
                            if(decizie.equalsIgnoreCase("da")) {
                                disponibil = true;
                                sofer.setDisponibilitate(true);
                            }

                        }

                    }

                    if (rezultat[1] == 3) {

                        while (page2) {
                            System.out.println("MANAGER");
                            System.out.println("Alegeti una din urmatoarele:");
                            System.out.println("1. Creaza restaurant");
                            System.out.println("2. Adauga preparate la meniul unui restaurant existent:");
                            System.out.println("3. Afiseaza restaurantele mele");
                            System.out.println("4. Afiseaza datele contului");
                            System.out.println("5. Logout");
                            int optiune3 = scan.nextInt();
                            if (optiune3 == 1) {
                                System.out.println("Introduceti numele restaurantului pe care doriti sa il creati:");
                                String restaurant;
                                boolean unic = false;
                                while (!unic) {
                                    restaurant = scan.next();
                                    if (!Service.unicitateRestaurant(restaurant)) {
                                        System.out.println("Restaurantul deja exista in sistem! Adauga alt nume!");
                                    } else {
                                        System.out.println("Strada:");
                                        String strada = scan.next();
                                        System.out.println("Costul de Livrare:");
                                        double costLivrare = scan.nextDouble();
                                        Restaurant restaurant1 = new Restaurant(restaurant, strada, costLivrare);
                                        Service.adaugaRestaurant(Service.gasesteManager(username), restaurant1);
                                        unic = true;
                                    }
                                }
                            }
                            if (optiune3 == 3) {
                                Service.afiseazaRestauranteManager(Service.gasesteManager(username));

                            }
                            if (optiune3 == 2) {
                                System.out.println("Introdu numele restaurantului:");
                                String restaurant;
                                boolean unic = false;
                                while (!unic) {
                                    restaurant = scan.next();
                                    if ((Service.restaurantExistent(Service.gasesteManager(username), restaurant))) {
                                        System.out.println("Introdu numele preparatului:");
                                        String numePreparat = scan.next();
                                        System.out.println("Introdu descriere:");
                                        String descriere = scan.next();
                                        System.out.println("Introdu pret:");
                                        double pret = scan.nextDouble();
                                        ItemMeniu preparat = new ItemMeniu(numePreparat, descriere, pret);
                                        Service.adaugaItemMeniuRestaurant(Service.gasesteRestaurantManager(Service.gasesteManager(username), restaurant), preparat);
                                        unic = true;
                                    } else {
                                        System.out.println("Restaurantul nu exista. Introdu alt restaurant.");
                                    }
                                }
                            }
                            if (optiune3 == 4){
                                Service.afiseazaDateManager(Service.gasesteManager(username));
                            }
                            if (optiune3 == 5) {
                                page2 = false;
                            }

                        }

                    }
                }

            }


        }

    }







