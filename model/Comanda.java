package model;

import java.util.ArrayList;
import java.util.List;

public class Comanda {
    private int id;
    private Client client;
    private Restaurant restaurant;
    private String status;
    private double totalPlata;
    private List<ItemComanda> listaItemiComandati;

    public Comanda(Client client, Restaurant restaurant, String status, double totalPlata) {
        this.client = client;
        this.restaurant = restaurant;
        this.status = status;
        this.totalPlata = totalPlata;
        this.listaItemiComandati = new ArrayList<>();
    }

    public Comanda() {
        this.client = null;
        this.restaurant = null;
        this.status = null;
        this.totalPlata = 0;
        this.listaItemiComandati = new ArrayList<>();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
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

    public void setTotalPlata(double totalPlata) {
        this.totalPlata = totalPlata;
    }

    public List<ItemComanda> getListaItemiComandati() {
        return listaItemiComandati;
    }

    public void setListaItemiComandati(List<ItemComanda> listaItemiComandati) {
        this.listaItemiComandati = listaItemiComandati;
    }

    public void adaugaItemComanda(ItemComanda itemComanda) {
        this.listaItemiComandati.add(itemComanda);
    }
}
