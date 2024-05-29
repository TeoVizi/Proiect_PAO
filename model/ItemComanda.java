package model;

public class ItemComanda {
    private int id;

    private ItemMeniu itemMeniu;
    private int cantitate;

    public ItemComanda(ItemMeniu itemMeniu, int cantitate) {
        this.itemMeniu = itemMeniu;
        this.cantitate = cantitate;
    }

    public ItemComanda() {
        this.itemMeniu = new ItemMeniu();
        this.cantitate = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setItemMeniu(ItemMeniu itemMeniu) {
        this.itemMeniu = itemMeniu;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public ItemMeniu getItemMeniu() {
        return itemMeniu;
    }

    public int getCantitate() {
        return cantitate;
    }
}