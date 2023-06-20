package org.example.Interface;

import org.example.Entity.Client;
import org.example.Entity.HistoriqueOperation;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IClient extends Remote {
    void verserArgent(int numeroCompte, double montant) throws RemoteException;
    Client connexion(String username, String password) throws RemoteException;
    List<HistoriqueOperation> consulterOperations(long idClient) throws RemoteException;
}
