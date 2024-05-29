package model;

import java.util.ArrayList;
import java.util.List;

public class Comanda {
    private Client client;
    private Restaurant restaurant;

    private List<ItemComanda> listaItemiComandati;
    private String status;
    private double totalPlata;

    public Comanda(Client client, Restaurant restaurant) {
        this.client = client;
        this.restaurant = restaurant;
        this.listaItemiComandati = new ArrayList<>();
        this.status = "Nou";
        this.totalPlata = 0.0;
    }

    public Comanda() {
        this.client = new Client();
        this.restaurant = new Restaurant();
        this.listaItemiComandati = new ArrayList<>();
        this.status = "Nou";
        this.totalPlata = 0.0;
    }

    public void adaugaItemComanda(ItemComanda item) {
        listaItemiComandati.add(item);
        calculeazaTotal();
    }

    public void calculeazaTotal() {
        double totalItems = listaItemiComandati.stream()
                .mapToDouble(item -> item.getItemMeniu().getPret() * item.getCantitate())
                .sum();
        if (client != null && client.getIsPremium()) {
            this.totalPlata = totalItems; //clienții premium nu platesc costul de livrare
        } else {
            this.totalPlata = totalItems + (restaurant != null ? restaurant.getCostLivrare() : 0); // include costul de livrare daca restaurantul este setat
        }
    }

    public Client getClient() {
        return client;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public List<ItemComanda> getListaItemiComandati() {
        return listaItemiComandati;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalPlata() {
        return totalPlata;
    }

}
