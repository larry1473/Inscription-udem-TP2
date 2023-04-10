package gui.view;

import com.sun.prism.paint.Color;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;

import javafx.stage.Stage;





public class App{

    public static class Gui extends Application{


            
        @Override
        public void start(Stage window) throws Exception {
            //Parent root;
            // Scene scene = new Scene ( root, 1200 ,800);
            window.setTitle("Inscription UdeM");
            BorderPane borderPaneRoot = new BorderPane();
            Scene scene = new Scene (borderPaneRoot , 800, 400);
            //window.setScene(scene);
            
            VBox leftBox = new VBox();
            
            leftBox.setStyle("-fx-background: red");
            /*scene.setFill(Color.web("#FFFFF"));
            HBox buttonBox = new HBox();
            Label leftBoxLabel = new Label("Liste des cours");
            Button lefButton = new Button("Code");
            Button rightButton = new Button("Cours");
            TextArea coursesArea = new TextArea();
            buttonBox.getChildren().addAll(lefButton,rightButton);
            //coursesArea.getChildrenUnmodifiable().addAll(buttonBox);
            leftBox.getChildren().addAll(leftBoxLabel,buttonBox,coursesArea);*/
            
            
             // Creation du formulaire  pour inscription UdeM

             VBox rightBox = new VBox();
             rightBox.setStyle("-fx-background: red");
             Label rightBoxLabel = new Label("Formulaire d'inscription");
             HBox prenomBox = new HBox ();
             HBox nomBox = new HBox();
             HBox emailBox = new HBox();
             HBox matriculeBox = new HBox();
             Label labelPrenom = new Label("Prénom");
             TextField textFieldPrenom = new TextField();
             Label labelNom = new Label ("Nom");
             TextField textFieldNom = new TextField();
             Label labelEmail= new Label("Email");
             TextField textFieldEmail = new TextField();
             Label labelMatricule = new Label ("Maticule");
             TextField textFieldMAtricule = new TextField();
             Button FormButton = new Button("Envoyer");
             prenomBox.getChildren().addAll(labelPrenom,textFieldPrenom);
             nomBox.getChildren().addAll(labelNom,textFieldNom);
             emailBox.getChildren().addAll(labelEmail,textFieldEmail);
             matriculeBox.getChildren().addAll(labelMatricule,textFieldMAtricule);
             rightBox.getChildren().addAll(rightBoxLabel, prenomBox, nomBox,emailBox,matriculeBox,FormButton);


             //rightBox.getChildren().addAll(null)


            
            borderPaneRoot.setLeft(leftBox);
            borderPaneRoot.setRight(rightBox);
            window.setScene(scene);
            window.show();

            //borderPaneRoot.setRight(rightBox);
        }

    }
    

   

    public static void main(String[] args){

        Application.launch(Gui.class);

    }

    public static void test(){
        
    }
    
}

// test