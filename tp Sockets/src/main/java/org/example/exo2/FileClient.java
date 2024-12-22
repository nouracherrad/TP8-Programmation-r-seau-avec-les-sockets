package org.example.exo2;


import java.io.*;
import java.net.Socket;

public class FileClient {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 1234;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connecté au serveur de fichiers.");
            System.out.println(in.readLine()); // Message de bienvenue du serveur

            System.out.print("Entrez le nom du fichier à demander : ");
            String fileName = consoleInput.readLine();
            out.println(fileName); // Envoi du nom du fichier

            String serverResponse = in.readLine();
            System.out.println("Serveur : " + serverResponse);

            if (serverResponse.contains("Début du transfert")) {
                saveFile(socket, fileName);
                System.out.println("Fichier " + fileName + " téléchargé avec succès.");
            }
        } catch (IOException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }

    private static void saveFile(Socket socket, String fileName) {
        try (InputStream socketInput = socket.getInputStream();
             FileOutputStream fileOutput = new FileOutputStream("Téléchargé_" + fileName)) {

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = socketInput.read(buffer)) != -1) {
                fileOutput.write(buffer, 0, bytesRead);
            }

        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde du fichier : " + e.getMessage());
        }
    }
}
