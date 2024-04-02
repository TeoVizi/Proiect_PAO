public class Client extends Utilizator {
    private String strada;
    private String numar;
    private String oras;

    public Client(String nume, String adresaEmail, String strada, String numar, String oras) {
        super(nume, adresaEmail);
        this.strada = strada;
        this.numar = numar;
        this.oras = oras;
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

    public String getAdresaCompleta() {
        return strada + ", Nr. " + numar + ", " + oras;
    }
}
