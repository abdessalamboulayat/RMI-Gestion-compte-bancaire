package org.example;

import org.example.Entity.Client;
import org.example.Entity.Compte;
import org.example.Entity.HistoriqueOperation;
import org.example.Interface.IBanque;
import org.example.Interface.IClient;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        Scanner clavier = new Scanner(System.in);
        // Acces à l'objet distant banque
        String urlBanque = "rmi://localhost/banque";
        IBanque iBanque = (IBanque) Naming.lookup(urlBanque);
        // Acces à l'objet distant client
        String urlClient = "rmi://localhost/client";
        IClient iClient = (IClient) Naming.lookup(urlClient);
        boolean isConnected = false;
        Client client = null;
        while (client == null){
            // connexion du client
            System.out.println("Username : ");
            String username = clavier.nextLine();
            System.out.println("Password : ");
            String password = clavier.nextLine();
            client = iClient.connexion(username,password);

            // si les informations ne sont pas correctes
            if(client == null){
                System.out.println("Veuillez vérifier vos informations et réessayez?");
            }
            isConnected=true;
        }
        // si l'utilisateur est connecté
        while (isConnected) {
            // Afficher le menu au client
            System.out.println("- Bienvenue dans votre compte : ");
            System.out.println("- Vous voulez : ");
            System.out.println("1- Consulter mon solde ");
            System.out.println("2- déposer l'argent ");
            System.out.println("3- retirer l'argent ");
            System.out.println("4- Verement d'argent à un autre compte ");
            System.out.println("5- Consulter l'historique d'opérations ");
            System.out.println("Votre choix : ");
            // choix du client
            String choix = clavier.nextLine();
            switch (choix) {
                case "1":
                    System.out.println("1- Consulter mon solde");
                    double solde = iBanque.recupererSolde(client.getNumeroCompte());
                    System.out.println("-------------");
                    System.out.println(" -- Votre solde : "+ solde);
                    System.out.println("-------------");
                    break;
                case "2":
                    System.out.println("2- déposer l'argent");
                    System.out.println("Veuillez saisir le montant : ");
                    // le montant que veut le client déposer
                    double montantDeposer = clavier.nextDouble();
                    iBanque.deposer(client.getNumeroCompte(),montantDeposer);
                    //
                    System.out.println(" l'id du client : "+client.getIdClient());
                    // instancier un objez historiqueOperation
                    HistoriqueOperation historiqueOperation = new HistoriqueOperation("Depot",montantDeposer, LocalDate.now(),client.getIdClient());
                    iBanque.ajouterOperation(historiqueOperation);
                    break;
                case "3":
                    System.out.println("3- retirer l'argent");
                    System.out.println("Veuillez saisir le montant");
                    // le montant que veut le client retirer
                    double montantRetirer = clavier.nextDouble();
                    iBanque.retirer(client.getNumeroCompte(),montantRetirer);
                    // instancier un objez historiqueOperation
                    historiqueOperation = new HistoriqueOperation("Depot",montantRetirer, LocalDate.now(),client.getIdClient());
                    iBanque.ajouterOperation(historiqueOperation);
                    break;
                case "4":
                    System.out.println("4- Verement d'argent à un autre compte");
                    System.out.println("Veuillez saisir le numero du compte");
                    // le numero de compte
                    int numeroCompte = clavier.nextInt();
                    System.out.println("Veuillez saisir le montant");
                    // le montant que le client veut verser
                    double montant = clavier.nextDouble();
                    iClient.verserArgent(numeroCompte, montant);
                    // instancier un objez historiqueOperation
                    historiqueOperation = new HistoriqueOperation("Depot",montant, LocalDate.now(),client.getIdClient());
                    iBanque.ajouterOperation(historiqueOperation);
                    break;
                case "5":
                    List<HistoriqueOperation> historiqueOperations = new ArrayList<>();
                    historiqueOperations = iClient.consulterOperations(client.getIdClient());
                    System.out.println("-------------------");
                    for (int i=0; i<historiqueOperations.size(); i++){
                        System.out.println("- operation : "+historiqueOperations.get(i).getOperation());
                        System.out.println("- montant : "+historiqueOperations.get(i).getMontant());
                        System.out.println("- date d'operation : "+historiqueOperations.get(i).getDate());
                        System.out.println("===================");
                    }
                    System.out.println("-------------------");
                    break;
                default:
                    break;
            }
        }
    }
}