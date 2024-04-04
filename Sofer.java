public class Sofer extends Utilizator {
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

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

}
