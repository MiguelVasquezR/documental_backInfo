package cd.DAO.Conexion;

import java.sql.Connection;
import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Exception;

public class Conexion {

    private String url = System.getenv("URL_DATABASES");
    private String user = System.getenv("USER_DATABASES");
    private String password = System.getenv("PASSWORD_DATABASES");

    private Connection connection;

    public Connection getConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = java.sql.DriverManager.getConnection(url, user, password);
            System.out.println("Conexion exitosa");
            return connection;
        } catch (Exception e) {
            System.out.println("Error en la conexión");
            e.printStackTrace();
            return null;
        }
    }

    public void cerrarConexion() {
        try {
            connection.close();
            System.out.println("Conexión cerrada en Conexion");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}