# TP8-Programmation-r-seau-avec-les-sockets
Programmation réseau avec les sockets
# README

## Exercices : Communication Client-Serveur en Java

### Exercice 1 : Jeu du Nombre Magique

#### Objectif :
Créer une application client-serveur permettant de jouer à un jeu où le client doit deviner un nombre magique généré aléatoirement par le serveur.

#### Fonctionnement :
1. **Serveur** :
   - Génère un nombre magique entre 0 et 100.
   - Accepte une connexion client.
   - Communique avec le client pour lui donner des indices ("Trop bas", "Trop haut") jusqu'à ce qu'il trouve le nombre.

2. **Client** :
   - Se connecte au serveur.
   - Envoie des propositions de nombre jusqu'à trouver le bon.

#### Instructions pour exécuter :
- Lancer d'abord la classe `JeuServeur` pour démarrer le serveur.
- Ensuite, exécuter la classe `JeuClient` pour se connecter au serveur et jouer.

#### Points d'apprentissage :
- Gestion de la communication client-serveur avec des sockets.
- Utilisation de flux d'entrée et de sortie pour échanger des données entre le client et le serveur.

---

### Exercice 2 : Serveur de Fichiers

#### Objectif :
Créer une application client-serveur permettant à plusieurs clients de demander des fichiers spécifiques au serveur.

#### Fonctionnement :
1. **Serveur** :
   - Écoute les connexions entrantes sur un port dédié (1234).
   - Gère chaque client dans un thread séparé.
   - Vérifie l'existence des fichiers demandés et les envoie au client si disponibles.

2. **Client** :
   - Se connecte au serveur.
   - Envoie le nom d'un fichier au serveur.
   - Reçoit le fichier si disponible, ou un message d'erreur si le fichier est introuvable.

#### Instructions pour exécuter :
- Lancer la classe `FileServer` pour démarrer le serveur.
- Ensuite, exécuter plusieurs instances de la classe `FileClient` pour demander des fichiers.
- Assurez-vous que les fichiers demandés existent dans le répertoire du serveur.

#### Points d'apprentissage :
- Implémentation d'un serveur multi-clients à l'aide de threads.
- Gestion des fichiers en Java (lecture et écriture).
- Transmission de fichiers via des flux réseau.

---

### Dépendances et Configuration :
- Java 8 ou version ultérieure.
- Aucun framework ou bibliothèque externe requis.

---

### Structure des fichiers :
- **Exercice 1** :
  - `JeuServeur.java`
  - `JeuClient.java`

- **Exercice 2** :
  - `FileServer.java`
  - `FileClient.java`

---

### Instructions supplémentaires :
- Pour l'Exercice 2, le fichier téléchargé sera sauvegardé sous le nom `Téléchargé_<nom_du_fichier>` dans le répertoire du client.
- En cas d'erreur ou de fichiers introuvables, vérifiez les logs de la console pour plus de détails.
