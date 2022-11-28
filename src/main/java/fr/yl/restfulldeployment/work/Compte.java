package fr.yl.restfulldeployment.work;

public class Compte {
    private int id_compte;
    private String email;
    private String password;

    public Compte() {
        this.id_compte = 0;
        this.email = "";
        this.password = "";
    }

    public Compte(int id_compte, String email, String password) {
        this.id_compte = id_compte;
        this.email = email;
        this.password = password;
    }

    public int getId_compte() {
        return id_compte;
    }

    public void setId_compte(int id_compte) {
        this.id_compte = id_compte;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
