package gui.controller;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import client.Client;
import javafx.scene.control.TextField;
import server.models.Course;

public class GuiController {

    private Client client;

    public GuiController(){
    

    }
    /**
     * this method sends a message to client to send a request to the server
     * foe the list of courses of  specific session
     * @param arg the wanted session
     * @return return the list of courses
     */
    public ArrayList<Course> charger(String arg){
        
            try {
                Client cl = new Client(new Socket("localhost",1337));
                this.client = cl;
                 cl.requestCourses(arg);
                 return cl.getCourses();
            } 
            catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 
        return null;

    }

    /**
     * this method sends a message to client to send a request to the server to register a new student
     * @param firstName the student's first name
     * @param lastName the student's last name
     * @param email the student's email address
     * @param matricule the student's matricule
     * @param courseCode the student's course code
     * @param arg the wanted session
     */
    public void envoyer(String firstName,String lastName,String email,String matricule,String courseCode,String arg){
           try {
            Client  cl = new Client(new Socket("localhost",1337));
            cl.setCourses(this.client.getCourses());
            cl.Register(firstName, lastName, email, matricule, courseCode, arg);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



    }

    /**
     * this method check if a given email address is of the valid format
     * @param em the email address we want to check
     * @return 0 if the email address format is valid else 1
     */
    public int validateEmail(TextField em){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-z]+)+");
        Matcher m = pattern.matcher(em.getText());
        if(m.find() && m.group().equals(em.getText())){
            return 0;
        }
        else{
            return 1;
        }

    }

    /**
     * this method checks if a given matricul is correct 
     * @param matricule the matricule to check
     * @return 0 if its correct else 1
     */
    public int validateMatricule(String matricule){
        if(matricule.length() < 6 || matricule.length() > 6){

            return 1;
        }
        return 0;

    }

    
}
