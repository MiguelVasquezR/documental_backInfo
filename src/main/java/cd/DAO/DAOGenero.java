package cd.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
        }finally {
            try{
                con.close();
                if (con.isClosed()){
                    conexion.cerrarConexion();
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public boolean crearGenero(Genero genero){
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = conexion.getConexion();
            String sql = "INSERT INTO genero (ID, Nombre, IDPelicula) VALUES (?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, genero.getID());
            ps.setString(2, genero.getNombre());
            ps.setString(3, genero.getIDPelicula());
            int res = ps.executeUpdate();
            if (res>0) {
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            System.out.println("Error en DAOGenero.crearGenero: " + e.getMessage());
            return false;
        }finally {
            try{
                con.close();
                if (con.isClosed()){
                    conexion.cerrarConexion();
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public ArrayList<Genero> obtenerGeneros(){
        ArrayList<Genero> generos = new ArrayList<Genero>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;

        try{
            con = conexion.getConexion();
            String sql = "SELECT * FROM genero";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Genero genero = new Genero();
                genero.setID(rs.getString("ID"));
                genero.setNombre(rs.getString("Nombre"));
                genero.setIDPelicula(rs.getString("IDPelicula"));
                generos.add(genero);
            }
            return generos;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }finally {
            try{
                con.close();
                if (con.isClosed()){
                    conexion.cerrarConexion();
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

}
