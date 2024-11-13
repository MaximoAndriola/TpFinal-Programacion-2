package controller;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.clases.GestionJSON.GestionJSON;
import model.clases.Ranking.Apuesta;
import model.clases.Ranking.Ranking;
import model.clases.Usuario.Usuario;
import model.exepciones.NombreInvalidoExeption;
import javafx.scene.layout.Pane;
import model.exepciones.ValorInvalidoException;
import path.Utiles;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class MainMenuController {
    private ArrayList<Usuario> usuarios;
    private int iUsuarioLogeado;
    private Ranking ranking;

    public void setApuestas (ArrayList<Apuesta> apuestas){
        ranking.setApuestas(apuestas);
    }

    public void setUsuarios(ArrayList<Usuario> usuarios, int iUsuarioLogeado) {
        this.usuarios = usuarios;
        this.iUsuarioLogeado = iUsuarioLogeado;
    }

    @FXML
    private TextField txtFIngresarNombre;

    @FXML
    private TextField txtFIngresarSaldo;

    @FXML
    private Text txtSaldo;

    private DoubleProperty saldoProperty = new SimpleDoubleProperty();

    @FXML
    private Button buttonCerrarSesion;

    @FXML
    private Button buttonJugar;

    @FXML
    private Button buttonBuscar;

    @FXML
    private Label lblValorInvalidoExeption;

    @FXML
    private TableColumn<Apuesta, Double> colApuesta;

    @FXML
    private TableColumn<Apuesta, String> colUsername;

    @FXML
    private Pane paneIngresarSaldo;

    @FXML
    private TableView<Apuesta> tblApuestas;

    @FXML
    void initialize(){
        if (usuarios != null) {
            saldoProperty.set(usuarios.get(iUsuarioLogeado).getSaldo());
            txtSaldo.textProperty().bind(saldoProperty.asString());
        }else {
            txtSaldo.setText("NO DISPONIBLE");
        }
        ranking = new Ranking();
        ranking.setApuestas(GestionJSON.mapeoApuestas());

        colUsername.setCellValueFactory(new PropertyValueFactory<>("nombreUsuario"));
        colApuesta.setCellValueFactory(new PropertyValueFactory<>("apuestaRealizada"));
    }

    @FXML
    void jugar(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Utiles.SELECCION_JUGADORES_VIEW));
        Parent root = loader.load();

        Stage stage = (Stage) buttonJugar.getScene().getWindow();
        stage.setScene(new Scene(root));

        DesarrolloPartidaController desarrolloPartidaController = loader.getController();
        desarrolloPartidaController.setUsuarios(usuarios, iUsuarioLogeado);
        desarrolloPartidaController.setApuestas(ranking.getApuestas());
    }

    @FXML
    void cerrarSesion(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Utiles.LOGIN_VIEW));
        Parent root = loader.load();

        LoginController loginController = loader.getController();
        loginController.setUsuarios(usuarios);

        Stage stage = (Stage) buttonCerrarSesion.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void buttonScoreboard(ActionEvent event) {
        tblApuestas.setVisible(true);
        txtFIngresarNombre.setVisible(true);
        buttonBuscar.setVisible(true);

        tblApuestas.getItems().clear();
        tblApuestas.getItems().addAll(ranking.filtrarTop10());
    }

    @FXML
    void filtrarNombre(ActionEvent event) {
        try {
            tblApuestas.getItems().clear();
            tblApuestas.getItems().addAll(ranking.filtrarPorNombre(txtFIngresarNombre.getText()));
        } catch (NombreInvalidoExeption e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void cargarSaldo(ActionEvent event) {
        paneIngresarSaldo.setVisible(true);
    }


    @FXML
    void confirmarPago(ActionEvent event) {

        try {
            usuarios.get(iUsuarioLogeado).agregarSaldo(txtFIngresarSaldo.getText());
            paneIngresarSaldo.setVisible(false);
            txtSaldo.setText(Double.toString(usuarios.get(iUsuarioLogeado).getSaldo())+"$");
            GestionJSON.cargarArray(usuarios);
        } catch (ValorInvalidoException e) {
            lblValorInvalidoExeption.setText(e.getMessage());
        }
    }

    @FXML
    void cancelarPago(ActionEvent event) {
        paneIngresarSaldo.setVisible(false);
    }

}