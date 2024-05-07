package cd.DAO.Conexion;

import java.sql.Connection;
import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Exception;

public class Conexion {


    private String url;
    private String user;
    private String password;
    private Connection connection;

    public Conexion() {
        Properties prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("cd/config.properties")) {
            prop.load(input);
            System.out.println("Properties cargado");
            url = prop.getProperty("db.url");
            user = prop.getProperty("db.user");
            password = prop.getProperty("db.password");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = java.sql.DriverManager.getConnection(url, user, password);
            System.out.println("Conexion exitosa");
            return connection;
        } catch (Exception e) {
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
