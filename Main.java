import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean page1 = true;
        boolean page2 =true;
        Service.initializeData();

        while (page1) {
        System.out.println("Bine ati venit in aplicatia de food delivery YumYumDelivery!");
        System.out.println("Va rugam sa alegeti o actiune de mai jos:");
        System.out.println("1. Creati un cont nou");
        System.out.println("2. Logare");

        int optiune1 = scan.nextInt();

        if (optiune1 == 1) {
            System.out.println("Alegeti tipul de cont pe care il doriti sau intoarceti-va la pagina anterioara:");
            System.out.println("1. Client");
            System.out.println("2. Sofer");
            System.out.println("3. Manager");
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
                    if(Service.unicitateUsername(username)) {
                        unic = true;
                    } else {
                        System.out.println("Utilizator deja existent! Introdu alt username!");
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
                        if(Service.unicitateUsername(username)) {
                            unic = true;
                        } else {
                            System.out.println("Utilizator deja existent! Introdu alt username!");
                        }
                    }

                    System.out.println("Parola:");
                    String parola = scan.next();
                    System.out.println("Locatia (orasul):");
                    String locatie = scan.next();
                    Sofer sofer = new Sofer(nume,email,username,parola,locatie);
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
                    if(Service.unicitateUsername(username)) {
                        unic = true;
                    } else {
                        System.out.println("Utilizator deja existent! Introdu alt username!");
                    }
                }
                System.out.println("Parola:");
                String parola = scan.next();
                Manager manager = new Manager(nume,email,username,parola);
                Service.adaugaManager(manager);
                    }

                }

                if (optiune1 == 2) {
                    int[] rezultat = {0,0};
                    boolean logat = false;
                    String username = "";
                    String parola = "";
                    while (!logat) {
                        System.out.println("Introduceti user-ul:");
                        username = scan.next();
                        System.out.println("Introduceti parola:");
                        parola = scan.next();
                        if (Service.logareUtilizator(username,parola)[0] == 0) {
                            System.out.println("Username sau parola incorecte!");
                        } else {
                            logat = true;
                            rezultat = Service.logareUtilizator(username,parola);
                        }
                    }

                    if (rezultat[1] == 1){
                        System.out.println("CLIENT");
                    }

                    if (rezultat[1] == 2){
                        System.out.println("SOFER");
                    }

                    if (rezultat[1] == 3){

                        while(page2) {
                            System.out.println("MANAGER");
                            System.out.println("Alegeti una din urmatoarele:");
                            System.out.println("1. Creaza restaurant");
                            System.out.println("2. Adauga preparate la meniul unui restaurant existent:");
                            System.out.println("3. Afiseaza restaurantele mele");
                            int optiune3 = scan.nextInt();
                            if (optiune3 == 1) {
                                System.out.println("Introduceti numele restaurantului pe care doriti sa il creati:");
                                String restaurant;
                                boolean unic = false;
                                while (!unic) {
                                    restaurant = scan.next();
                                    if(!Service.unicitateRestaurant(restaurant))
                                    {
                                        System.out.println("Restaurantul deja exista in sistem! Adauga alt nume!");
                                    } else {
                                            System.out.println("Strada:");
                                            String strada = scan.next();
                                            System.out.println("Costul de Livrare:");
                                            double costLivrare = scan.nextDouble();
                                            Restaurant restaurant1 = new Restaurant(restaurant,strada,costLivrare);
                                            Service.adaugaRestaurant(Service.gasesteManager(username),restaurant1);
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
                                            ItemMeniu preparat = new ItemMeniu(numePreparat,descriere,pret);
                                            Service.adaugaItemMeniuRestaurant();
                                            unic = true;
                                        }
                                    }

                                }

                            }
                        }

                    }


                }

            }

        }





