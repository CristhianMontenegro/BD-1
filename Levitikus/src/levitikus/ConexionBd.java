/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package levitikus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBd {

    // Parámetros de conexión
    private static final String URL = "jdbc:postgresql://10.4.3.195:5432/supportsp"; // Cambia "tu_base_de_datos" por el nombre de tu base de datos
    private static final String USER = "supportsp"; // Cambia "tu_usuario" por tu nombre de usuario
    private static final String PASSWORD = "stL098m"; // Cambia "tu_contraseña" por tu contraseña

    // Método para establecer la conexión
    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            // Cargar el controlador JDBC
            Class.forName("org.postgresql.Driver");
            // Establecer la conexión
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión a la base de datos establecida.");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el controlador JDBC de PostgreSQL.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos.");
            e.printStackTrace();
            throw e; // Lanzar la excepción para que pueda ser manejada en el código que llama a este método
        }
        return connection;
    }

    // Método para cerrar la conexión
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión a la base de datos cerrada.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión.");
                e.printStackTrace();
            }
        }
    }
}

