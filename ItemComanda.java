public class ItemComanda {
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

    public void setItemComanda(ItemMeniu itemMeniu) {
        this.itemMeniu = itemMeniu;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public ItemMeniu getItemComanda() {
        return itemMeniu;
    }

    public int getCantitate() {
        return cantitate;
    }
}