public abstract class Item {
    protected String nume;
    protected String descriere;

    public Item(String nume, String descriere) {
        this.nume = nume;
        this.descriere = descriere;
    }

    public Item() {
        // Constructor implicit fără parametri
        this.nume = null;
        this.descriere = null;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }
}
