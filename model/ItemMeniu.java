package model;

public class ItemMeniu {
    private int id;
    private String nume;
    private String descriere;
    private double pret;
    private int meniuId;

    // Constructor with all parameters
    public ItemMeniu(int id, String nume, String descriere, double pret, int meniuId) {
        this.id = id;
        this.nume = nume;
        this.descriere = descriere;
        this.pret = pret;
        this.meniuId = meniuId;
    }

    // Constructor without id
    public ItemMeniu(String nume, String descriere, double pret, int meniuId) {
        this.nume = nume;
        this.descriere = descriere;
        this.pret = pret;
        this.meniuId = meniuId;
    }

    // Constructor without meniuId
    public ItemMeniu(String nume, String descriere, double pret) {
        this.nume = nume;
        this.descriere = descriere;
        this.pret = pret;
    }

    public ItemMeniu() {
        this.id = 0;
        this.nume = null;
        this.descriere = null;
        this.pret = 0;
        this.meniuId = 0;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public int getMeniuId() {
        return meniuId;
    }

    public void setMeniuId(int meniuId) {
        this.meniuId = meniuId;
    }

    @Override
    public String toString() {
        return "ItemMeniu{" +
                "nume='" + nume + '\'' +
                ", descriere='" + descriere + '\'' +
                ", pret=" + pret +
                '}';
    }
}
