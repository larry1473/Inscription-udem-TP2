package gui.view;

import com.sun.prism.paint.Color;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;





public class App{

    public static class Gui extends Application{
        Button envoyer;
        Button charger;
        TableView table;
        ChoiceBox session;
        TextField  firstNameTextField;
        TextField lastNameTextField;
        TextField emailTextField;
        TextField matriculeTextField;
       


           
        @Override
        public void start(Stage window) throws Exception {


            //Parent root;
            // Scene scene = new Scene ( root, 1200 ,800);
            window.setTitle("Inscription UdeM");
           

            BorderPane root = new BorderPane();
            VBox leftBox = new VBox();
            HBox buttonBox = new HBox();
            buttonBox.setPadding(new Insets(5, 0, 10, 0));
            buttonBox.setSpacing(70);
            buttonBox.setPadding(new Insets(10, 0, 5, 20));
            // adding elements to the left box
            Label leftBoxLabel = new Label("Liste des cours");
            leftBoxLabel.setPadding(new Insets(15, 0, 5, 140));
            leftBoxLabel.setFont(Font.font("Arial", FontWeight.LIGHT,FontPosture.REGULAR,20));

            table = new TableView();
            table.setEditable(false);
            TableColumn code = new TableColumn("Code");
            TableColumn cours = new TableColumn("Cours");
            session = new ChoiceBox();
            session.getItems().addAll("Hiver", "Ete","Automne");
            

            charger = new Button ("Charger");  
            buttonBox.getChildren().addAll(session,charger);
            



            table.getColumns().addAll(code, cours);
            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            leftBox.getChildren().addAll(leftBoxLabel,table,buttonBox);
           
           
            VBox rightBox = new VBox();
            HBox lastNameBox = new HBox();
            lastNameBox.setPadding(new Insets(0,0,5,25));
            HBox firstNameBox = new HBox();
            firstNameBox.setPadding(new Insets(0,0,5,25));
            HBox emailBox = new HBox();
            emailBox.setPadding(new Insets(0, 0, 5, 25));
            HBox matriculeBox = new HBox();
            matriculeBox.setPadding(new Insets(0, 0, 5, 25));

            Label rightLabel = new Label("Formulaire d'inscription");
            rightLabel.setPadding(new Insets(15, 0, 40, 100));
            rightLabel.setFont(Font.font("Arial", FontWeight.LIGHT,FontPosture.REGULAR,20));

            Label firstNameLabel = new Label("Prénom");
            firstNameTextField = new TextField();
            firstNameLabel.setPadding(new Insets(0, 25, 0, 0));
            firstNameBox.getChildren().addAll(firstNameLabel,firstNameTextField);

            Label lastNameLabel = new Label("Nom");
            lastNameTextField = new TextField();
            lastNameLabel.setPadding(new Insets(0, 41, 0, 0));
            lastNameBox.getChildren().addAll(lastNameLabel,lastNameTextField);

            Label emailLabel = new Label("Email");
            emailTextField = new TextField();
            emailBox.getChildren().addAll(emailLabel,emailTextField);
            emailLabel.setPadding(new Insets(0, 40, 0, 0));


            Label matriculeLabel = new Label("Matricule");
            matriculeTextField = new TextField();
            matriculeBox.getChildren().addAll(matriculeLabel,matriculeTextField);
            matriculeLabel.setPadding(new Insets(0, 20, 0, 0));

            HBox btnBox = new HBox();
            envoyer = new Button("envoyer");
            envoyer.setPadding(new Insets(4, 15, 4, 15));
            btnBox.getChildren().add(envoyer);
            btnBox.setPadding(new Insets(15, 0, 0, 135));
            //envoyer.setPadding(new Insets(0, 0, 0, 100));


            leftBox.setStyle("-fx-background-color: beige");
            leftBox.setPrefSize(395, 400);
            leftBox.setPadding(new Insets(0, 10, 0, 10));

            rightBox.setStyle("-fx-background-color: beige");
            rightBox.setPadding(new Insets(0, 10, 0, 0));
            rightBox.setPrefSize(395, 400);

           
            rightBox.getChildren().addAll(rightLabel,firstNameBox,lastNameBox,emailBox,matriculeBox, btnBox);
            //root.setStyle("-fx-background-color: Black");
            root.setLeft(leftBox);
            root.setRight(rightBox);
            Scene scene = new Scene (root , 800, 400);
           
            //root.getChildren().addAll(leftBox,rightBox);
            window.setScene(scene);
            window.show();
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

             
             //rightBox.setStyle("-fx-background: red");
             //Label rightBoxLabel = new Label("Formulaire d'inscription");
             //HBox prenomBox = new HBox ();
             //HBox nomBox = new HBox();
             //HBox emailBox = new HBox();
             //HBox matriculeBox = new HBox();
             //Label labelPrenom = new Label("Prénom");
             //TextField textFieldPrenom = new TextField();
             //Label labelNom = new Label ("Nom");
             //TextField textFieldNom = new TextField();
             //Label labelEmail= new Label("Email");
             //TextField textFieldEmail = new TextField();
             //Label labelMatricule = new Label ("Maticule");
             //TextField textFieldMAtricule = new TextField();
             //Button FormButton = new Button("Envoyer");
             //prenomBox.getChildren().addAll(labelPrenom,textFieldPrenom);
             //nomBox.getChildren().addAll(labelNom,textFieldNom);
             //emailBox.getChildren().addAll(labelEmail,textFieldEmail);
             //matriculeBox.getChildren().addAll(labelMatricule,textFieldMAtricule);
             //rightBox.getChildren().addAll(rightBoxLabel, prenomBox, nomBox,emailBox,matriculeBox,FormButton);


             //rightBox.getChildren().addAll(null)


           
           

            //borderPaneRoot.setRight(rightBox);
        }

    }
   

   

    public static void main(String[] args){

        Application.launch(Gui.class);

    }

    public static void test(){
       
    }
   
}