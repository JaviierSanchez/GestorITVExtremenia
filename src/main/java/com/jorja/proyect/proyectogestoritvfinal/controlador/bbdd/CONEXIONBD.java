package com.jorja.proyect.proyectogestoritvfinal.controlador.bbdd;

import javafx.scene.control.Alert;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    /**
     * Metodo para cerrar la conexion con la base de datos
     * @return
     */

    /**
     * Metodo para hacer una copia de seguridad de la base de datos
     * @return true si la copia de seguridad se realizó correctamente, false en caso contrario
     */
    public boolean hacerCopiaDeSeguridad() {
        String userHome = System.getProperty("user.home");
        String downloadDir = userHome + "/Downloads";
        String fecha = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String backupFile = downloadDir + "/backup_" + DBNAME + "_" + fecha + ".sql";

        // Especificar la ruta completa a mysqldump
        String mysqldumpPath = "C:\\xampp\\mysql\\bin\\mysqldump.exe";

        String command = String.format("\"%s\" -h %s -u root --databases %s -r \"%s\"", mysqldumpPath, DBHOST, DBNAME, backupFile);

        try {
            Process process = Runtime.getRuntime().exec(command);
            int processComplete = process.waitFor();

            if (processComplete == 0) {
                mostrarAlerta("Copia de Seguridad", "La copia de seguridad se realizó correctamente. Archivo guardado en: " + backupFile, Alert.AlertType.INFORMATION);
                return true;
            } else {
                mostrarAlerta("Error", "No se pudo realizar la copia de seguridad.", Alert.AlertType.ERROR);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }
}
