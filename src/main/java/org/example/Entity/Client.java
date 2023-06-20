package org.example.Entity;

import java.io.Serializable;

public class Client implements Serializable {
    private Long idClient;
    private String username;
    private String nom;
    private String prenom;
    private String cne;
    private String password;
    private int numeroCompte;

    public Client(Long idClient, String username, String nom, String prenom, String cne, String password, int numeroCompte) {
        this.idClient = idClient;
        this.username = username;
        this.nom = nom;
        this.prenom = prenom;
        this.cne = cne;
        this.password = password;
        this.numeroCompte = numeroCompte;
    }

    public Client(String username, String nom, String prenom, String cne, String password, int numeroCompte) {
        this.username = username;
        this.nom = nom;
        this.prenom = prenom;
        this.cne = cne;
        this.password = password;
        this.numeroCompte = numeroCompte;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public int getNumeroCompte() {
        return numeroCompte;
    }
    public void setNumeroCompte(int numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
