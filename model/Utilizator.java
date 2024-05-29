package model;

public class Utilizator {
    private int id;

    protected String nume;
    protected String email;
    private String username;
    private String parola;

    public Utilizator(String nume, String email, String username, String parola) {
        this.nume = nume;
        this.email = email;
        this.username = username;
        this.parola = parola;
    }

    public Utilizator() {
        this.nume = null;
        this.email = null;
        this.username = null;
        this.parola = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String Email) {
        this.email = email;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
