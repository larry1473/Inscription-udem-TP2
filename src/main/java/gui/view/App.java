package gui.view;

import java.util.ArrayList;

import gui.controller.GuiController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import server.models.Course;





public class App{

    public static class Gui extends Application{
        GuiController guiController = new GuiController();
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


            window.setTitle("Inscription UdeM");
           

            BorderPane root = new BorderPane();
            VBox leftBox = new VBox();
            HBox buttonBox = new HBox();
            buttonBox.setPadding(new Insets(5, 0, 10, 0));
            // adding elements to the left box
            Label leftBoxLabel = new Label("Liste des cours");
            leftBoxLabel.setPadding(new Insets(15, 0, 5, 140));
            leftBoxLabel.setFont(Font.font("Arial", FontWeight.LIGHT,FontPosture.REGULAR,20));

            table = new TableView();
            table.setEditable(false);
            TableColumn<String,Course> code = new TableColumn("Code");
            code.setCellValueFactory(new PropertyValueFactory<>("code"));
            TableColumn<String,Course> cours = new TableColumn("Cours");
            cours.setCellValueFactory(new PropertyValueFactory<>("name"));
            session = new ChoiceBox();
            session.getItems().addAll("Hiver", "Ete","Automne");

            charger = new Button ("Charger");  
            buttonBox.getChildren().addAll(session,charger);
            buttonBox.setMargin(session, new Insets(10, 40, 10, 40));
            buttonBox.setMargin(charger, new Insets(10, 0, 10, 60));



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
            //root.setBottom(buttonBox);
            Scene scene = new Scene (root , 800, 400);
           
            //root.getChildren().addAll(leftBox,rightBox);
            window.setScene(scene);
            window.show();


            addButtonLoadListener(session, charger, guiController);
            addSendButtonListener(lastNameTextField, firstNameTextField, emailTextField, matriculeTextField, guiController, envoyer, table, session);
         
        }

    /**
     *
     * @param firsTextField
     * @param lastTextField
     * @param emailTextfield
     * @param matriculeTextField
     * @param guiController
     * @param sendButton
     * @param table
     * @param session
     */   
    private void addSendButtonListener(TextField firsTextField,TextField lastTextField,TextField emailTextfield, TextField matriculeTextField,GuiController guiController,Button sendButton,TableView table,ChoiceBox session){

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
           
            public void handle(ActionEvent event) {
               Course cours = (Course)table.getSelectionModel().getSelectedItem();
               if(firsTextField.getText().equals("") && lastNameTextField.getText().equals("")  && emailTextField.getText().equals("")  && matriculeTextField.getText().equals("")) {
                    Alert error = new Alert(AlertType.ERROR);
                    error.setHeaderText("Informations manquantes");
                    error.setContentText("Vous n'avez pas fournit vous informations ");
                    error.showAndWait();
               }
               if(cours != null){
                String courseCode = cours.getCode();

                int flag = guiController.validateEmail(emailTextfield);
                int matFlag = guiController.validateMatricule(matriculeTextField.getText());
                if(flag == 1 ){
                    Alert error = new Alert(AlertType.WARNING);
                    error.setHeaderText(null);
                    error.setContentText("le format de l'email est incorrect");
                    error.showAndWait();
                }
                if(matFlag == 1){
                    Alert error = new Alert(AlertType.WARNING);
                    error.setHeaderText(null);
                    error.setContentText("le matricule doit etre compose de 6 chiffres");
                    error.showAndWait();
                }
                if(firsTextField.getText().equals("") && lastNameTextField.getText().equals("")  && emailTextField.getText().equals("")  && matriculeTextField.getText().equals("")) {
                    Alert error = new Alert(AlertType.ERROR);
                    error.setHeaderText("Informations manquantes");
                    error.setContentText("Vous n'avez pas fournit vous informations ");
                    error.showAndWait();
                }
                else{

                    guiController.envoyer(firsTextField.getText(), lastTextField.getText(), emailTextfield.getText(), matriculeTextField.getText(),courseCode, (String)session.getSelectionModel().getSelectedItem());
                    firsTextField.clear();
                    lastTextField.clear();
                    emailTextfield.clear();
                    matriculeTextField.clear();

                }
             
               
           

               }
               else{

                Alert error = new Alert(AlertType.ERROR);
                error.setHeaderText("Erreur non selection");
                error.setContentText("Vous avez pas selection de cours ");
                error.showAndWait();

               }
               
               
            }

           
        };
        sendButton.setOnAction(event);

    }
    /**
     * this method adds a click event to a button given as an argument
     * @param session the choice box containing the different sessions
     * @param load the button that needs the click event.
     * @param guiController the model controller
     */   
    private void addButtonLoadListener(ChoiceBox session,Button load ,GuiController guiController){

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if(session == null){
                    guiController.charger("Hiver");
                   
                }
                else{
                   
                    ArrayList<Course> course = guiController.charger((String)session.getSelectionModel().getSelectedItem());
                    loadTable(course,table);
                }
            }

           
        };

        load.setOnAction(event);

    }
    /**
     * this methode populates a given table view with data from a array list containing courses
     * @param course the list of courses
     * @param table the table view to be populated
     */
    private void loadTable(ArrayList<Course> course,TableView table) {
        table.getItems().clear();
        for (Course c : course) {
            table.getItems().add(c);
           
        }


    }


    }



 
   

   

    public static void main(String[] args){

        Application.launch(Gui.class);

    }

    
    public void test(){

    }   
    
   
}
