package service;

import model.Client;
import model.Sofer;
import repository.SoferRepository;

import java.util.Scanner;

public class SoferService implements CRUDService<Sofer> {
    private SoferRepository soferRepository;
    private static SoferService instance;

    SoferService() {
        this.soferRepository = new SoferRepository();
    }

    public static SoferService getInstance() {
        if (instance == null) {
            instance = new SoferService();
        }
        return instance;
    }

    @Override
    public void create(Sofer sofer) {
        soferRepository.addSofer(sofer);
    }

    @Override
    public Sofer read(int id) {
        return soferRepository.getSoferById(id);
    }

    @Override
    public void update(Sofer sofer) {
        soferRepository.updateSofer(sofer);
    }

    @Override
    public void delete(int id) {
        soferRepository.deleteSofer(id);
    }

    public void createTable() {
        soferRepository.createTable();
    }

    public int getIdByUsername(String username) {
        return soferRepository.getIdByUsername(username);
    }

    public boolean usernameExists(String username) {
        return soferRepository.usernameExists(username);
    }
    public Sofer getSoferByUsernameAndPassword(String username, String password) {
        return soferRepository.getSoferByUsernameAndPassword(username, password);
    }

    public boolean emailExists(String email) {
        return soferRepository.emailExists(email);
    }

    private void deleteAccount(Sofer sofer, Scanner scanner) {
        SoferService soferService = SoferService.getInstance();

        System.out.print("Sunteti sigur ca doriti sa va stergeti contul? (da/nu): ");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("da")) {
            soferService.delete(soferRepository.getIdByUsername(sofer.getUsername()));
            System.out.println("Contul a fost șters cu succes.");
        } else {
            System.out.println("Ștergerea contului a fost anulată.");
        }
    }

    public static void handleSoferActions(Sofer sofer, Scanner scanner) {
        SoferService soferService = SoferService.getInstance();

        while (true) {
            System.out.println("1. Afisare detalii cont");
            System.out.println("2. Schimbare parola");
            System.out.println("3. Devino disponibil");
            System.out.println("4. Stergere cont");
            System.out.println("5. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println("Nume: " + sofer.getNume());
                System.out.println("Email: " + sofer.getEmail());
                System.out.println("Username: " + sofer.getUsername());
                System.out.println("Locatie: " + sofer.getLocatie());
                System.out.println("Disponibilitate: " + sofer.getDisponibilitate());
            } else if (choice == 2) {
                System.out.println("Introduce parola noua:");
                String newPassword = scanner.nextLine();
                sofer.setParola(newPassword);
                soferService.update(sofer);
                System.out.println("Parola schimbata cu succes.");
            } else if (choice == 3) {
                System.out.println("Seteaza Disponibilitatea (true/false):");
                boolean isAvailable = scanner.nextBoolean();
                sofer.setDisponibilitate(isAvailable);
                soferService.update(sofer);
                System.out.println("Disponibilitate schimbata cu succes.");
            } else if (choice == 4) {
                soferService.deleteAccount(sofer, scanner);
            } else if (choice == 5) {
                break;
            }
        }
    }


}
