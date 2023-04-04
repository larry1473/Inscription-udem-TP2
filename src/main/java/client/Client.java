package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Scanner;

import server.Server;
import server.models.Course;
import server.models.RegistrationForm;
/**
 * @author
 */
public class Client {
    
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private  Socket socket;
    private ArrayList<Course> courses;
    public  String ready;

    public Client(Socket socket){
        this.socket = socket;
        
    
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
                int[] tab = {1};
                this.courses = (ArrayList<Course>) objectInputStream.readObject();
                this.courses.forEach(obj ->{
                    
                    System.out.println(tab[0]++ + ". "  + obj.getCode() + " " + obj.getName());
                    

                });
            }
            this.disconnect();
            
        } catch (IOException | ClassNotFoundException  e) {
             System.out.println("probleme avec le serveur or the list of courses was not found");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }




    public void Register(String arg) {
        String firstName = null;
        String lastName = null;
        String email = null;
        String matricule = null;
        String courseCode = null;
        while( firstName == null || lastName == null || email == null || matricule == null || courseCode == null){

            String resMessage;
            
            
            if(firstName == null ){
                resMessage = "Veuillez saisir votre prenom: ";
                System.out.print(resMessage);
                Scanner s = new Scanner(System.in);
                firstName  = s.next();
                
                
               
            }
            if(lastName == null ){
                resMessage = "Veuillez saisir votre nom: ";
                System.out.print(resMessage);
                Scanner s = new Scanner(System.in);
                lastName  = s.next();
                
               
            }
            if(email == null ){
                resMessage = "Veuillez saisir votre email: ";
                System.out.print(resMessage);
                Scanner s = new Scanner(System.in);
                email  = s.next();
                
               
            }
            if(matricule == null ){
                resMessage = "Veuillez saisir votre matricule: ";
                System.out.print(resMessage);
                Scanner s = new Scanner(System.in);
                matricule  = s.next();
                
               
            }
            if(courseCode == null ){
                resMessage = "Veuillez saisir le code du cours: ";
                System.out.print(resMessage);                
                Scanner s= new Scanner(System.in);
                courseCode  = s.next();
                
               
            }
            

        }
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(Server.REGISTER_COMMAND+ " " +arg);
            Course cours = new Course(this.getNameFromCourseCode(courseCode),courseCode,this.getSessionFromCourseCode(courseCode));
            RegistrationForm resg = new RegistrationForm(firstName,lastName,email,matricule,cours);
            objectOutputStream.writeObject(resg);
            String recievedMessage = (String) objectInputStream.readObject();
            

        } catch (IOException | ClassNotFoundException   e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.disconnect();
    }

    private  String getNameFromCourseCode(String courseCode){
        String[] name = new String[1]; 
        courses.forEach(obj ->{
            if(obj.getCode().toLowerCase().equals(courseCode.toLowerCase())){
                name[0] = obj.getName();
            }
        });
        return name[0];
    }

    private  String getSessionFromCourseCode(String courseCode){
        String[] session = new String[1]; 
        courses.forEach(obj ->{
            if(obj.getCode().toLowerCase().equals(courseCode.toLowerCase())){
                session[0] = obj.getSession();
            }
        });
        return session[0];
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    private void disconnect() {
        try {
            objectInputStream.close();
            objectInputStream.close();
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }




    

    
}
