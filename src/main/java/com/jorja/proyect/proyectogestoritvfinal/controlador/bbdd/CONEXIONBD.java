package com.jorja.proyect.proyectogestoritvfinal.controlador.bbdd;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.jorja.proyect.proyectogestoritvfinal.controlador.Utils.mostrarAlerta;
import static com.jorja.proyect.proyectogestoritvfinal.controlador.bbdd.ConfiguracionBD.DBHOST;
import static com.jorja.proyect.proyectogestoritvfinal.controlador.bbdd.ConfiguracionBD.DBNAME;


public class CONEXIONBD {

    public Connection conexion;


    /***
     * Metodo para abrir la conexion con la base de datos
     * @return
     */
    public Connection abrirConexion() {
        try {

            // Driver mysql
            Class.forName("com.mysql.cj.jdbc.Driver");

            try {
                conexion = (Connection) DriverManager.getConnection("jdbc:mysql://" + DBHOST + ":3306/" + DBNAME, "root", "");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return conexion;
    }


    public static void comprobarConexion(Connection conexion){
        if (conexion == null) {
            mostrarAlerta("Error de Conexión", "No se pudo establecer la conexión con la base de datos.", Alert.AlertType.ERROR);
            return;
        }
    }


    /***
     * Metodo para cerrar la conexion con la base de datos
     * @return
     */
    public boolean cerrarConexion() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(CONEXIONBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}
