public abstract class Item {
    protected String nume;
    protected String descriere;

    public Item(String nume, String descriere) {
        this.nume = nume;
        this.descriere = descriere;
    }

    public String getNume() {
        return nume;
    }

    public String getDescriere() {
        return descriere;
    }
}
