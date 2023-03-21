package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import server.Server;
import server.models.Course;
/**
 * @author
 */
public class Client {
    
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    public static Socket socket;

    public Client(Socket socket){
        
        Client.socket = socket;
    }

 

    
    /**
     * Cette mmethode est utilisée par le client pour demander au serveur la liste des courses disponibles
     * @param arg the session 
     */
    public void requestCourses(String arg){
        try {
            // Envoie la regret de chargement au serveur 
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(Server.LOAD_COMMAND + " " +arg);

            // Reception de la liste du serveur.
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            if(objectInputStream != null){
                ArrayList<Course> courses = (ArrayList<Course>) objectInputStream.readObject();
                courses.forEach(System.out::println);
            }
            
        } catch (IOException | ClassNotFoundException  e) {
             System.out.println("probleme avec le serveur or the list of courses was not found");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    

    
}
