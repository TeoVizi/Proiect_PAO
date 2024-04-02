import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String nume;
    private String adresa;
    private List<Meniu> meniu;

    public Restaurant(String nume, String adresa) {
        this.nume = nume;
        this.adresa = adresa;
        this.meniu = new ArrayList<>();
    }

    public Restaurant() {
        this.nume = null;
        this.adresa = null;
        this.meniu = new ArrayList<>();
    }

    public void adaugaMeniu(Meniu meniu) {
        this.meniu.add(meniu);
    }

    public List<Meniu> getMeniu() {
        return meniu;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    public String getAdresa() {
        return adresa;
    }
}
