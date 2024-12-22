package org.example.exo1;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class JeuServeur {
    public static void main(String[] args) {
        int nombreMagique = (int) (Math.random() * 101); // Génération du nombre magique
        System.out.println("Le nombre magique est : " + nombreMagique); // Debug

        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Serveur en attente de connexions sur le port 8080...");

            Socket socket = serverSocket.accept(); // Accepter un client
            System.out.println("Connexion acceptée avec le client : " + socket.getInetAddress());

            try (
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)
            ) {
                writer.println("Bienvenue ! Essayez de deviner le nombre magique (entre 0 et 100).");

                String tentative;
                while ((tentative = reader.readLine()) != null) {
                    try {
                        int nombreDevine = Integer.parseInt(tentative);
                        if (nombreDevine < nombreMagique) {
                            writer.println("Trop bas !");
                        } else if (nombreDevine > nombreMagique) {
                            writer.println("Trop haut !");
                        } else {
                            writer.println("Félicitations ! Vous avez trouvé le nombre magique.");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        writer.println("Veuillez entrer un nombre entier valide.");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
