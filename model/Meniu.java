package model;

import java.util.ArrayList;
import java.util.List;

public class Meniu {
    private int id;
    private int restaurantId; // Add this field
    private List<ItemMeniu> listaItemiMeniu;

    public Meniu() {
        this.listaItemiMeniu = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRestaurantId() {
        return restaurantId; // Add getter
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId; // Add setter
    }

    public void adaugaItemMeniu(ItemMeniu item) {
        listaItemiMeniu.add(item);
    }

    public List<ItemMeniu> getListaItemiMeniu() {
        return listaItemiMeniu;
    }

    public void setListaItemiMeniu(List<ItemMeniu> listaItemiMeniu) {
        this.listaItemiMeniu = listaItemiMeniu;
    }
}
