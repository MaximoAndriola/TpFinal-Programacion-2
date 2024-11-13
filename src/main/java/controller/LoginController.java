package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.clases.Usuario.Usuario;
import path.Utiles;

import java.util.ArrayList;

public class LoginController {
    private ArrayList<Usuario> usuarios;

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @FXML
    private Button buttonIniciarSesion;

    @FXML
    private Button buttonRegistrarse;

    @FXML
    void iniciarSesion(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Utiles.INICIAR_SESION_VIEW));
        Parent root = loader.load();

        IniciarSesionController iniciarSesionController = loader.getController();
        iniciarSesionController.setUsuarios(usuarios);

        Stage actualStage = (Stage) buttonIniciarSesion.getScene().getWindow();
        actualStage.setScene(new Scene(root));
    }

    @FXML
    void registrarse(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Utiles.REGISTRARSE_VIEW));
        Parent root = loader.load();

        RegistrarseController registrarseController = loader.getController();
        registrarseController.setUsuarios(usuarios);

        Stage actualStage = (Stage) buttonRegistrarse.getScene().getWindow();
        actualStage.setScene(new Scene(root));
    }
}
