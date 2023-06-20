package org.example.Interface;

import org.example.Database.MethodDb;
import org.example.Entity.Client;
import org.example.Entity.Compte;
import org.example.Entity.HistoriqueOperation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

public class IClientImp extends UnicastRemoteObject implements IClient{
    MethodDb methodDb;

    public IClientImp(MethodDb methodDb) throws RemoteException  {
        this.methodDb = methodDb;
    }

    /**
     * cette méthode permet au client de verser l'argent à un autre compte
     * @param numeroCompte
     * @param montant
     * @throws RemoteException
     */
    @Override
    public void verserArgent(int numeroCompte, double montant) throws RemoteException {
        try {
            Compte compte = methodDb.recupererCompteParNumero(numeroCompte);
            if(compte != null){
                methodDb.deposer(numeroCompte, montant);
            }
            else {
                System.out.println("Veuillez vérifier le numero du compte!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * cette méthode permet au client de se connecter à son compte
     * @param username
     * @param password
     * @return
     * @throws RemoteException
     */
    @Override
    public Client connexion(String username, String password) throws RemoteException {
        try {
            Client client = methodDb.recupererClientParUsername(username);
            if (client != null){
                if(password.equals(client.getPassword())){
                    return client;
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * cette méthode permet au client de consulter l'historique de ses opérations
     * @param idClient
     * @return
     * @throws RemoteException
     */
    @Override
    public List<HistoriqueOperation> consulterOperations(long idClient) throws RemoteException {
        try {
            return methodDb.recupererLesOperations(idClient);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
