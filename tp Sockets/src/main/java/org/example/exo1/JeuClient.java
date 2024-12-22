package org.example.exo1;


import java.io.*;
import java.net.*;
import java.util.Scanner;

public class JeuClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8080)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            System.out.println("Connexion au serveur réussie !");
            System.out.println(reader.readLine()); // Message de bienvenue

            String tentative;
            while (true) {
                System.out.print("Entrez un nombre : ");
                tentative = scanner.nextLine();
                writer.println(tentative); // Envoi de la tentative

                String response = reader.readLine(); // Réception de la réponse
                System.out.println("Serveur : " + response);

                if (response.contains("Félicitations")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }}


