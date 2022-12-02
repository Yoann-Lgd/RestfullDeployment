package fr.yl.restfulldeployment.work;

public class Compte {
    private int idCompte;
    private String email;
    private String password;

    public Compte() {
        this.idCompte = 0;
        this.email = "";
        this.password = "";
    }

    public Compte(int idCompte, String email, String password) {
        this.idCompte = idCompte;
        this.email = email;
        this.password = password;
    }

    public int getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
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
