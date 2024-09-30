package org.example.proyectoad.DAO;

import org.example.proyectoad.domain.Concierto;
import org.example.proyectoad.util.R;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GrupoDAO {

    private Connection conexion;

    public void conectar() throws ClassNotFoundException, SQLException, IOException {
        Properties configuration = new Properties();
        configuration.load(R.getProperties("database.properties"));
        String host = configuration.getProperty("host");
        String port = configuration.getProperty("port");
        String name = configuration.getProperty("name");
        String username = configuration.getProperty("username");
        String password = configuration.getProperty("password");

        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC",
                username, password);
    }

    public void desconectar() throws SQLException {
        conexion.close();
    }

    public void guardarConcierto(Concierto concierto) throws SQLException {
        String sql = "INSERT INTO grupos (id, presupuesto, nombre, descripcion) VALUES (?, ?, ?, ?)";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setInt(1, concierto.getId());
        sentencia.setString(2, concierto.getPresupuesto());
        sentencia.setString(3, concierto.getNombre());
        sentencia.setString(4, concierto.getDescripcion());
        sentencia.executeUpdate();
    }

    public void eliminarConcierto(Concierto concierto) throws SQLException {
        String sql = "DELETE FROM grupos WHERE nombre = ?";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, concierto.getNombre());
        sentencia.executeUpdate();
    }

    public void modificarConcierto(Concierto conciertoAntiguo, Concierto conciertoNuevo) throws SQLException {
        String sql = "UPDATE grupos SET descripcion = ?, nombre = ?, presupuesto = ? WHERE id = ?";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, conciertoNuevo.getDescripcion());
        sentencia.setString(2, conciertoNuevo.getNombre());
        sentencia.setString(3, conciertoNuevo.getPresupuesto());
        sentencia.setInt(4, conciertoAntiguo.getId());
        sentencia.executeUpdate();
    }

    public List<Concierto> obtenerConciertos() throws SQLException {
        List<Concierto> conciertos = new ArrayList<>();
        String sql = "SELECT * FROM grupos";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {
            Concierto concierto = new Concierto();
            concierto.setId(resultado.getInt(1));
            concierto.setPresupuesto(resultado.getString(2));
            concierto.setNombre(resultado.getString(3));
            concierto.setDescripcion(resultado.getString(4));

            conciertos.add(concierto);
        }

        return conciertos;
    }

    public boolean existeConcierto(String nombre) throws SQLException {
        String sql = "SELECT * FROM grupos WHERE nombre = ?";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        ResultSet resultado = sentencia.executeQuery();

        return resultado.next();
    }
}
