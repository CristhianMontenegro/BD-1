package levitikus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBd {

    // Parámetros de conexión
    private static final String URL = "jdbc:postgresql://10.4.3.195:5432/supportsp";
    private static final String USER = "supportsp";
    private static final String PASSWORD = "stL098m";

    // Instancia única de la clase
    private static ConexionBd instance;
    private Connection connection;

    // Constructor privado para evitar la creación de instancias
    private ConexionBd() throws SQLException {
        try {
            // Cargar el controlador JDBC
            Class.forName("org.postgresql.Driver");
            // Establecer la conexión
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión a la base de datos establecida.");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el controlador JDBC de PostgreSQL.");
            throw new SQLException("No se encontró el controlador JDBC.", e);
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos.");
            throw e;
        }
    }

    // Método para obtener la instancia única de la clase
    public static ConexionBd getInstance() throws SQLException {
        if (instance == null || instance.connection.isClosed()) {
            instance = new ConexionBd();
        }
        return instance;
    }

    // Método para obtener la conexión a la base de datos
    public Connection getConnection() {
        return connection;
    }

    // Método para cerrar la conexión
    public static void closeConnection() {
        if (instance != null && instance.connection != null) {
            try {
                instance.connection.close();
                System.out.println("Conexión a la base de datos cerrada.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión.");
            } finally {
                instance = null; // Resetear la instancia
            }
        }
    }
}
