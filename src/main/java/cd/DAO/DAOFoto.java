package cd.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cd.DAO.Conexion.Conexion;
import cd.Modelo.Foto;

public class DAOFoto {
    private static Conexion conexion = new Conexion();
    
    public String crearFoto(Foto foto) {
        System.out.println(foto.toString());
        PreparedStatement ps = null;
        Connection c = null;
        try {
            c = conexion.getConexion();
            ps = c.prepareStatement(
                    "insert into Foto(ID, LinkFoto) values (?,?)");
            ps.setString(1, foto.getID());
            ps.setString(2, foto.getLinkFoto());
            ps.executeUpdate();
            return "Foto Creada";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al crear foto";
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

    public String eliminarFoto(String ID) {
        PreparedStatement ps = null;
        Connection c = null;
        try {
            c = conexion.getConexion();
            ps = c.prepareStatement("delete from Foto where ID = ?");
            ps.setString(1, ID);
            ps.executeUpdate();
            return "Foto Eliminada";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al eliminar foto";
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

    public Foto obtenerFoto(String ID){
        PreparedStatement ps = null;
        Connection c = null;
        ResultSet rs = null;
        try {
            c = conexion.getConexion();
            ps = c.prepareStatement("select * from Foto where ID = ?");
            ps.setString(1, ID);
            rs = ps.executeQuery();
            Foto foto;
            if (rs.next()) {
                foto = new Foto();
                foto.setID(rs.getString("ID"));
                foto.setLinkFoto(rs.getString("LinkFoto"));
                return foto;
            }else{
                return null;
            }
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
    
}
