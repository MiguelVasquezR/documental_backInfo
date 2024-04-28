package cd.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import cd.DAO.Conexion.Conexion;
import cd.Modelo.Pelicula;
import com.google.gson.JsonObject;

public class DAOPelicula {
    private static Conexion conexion = new Conexion();   

    public boolean crearPelicula(Pelicula pelicula) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = conexion.getConexion();
            ps = con.prepareStatement("INSERT INTO pelicula (ID, Codigo, Titulo, Tipo, Ano, Proviene, LinkFoto, IDAutor ) VALUE (?,?,?,?,?,?,?,?)");
            ps.setString(1, pelicula.getID());
            ps.setString(2, pelicula.getCodigo());
            ps.setString(3, pelicula.getTitulo());
            ps.setString(4, pelicula.getTipo());
            ps.setInt(5, pelicula.getAno());
            ps.setString(6, pelicula.getOrigen());
            ps.setString(7, pelicula.getPortada());
            ps.setString(8, pelicula.getIDAutor());
            int res = ps.executeUpdate();
            if (res > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<JsonObject> obtenerPeliculas() {
        ArrayList<JsonObject> peliculas = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = conexion.getConexion();
            ps = con.prepareStatement("select p.ID, p.Codigo, p.Titulo, p.Tipo, p.Ano, p.Proviene, f.LinkFoto, a.Nombre, a.Paterno, a.Materno from pelicula as p, autor as a, foto as f where p.IDAutor = a.ID and f.ID = p.LinkFoto;");
            rs = ps.executeQuery();
            JsonObject jsonObject;
            while (rs.next()) {
                jsonObject = new JsonObject();
                jsonObject.addProperty("ID", rs.getString("ID"));
                jsonObject.addProperty("Codigo", rs.getString("Codigo"));
                jsonObject.addProperty("Titulo", rs.getString("Titulo"));
                jsonObject.addProperty("Tipo", rs.getString("Tipo"));
                jsonObject.addProperty("Ano", rs.getInt("Ano"));
                jsonObject.addProperty("Proviene", rs.getString("Proviene"));
                jsonObject.addProperty("LinkFoto", rs.getString("LinkFoto"));
                jsonObject.addProperty("Nombre", rs.getString("Nombre"));
                jsonObject.addProperty("Paterno", rs.getString("Paterno"));
                jsonObject.addProperty("Materno", rs.getString("Materno"));
                peliculas.add(jsonObject);
            }
            return  peliculas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean eliminarPelicula(String id) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = conexion.getConexion();
            ps = con.prepareStatement("DELETE FROM pelicula WHERE ID = ?");
            ps.setString(1, id);
            int res = ps.executeUpdate();
            if (res > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editarPelicula(Pelicula pelicula) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = conexion.getConexion();
            ps = con.prepareStatement("UPDATE pelicula SET Codigo = ?, Titulo = ?, Tipo = ?, Ano = ?, Proviene = ?, LinkFoto = ?, IDAutor = ? WHERE ID = ?");
            ps.setString(1, pelicula.getCodigo());
            ps.setString(2, pelicula.getTitulo());
            ps.setString(3, pelicula.getTipo());
            ps.setInt(4, pelicula.getAno());
            ps.setString(5, pelicula.getOrigen());
            ps.setString(6, pelicula.getPortada());
            ps.setString(7, pelicula.getIDAutor());
            ps.setString(8, pelicula.getID());
            int res = ps.executeUpdate();
            if (res > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }




}
