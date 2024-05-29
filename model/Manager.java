package model;

import java.util.ArrayList;

public class Manager extends Utilizator{
    private int id;

    private ArrayList<Restaurant> restaurante;

    public Manager(String nume, String email,String username, String parola) {
        super(nume, email, username, parola);
        this.restaurante = new ArrayList<Restaurant>();
    }

    public Manager() {
        super();
        this.restaurante = new ArrayList<Restaurant>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Restaurant> getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(ArrayList<Restaurant> restaurante) {
        this.restaurante = restaurante;
    }
}
