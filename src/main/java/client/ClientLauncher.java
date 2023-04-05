package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
/**
 * @author 
 */
public class ClientLauncher {

    private static Client client;

    /**
     * This method creates the terminal interface to interact with the user.
     * It displays the welcome message and and calls the client's methode to answer to user's request.
     * @throws UnknownHostException thrown if the ip address of the host could not be determined.
     * @throws IOException Signals that an I/O exception of some sort has occurred
     */
    private static void displayMessage() throws UnknownHostException, IOException {
        String resMessage = "*** Bienvenue au portail d'inscription de cours de l'UDEM ***\n"+
                            "veuillez choisir la session pour laquelle vous voulez consulter la liste des cours de l'UDEM\n"+
                            " 1.Automne\n 2.Hiver\n 3.Ete\n";

        System.out.println(resMessage);
        Scanner s = new Scanner(System.in);
         client  =  new Client(new Socket("localhost",1337));
        int num = s.nextInt();
        if(num == 1){
            System.out.println("> Choix: 1 \n les cours offerts pendant la session d'automne sont:");
            client.requestCourses("Automne");
            reDisplay("Automne");

        }
        else if(num == 2){
            System.out.println("> Choix: 2 \n les cours offerts pendant la session d'hiver sont:");
            client.requestCourses("Hiver");
            reDisplay("Hiver");
        }
        else if(num == 3){
            System.out.println("> Choix: 3 \n les cours offerts pendant la session d'ete sont:");
            client.requestCourses("Ete");

            reDisplay("Ete");
        }


    }

    /**
     * This method prompts the user to choosd if he wants to register a student or view other courses.
     * @throws UnknownHostException thrown if the ip address of the host could not be determined.
     * @throws IOException Signals that an I/O exception of some sort has occurred
     */
    private static void reDisplay(String cmd) throws UnknownHostException, IOException {
        String resMessage = "> Choix: \n 1.Consulter les cours offerts pour les autre session\n 2.Inscription a un cours ";
        System.out.println(resMessage);
        Scanner s = new Scanner(System.in);
        int num = s.nextInt();
        if(num == 1){
            displayMessage();
        }
        else if(num == 2){
            resMessage = "> choix: 2 \n";
            System.out.println(resMessage);
            Socket soc = new Socket("localhost",1337);
            Client cl =  new Client(soc);
            cl.setCourses(client.getCourses());
            String RegisMsg = cl.Register(cmd);
            System.out.println(RegisMsg);



        }
    }


    public static void main(String[] args){
        try {
            displayMessage();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("the server is not running try again later");
            //e.printStackTrace();
        }
      
     }
    
}
