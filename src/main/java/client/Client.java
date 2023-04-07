package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import server.Server;
import server.models.Course;
import server.models.RegistrationForm;
/**
 * @author Larry fotso, Reine wendkuni
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
     * the method sends a request to the server to get a list of courses of a specific session.
     * @param arg the session we want to get a list of courses
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




    /**
     * This method send a request to the server to register a student.
     * @param arg the session we want our student.
     * @return return a message to say if the student was successfully registered or not.
     */
    public String Register(String arg) {
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
            
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(Server.REGISTER_COMMAND+ " " +arg);
            Course cours = new Course(this.getNameFromCourseCode(courseCode),courseCode,this.getSessionFromCourseCode(courseCode));
            RegistrationForm resg = new RegistrationForm(firstName,lastName,email,matricule,cours);
            objectOutputStream.writeObject(resg);
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            this.ready = (String)objectInputStream.readObject();
            if(ready != null){
                return "felicitation ! Inscription reussi " + firstName + " au cours de  " + courseCode;

            }
            else{
                return " Echec d'Inscription de " + firstName + " au cours de " + courseCode;
            }
            

        } catch (IOException | ClassNotFoundException   e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.disconnect();
        return null;
    }

    public String Register(String firstName,String lastName,String email,String matricule,String courseCode,String arg){
        try {
            
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(Server.REGISTER_COMMAND+ " " +arg);
            Course cours = new Course(this.getNameFromCourseCode(courseCode),courseCode,this.getSessionFromCourseCode(courseCode));
            RegistrationForm resg = new RegistrationForm(firstName,lastName,email,matricule,cours);
            objectOutputStream.writeObject(resg);
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            this.ready = (String)objectInputStream.readObject();
            if(ready != null){
                return "felicitation ! Inscription reussi " + firstName + " au cours de  " + courseCode;

            }
            else{
                return " Echec d'Inscription de " + firstName + " au cours de " + courseCode;
            }
            

        } catch (IOException | ClassNotFoundException   e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.disconnect();
        return null;


    }

    /**
     * this gets a course code and returns a name  if present in the list of code 
     * @param courseCode the course code we want to check if it exists in the list of course.
     * @return returns a name if it exists in the list  else null;
     */
    private  String getNameFromCourseCode(String courseCode){
        String[] name = new String[1]; 
        courses.forEach(obj ->{
            if(obj.getCode().toLowerCase().equals(courseCode.toLowerCase())){
                name[0] = obj.getName();
            }
        });
        return name[0];
    }

    /**
     * this gets a course code and returns an equivalent course code if present in the list of code 
     * @param courseCode the course code we want to check if it exists in the list of course.
     * @return course code if it exists in the list of course else it returns null.
     */
    private  String getSessionFromCourseCode(String courseCode){

        
        String[] session = new String[1]; 
        courses.forEach(obj ->{
            if(obj.getCode().toLowerCase().equals(courseCode.toLowerCase())){
                session[0] = obj.getSession();
            }
        });
        return session[0];
    }

    /**
     * returns a list of courses that
     * @return return the client's list of courses
     */
    public ArrayList<Course> getCourses() {
        return courses;
    }

    /**
     * Gives a list of courses to the client 
     * @param courses list of courses
     */
    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    /**
     * this method close all the streams
     */
    private void disconnect() {
        try {
            //objectInputStream.close();
            objectOutputStream.close();
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }




    

    
}
