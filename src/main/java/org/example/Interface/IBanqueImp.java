package org.example.Interface;

import org.example.Database.MethodDb;
import org.example.Entity.Client;
import org.example.Entity.Compte;
import org.example.Entity.HistoriqueOperation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class IBanqueImp extends UnicastRemoteObject implements IBanque {
    MethodDb methodDb;
    public IBanqueImp(MethodDb methodDb) throws RemoteException {
        this.methodDb = methodDb;
    }

    /**
     * cette méthode permet de créer un compte
     * @param compte
     * @return
     * @throws RemoteException
     */
    @Override
    public Long creeCompte(Compte compte) throws RemoteException {
        try {
            // vérifier si le compte existe déja
            Compte compte1 = methodDb.recupererCompteParNumero(compte.getNumeroCompte());
            // si le compte n'existe pas
            if (compte1 == null){
                methodDb.ajouterCompte(compte.getNumeroCompte(),compte.getSolde());
                return compte.getIdCompte();
            }
            // si le compte existe déjà
            else {
                System.out.println("Un compte avec le même numéro compte existe déjà!");
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * cette méthode permet de récuperer le solde d'un compte
     * @param numeroCompte
     * @return
     * @throws RemoteException
     */
    @Override
    public double recupererSolde(int numeroCompte) throws RemoteException {
        try {
            double solde = methodDb.recupererLeSolde(numeroCompte);
            return solde;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * cette méthode permet de déposer l'argent dans un compte
     * @param numeroCompte
     * @param montant
     * @throws RemoteException
     */
    @Override
    public void deposer(int numeroCompte, double montant) throws RemoteException {
        try {
            methodDb.deposer(numeroCompte, montant);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * cette méthode permet de retirer l'argent d'un compte
     * @param numeroCompte
     * @param montant
     * @throws RemoteException
     */
    @Override
    public void retirer(int numeroCompte, double montant) throws RemoteException {
        try {
            methodDb.retirer(numeroCompte,montant);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * cette méthode permet d'ajouter un nouveau client
     * @param client
     * @throws RemoteException
     */
    @Override
    public void ajouterClient(Client client) throws RemoteException {
        try {
            methodDb.ajouterClient(client);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * cette méthode permet d'affecter un compte à un client
     * @param numeroCompte
     * @param cneClient
     * @throws RemoteException
     */
    @Override
    public void affecterUnCompteAunClient(int numeroCompte, String cneClient) throws RemoteException {
        try {
            methodDb.affecterUnCompteAunClient(numeroCompte, cneClient);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * cette méthode permet de supprimer un compte
     * @param numeroCompte
     * @throws RemoteException
     */
    @Override
    public void supprimerUnCompte(int numeroCompte) throws RemoteException {
        try {
            methodDb.supprimerCompte(numeroCompte);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * cette méthode permet d'ajouter une nouvelle operation à l'historique des opérations
     * @param historiqueOperation
     * @throws RemoteException
     */
    @Override
    public void ajouterOperation(HistoriqueOperation historiqueOperation) throws RemoteException {
        try {
            methodDb.ajouterUneOperation(historiqueOperation.getOperation(),historiqueOperation.getMontant(),historiqueOperation.getDate(),historiqueOperation.getIdClient());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
