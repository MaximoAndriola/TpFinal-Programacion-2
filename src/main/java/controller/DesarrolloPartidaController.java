package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.clases.Cartas.Mazo;
import model.clases.GestionJSON.GestionJSON;
import model.clases.Jugadores.ActorBlackjack;
import model.clases.Jugadores.Croupier;
import model.clases.Jugadores.Jugador;
import model.clases.Partida.ManejoPartida;
import model.clases.Ranking.Apuesta;
import model.clases.Usuario.InicioSesion;
import model.clases.Usuario.Usuario;
import model.exepciones.NombreInvalidoExeption;
import model.exepciones.ValorInvalidoException;
import path.Utiles;
import java.util.ArrayList;

public class DesarrolloPartidaController {
    ArrayList<Usuario> usuarios;
    int iUsuarioLogeado;
    ArrayList<Apuesta> apuestas;
    ArrayList<Jugador> jugadores = new ArrayList<>();
    Croupier croupier;
    Mazo mazo;
    VBox jugador;

    public void setApuestas(ArrayList<Apuesta> apuestas){
        this.apuestas = apuestas;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios, int iUsuarioLogeado) {
        this.usuarios = usuarios;
        this.iUsuarioLogeado = iUsuarioLogeado;
    }

    @FXML
    private Group grupoJugadores;

    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonContinuar0;

    @FXML
    private Button buttonSeguirJugando;

    @FXML
    private Label lblContinuarExeption;

    @FXML
    private Pane paneCroupier;

    @FXML
    private Text txtSaldo;

    @FXML
    void agregarJugador(ActionEvent event) {
        Button buttonAgregarJugador = (Button) event.getSource();
        int iJugador = Integer.parseInt(buttonAgregarJugador.getId().replace("buttonAgregarJugador", ""));

        jugador = (VBox) grupoJugadores.getChildren().get(iJugador);
        StackPane pane = (StackPane) jugador.getChildren().get(4);
        TextField txtIngresarNombre = (TextField) pane.getChildren().getFirst();

        Button buttonConfrimar = (Button) jugador.getChildren().get(5);

        Label lbl = (Label) jugador.getChildren().get(2);

        setVisibilidadAgregarJugadorTrue(buttonAgregarJugador, txtIngresarNombre, buttonConfrimar, lbl);
    }

    void setVisibilidadAgregarJugadorTrue(Button button, TextField txt, Button confirmar, Label lbl){
        button.setVisible(false);
        txt.setVisible(true);
        confirmar.setVisible(true);
        lbl.setVisible(true);
    }

    @FXML
    void confirmar(ActionEvent event) {
        Button buttonConfirmar = (Button) event.getSource();
        int iJugador = Integer.parseInt(buttonConfirmar.getId().replace("buttonConfirmar", ""));

        // Obtén el VBox del jugador actual
        jugador = (VBox) grupoJugadores.getChildren().get(iJugador);

        // Accede a los elementos dentro del VBox del jugador actual
        Label lblExeption = (Label) jugador.getChildren().get(2);
        StackPane pane = (StackPane) jugador.getChildren().get(4);
        TextField txtIngresarNombre = (TextField) pane.getChildren().getFirst();

        try {
            // Intentar validar el nombre
            InicioSesion.validarNombreJugador(txtIngresarNombre.getText(), jugadores);

            // Si es válido, agregar al jugador y actualizar UI
            jugadores.add(new Jugador(txtIngresarNombre.getText()));
            lblExeption.setText(" ");
            buttonConfirmar.setVisible(false);
            txtIngresarNombre.setEditable(false);

            // Mostrar los botones para el siguiente jugador, si hay uno
            if (iJugador < grupoJugadores.getChildren().size() - 1) {
                // Obtén el siguiente VBox de jugador
                VBox jugadorSig = (VBox) grupoJugadores.getChildren().get(iJugador + 1);
                StackPane paneSig = (StackPane) jugadorSig.getChildren().get(4);
                Button buttonSig = (Button) paneSig.getChildren().get(1);
                buttonSig.setVisible(true);
            }
        } catch (NombreInvalidoExeption e) {
            // Mostrar mensaje de error si el nombre es inválido
            lblExeption.setText(e.getMessage());
        }
    }


