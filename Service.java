import java.util.ArrayList;
import java.util.Map;

public class Service {
    private static ArrayList<Utilizator> utilizatori = new ArrayList<>() ;
    private static ArrayList<Restaurant> restaurante;
    private static Map<Restaurant, Meniu> meniuri;

    private Service()
    {

    }

    public static boolean unicitateUsername(String username)
    {
        for (Utilizator utilizator : utilizatori) {
            if (utilizator.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }
    public static void adaugaClient(Client client) {
        utilizatori.add(client);
    }

    public static void adaugaSofer(Sofer sofer) {
        utilizatori.add(sofer);
    }

    public static void adaugaManager(Manager manager) {
        utilizatori.add(manager);
    }
    public static ArrayList<Utilizator> getUtilizatori()
    {
        return utilizatori;
    }
}
