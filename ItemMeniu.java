public class ItemMeniu {
    private String nume;
    private String descriere;
    private double pret;

    public ItemMeniu(String nume, String descriere, double pret) {
        this.nume = nume;
        this.descriere = descriere;
        this.pret = pret;
    }

    public ItemMeniu() {
        this.nume = null;
        this.descriere = null;
        this.pret = 0;
    }

    public String getNume() {
        return nume;
    }

    public String getDescriere() {
        return descriere;
    }

    public double getPret() {
        return pret;
    }
}
