import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String nume;
    private String adresa;
    private List<Meniu> meniu;
    private double costLivrare;

    public Restaurant(String nume, String adresa, double costLivrare) {
        this.nume = nume;
        this.adresa = adresa;
        this.meniu = new ArrayList<>();
        this.costLivrare = costLivrare;
    }

    public Restaurant() {
        this.nume = null;
        this.adresa = null;
        this.meniu = new ArrayList<>();
        this.costLivrare = 0;
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

    public void setCostLivrare(double costLivrare) {
        this.costLivrare = costLivrare;
    }

    public double getCostLivrare() {
        return costLivrare;
    }
}
