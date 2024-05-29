package service;
import model.Client;
import model.Comanda;
import model.Restaurant;

import java.util.List;

public class ComandaService {
    public static void afiseazaTotalComanda(Comanda comanda) {
        Client client = comanda.getClient();
        Restaurant restaurant = comanda.getRestaurant();
        double totalPlata = comanda.getTotalPlata();
        System.out.println("Totalul comenzii: " + totalPlata);
    }
}
