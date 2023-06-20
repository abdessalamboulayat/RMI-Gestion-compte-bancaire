package org.example.Database;

import org.example.Entity.Client;
import org.example.Entity.Compte;
import org.example.Entity.HistoriqueOperation;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MethodDb {
    ConnexionDb connexionDb = new ConnexionDb();

    /**
     * cette méthode nous permet d'ajouter un nouveau compte
     * @param numeroCompte
     * @param solde
     * @return
     * @throws SQLException
     */
    public int ajouterCompte(int numeroCompte, double solde) throws SQLException {
        String requete="INSERT INTO Compte(numeroCompte, solde) VALUES('"+numeroCompte+"','"+solde+"')";
        PreparedStatement ps = connexionDb.connexion().prepareStatement(requete);
        int res = ps.executeUpdate();
        if(res<0){
            return 0;
        }
        return 1;
    }

    /**
     * cette méthode nous permet d'ajouter un nouveau client
     * @param client
     * @return
     * @throws SQLException
     */
    public int ajouterClient(Client client) throws SQLException {
        String requete="INSERT INTO Client(nom,prenom,cne,numeroCompte,username,password) VALUES('"+client.getNom()+"','"+client.getPrenom()+"','"+client.getCne()+"','"+client.getNumeroCompte()+"','"+client.getUsername()+"','"+client.getPassword()+"')";
        PreparedStatement ps = connexionDb.connexion().prepareStatement(requete);
        int res = ps.executeUpdate();
        if(res<0){
            return 0;
        }
        return 1;
    }

    /**
     * cette méthode nous permet de recuperer un client par username
     * @param username
     * @return
     * @throws SQLException
     */
    public Client recupererClientParUsername(String username) throws SQLException {
        String requete = "SELECT * FROM Client WHERE username = '"+username+"'";
        PreparedStatement ps = connexionDb.connexion().prepareStatement(requete);
        ResultSet res = ps.executeQuery();
        if(!res.next()){
            return null;
        }
        Client client = new Client(res.getLong("idClient"),res.getString("username"), res.getString("nom"),res.getString("prenom"),res.getString("cne"),res.getString("password"),Integer.parseInt(res.getString("numeroCompte")));
        return client;
    }

    /**
     * cette méthode nous permet de recuperer un client par cne
     * @param cne
     * @return
     * @throws SQLException
     */
    public Client recupererClientParCne(String cne) throws SQLException{
        String requete = "SELECT * FROM Client WHERE cne = '"+cne+"'";
        PreparedStatement ps = connexionDb.connexion().prepareStatement(requete);
        ResultSet res = ps.executeQuery();
        if(!res.next()){
            return null;
        }
        Client client = new Client(res.getString("username"), res.getString("nom"),res.getString("prenom"),res.getString("cne"),res.getString("password"),Integer.parseInt(res.getString("numeroCompte")));
        return client;
    }
    /**
     * cette méthode nous permet de recuperer le compte par numero compte
     * @param numero
     * @return
     * @throws SQLException
     */
    public Compte recupererCompteParNumero(int numero) throws SQLException {
        String requete = "SELECT * FROM Compte WHERE numeroCompte = '"+numero+"'";
        PreparedStatement ps = connexionDb.connexion().prepareStatement(requete);
        ResultSet res = ps.executeQuery();
        if(!res.next()){
            return null;
        }
        Compte compte = new Compte(res.getInt("numeroCompte"),Double.parseDouble(res.getString("solde")));
        return compte;
    }

    /**
     * cette méthode nous permet de récuperer le solde d'un compte
     * @param numero
     * @return
     * @throws SQLException
     */
    public double recupererLeSolde(int numero) throws SQLException{
        String requete = "SELECT solde FROM Compte WHERE numeroCompte = '"+numero+"'";
        PreparedStatement ps = connexionDb.connexion().prepareStatement(requete);
        ResultSet res = ps.executeQuery();
        if(!res.next()){
            //return null;
        }
        double solde = Double.parseDouble(res.getString("solde"));
        return solde;
    }

    /**
     * cette méthode nous permet d'augmenter le montant
     * @param numero
     * @param montant
     * @throws SQLException
     */
    public void deposer(int numero, double montant) throws SQLException{
        Compte compte = recupererCompteParNumero(numero);
        double montantTotal = compte.getSolde()+montant;
        String requete = "UPDATE Compte SET solde = '"+ montantTotal +"'WHERE numeroCompte = '"+numero+"'";
        PreparedStatement ps = connexionDb.connexion().prepareStatement(requete);
        ps.executeUpdate();
    }

    /**
     * cette méthode nous permet de retirer le montant
     * @param numero
     * @param montant
     * @throws SQLException
     */
    public void retirer(int numero, double montant) throws SQLException{
        Compte compte = recupererCompteParNumero(numero);
        double montantExistant = compte.getSolde();
        if(montantExistant > montant){
            double montantRestant = montantExistant - montant;
            String requete = "UPDATE Compte SET solde = '"+ montantRestant +"'WHERE numeroCompte = '"+numero+"'";
            PreparedStatement ps = connexionDb.connexion().prepareStatement(requete);
            ps.executeUpdate();
        }
        else {
            System.out.println("Le montant que vous demandez est introuvable");
        }
    }

    /**
     * cette méthode nous permet de supprimer un compte
     * @param numeroCompte
     * @throws SQLException
     */
    public void supprimerCompte(int numeroCompte) throws SQLException{
        Compte compte = recupererCompteParNumero(numeroCompte);
        if(compte != null){
            String requete  = "DELETE FROM Compte WHERE numeroCompte = '"+numeroCompte+"'";
            PreparedStatement ps = connexionDb.connexion().prepareStatement(requete);
            ps.execute();
        }
        else {
            System.out.println("Veuillez verifier le numero du compte");
        }
    }
    public void affecterUnCompteAunClient(int numeroCompte, String cne) throws SQLException {
        Client client = recupererClientParCne(cne);
        if(client != null){
            Compte compte = recupererCompteParNumero(numeroCompte);
            if(compte != null){
                String requete = "UPDATE Client SET numeroCompte = '"+ numeroCompte +"'WHERE cne = '"+cne+"'";
                PreparedStatement ps = connexionDb.connexion().prepareStatement(requete);
                ps.executeUpdate();
            }
            else{
                System.out.println("Veuillez verifier le numero du compte");
            }
        }
        else {
            System.out.println("Veuillez vérifier le CNE du client");
        }
    }

    /**
     * cette méthode nous permet de consulter le solde
     * @param numeroCompte
     * @return
     * @throws SQLException
     */
    public double consulterSolde(int numeroCompte) throws SQLException{
        Compte compte = recupererCompteParNumero(numeroCompte);
        if(compte != null){
            String requete = "SELECT solde FROM Compte WHERE numeroCompte = '"+numeroCompte+"'";
            PreparedStatement ps = connexionDb.connexion().prepareStatement(requete);
            ResultSet res = ps.executeQuery();
            return Double.parseDouble(res.getString("solde"));
        }
        else {
            System.out.println("Veuillez verifier le numero du compte");
        }
        return 0;
    }

    /**
     * cette méthode nous permet d'ajouter une opération à l'hitorique d'opération
     * @param operation
     * @param montant
     * @param date
     * @param idClient
     */
    public void ajouterUneOperation(String operation, double montant, LocalDate date, Long idClient) throws SQLException{
        String requete="INSERT INTO historiqueOperation(operation,montant,date,idClient) VALUES('"+operation+"','"+montant+"','"+date+"','"+idClient+"')";
        PreparedStatement ps = connexionDb.connexion().prepareStatement(requete);
        int res = ps.executeUpdate();
    }

    /**
     * cette nous permet de recuperer toutes les opérations d'un client
     * @param idClient
     * @return
     * @throws SQLException
     */
    public List<HistoriqueOperation> recupererLesOperations(long idClient) throws SQLException {
        String requete="SELECT * FROM historiqueOperation WHERE idClient = '"+idClient+"'";
        PreparedStatement ps = connexionDb.connexion().prepareStatement(requete);
        ResultSet res = ps.executeQuery();
        List<HistoriqueOperation> historiqueOperations = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (res.next()){
            // Parse the string to LocalDateTime using the formatter
            LocalDate localDate = LocalDate.parse(res.getString("date"), formatter);
            HistoriqueOperation historiqueOperation = new HistoriqueOperation(res.getString("operation"),res.getDouble("montant"),localDate);
            historiqueOperations.add(historiqueOperation);
        }
        return historiqueOperations;
    }
}
