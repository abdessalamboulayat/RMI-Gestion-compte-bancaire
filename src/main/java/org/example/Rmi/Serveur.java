package org.example.Rmi;

import org.example.Database.MethodDb;
import org.example.Interface.IBanqueImp;
import org.example.Interface.IBanque;
import org.example.Interface.IClient;
import org.example.Interface.IClientImp;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Serveur {
    private int port;
    public Serveur(int port) {
        this.port = port;
    }
    public void register() throws RemoteException {
        LocateRegistry.createRegistry(port);
    }
    public static void main(String[] args) throws Exception {
        System.out.println("Demarrage du serveur ... ");
        final int PORT = 1099;
        Serveur serveur = new Serveur(PORT);
        //LocateRegistry.createRegistry(1099);
        serveur.register();
        // instancier un objet methodeDb qui contient les méthodes d'interactions avec la base de données
        MethodDb methodDb = new MethodDb();

        IBanqueImp iBanqueImp = new IBanqueImp(methodDb);
        Naming.rebind("banque", iBanqueImp);

        IClientImp iClient = new IClientImp(methodDb);
        Naming.rebind("client", iClient);
    }
}
