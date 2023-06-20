package org.example.Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnexionDb {
    public ConnexionDb(){}
    public Connection connexion() {
        try {
            //charger le pilote JDBC
            Class.forName("com.mysql.jdbc.Driver");
            //Cree une connexion à la base de données
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banqueApp", "root", "");
            return connection;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
