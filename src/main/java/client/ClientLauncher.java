package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
/**
 * @author 
 */
public class ClientLauncher {

    public static void main(String[] args){
        try {
         new Client(new Socket("localhost",1337)).requestCourses("Hiver");
     } 
     catch (UnknownHostException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
     } 
     catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
     }
     }
    
}
