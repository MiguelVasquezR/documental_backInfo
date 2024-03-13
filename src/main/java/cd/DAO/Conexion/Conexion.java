package cd.DAO.Conexion;

import java.sql.Connection;
import java.lang.Exception;

public class Conexion {

    private String url = "jdbc:mysql://localhost:3306/centroDocumental";
    private String user = "root";
    private String password = "FormulaUno";

    public Connection getConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = java.sql.DriverManager.getConnection(url, user, password);
            return c;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
