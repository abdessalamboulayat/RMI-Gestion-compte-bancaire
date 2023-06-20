package org.example;

import org.example.Entity.Client;
import org.example.Entity.Compte;
import org.example.Interface.IBanque;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ManagerBanque {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        Scanner clavier = new Scanner(System.in);
        // Acces à l'objet distant banque
        String urlBanque = "rmi://localhost/banque";
        IBanque iBanque = (IBanque) Naming.lookup(urlBanque);
        while (true) {
            // Afficher le menu à l'utilisateur
            System.out.println("- Bienvenue dans votre espace de travail : ");
            System.out.println("- Vous voulez : ");
            System.out.println("1- Créer un compte");
            System.out.println("2- Affecter un compte à un client");
            System.out.println("3- Supprimer un compte");
            // choix du manager de banque
            String choix = clavier.nextLine();
            switch (choix) {
                case "1":
                    System.out.println("1- Création d'un nouveau compte");
                    // nom du nouveau client
                    System.out.println("Nom : ");
                    String nom = clavier.nextLine();
                    // prénom du nouveau client
                    System.out.println("Prenom : ");
                    String prenom = clavier.nextLine();
                    // CNE du nouveau client
                    System.out.println("CNE : ");
                    String cne = clavier.nextLine();
                    // username du nouveau client
                    System.out.println("Username : ");
                    String username = clavier.nextLine();
                    // mot de passe du nouveau client
                    System.out.println("Password : ");
                    String password = clavier.nextLine();
                    // numero du nouveau compte
                    System.out.println("* numero compte : ");
                    int numeroCompte = clavier.nextInt();
                    // solde pour alimenter le nouveau compte
                    System.out.println("* solde : ");
                    double solde = clavier.nextDouble();
                    // instancier un nouveau objet compte
                    Compte compte = new Compte(numeroCompte, solde);
                    // instancier un nouveau client
                    Client client = new Client(username, nom, prenom, cne, password, numeroCompte);
                    // ajouter le nouveau compte
                    iBanque.creeCompte(compte);
                    // ajouter le nouveau client
                    iBanque.ajouterClient(client);
                    break;
                case "2":
                    System.out.println("2- affecter un compte à un client : ");
                    System.out.println("numero du compte : ");
                    int numero = clavier.nextInt();
                    System.out.println("CNE du client : ");
                    String cneClient = clavier.nextLine();
                    // affecter le compte au client
                    iBanque.affecterUnCompteAunClient(numero, cneClient);
                    break;
                case "3":
                    System.out.println("3- supprimer un compte : ");
                    System.out.println("numero du compte : ");
                    numero = clavier.nextInt();
                    // supprimer le compte
                    iBanque.supprimerUnCompte(numero);
                    break;
            }
        }
    }
}
