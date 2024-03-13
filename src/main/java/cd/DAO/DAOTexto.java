package cd.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import cd.DAO.Conexion.Conexion;
import cd.Modelo.Texto;

public class DAOTexto {
    private static Conexion conexion = new Conexion();

    public boolean crearTexto(Texto texto){
        Connection con = null;
        PreparedStatement ps = null;        
        try{
            con = conexion.getConexion();        
            ps = con.prepareStatement("INSERT INTO texto (ID, LinkFoto, Titulo, Ano, Codigo, IDAutor, NumPaginas, Ubicacion, Disponibilidad, Resena, Tipo, Atributos) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, texto.getID());
            ps.setString(2, texto.getLinkFoto());
            ps.setString(3, texto.getTipo());
            ps.setInt(4, texto.getAno());
            ps.setString(5, texto.getCodigo());
            ps.setString(6, texto.getIDAutor());
            ps.setInt(7, texto.getNumPaginas());
            ps.setString(8, texto.getUbicacion());
            ps.setString(9, texto.getDisponibilidad());
            ps.setString(10, texto.getResena());
            ps.setString(11, texto.getTipo());            
            ps.setString(12, texto.getAtributos().toString());            
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

    public boolean eliminarTexto(String ID){
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = conexion.getConexion();
            ps = con.prepareStatement("DELETE FROM texto WHERE ID = ?");
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

    public boolean editarTexto(Texto texto){
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = conexion.getConexion();
            ps = con.prepareStatement("UPDATE texto SET LinkFoto = ?, Titulo = ?, Ano = ?, Codigo = ?, IDAutor = ?, NumPaginas = ?, Ubicacion = ?, Disponibilidad = ?, Resena = ?, Tipo = ?, Atributos = ? WHERE ID = ?");
            ps.setString(1, texto.getLinkFoto());
            ps.setString(2, texto.getTitulo());
            ps.setInt(3, texto.getAno());
            ps.setString(4, texto.getCodigo());
            ps.setString(5, texto.getIDAutor());
            ps.setInt(6, texto.getNumPaginas());
            ps.setString(7, texto.getUbicacion());
            ps.setString(8, texto.getDisponibilidad());
            ps.setString(9, texto.getResena());
            ps.setString(10, texto.getTipo());            
            ps.setString(11, texto.getAtributos());
            ps.setString(12, texto.getID());
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

    public ArrayList<Texto> obtenerTextos(){
        ArrayList<Texto> textos = new ArrayList<Texto>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = conexion.getConexion();
            ps = con.prepareStatement("SELECT * FROM texto");
            rs = ps.executeQuery();
            while(rs.next()){
                Texto texto = new Texto();
                texto.setID(rs.getString("ID"));
                texto.setLinkFoto(rs.getString("LinkFoto"));
                texto.setTitulo(rs.getString("Titulo"));
                texto.setAno(rs.getInt("Ano"));
                texto.setCodigo(rs.getString("Codigo"));
                texto.setIDAutor(rs.getString("IDAutor"));
                texto.setNumPaginas(rs.getInt("NumPaginas"));
                texto.setUbicacion(rs.getString("Ubicacion"));
                texto.setDisponibilidad(rs.getString("Disponibilidad"));
                texto.setResena(rs.getString("Resena"));
                texto.setTipo(rs.getString("Tipo"));
                texto.setAtributos(rs.getString("Atributos"));
                textos.add(texto);
            }
            return textos;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

}