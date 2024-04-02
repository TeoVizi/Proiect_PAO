public class Main {
    public static void main(String[] args) {
        Utilizator utilizator = new Utilizator("George Ionescu", "george.ionescu@email.com");

        System.out.println(utilizator.getNume());
        System.out.println(utilizator.getEmail());

        utilizator.setNume("George Alin Ionescu");
        utilizator.setEmail("george.a.ionescu@email.com");

        System.out.println(utilizator.getNume());
        System.out.println(utilizator.getEmail());
    }
}
