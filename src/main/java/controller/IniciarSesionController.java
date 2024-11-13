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
import model.clases.Usuario.InicioSesion;
import model.clases.Usuario.Usuario;
import model.exepciones.ContraseniaInvalidaExeption;
import model.exepciones.NombreInvalidoExeption;
import path.Utiles;

import java.util.ArrayList;

public class IniciarSesionController {
    private ArrayList<Usuario> usuarios;

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    @FXML
    private Button backButton;

    @FXML
    private Button iniciarSesionButton;

    @FXML
    private Label exeptionInicioSesionUsuario;

    @FXML
    private PasswordField txtIngresarContrasenia;

    @FXML
    private TextField txtIngresarUsuario;

    @FXML
    void iniciarSesion(ActionEvent event) throws Exception{
        try {
            InicioSesion.ingresar(txtIngresarUsuario.getText(), txtIngresarContrasenia.getText(),usuarios);
            exeptionInicioSesionUsuario.setText("");

            FXMLLoader loader = new FXMLLoader(getClass().getResource(Utiles.MAIN_MENU_VIEW));
            Parent root = loader.load();

            MainMenuController mainMenuController = loader.getController();

            int iUsuarioLogeado = 0;

            for (int i = 0; i < usuarios.size(); i++) {
                Usuario uLogeado = usuarios.get(i);
                if (uLogeado.getNombre().equals(txtIngresarUsuario.getText())) {
                    iUsuarioLogeado = i;
                }
            }
            mainMenuController.setUsuarios(usuarios, iUsuarioLogeado);

            Stage stage = (Stage) iniciarSesionButton.getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (NombreInvalidoExeption e) {
            exeptionInicioSesionUsuario.setText(e.getMessage());
            System.out.println(e.getMessage());
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
