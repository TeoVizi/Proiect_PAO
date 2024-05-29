package model;

public class Client extends Utilizator {
    private int id;
    private String strada;
    private String numar;
    private String oras;
    private boolean isPremium;

    public Client(String nume, String email, String username, String parola, String strada, String numar, String oras ,boolean isPremium) {
        super(nume, email, username, parola);
        this.strada = strada;
        this.numar = numar;
        this.oras = oras;
        this.isPremium = isPremium;
    }

    public Client() {
        super(null, null,null,null);
        this.strada = null;
        this.numar = null;
        this.oras = null;
        this.isPremium = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStrada() {
        return strada;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    public String getNumar() {
        return numar;
    }

    public void setNumar(String numar) {
        this.numar = numar;
    }

    public String getOras() {
        return oras;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }
    public boolean getIsPremium() {
        return isPremium;
    }

    public void setPremium(boolean isPremium) {
        this.isPremium = isPremium;
    }

    public String getAdresaCompleta() {
        return strada + ", Nr. " + numar + ", " + oras;
    }
}