    @FXML
    void continuar(ActionEvent event) throws Exception{

        if (jugadores.isEmpty()){
            lblContinuarExeption.setText("Se debe ingresar al menos un jugador.");
        }else {
            croupier = new Croupier("CROUPIER");
            mazo = new Mazo();

            croupier.repartir(mazo, jugadores, croupier);

            buttonContinuar0.setVisible(false);

            for (int i = 0; i < jugadores.size(); i++){
                jugador = (VBox) grupoJugadores.getChildren().get(i);
                Pane pane = (Pane) jugador.getChildren().get(1);
                actualizarCartas(i, pane);
            }
            actualizarCartasCroupier();

            //Setear visibilidad apuesta
            jugador = (VBox) grupoJugadores.getChildren().getFirst();
            StackPane stackPane = (StackPane) jugador.getChildren().get(6);
            HBox hBox = (HBox) stackPane.getChildren().get(1);
            Text txtIngresarApuesta = (Text) hBox.getChildren().getFirst();
            TextField txtFIngresarApuesta = (TextField) hBox.getChildren().get(1);
            Button button = (Button) jugador.getChildren().get(7);
            Text txtApuesta = (Text) stackPane.getChildren().getFirst();
            txtApuesta.setText("");

            setVisibilidadApuestaTrue(txtIngresarApuesta, txtFIngresarApuesta, button);

            if (jugadores.size() < 4) {
                int i;
                for (i = 0; i < jugadores.size(); i++) {
                    jugador = (VBox) grupoJugadores.getChildren().get(i);
                    StackPane pane = (StackPane) jugador.getChildren().get(4);

                    Button buttonAgregar = (Button) pane.getChildren().get(1);
                    Button buttonConfirmar = (Button)jugador.getChildren().get(5);
                    Label lbl = (Label) jugador.getChildren().get(2);

                    setVisibilidadAgregarJugadorFalse(buttonAgregar, buttonConfirmar, lbl);
                }
                jugador = (VBox) grupoJugadores.getChildren().get(i);
                StackPane pane = (StackPane) jugador.getChildren().get(4);

                Button buttonAgregar = (Button) pane.getChildren().get(1);
                Button buttonConfirmar = (Button)jugador.getChildren().get(5);
                Label lbl = (Label) jugador.getChildren().get(2);

                setVisibilidadAgregarJugadorFalse(buttonAgregar, buttonConfirmar, lbl);
                TextField txt = (TextField) pane.getChildren().getFirst();
                txt.setVisible(false);
            }
            txtSaldo.setText("SALDO: "+(usuarios.get(iUsuarioLogeado).getSaldo())+ "$");
        }
    }

    void setVisibilidadApuestaTrue(Text txtIngresarApuesta, TextField txtFIngresarApuesta, Button button){
        txtIngresarApuesta.setVisible(true);
        txtFIngresarApuesta.setVisible(true);
        button.setVisible(true);
    }

    void setVisibilidadApuestaFalse(Text txtIngresarApuesta, TextField txtFIngresarApuesta, Button button, Text txtApuesta){
        txtIngresarApuesta.setVisible(false);
        txtFIngresarApuesta.setVisible(false);
        button.setVisible(false);
        txtApuesta.setVisible(true);
    }

    @FXML
    void confirmarApuesta(ActionEvent event) {
        Button buttonConfirmarApuesta = (Button) event.getSource();
        int iJugador = Integer.parseInt(buttonConfirmarApuesta.getId().replace("buttonConfirmarApuesta", ""));
        System.out.println(iJugador);
        jugador = (VBox) grupoJugadores.getChildren().get(iJugador);
        StackPane stackPane = (StackPane) jugador.getChildren().get(6);
        HBox hBox = (HBox) stackPane.getChildren().get(1);
        Text txtIngresarApuesta = (Text) hBox.getChildren().getFirst();
        TextField txtFIngresarApuesta = (TextField) hBox.getChildren().get(1);
        Button button = (Button) jugador.getChildren().get(7);

        jugadores.get(iJugador).setSaldo(usuarios.get(iUsuarioLogeado).getSaldo());

        Text txtExeption = (Text)jugador.getChildren().get(8);

        try {
            jugadores.get(iJugador).apostar(txtFIngresarApuesta.getText());

            usuarios.get(iUsuarioLogeado).setSaldo(jugadores.get(iJugador).getSaldo());
            GestionJSON.cargarArray(usuarios);

            HBox box = (HBox) jugador.getChildren().get(3);
            Button pedir = (Button) box.getChildren().getFirst();
            Button plantarse = (Button) box.getChildren().get(1);

            Text txtApuesta = (Text) stackPane.getChildren().getFirst();
            txtApuesta.setText(txtFIngresarApuesta.getText()+"$");

            setVisibilidadApuestaFalse(txtIngresarApuesta, txtFIngresarApuesta, button, txtApuesta);
            pedir.setVisible(true);
            plantarse.setVisible(true);

            txtExeption.setText("");

            txtSaldo.setText("SALDO: "+(usuarios.get(iUsuarioLogeado).getSaldo())+ "$");
        } catch (ValorInvalidoException e) {
            txtExeption.setText(e.getMessage());
        }
    }

