import model.*;
import service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        LoginRegisterService loginRegisterService = LoginRegisterService.getInstance();
        loginRegisterService.initialize();

        while (true) {
            System.out.println("Bun venit la Food Delivery App!");
            System.out.println("1. Inregistrare");
            System.out.println("2. Autentificare");
            System.out.println("3. Iesire");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    loginRegisterService.handleRegistration(scanner);
                    break;
                case 2:
                    loginRegisterService.handleLogin(scanner);
                    break;
                case 3:
                    System.out.println("La revedere!");
                    return;
                default:
                    System.out.println("Optiune invalida.");
            }
        }





    }




}