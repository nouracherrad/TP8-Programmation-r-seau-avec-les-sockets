package org.example.exo2;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {

    private static final int PORT = 1234;

    public static void main(String[] args) {
        System.out.println("Démarrage du serveur de fichiers...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Serveur en écoute sur le port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept(); // Acceptation d'une nouvelle connexion
                System.out.println("Nouvelle connexion : " + clientSocket.getInetAddress());

                // Lancement d'un thread pour gérer le client
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Gestion du client dans un thread séparé
    private static class ClientHandler extends Thread {
        private final Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                out.println("Bienvenue sur le serveur de fichiers ! Entrez le nom du fichier à télécharger :");

                String fileName = in.readLine(); // Lecture du nom du fichier demandé
                System.out.println("Client demande le fichier : " + fileName);

                File file = new File(fileName);
                if (file.exists() && file.isFile()) {
                    out.println("Fichier trouvé. Début du transfert...");
                    sendFile(file, clientSocket);
                    out.println("Transfert terminé.");
                } else {
                    out.println("Le fichier \"" + fileName + "\" n'existe pas.");
                }

            } catch (IOException e) {
                System.err.println("Erreur lors de la communication avec le client : " + e.getMessage());
            }
        }

        private void sendFile(File file, Socket socket) {
            try (BufferedInputStream fileInput = new BufferedInputStream(new FileInputStream(file));
                 OutputStream socketOutput = socket.getOutputStream()) {

                byte[] buffer = new byte[4096];
                int bytesRead;

                while ((bytesRead = fileInput.read(buffer)) != -1) {
                    socketOutput.write(buffer, 0, bytesRead);
                }
                socketOutput.flush();

            } catch (IOException e) {
                System.err.println("Erreur lors de l'envoi du fichier : " + e.getMessage());
            }
        }
    }
}