package cd.DAO.Conexion;

import java.sql.Connection;
import java.lang.Exception;

public class Conexion {

    private String url = "documental.mysql.database.azure.com";
    private String user = "miguel";
    private String password = "Letras2001";

    public Connection getConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = java.sql.DriverManager.getConnection(url, user, password);
            System.out.println("Conexion exitosa");
            return c;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
