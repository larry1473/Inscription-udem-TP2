package gui.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {
    

    @Override
    public void start(Stage arg0) throws Exception {
        String javaVersion = System.getProperty("java.version");
        String javaFxVersion = System.getProperty("javafx.version");
        Label label = new Label("Hello World, " + javaFxVersion + "." + javaFxVersion);
        Scene scene = new Scene(new StackPane(),640,480);
        arg0.setScene(scene);
        arg0.show();
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }

    public static void main(String[] args){

        launch();

    }
    
}
