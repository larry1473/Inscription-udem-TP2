package gui.controller;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import client.Client;
import javafx.scene.control.TextField;
import server.models.Course;

public class GuiController {

    private Client client;

    public GuiController(){
        try {
            client = new Client(new Socket("localhost",1337));
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public ArrayList<Course> charger(String arg){
        if(this.client != null){
            client.requestCourses(arg);
            return this.client.getCourses();
        }
        return null;

    }


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

    public int validateEmail(TextField emailTextfield) {
        return 0;
    }

    public int validateMatricule(String text) {
        return 0;
    }

    test

    
}
