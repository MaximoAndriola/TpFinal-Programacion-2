package app;

import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.clases.GestionJSON.GestionJSON;
import model.clases.Usuario.Usuario;
import path.Utiles;
import java.util.ArrayList;


public class App extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        ArrayList<Usuario> usuarios = GestionJSON.mapeoUsuarios();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(Utiles.LOGIN_VIEW));
        Parent root = loader.load();

        LoginController loginController = loader.getController();
        loginController.setUsuarios(usuarios);

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }
}