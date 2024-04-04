public class Sofer extends Utilizator {
    private String locatie;
    private boolean disponibilitate;

    public Sofer(String nume, String email, String locatie, boolean disponibilitate) {
        super(nume, email);
        this.locatie = locatie;
        this.disponibilitate = disponibilitate;
    }

    public  Sofer() {
        super();
        this.locatie = null;
        this.disponibilitate = false;
    }

    public String getLocatie() {
        return locatie;
    }
    public boolean isDisponibil() {
        return disponibilitate;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public void setDisponibilitate(boolean disponibilitate) {
        this.disponibilitate = disponibilitate;
    }
}
