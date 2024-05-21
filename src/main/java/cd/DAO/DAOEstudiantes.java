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

    public Estudiante getEstudiante(String id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Estudiante estudiante = new Estudiante();
        try {
            c = conexion.getConexion();
            ps = c.prepareStatement("SELECT * FROM Estudiante where ID = ?");
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                estudiante = new Estudiante();
                estudiante.setID(rs.getString("ID"));
                estudiante.setNombre(rs.getString("Nombre"));
                estudiante.setPaterno(rs.getString("Paterno"));
                estudiante.setMaterno(rs.getString("Materno"));
                estudiante.setMatricula(rs.getString("Matricula"));
                estudiante.setTelefono(rs.getString("Telefono"));
                estudiante.setCorreo(rs.getString("Correo"));
            }
            return estudiante;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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

    public String crearEstudiante(Estudiante estudiante) {
        PreparedStatement ps = null;
        Connection c = null;
        try {
            String verificar = verficarExistencia(estudiante.getMatricula());
            if (verificar.equals("No Existe")) {                
                c = conexion.getConexion();
                ps = c.prepareStatement(
                        "insert into Estudiante(ID, Nombre, Materno, Paterno, Matricula, Correo, Telefono) values (?,?,?,?,?,?,?)");
                ps.setString(1, estudiante.getID());
                ps.setString(2, estudiante.getNombre());
                ps.setString(3, estudiante.getMaterno());
                ps.setString(4, estudiante.getPaterno());
                ps.setString(5, estudiante.getMatricula());
                ps.setString(6, estudiante.getCorreo());
                ps.setString(7, estudiante.getTelefono());
                int res = ps.executeUpdate();
                if (res > 0) {
                    return "Creado";
                } else {
                    return "No Creado";
                }
            }else if(verificar.equals("Existe")){
                return "Usuario Existente";
            }else{
                return "Error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "No se ha agregado";
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

    public boolean eliminarEstudiante(String ID) {
        PreparedStatement ps = null;
        Connection c = null;
        try {
            c = conexion.getConexion();
            ps = c.prepareStatement("delete from Estudiante where ID = ?");
            ps.setString(1, ID);
            int res = ps.executeUpdate();
            if (res > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
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

    public boolean editarEstudiante(Estudiante estudiante) {
        PreparedStatement ps = null;
        Connection c = null;
        try {
            c = conexion.getConexion();
            ps = c.prepareStatement(
                    "update Estudiante set Nombre = ?, Materno = ?, Paterno = ?, Matricula = ?, Correo = ?, Telefono = ? where ID = ?");
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getMaterno());
            ps.setString(3, estudiante.getPaterno());
            ps.setString(4, estudiante.getMatricula());
            ps.setString(5, estudiante.getCorreo());
            ps.setString(6, estudiante.getTelefono());
            ps.setString(7, estudiante.getID());

            int res = ps.executeUpdate();
            if (res > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
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

    private String verficarExistencia(String matricula) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = conexion.getConexion();
            ps = c.prepareStatement("SELECT * FROM Estudiante WHERE Matricula = ?");
            ps.setString(1, matricula);
            rs = ps.executeQuery();
            if (rs.next()) {
                return "Existe";
            } else {
                return "No Existe";
            }
        } catch (Exception e) {
            return "Error";
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

    public Estudiante obtenerEstudianteByMatricula(String matricula){
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = conexion.getConexion();
            ps = c.prepareStatement("SELECT * FROM Estudiante WHERE Matricula = ?");
            ps.setString(1, matricula);
            rs = ps.executeQuery();
            Estudiante estudiante = null;
            if(rs.next()){
                estudiante = new Estudiante();
                estudiante.setID(rs.getString("ID"));
                estudiante.setNombre(rs.getString("Nombre"));
                estudiante.setPaterno(rs.getString("Paterno"));
                estudiante.setMaterno(rs.getString("Materno"));
                estudiante.setMatricula(rs.getString("Matricula"));
                estudiante.setTelefono(rs.getString("Telefono"));
                estudiante.setCorreo(rs.getString("Correo"));
                return estudiante;
            }else{
                return null;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
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




}
