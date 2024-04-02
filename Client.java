public class Client extends Utilizator{
    private String adresaLivrare;

    public Client(String nume, String adresaEmail, String adresaLivrare) {
        super(nume, adresaEmail);
        this.adresaLivrare = adresaLivrare;
    }

    public void setAdresaLivrare(String adresaLivrare) {
        this.adresaLivrare = adresaLivrare;
    }

    public String getAdresaLivrare() {
        return adresaLivrare;
    }
}