    void setVisibilidadAgregarJugadorFalse (Button buttonAgregar, Button buttonConfirmar, Label lbl){
        lbl.setVisible(false);
        buttonAgregar.setVisible(false);
        buttonConfirmar.setVisible(false);
    }

    @FXML
    void seguirJugando(ActionEvent event) throws Exception {
        croupier.vaciarMano();
        paneCroupier.getChildren().clear();

        for (int i = 0; i < jugadores.size(); i++) {
            jugadores.get(i).vaciarMano();

            jugador = (VBox) grupoJugadores.getChildren().get(i);
            Pane pane = (Pane) jugador.getChildren().get(1);
            pane.getChildren().clear();

            jugador = (VBox) grupoJugadores.getChildren().get(i);
            StackPane stackPane = (StackPane) jugador.getChildren().get(6);
            Text txtApuesta = (Text) stackPane.getChildren().getFirst();
            txtApuesta.setText("");
            Text mensajeGanador = (Text) jugador.getChildren().getFirst();
            mensajeGanador.setText("");
        }

        continuar(event);
        buttonSeguirJugando.setVisible(false);
    }

    void actualizarCartas (int iJugador, Pane pane){
        String carta1 = "file:src/main/resources/assets/cartas/" + jugadores.get(iJugador).getMano().getCartas().getFirst().getCarta() + ".png";
        String carta2 = "file:src/main/resources/assets/cartas/" + jugadores.get(iJugador).getMano().getCartas().get(1).getCarta() + ".png";
        agregarCarta(carta1, pane);
        agregarCarta(carta2, pane);
    }

    void actualizarCartasCroupier (){
        String carta1 = "file:src/main/resources/assets/cartas/DORSO.jpg";
        String carta2 = "file:src/main/resources/assets/cartas/" + croupier.getMano().getCartas().get(1).getCarta() + ".png";
        agregarCarta(carta1, paneCroupier);
        agregarCarta(carta2, paneCroupier);
    }

    // Metodo para añadir cartas al Pane de manera superpuesta
    void agregarCarta(String rutaImagenCarta, Pane pane) {
        // Crear una nueva ImageView para la carta
        ImageView nuevaCarta = new ImageView(new Image(rutaImagenCarta));
        nuevaCarta.setFitWidth(100); // Ancho de cada carta
        nuevaCarta.setFitHeight(150); // Alto de cada carta

        // Calcular la posición en la que debe colocarse la nueva carta
        int cantidadCartas = pane.getChildren().size();
        double offsetX = cantidadCartas * 20; // Ajustar para controlar el nivel de superposición

        // Establecer la posición en el Pane
        nuevaCarta.setLayoutX(offsetX);
        nuevaCarta.setLayoutY(0); // Puedes ajustar Y si quieres inclinar las cartas

        // Añadir la nueva carta al Pane
        pane.getChildren().add(nuevaCarta);
    }

    @FXML
    void pedir(ActionEvent event) {
        Button button = (Button) event.getSource();
        int iJugador = Integer.parseInt(button.getId().replace("pedir", ""));

        jugadores.get(iJugador).pedirCarta(mazo);

        String carta = "file:src/main/resources/assets/cartas/" + jugadores.get(iJugador).getMano().getCartas().getLast().getCarta() + ".png";
        jugador = (VBox) grupoJugadores.getChildren().get(iJugador);
        Pane pane = (Pane) jugador.getChildren().get(1);
        agregarCarta(carta, pane);

        if (jugadores.get(iJugador).getMano().getValor() >= 21){
            button.setVisible(false);
        }
    }

