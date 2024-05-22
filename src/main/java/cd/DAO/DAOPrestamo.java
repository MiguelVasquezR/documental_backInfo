package cd.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import cd.DAO.Conexion.Conexion;
import cd.Modelo.Prestamo;

public class DAOPrestamo {
    private static Conexion conexion = new Conexion();    

    public boolean crearPrestamo(Prestamo prestamo) {
        Connection con = null;
        PreparedStatement ps = null;

        try{
            con = conexion.getConexion();        
            ps = con.prepareStatement("INSERT INTO Prestamo (ID, FechaPrestamo, FechaRegreso, IDTexto, IDEstudiante, Estado) VALUES (?,?,?,?,?,?)");
            ps.setString(1, prestamo.getID());
            ps.setString(2, prestamo.getFechaPrestamo());
            ps.setString(3, prestamo.getFechaRegreso());
            ps.setString(4, prestamo.getIDTexto());
            ps.setString(5, prestamo.getIDEstudiante());
            ps.setString(6, prestamo.getEstado());
                        
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
                con.close();
                if (con.isClosed()){
                    conexion.cerrarConexion();
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        
    }

    public ArrayList<Prestamo> obtenerPrestamos() {
        ArrayList<Prestamo> prestamos = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = conexion.getConexion();
            ps = con.prepareStatement("SELECT * FROM Prestamo");
            rs = ps.executeQuery();
            while(rs.next()){
                Prestamo prestamo = new Prestamo();
                prestamo.setID(rs.getString("ID"));
                prestamo.setFechaPrestamo(rs.getString("FechaPrestamo"));
                prestamo.setFechaRegreso(rs.getString("FechaRegreso"));
                prestamo.setIDTexto(rs.getString("IDTexto"));
                prestamo.setIDEstudiante(rs.getString("IDEstudiante"));
                prestamo.setEstado(rs.getString("Estado"));
                prestamos.add(prestamo);
            }
            return prestamos;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally {
            try{
                if (!con.isClosed()){
                    con.close();
                    ps.close();
                    rs.close();
                }

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    public boolean eliminarPrestamo(String id) {
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = conexion.getConexion();
            ps = con.prepareStatement("DELETE FROM Prestamo WHERE ID = ?");
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
                con.close();
                if (con.isClosed()){
                    conexion.cerrarConexion();
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public boolean editarPrestamo(Prestamo prestamo) {
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = conexion.getConexion();
            ps = con.prepareStatement("UPDATE Prestamo SET FechaPrestamo = ?, FechaRegreso = ?, IDTexto = ?, IDEstudiante = ? WHERE ID = ?");

            ps.setString(1, prestamo.getFechaPrestamo());
            ps.setString(2, prestamo.getFechaRegreso());

            ps.setString(3, prestamo.getIDTexto());
            ps.setString(4, prestamo.getIDEstudiante());
            ps.setString(5, prestamo.getID());
            
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
                con.close();
                if (con.isClosed()){
                    conexion.cerrarConexion();
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public Prestamo devolucion(String ID){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            connection = conexion.getConexion();
            ps = connection.prepareStatement("SELECT * FROM Prestamo WHERE IDTexto = ?");
            ps.setString(1, ID);
            rs = ps.executeQuery();
            if(rs.next()){
                Prestamo prestamo = new Prestamo();
                prestamo.setID(rs.getString("ID"));
                prestamo.setFechaPrestamo(rs.getString("FechaPrestamo"));
                prestamo.setFechaRegreso(rs.getString("FechaRegreso"));
                prestamo.setIDTexto(rs.getString("IDTexto"));
                prestamo.setIDEstudiante(rs.getString("IDEstudiante"));
                prestamo.setEstado(rs.getString("Estado"));
                return prestamo;
            }else{
                return null;
            }

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        finally {
            try{
                connection.close();
                if (connection.isClosed()){
                    conexion.cerrarConexion();
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public boolean confirmarDevolucion(String id) {
        Connection connection = null;
        PreparedStatement ps = null;

        try{
            connection = conexion.getConexion();
            ps = connection.prepareStatement("UPDATE Prestamo SET Estado = 'Devuelto' WHERE ID = ?");
            ps.setString(1, id);
            int res = ps.executeUpdate();
            if(res>0){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }finally {
            try{
                connection.close();
                if (connection.isClosed()){
                    conexion.cerrarConexion();
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

    }

    public boolean libroDisponible(String codigo){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            connection = conexion.getConexion();
            ps = connection.prepareStatement("select Estado from Prestamo as p, texto as t where p.IDTexto = t.ID and t.Codigo = ?;");
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            if(rs.next()) {
                if (rs.getString("Estado").equals("Prestado")) {
                    return false;
                } else {
                    return true;
                }
            }
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        finally {
            try{
                connection.close();
                if (connection.isClosed()){
                    conexion.cerrarConexion();
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }



}