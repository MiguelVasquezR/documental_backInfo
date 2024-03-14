package cd.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cd.DAO.Conexion.Conexion;
import cd.Modelo.Genero;

public class DAOGenero {    
    private static Conexion conexion = new Conexion();

    public Genero obtenerIDGenero(String nombre){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        try{
            con = conexion.getConexion();
            String sql = "SELECT ID FROM genero WHERE Nombre = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            Genero genero;
            if(rs.next()){
                genero = new Genero();
                genero.setID(rs.getString("ID"));
                genero.setNombre(rs.getString("Nombre"));
                return genero;            
            }else{
                return null;
            }
        }catch(Exception e){
            System.out.println("Error en DAOGenero.obtenerGenerobyID: " + e.getMessage());
            return null;
        }
    }

    public boolean crearGenero(Genero genero){
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = conexion.getConexion();
            String sql = "INSERT INTO genero (ID, Nombre) VALUES (?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, genero.getID());
            ps.setString(2, genero.getNombre());
            int res = ps.executeUpdate();
            if (res>0) {
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            System.out.println("Error en DAOGenero.crearGenero: " + e.getMessage());
            return false;
        }
    }

}
