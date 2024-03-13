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
            ps = con.prepareStatement("INSERT INTO prestamo (ID, FechaPrestamo, FechaRegreso, IDTexto, IDEstudiante) VALUES (?,?,?,?,?)");
            ps.setString(1, prestamo.getID());

            java.sql.Date sqlPrestamo = new java.sql.Date(prestamo.getFechaPrestamo().getTime());
            ps.setDate(2, sqlPrestamo);

            java.sql.Date sqlRegreso = new java.sql.Date(prestamo.getFechaRegreso().getTime());
            ps.setDate(3, sqlRegreso);

            ps.setString(4, prestamo.getIDTexto());
            ps.setString(5, prestamo.getIDEstudiante());
                        
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
        
    }

    public ArrayList<Prestamo> obtenerPrestamos() {
        ArrayList<Prestamo> prestamos = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = conexion.getConexion();
            ps = con.prepareStatement("SELECT * FROM prestamo");
            rs = ps.executeQuery();
            while(rs.next()){
                Prestamo prestamo = new Prestamo();
                prestamo.setID(rs.getString("ID"));
                prestamo.setFechaPrestamo(rs.getDate("FechaPrestamo"));
                prestamo.setFechaRegreso(rs.getDate("FechaRegreso"));
                prestamo.setIDTexto(rs.getString("IDTexto"));
                prestamo.setIDEstudiante(rs.getString("IDEstudiante"));
                prestamos.add(prestamo);
            }
            return prestamos;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean eliminarPrestamo(String id) {
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = conexion.getConexion();
            ps = con.prepareStatement("DELETE FROM prestamo WHERE ID = ?");
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
        }
    }

    public boolean editarPrestamo(Prestamo prestamo) {
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = conexion.getConexion();
            ps = con.prepareStatement("UPDATE prestamo SET FechaPrestamo = ?, FechaRegreso = ?, IDTexto = ?, IDEstudiante = ? WHERE ID = ?");
            
            java.sql.Date sqlPrestamo = new java.sql.Date(prestamo.getFechaPrestamo().getTime());
            ps.setDate(1, sqlPrestamo);

            java.sql.Date sqlRegreso = new java.sql.Date(prestamo.getFechaRegreso().getTime());
            ps.setDate(2, sqlRegreso);

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
        }
    }

}