package server;

import javafx.util.Pair;
import server.models.Course;
import server.models.RegistrationForm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Cette classe a pour role de faire office de serveur.
 * Donc permettant la gestion des cours et des inscriptions.
 *
 */
public class Server {

    public final static String REGISTER_COMMAND = "INSCRIRE";
    public final static String LOAD_COMMAND = "CHARGER";
    private final ServerSocket server;
    private Socket client;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private final ArrayList<EventHandler> handlers;

    public Server(int port) throws IOException {
        this.server = new ServerSocket(port, 1);
        this.handlers = new ArrayList<EventHandler>();
        this.addEventHandler(this::handleEvents);
    }

    /**
     * Cette methode prend en parametre un objet de type EventHandler qui sera ajouté à la liste
     * @param h une request de type EventHandler
     */
    public void addEventHandler(EventHandler h) {
        this.handlers.add(h);
    }

    /**
     * cette methode premette d'envoyer des alert lorsqu'une requête est envoyée par le client.
     * @param cmd la commandes a executer par le serveur
     * @param arg la session voulue par le client.
     */
    private void alertHandlers(String cmd, String arg) {
        for (EventHandler h : this.handlers) {
            h.handle(cmd, arg);
        }
    }

    /**
     * Cette méthode permet de lancer le serveur.
     * Elle permet au client de ce connecter au serveur, et ecoute les eventuels requete du client.
     * si un requete est emisse par le client elle effectue ce que le client demande.
     * Les requetes eventuels pouvant emit par le clients sont chargés, pour charger la liste des cours en fonction
     * d'une session, et les envoyer au client et aussi l'inscription d'un etudiant a une session.
     * @throws IOException une erreur s'est produite si les streams sont indisponibles.
     */
    public void run() {
        while (true) {
            try {
                client = server.accept();
                System.out.println("Connecté au client: " + client);
                objectInputStream = new ObjectInputStream(client.getInputStream());
                objectOutputStream = new ObjectOutputStream(client.getOutputStream());
                listen();
                System.out.print("not Listening");
                disconnect();
                System.out.println("Client déconnecté!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Cette permet d'ecouter les requete emits par le client au serveur.
     * Apres l'ecoute de la requete elle appelle les methodes neccesaires pour gerer les requetes.
     * @throws IOException cette erreur s'est produite si aucuns object n'est disposible sur le stream.
     * @throws ClassNotFoundException cette erreur s'est produite si la classe voulue n'existe pas.
     */
    public void listen() throws IOException, ClassNotFoundException {
        String line;
        if ((line = this.objectInputStream.readObject().toString()) != null) {
            Pair<String, String> parts = processCommandLine(line);
            String cmd = parts.getKey();
            String arg = parts.getValue();
            this.alertHandlers(cmd, arg);
        }
    }

    /**
     * Cette methode prend une chaine de caratere et a divise dans la forme appropriate 
     * et la retourne dans un HashMap.
     * @return retourne un HashMap contenant la chaine de caratere et la valeur de la
     */
    public Pair<String, String> processCommandLine(String line) {
        String[] parts = line.split(" ");
        String cmd = parts[0];
        String args = String.join(" ", Arrays.asList(parts).subList(1, parts.length));
        return new Pair<>(cmd, args);
    }

    /**
     * Cette methode gere la deconnexion du client et des stream entrant et sortant.
     * @throws IOException Gerer une erreur s'est produite si les streams sont indisponibles.
     */
    public void disconnect() throws IOException {
        objectOutputStream.close();
        objectInputStream.close();
        client.close();
    }

    /**
     * * Cette methode prend en parametre des chaine de caractere et permet de gerer les requete envoyer
     * par le client, soit les inscription d'un etudiant a une session ou encore pour ou encore charger la liste des cours.
     * 
     * @param cmd la commandes a executer par le serveur
     * @param arg la session voulue par le client.
     */
    public void handleEvents(String cmd, String arg) {
        if (cmd.equals(REGISTER_COMMAND)) {
                try {
                    handleRegistration();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    System.out.println("unhandle Registration");
                    e.printStackTrace();
                }   
        } else if (cmd.equals(LOAD_COMMAND)) {
            handleLoadCourses(arg);
        }
    }

    /**
     Lire un fichier texte contenant des informations sur les cours et les transformer en liste d'objets 'Course'.
     La méthode filtre les cours par la session spécifiée en argument.
     Ensuite, elle renvoie la liste des cours pour une session au client en utilisant l'objet 'objectOutputStream'.
     @param arg la session pour laquelle on veut récupérer la liste des cours
     @throws Exception si une erreur se produit lors de la lecture du fichier ou de l'écriture de l'objet dans le flux
     */
    public void handleLoadCourses(String arg) {
        // la liste a retourner avec le objectOutputStream.
        List<Course> courses = new ArrayList<>();
        // lecture et creation de la list de cours en fonction de la session.
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("src/main/java/server/data/cours.txt")));
            String line;
             while((line = br.readLine()) != null){
                System.out.println("in");
                 String[] tab = line.split("\t");
                 if(tab[tab.length-1].equals(arg)){
                     courses.add(new Course(tab[0], tab[1], tab[2]));
                     
                }
                
            }
            br.close();
             
 
         } catch (IOException e) {
             // TODO Auto-generated catch block
             System.out.println("le fichier n'as pas ete trouve");
             //e.printStackTrace();
         }
        // passage de la liste au objectOutputStream.
        try {
            
            System.out.println(courses.toString());
            objectOutputStream.writeObject(courses);
            objectOutputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("une erreur cest produit en ecrivant l'objet");
            e.printStackTrace();
        }

    }

    /**
     Récupérer l'objet 'RegistrationForm' envoyé par le client en utilisant 'objectInputStream', l'enregistrer dans un fichier texte
     et renvoyer un message de confirmation au client.
     * @throws IOException
     @throws Exception si une erreur se produit lors de la lecture de l'objet, l'écriture dans un fichier ou dans le flux de sortie.
     */
    public void handleRegistration() throws IOException {
        RegistrationForm registrationForm;
        try {
            registrationForm = (RegistrationForm)objectInputStream.readObject();
            PrintWriter fich = new PrintWriter(new BufferedWriter(new FileWriter( "src/main/java/server/data/inscription.txt", true))); //true c'est elle qui permet d'écrire à la suite des donnée enregistrer et non de les remplacé
            fich.println(registrationForm.toString());
            fich.close();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
    }

    public static void main(String[] args) {
        try {
            RegistrationForm rf = new RegistrationForm ("t", "g" , "reineguinguiere@yahoo.fr", "202312", new Course ("Prog1" , "IFT1015", "ete") );
            PrintWriter fich = new PrintWriter(new BufferedWriter(new FileWriter( "src/main/java/server/data/inscription.txt", true))); //true c'est elle qui permet d'écrire à la suite des donnée enregistrer et non de les remplacé
            fich.println("");
            fich.println(rf.toString() );

            fich.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
            
        
        }
      }

    
    
    // Test push


  

   
    

