package com.jorja.proyect.proyectogestoritvfinal.controlador.ventanas.ventanaPrincipal;

import com.jorja.proyect.proyectogestoritvfinal.controlador.bbdd.CONEXIONBD;
import com.jorja.proyect.proyectogestoritvfinal.modelo.Sesion;
import com.jorja.proyect.proyectogestoritvfinal.modelo.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentanaPrincipalUsuarioControlador {

    private static Connection conexion;
    private static PreparedStatement sentencia;
    private static ResultSet resultado;

    public static void sacarNombreUsuarioLogueado(Label label){
        // Sacar nombre usuario que ha iniciado sesion
        Usuario usuarioActual = Sesion.getUsuarioActual();
        if (usuarioActual != null) {
            label.setText(usuarioActual.getNombre());
        } else {
            label.setText("Usuario desconodido");
        }
    }

    public static Usuario cargarUsuarioPorCorreo(String correo) {
        String sql = "SELECT * FROM datos_usuario WHERE correo = ?";
        Usuario usuario = null;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, correo);
            resultado = sentencia.executeQuery();
            if (resultado.next()) {
                int id = resultado.getInt("id");
                String nombre = resultado.getString("nombre");
                String apellido = resultado.getString("apellido");
                String telefono = resultado.getString("telefono");
                String contraseña = resultado.getString("contraseña");
                boolean administrador = resultado.getBoolean("administrador");
                String fechaAlta = resultado.getString("fechaAlta");

                usuario = new Usuario(id, nombre, apellido, telefono, correo, contraseña, administrador, fechaAlta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public static ObservableList<Usuario> addUsuario(CONEXIONBD cbd, Usuario usuario) {

        ObservableList<Usuario> listaUsuario = FXCollections.observableArrayList();
        String sql = "SELECT * FROM datos_usuario ORDER BY id";
        conexion = cbd.abrirConexion();
        try {
            sentencia = conexion.prepareStatement(sql);
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                usuario = new Usuario(
                        resultado.getInt("id"),
                        resultado.getString("Nombre"),
                        resultado.getString("Apellido"),
                        resultado.getString("Telefono"),
                        resultado.getString("Correo"),
                        resultado.getString("Contraseña"),
                        resultado.getBoolean("Administrador"),
                        resultado.getString("FechaAlta"));
                listaUsuario.add(usuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuario;
    }
    // Método para verificar si el ID de usuario existe en la base de datos
    public static boolean existeUsuario(int idUsuario) {
        String sql = "SELECT id FROM datos_usuario WHERE id = ?";
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, idUsuario);
            resultado = sentencia.executeQuery();
            return resultado.next(); // Retorna true si el usuario existe, false si no
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
