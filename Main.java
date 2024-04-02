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
        Client client = new Client("Mihai Popescu", "mihai.popescu@email.com", "Strada Libertatii", "10", "Bucuresti");

        System.out.println( client.getAdresaCompleta());

        System.out.println( client.getNume());
        System.out.println( client.getEmail());
        System.out.println( client.getStrada());
        System.out.println( client.getNumar());
        System.out.println( client.getOras());

        client.setStrada("Strada Unirii");
        client.setNumar("20A");
        client.setOras("Cluj-Napoca");

        System.out.println(client.getAdresaCompleta());
    }
}
