import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean page1 = true;

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
                    System.out.println("Ati ales optiunea Sofer:");
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
                System.out.println("Ati ales optiunea Sofer:");
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

            }
        }

    }

