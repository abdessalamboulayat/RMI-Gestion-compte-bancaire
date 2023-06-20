package org.example.Interface;

import org.example.Entity.Client;
import org.example.Entity.Compte;
import org.example.Entity.HistoriqueOperation;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IBanque extends Remote {
    Long creeCompte(Compte compte) throws RemoteException;
    double recupererSolde(int numeroCompte) throws RemoteException;
    void deposer(int numeroCompte, double montant) throws RemoteException;
    void retirer(int numeroCompte, double montant) throws RemoteException;
    void ajouterClient(Client client) throws RemoteException;
    void affecterUnCompteAunClient(int numeroCompte, String cneClient) throws RemoteException;
    void supprimerUnCompte(int numeroCompte) throws RemoteException;
    void ajouterOperation(HistoriqueOperation historiqueOperation) throws RemoteException;
}
