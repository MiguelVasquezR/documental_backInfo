package cd.DAO.Conexion;

import java.sql.Connection;
import java.lang.Exception;

public class Conexion {

    private String url = "documental.mysql.database.azure.com";
    private String user = "miguel";
    private String password = "Maggpachito18";

    private Connection connection;

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
