package com.jorja.proyect.proyectogestoritvfinal.controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.jorja.proyect.proyectogestoritvfinal.controlador.ConfiguracionBD.DBHOST;
import static com.jorja.proyect.proyectogestoritvfinal.controlador.ConfiguracionBD.DBNAME;

public class CONEXIONBD {

    Connection conexion;


    /***
     * Metodo para abrir la conexion con la base de datos
     * @return
     */
    public Connection abrirConexion() {
        try {

           // Driver mysql
            Class.forName("com.mysql.cj.jdbc.Driver");

            try {
                conexion = (Connection) DriverManager.getConnection("jdbc:mysql://" + DBHOST + ":3306/" + DBNAME, "root", "root");


            } catch (SQLException e) {

                e.printStackTrace();

            }
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }

        return conexion;

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
