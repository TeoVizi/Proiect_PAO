package model;

public class ItemComanda {
    private int id;
    private ItemMeniu itemMeniu;
    private int cantitate;

    public ItemComanda(ItemMeniu itemMeniu, int cantitate) {
        this.itemMeniu = itemMeniu;
        this.cantitate = cantitate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ItemMeniu getItemMeniu() {
        return itemMeniu;
    }

    public void setItemMeniu(ItemMeniu itemMeniu) {
        this.itemMeniu = itemMeniu;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    @Override
    public String toString() {
        return "ItemMeniu{" +
                "nume='" + itemMeniu.getNume() + '\'' +
                ", descriere='" + itemMeniu.getDescriere() + '\'' +
                ", pret=" + itemMeniu.getPret() +
                ", cantitate=" + cantitate +
                '}';
    }
}
