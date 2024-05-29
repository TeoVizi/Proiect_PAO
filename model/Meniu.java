package model;

import java.util.ArrayList;
import java.util.List;

public class Meniu {
    private List<ItemMeniu> listaItemiMeniu;

    public Meniu() {
        this.listaItemiMeniu = new ArrayList<>();
    }

    public void adaugaItemMeniu(ItemMeniu item) {
        listaItemiMeniu.add(item);
    }

    public List<ItemMeniu> getListaItemiMeniu() {
        return listaItemiMeniu;
    }

}