    @FXML
    void plantarse(ActionEvent event) {
        Button buttonPlantarse = (Button) event.getSource();
        int iJugador = Integer.parseInt(buttonPlantarse.getId().replace("buttonPlantarse", ""));

        // Obtener el VBox del jugador y su HBox para los botones
        VBox jugadorActual = (VBox) grupoJugadores.getChildren().get(iJugador);
        HBox box = (HBox) jugadorActual.getChildren().get(3);

        // Obtener los botones y asegurarse de que se oculten
        Button buttonPedir = (Button) box.getChildren().get(0); // El primer botón es "pedir"
        buttonPlantarse = (Button) box.getChildren().get(1); // El segundo botón es "plantarse"

        buttonPedir.setVisible(false);
        buttonPlantarse.setVisible(false);

        // Avanzar al siguiente jugador si hay uno
        if (iJugador < jugadores.size() - 1) {
            jugador = (VBox) grupoJugadores.getChildren().get(iJugador+1);
            StackPane stackPane = (StackPane) jugador.getChildren().get(6);
            HBox hBox = (HBox) stackPane.getChildren().get(1);
            Text txtIngresarApuesta = (Text) hBox.getChildren().getFirst();
            TextField txtFIngresarApuesta = (TextField) hBox.getChildren().get(1);
            Button button = (Button) jugador.getChildren().get(7);

            setVisibilidadApuestaTrue(txtIngresarApuesta, txtFIngresarApuesta, button);
        } else {
            juegaCroupier();// Si es el último jugador, comienza el juego del crupier
            buttonSeguirJugando.setVisible(true);
        }
    }

    void juegaCroupier(){
        croupier.jugar(mazo);
        paneCroupier.getChildren().removeFirst();

        for (int i = 0; i < croupier.getMano().getCartas().size(); i++) {
            String carta = "file:src/main/resources/assets/cartas/" + croupier.getMano().getCartas().get(i).getCarta() + ".png";

            agregarCarta(carta, paneCroupier);
        }

        try {
            determinarGanadores();
        } catch (ValorInvalidoException e) {
e           .getMessage();
        }
    }

    void determinarGanadores() throws ValorInvalidoException {
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador jugador1 = jugadores.get(i);
            jugador = (VBox) grupoJugadores.getChildren().get(i);
            Text mensajeGanador = (Text) jugador.getChildren().getFirst();

            ActorBlackjack ganador = ManejoPartida.determinarGanador(croupier, jugador1);

            if (ganador.getNombre().equals("BLACKJACK")){
                usuarios.get(iUsuarioLogeado).devolverApuesta(jugador1.getApuesta() * 2.5);

                Apuesta apuesta = new Apuesta();
                apuesta.setApuestaRealizada(jugador1.getApuesta());
                apuesta.setNombreUsuario(usuarios.get(iUsuarioLogeado).getNombre());
                apuestas.add(apuesta);
                GestionJSON.cargarArrayApuestas(apuestas);

                mensajeGanador.setText("BLACKJACK!!!");
            }else if (ganador.getNombre().equals("CROUPIER")){

               mensajeGanador.setText("GANÓ LA CASA");
            }else {
                usuarios.get(iUsuarioLogeado).devolverApuesta(jugador1.getApuesta() * 2);

                Apuesta apuesta = new Apuesta();
                apuesta.setApuestaRealizada(jugador1.getApuesta());
                apuesta.setNombreUsuario(usuarios.get(iUsuarioLogeado).getNombre());
                apuestas.add(apuesta);
                GestionJSON.cargarArrayApuestas(apuestas);

                mensajeGanador.setText("FELICIDADES, GANASTE");
            }
        }
    }

    @FXML
    void back(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Utiles.MAIN_MENU_VIEW));
        Parent root = loader.load();

        Stage stage = (Stage) buttonBack.getScene().getWindow();
        stage.setScene(new Scene(root));

        MainMenuController mainMenuController = loader.getController();
        mainMenuController.setUsuarios(usuarios, iUsuarioLogeado);
        mainMenuController.setApuestas(apuestas);
    }
}
