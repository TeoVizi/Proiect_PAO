public class ItemMeniu extends Item {
    private double pret;

    public ItemMeniu(String nume, String descriere, double pret) {
        super(nume, descriere); // Apelarea constructorului din clasa de bază
        this.pret = pret;
    }

    public ItemMeniu() {
        super(); // Apelarea constructorului implicit din clasa de bază
        this.pret = 0;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }
}