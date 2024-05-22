package cd.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import cd.DAO.Conexion.Conexion;
import cd.Modelo.Autor;

public class DAOAutor {
    private static Conexion conexion = new Conexion();    

    public ArrayList<Autor> obtenerAutores() {
        ArrayList<Autor> autores = new ArrayList<Autor>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection c = null;
        try{
            c = conexion.getConexion();
            ps = c.prepareStatement("SELECT * FROM Autor");
            rs = ps.executeQuery();
            Autor autor = null;
            while(rs.next()){
                autor = new Autor();
                autor.setID(rs.getString(1));
                autor.setNombre(rs.getString(2));
                autor.setPaterno(rs.getString(3));
                autor.setMaterno(rs.getString(4));
                autores.add(autor);
            }
            return autores;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        finally {
            try{
                c.close();
                if (c.isClosed()){
                    conexion.cerrarConexion();
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    public String crearAutor(Autor autor){
        PreparedStatement ps = null;
        Connection c = null;
        try{
            c = conexion.getConexion();
            ps = c.prepareStatement("INSERT INTO Autor (ID, nombre, paterno, materno) VALUES (?,?,?,?)");
            ps.setString(1, autor.getID());
            ps.setString(2, autor.getNombre());
            ps.setString(3, autor.getPaterno());
            ps.setString(4, autor.getMaterno());
            int res = ps.executeUpdate();            
            if (res>0) {
                return autor.getID();
            }else{
                return "Error al crear autor";
            }            
        }catch(Exception e){
            e.printStackTrace();
            return "Error al crear autor";
        } finally {
            try{
                c.close();
                if (c.isClosed()){
                    conexion.cerrarConexion();
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public boolean eliminarAutor(String id){
        PreparedStatement ps = null;
        Connection c = null;
        try{
            c = conexion.getConexion();
            ps = c.prepareStatement("DELETE FROM Autor WHERE ID = ?");
            ps.setString(1, id);
            int res = ps.executeUpdate();            
            if (res>0) {
                return true;
            }else{
                return false;
            }            
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally {
            try{
                c.close();
                if (c.isClosed()){
                    conexion.cerrarConexion();
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public boolean editarAutor(Autor autor){
        PreparedStatement ps = null;
        Connection c = null;
        try{
            c = conexion.getConexion();
            ps = c.prepareStatement("UPDATE Autor SET nombre = ?, paterno = ?, materno = ? WHERE ID = ?");
            ps.setString(1, autor.getNombre());
            ps.setString(2, autor.getPaterno());
            ps.setString(3, autor.getMaterno());
            ps.setString(4, autor.getID());
            int res = ps.executeUpdate();            
            if (res>0) {
                return true;
            }else{
                return false;
            }            
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            try{
                c.close();
                if (c.isClosed()){
                    conexion.cerrarConexion();
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public Autor obtenerAutor(String id){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection c = null;
        try{
            c = conexion.getConexion();
            ps = c.prepareStatement("SELECT * FROM Autor WHERE ID = ?");
            ps.setString(1, id);
            rs = ps.executeQuery();
            Autor autor = null;
            while(rs.next()){
                autor = new Autor();
                autor.setID(rs.getString(1));
                autor.setNombre(rs.getString(2));
                autor.setPaterno(rs.getString(3));
                autor.setMaterno(rs.getString(4));
            }
            return autor;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        finally {
            try{
                c.close();
                if (c.isClosed()){
                    conexion.cerrarConexion();
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }


}