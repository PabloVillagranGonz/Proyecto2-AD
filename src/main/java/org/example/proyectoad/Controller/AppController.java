package org.example.proyectoad.Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import org.example.proyectoad.DAO.GrupoDAO;
import org.example.proyectoad.domain.Concierto;
import org.example.proyectoad.util.AlertUtils;
import org.example.proyectoad.domain.Concierto;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AppController {

    public TextField tfNombre;
    public TextField tfDescripcion;
    public TextField tfPresupuesto;
    public ComboBox<String> cbTipo;
    public ListView<Concierto> lvConcierto;
    public Label lbEstado;
    public Button btNuevo, btModificar, btGuardar, btEliminar;
    private enum Accion {
        NUEVO, MODIFICAR
    }
    private Accion accion;

    private final GrupoDAO grupoDAO;
    private Concierto conciertoSeleccionado;

    public AppController() {
        grupoDAO = new GrupoDAO();
        try {
            grupoDAO.conectar();
        } catch (SQLException sqle) {
            AlertUtils.mostrarError("Error al conectar con la base de datos");
        } catch (ClassNotFoundException cnfe) {
            AlertUtils.mostrarError("Error al iniciar la aplicación");
        } catch (IOException ioe) {
            AlertUtils.mostrarError("Error al cargar la configuración");
        }

        System.out.println(System.getProperty("user.home"));
    }

    public void cargarDatos() {
        modoEdicion(false);

        lvConcierto.getItems().clear();
        try {
            List<Concierto> conciertos = grupoDAO.obtenerConciertos();
            lvConcierto.setItems(FXCollections.observableList(conciertos));

            String[] tipos = new String[]{"<Selecciona tipo>", "Rock", "Pop", "Regueton", "Indie"};
            cbTipo.setItems(FXCollections.observableArrayList(tipos));
        } catch (SQLException sqle) {
            AlertUtils.mostrarError("Error cargando los datos de la aplicación");
        }
    }

    @FXML
    public void nuevoConcierto(Event event) {
        limpiarCajas();
        modoEdicion(true);
        accion = Accion.NUEVO;
    }

    @FXML
    public void modificarConcierto(Event event) {
        modoEdicion(true);
        accion = Accion.MODIFICAR;
    }

    @FXML
    public void eliminarConcierto(Event event) {
        Concierto concierto = lvConcierto.getSelectionModel().getSelectedItem();
        if (concierto == null) {
            lbEstado.setText("ERROR: No se ha seleccionado ningún concierto");
            return;
        }

        try {
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Eliminar concierto");
            confirmacion.setContentText("¿Estás seguro?");
            Optional<ButtonType> respuesta = confirmacion.showAndWait();
            if (respuesta.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE)
                return;

            grupoDAO.eliminarConcierto(concierto);
            lbEstado.setText("MENSAJE: Concierto eliminado con éxito");

            cargarDatos();
        } catch (SQLException sqle) {
            AlertUtils.mostrarError("No se ha podido eliminar el concierto");
        }
    }

    @FXML
    public void guardarConcierto(Event event) {
        String nombre = tfNombre.getText();
        if (nombre.equals("")) {
            AlertUtils.mostrarError("El nombre es un campo obligatorio");
            return;
        }

        String descripcion = tfDescripcion.getText();
        String presupuesto = tfPresupuesto.getText();
        String tipo = cbTipo.getSelectionModel().getSelectedItem();
        Concierto concierto = new Concierto(presupuesto,nombre, descripcion,tipo);

        try {
            switch (accion) {
                case NUEVO:
                    grupoDAO.guardarConcierto(concierto);
                    break;
                case MODIFICAR:
                    grupoDAO.modificarConcierto(conciertoSeleccionado, concierto);
                    break;
            }
        } catch (SQLException sqle) {
            AlertUtils.mostrarError("Error al guadar el concierto");
        }

        lbEstado.setText("Concierto guardado con éxito");
        cargarDatos();

        modoEdicion(false);
    }

    @FXML
    public void cancelar() {
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Edición");
        confirmacion.setContentText("¿Estás seguro?");
        Optional<ButtonType> respuesta = confirmacion.showAndWait();
        if (respuesta.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE)
            return;

        modoEdicion(false);
        cargarConcierto(conciertoSeleccionado);
    }

    private void cargarConcierto(Concierto concierto) {
        tfNombre.setText(concierto.getNombre());
        tfPresupuesto.setText(concierto.getPresupuesto());
        tfDescripcion.setText(concierto.getDescripcion());
        cbTipo.setValue(concierto.getTipo());
    }

    @FXML
    public void seleccionarConcierto(Event event) {
        conciertoSeleccionado = lvConcierto.getSelectionModel().getSelectedItem();
        cargarConcierto(conciertoSeleccionado);
    }

    @FXML
    public void importar(ActionEvent event) {

    }
/*
    @FXML
    public void exportar(ActionEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            File fichero = fileChooser.showSaveDialog(null);

            FileWriter fileWriter = new FileWriter(fichero);
            CSVPrinter printer = new CSVPrinter(fileWriter,
                    CSVFormat.DEFAULT.withHeader("id", "presupuesto", "nombre", "descripcion", "tipo"));

            List<Concierto> conciertos = grupoDAO.obtenerConciertos();
            for (Concierto concierto : conciertos)
                printer.printRecord(concierto.getId(), concierto.getDescripcion(), concierto.getNombre(),
                        concierto.getPresupuesto(), concierto.getTipo());

            printer.close();
        } catch (SQLException sqle) {
            AlertUtils.mostrarError("Error al conectar con la base de datos");
        } catch (IOException ioe) {
            AlertUtils.mostrarError("Error al exportar los datos");
        }
    }

 */
    public void mostrarDialogo() {
        Dialog dialog = new Dialog();
        dialog.setTitle("hola pablo");
        dialog.setContentText("hola a todos");
        dialog.show();
    }

    private void limpiarCajas() {
        tfPresupuesto.setText("");
        tfNombre.setText("");
        tfDescripcion.setText("");
        cbTipo.setValue("<Selecciona tipo>");
        tfNombre.requestFocus();
    }

    private void modoEdicion(boolean activar) {
        btNuevo.setDisable(activar);
        btGuardar.setDisable(!activar);
        btModificar.setDisable(activar);
        btEliminar.setDisable(activar);

        tfDescripcion.setEditable(activar);
        tfNombre.setEditable(activar);
        tfDescripcion.setEditable(activar);
        cbTipo.setDisable(!activar);

        lvConcierto.setDisable(activar);
    }
}
