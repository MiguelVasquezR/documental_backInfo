package cd.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import cd.DAO.Conexion.Conexion;
import cd.Modelo.Pelicula;

public class DAOPelicula {
    private static Conexion conexion = new Conexion();   
    
    private String ID;
    private String Codigo;
    private String Titulo;
    private String Director;    
    private int Ano;    
    private String Proviene;
    private String Genero;
    private String LinkFoto;
    
    public ArrayList<Pelicula> obtenerPeliculas(){
        ArrayList<Pelicula> peliculas = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = conexion.getConexion();
            String query = "SELECT * FROM pelicula";
            ps = connection.prepareStatement(query);            
            rs = ps.executeQuery(query);
            Pelicula p;
            while(rs.next()){
                p = new Pelicula();
                p.setID(rs.getString("ID"));
                p.setTitulo(rs.getString("Titulo"));
                p.setLinkFoto(rs.getString("Duracion"));                
                p.setAno(rs.getInt("Ano"));
                p.setLinkFoto(rs.getString("LinkFoto"));
                p.setDirector(rs.getString("IDAutor"));                                                
                peliculas.add(p);
            }
            return peliculas;            
        } catch (Exception e) {            
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean crearPelicula(Pelicula p){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = conexion.getConexion();
            String query = "INSERT INTO pelicula (ID, Codigo, Titulo, Director, Ano, Proviene, Genero, LinkFoto) VALUES (?,?,?,?,?,?,?,?)";
            ps = connection.prepareStatement(query);
            ps.setString(1, p.getID());
            ps.setString(2, p.getCodigo());
            ps.setString(3, p.getTitulo());
            ps.setString(4, p.getDirector());
            ps.setInt(5, p.getAno());
            ps.setString(6, p.getProviene());
            ps.setString(7, p.getGenero());
            ps.setString(8, p.getLinkFoto());            
            int res = ps.executeUpdate();
            if (res>0) {
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarPelicula(String ID){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = conexion.getConexion();
            String query = "DELETE FROM pelicula WHERE ID = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, ID);
            int res = ps.executeUpdate();
            if (res>0) {
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editarPelicula(Pelicula p){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = conexion.getConexion();
            String query = "UPDATE pelicula SET Codigo = ?, Titulo = ?, Director = ?, Ano = ?, Proviene = ?, Genero = ?, LinkFoto = ? WHERE ID = ?";    
            ps = connection.prepareStatement(query);
            ps.setString(1, p.getCodigo());
            ps.setString(2, p.getTitulo());
            ps.setString(3, p.getDirector());
            ps.setInt(4, p.getAno());
            ps.setString(5, p.getProviene());
            ps.setString(6, p.getGenero());
            ps.setString(7, p.getLinkFoto());
            ps.setString(8, p.getID());
            int res = ps.executeUpdate();
            if (res>0) {
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
