package service;

import model.ItemMeniu;
import model.Meniu;
import model.Restaurant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeniuService {
    private static Map<Restaurant, Meniu> meniuri = new HashMap<>();

    public static void adaugaItemMeniuRestaurant(Restaurant restaurant, ItemMeniu item) {
        Meniu meniu = meniuri.get(restaurant);
        if (meniu == null) {
            meniu = new Meniu();
            meniuri.put(restaurant, meniu);
        }
        meniu.adaugaItemMeniu(item);
        System.out.println("Preparatul '" + item.getNume() + "' a fost adaugat cu succes la meniul restaurantului '" + restaurant.getNume() + "'.");
    }

    public static boolean existaItemMeniuRestaurant(Restaurant restaurant, String numeItem) {
        Meniu meniu = meniuri.get(restaurant);
        if (meniu != null) {
            List<ItemMeniu> itemeMeniu = meniu.getListaItemiMeniu();
            for (ItemMeniu item : itemeMeniu) {
                if (item.getNume().equalsIgnoreCase(numeItem)) {
                    return true;
                }
            }
        }
        return false;
    }
}
