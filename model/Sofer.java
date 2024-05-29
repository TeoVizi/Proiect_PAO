package model;

public class Sofer extends Utilizator {
    private int id;

    private String locatie;
    private boolean disponibilitate;

    public Sofer(String nume, String email,String username, String parola, String locatie) {
        super(nume, email, username, parola);
        this.locatie = locatie;

    }

    public  Sofer() {
        super();
        this.locatie = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setDisponibilitate(boolean disponibilitate) {
        this.disponibilitate = disponibilitate;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public boolean getDisponibilitate() {
        return disponibilitate;
    }
}
