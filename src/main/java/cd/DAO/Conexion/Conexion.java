package cd.DAO.Conexion;

import java.sql.Connection;
import java.lang.Exception;

public class Conexion {


    private String url = "jdbc:mysql://root:VLPhzuQPgjPdEZIKERdQRMwpGLYviSXm@roundhouse.proxy.rlwy.net:45385/centroDocumental";
    private String user = "root";
    private String password = "VLPhzuQPgjPdEZIKERdQRMwpGLYviSXm";

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
