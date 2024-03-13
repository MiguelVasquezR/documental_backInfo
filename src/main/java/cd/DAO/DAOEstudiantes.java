package cd.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import cd.DAO.Conexion.Conexion;
import cd.Modelo.Estudiante;

public class DAOEstudiantes {

    private static Conexion conexion = new Conexion();

    public ArrayList<Estudiante> getEstudiantes() {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
        try {
            c = conexion.getConexion();
            ps = c.prepareStatement("SELECT * FROM Estudiante");
            rs = ps.executeQuery();
            Estudiante estudiante = null;
            while (rs.next()) {
                estudiante = new Estudiante();
                estudiante.setID(rs.getString("ID"));
                estudiante.setNombre(rs.getString("Nombre"));                
                estudiante.setPaterno(rs.getString("Paterno"));
                estudiante.setMaterno(rs.getString("Materno"));
                estudiante.setMatricula(rs.getString("Matricula"));
                estudiante.setTelefono(rs.getString("Telefono"));
                estudiante.setCorreo(rs.getString("Correo"));
                estudiantes.add(estudiante);
            }
            return estudiantes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean crearEstudiante(Estudiante estudiante){
        PreparedStatement ps = null;
        Connection c = null;    
        try{
            c = conexion.getConexion();            
            ps = c.prepareStatement("insert into Estudiante(ID, Nombre, Materno, Paterno, Matricula, Correo, Telefono) values (?,?,?,?,?,?,?)");
            ps.setString(1, estudiante.getID());
            ps.setString(2, estudiante.getNombre());
            ps.setString(3, estudiante.getMaterno());
            ps.setString(4, estudiante.getPaterno());
            ps.setString(5, estudiante.getMatricula());
            ps.setString(6, estudiante.getCorreo());
            ps.setString(7, estudiante.getTelefono());
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

    public boolean eliminarEstudiante(String ID){
        PreparedStatement ps = null;
        Connection c = null;
        try{
            c = conexion.getConexion();
            ps = c.prepareStatement("delete from Estudiante where ID = ?");
            ps.setString(1, ID);
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

    public boolean editarEstudiante(Estudiante estudiante){
        PreparedStatement ps = null;
        Connection c = null;    
        try{
            c = conexion.getConexion();            
            ps = c.prepareStatement("update Estudiante set Nombre = ?, Materno = ?, Paterno = ?, Matricula = ?, Correo = ?, Telefono = ? where ID = ?");
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getMaterno());
            ps.setString(3, estudiante.getPaterno());
            ps.setString(4, estudiante.getMatricula());
            ps.setString(5, estudiante.getCorreo());
            ps.setString(6, estudiante.getTelefono());
            ps.setString(7, estudiante.getID());

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
