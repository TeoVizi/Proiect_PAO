public class Main {
    public static void main(String[] args) {
        Utilizator utilizator = new Utilizator("George Ionescu", "george.ionescu@email.com");

            //verificari Utilizator
        System.out.println(utilizator.getNume());
        System.out.println(utilizator.getEmail());

        utilizator.setNume("George Alin Ionescu");
        utilizator.setEmail("george.a.ionescu@email.com");

        System.out.println(utilizator.getNume());
        System.out.println(utilizator.getEmail());

            //verificari Client
        Client client = new Client("Mihai Popescu", "mihai.popescu@email.com", "Strada Libertatii", "10", "Bucuresti", true);

        System.out.println( client.getAdresaCompleta());

        System.out.println(client.getNume());
        System.out.println(client.getEmail());
        System.out.println( client.getStrada());
        System.out.println( client.getNumar());
        System.out.println( client.getOras());
        System.out.println( client.getIsPremium());

        client.setStrada("Strada Unirii");
        client.setNumar("20A");
        client.setOras("Cluj-Napoca");
        client.setPremium(false);
        System.out.println( client.getIsPremium());

        System.out.println(client.getAdresaCompleta());

            //verificari ItemMeniu

        ItemMeniu pizzaMargherita = new ItemMeniu("Pizza Margherita", "Pizza", 35.0);

        System.out.println(pizzaMargherita.getNume());
        System.out.println(pizzaMargherita.getPret());
        System.out.println(pizzaMargherita.getDescriere());

        pizzaMargherita.setDescriere("Pizza bombastica");
        System.out.println(pizzaMargherita.getDescriere());

            // verificaro Meniu

        Meniu meniu = new Meniu();

        meniu.adaugaItemMeniu(new ItemMeniu("Pizza Margherita", "Pizza", 35.0));
        meniu.adaugaItemMeniu(new ItemMeniu("Lasagna", "Lasagn", 45.0));
        meniu.adaugaItemMeniu(new ItemMeniu("Tiramisu", "Tiramisu", 25.0));


        for (ItemMeniu item : meniu.getItemiMeniu()) {
            System.out.println(item.getNume());
            System.out.println( item.getDescriere());
            System.out.println(  item.getPret() + " lei");
            System.out.println();
        }

        // verificari Restaurant

        Restaurant restaurant = new Restaurant("Gusto Italiano", "Strada Libertății 23, Oradea");

        Meniu meniu1 = new Meniu();
        meniu1.adaugaItemMeniu(new ItemMeniu("Pizza Margherita", "Pizza", 22.5));
        meniu1.adaugaItemMeniu(new ItemMeniu("Pasta Carbonara", "Spaghete", 27.0));

        restaurant.adaugaMeniu(meniu1);

        System.out.println(restaurant.getNume());
        System.out.println( restaurant.getAdresa());
        for (ItemMeniu item : restaurant.getMeniu().get(0).getItemiMeniu()) {
            System.out.println(item.getNume());
        }

        //verif ItemComanda

        ItemComanda comanda1 = new ItemComanda();

        comanda1.setItemMeniu(new ItemMeniu("Gelato", "Inghetata", 15.0));
        comanda1.setCantitate(3);

        System.out.println(comanda1.getItemMeniu().getNume());
        System.out.println(comanda1.getItemMeniu().getDescriere());
        System.out.println(comanda1.getCantitate());
        System.out.println(comanda1.getItemMeniu().getPret() * comanda1.getCantitate() + " lei");
    }
}
