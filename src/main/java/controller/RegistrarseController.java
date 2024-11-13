package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.clases.GestionJSON.GestionJSON;
import model.clases.Usuario.InicioSesion;
import model.clases.Usuario.Usuario;
import model.exepciones.ContraseniaInvalidaExeption;
import model.exepciones.NombreInvalidoExeption;
import path.Utiles;

import java.util.ArrayList;

public class RegistrarseController {
    private ArrayList<Usuario> usuarios;

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    @FXML
    private Button backButton;

    @FXML
    private Button registrarseButton;

    @FXML
    private Label exeptionRegistroPasword;

    @FXML
    private Label exeptionRegistroUsuario;

    @FXML
    private PasswordField txtIngresarContrasenia;

    @FXML
    private TextField txtIngresarUsuario;

    @FXML
    void registrarse(ActionEvent event) throws Exception{
        try {
            InicioSesion.registrarse(txtIngresarUsuario.getText(), txtIngresarContrasenia.getText(), usuarios);
            exeptionRegistroPasword.setText("");
            exeptionRegistroUsuario.setText("");

            FXMLLoader loader = new FXMLLoader(getClass().getResource(Utiles.LOGIN_VIEW));
            Parent root = loader.load();

            LoginController loginController = loader.getController();
            loginController.setUsuarios(usuarios);

            Stage stage = (Stage) registrarseButton.getScene().getWindow();
            stage.setScene(new Scene(root));

            GestionJSON.cargarArray(usuarios);
        }catch (NombreInvalidoExeption e){
            exeptionRegistroUsuario.setText(e.getMessage());
        }catch (ContraseniaInvalidaExeption e){
            exeptionRegistroPasword.setText(e.getMessage());
        }
    }

    @FXML
    void backButton(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Utiles.LOGIN_VIEW));
        Parent root = loader.load();

        LoginController loginController = loader.getController();
        loginController.setUsuarios(usuarios);

        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
