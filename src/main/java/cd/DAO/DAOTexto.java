package cd.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import cd.DAO.Conexion.Conexion;
import cd.Modelo.Texto;
import com.google.gson.JsonObject;

public class DAOTexto {
    private static Conexion conexion = new Conexion();

    public String crearTexto(Texto texto){
        Connection con = null;
        PreparedStatement ps = null;

        if (verificarCodigoTexto(texto.getCodigo()).equals("Existe")) {
            return "Existe";
        }

        try{
            con = conexion.getConexion();        
            ps = con.prepareStatement("INSERT INTO Texto (ID, LinkFoto, Titulo, Ano, Codigo, IDAutor, NumPaginas, Ubicacion, Disponibilidad, Resena, Tipo, Atributos) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, texto.getID());
            ps.setString(2, texto.getLinkFoto());
            ps.setString(3, texto.getTitulo());
            ps.setInt(4, texto.getAno());
            ps.setString(5, texto.getCodigo());
            ps.setString(6, texto.getIDAutor());
            ps.setInt(7, texto.getNumPaginas());
            ps.setString(8, texto.getUbicacion());
            ps.setString(9, texto.getDisponibilidad());
            ps.setString(10, texto.getResena());
            ps.setString(11, texto.getTipo());            
            ps.setString(12, texto.getAtributos());            
            int res = ps.executeUpdate();
            if (res>0) {
                return "Creado";
            }else{
                return "No Creado";
            }
        }catch(Exception e){
            e.printStackTrace();
            return "No Creado";
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

    public boolean eliminarTexto(String ID){
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = conexion.getConexion();
            ps = con.prepareStatement("DELETE FROM Texto WHERE ID = ?");
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

    public boolean editarTexto(Texto texto){
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = conexion.getConexion();
            ps = con.prepareStatement("UPDATE Texto SET Codigo = ?, Titulo = ?, NumPaginas = ?, Resena = ?, Ano = ?, Ubicacion = ?, Tipo = ? WHERE ID = ?");
            ps.setString(1, texto.getCodigo());
            ps.setString(2, texto.getTitulo());
            ps.setInt(3, texto.getNumPaginas());
            ps.setString(4, texto.getResena());
            ps.setInt(5, texto.getAno());
            ps.setString(6, texto.getUbicacion());
            ps.setString(7, texto.getTipo());
            ps.setString(8, texto.getID());
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

    public ArrayList<Texto> obtenerTextos(){
        ArrayList<Texto> textos = new ArrayList<Texto>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = conexion.getConexion();
            ps = con.prepareStatement("SELECT * FROM Texto");
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

    public ArrayList<JsonObject> getBiblioteca(){
        ArrayList<JsonObject> jsonObjects = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection c = null;
        try{
            c = conexion.getConexion();
            ps = c.prepareStatement("select t.ID, t.Titulo, a.Nombre, a.Paterno, a.Materno, f.LinkFoto from Texto as t, Foto as f, Autor as a where t.LinkFoto = f.ID and t.IDAutor = a.ID;");
            rs = ps.executeQuery();

            while(rs.next()){
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("ID", rs.getString("ID"));
                jsonObject.addProperty("Titulo", rs.getString("Titulo"));
                jsonObject.addProperty("Nombre", rs.getString("Nombre"));
                jsonObject.addProperty("Paterno", rs.getString("Paterno"));
                jsonObject.addProperty("Materno", rs.getString("Materno"));
                jsonObject.addProperty("LinkFoto", rs.getString("LinkFoto"));
                jsonObjects.add(jsonObject);
            }
            return jsonObjects;
        }catch (Exception e){
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

    public JsonObject VisualizarTexto(String ID){
        JsonObject jsonObject = new JsonObject();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = conexion.getConexion();
            ps = c.prepareStatement("select t.ID, t.Titulo, t.Ano, t.Codigo, t.NumPaginas, t.Ubicacion, t.Disponibilidad, t.Resena, t.Tipo, t.Atributos, f.LinkFoto, a.Nombre, a.Paterno, a.Materno from Foto as f, Texto as t, Autor as a where f.ID = t.LinkFoto and a.ID = t.IDAutor and t.ID = ?;");
            ps.setString(1, ID);
            rs = ps.executeQuery();
            if (rs.next()){
                jsonObject.addProperty("ID", rs.getString("ID"));
                jsonObject.addProperty("Titulo", rs.getString("Titulo"));
                jsonObject.addProperty("Ano", rs.getInt("Ano"));
                jsonObject.addProperty("Codigo", rs.getString("Codigo"));
                jsonObject.addProperty("NumPaginas", rs.getInt("NumPaginas"));
                jsonObject.addProperty("Ubicacion", rs.getString("Ubicacion"));
                jsonObject.addProperty("Disponibilidad", rs.getString("Disponibilidad"));
                jsonObject.addProperty("Resena", rs.getString("Resena"));
                jsonObject.addProperty("Tipo", rs.getString("Tipo"));
                jsonObject.addProperty("Atributos", rs.getString("Atributos"));
                jsonObject.addProperty("LinkFoto", rs.getString("LinkFoto"));
                jsonObject.addProperty("Nombre", rs.getString("Nombre"));
                jsonObject.addProperty("Paterno", rs.getString("Paterno"));
                jsonObject.addProperty("Materno", rs.getString("Materno"));
                return jsonObject;
            }else{
                return null;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return  null;
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

    public String verificarCodigoTexto(String codigo){
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = conexion.getConexion();
            ps = c.prepareStatement("select * from Texto where Codigo = ?");
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            if (rs.next()){
                return "Existe";
            }else{
                return "";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "";
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

    public ArrayList<JsonObject> obtenerTextoParaTabla(){
        ArrayList<JsonObject> jsonObjects = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection c=null;

        try{
            c = conexion.getConexion();
            ps = c.prepareStatement("select a.ID as IDAutor, t.ID as IDTexto, a.Nombre, a.Paterno, a.Materno, t.Titulo, t.Codigo from Autor as a, Texto as t where a.ID = t.IDAutor;");
            rs = ps.executeQuery();
            JsonObject texto = null;
            while(rs.next()){
                texto = new JsonObject();
                texto.addProperty("IDTexto", rs.getString("IDTexto"));
                texto.addProperty("IDAutor", rs.getString("IDAutor"));
                texto.addProperty("Nombre", rs.getString("Nombre"));
                texto.addProperty("Paterno", rs.getString("Paterno"));
                texto.addProperty("Materno", rs.getString("Materno"));
                texto.addProperty("Titulo", rs.getString("Titulo"));
                texto.addProperty("Codigo", rs.getString("Codigo"));
                System.out.println(texto);
                jsonObjects.add(texto);
            }
            return jsonObjects;
        }catch (Exception e) {
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

    public JsonObject obtenerLibroByCodigo(String codigo){
        JsonObject texto =null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            texto = new JsonObject();
            c = conexion.getConexion();
            ps = c.prepareStatement("select f.LinkFoto, a.ID as IDAutor, t.ID as IDTexto, a.Nombre, a.Paterno, a.Materno, t.Titulo, t.Codigo from Autor as a, Texto as t, Foto as f where a.ID = t.IDAutor and f.ID = t.LinkFoto and t.Codigo = ?;");
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            if (rs.next()){
                texto.addProperty("IDTexto", rs.getString("IDTexto"));
                texto.addProperty("IDAutor", rs.getString("IDAutor"));
                texto.addProperty("Nombre", rs.getString("Nombre"));
                texto.addProperty("Paterno", rs.getString("Paterno"));
                texto.addProperty("Materno", rs.getString("Materno"));
                texto.addProperty("Titulo", rs.getString("Titulo"));
                texto.addProperty("Codigo", rs.getString("Codigo"));
                texto.addProperty("LinkFoto", rs.getString("LinkFoto"));
                return  texto;
            }else{
                return null;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return  null;
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

    public JsonObject obtenerInfoPrestamo(String id){
        JsonObject jsonObject = new JsonObject();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = conexion.getConexion();
            ps = c.prepareStatement("select t.Titulo, f.LinkFoto from Texto as t, Foto as f where t.LinkFoto = f.ID and t.ID = ?;");
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()){
                jsonObject.addProperty("Titulo", rs.getString("Titulo"));
                jsonObject.addProperty("LinkFoto", rs.getString("LinkFoto"));
            }
            return jsonObject;
        }catch (Exception e){
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

    public String obtenerID(String codigo){
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            c = conexion.getConexion();
            ps = c.prepareStatement("select ID from Texto where Codigo = ?");
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            if (rs.next()){
                return rs.getString("ID");
            }else{
                return "";
            }

        }catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
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

    //Método para obtener el texto por su código
    public JsonObject obtenerTextoCodigo(String codigo){
        JsonObject texto = new JsonObject();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = conexion.getConexion();
            preparedStatement = connection.prepareStatement("select l.LinkFoto, t.ID as IDTexto, t.Titulo, t.Ano, t.Codigo, t.NumPaginas, t.Ubicacion, t.Resena, t.Tipo, a.Nombre, a.Paterno, a.Materno, a.ID as IDAutor from Texto as t, Autor as a, Foto as l where t.IDAutor = a.ID and l.ID = t.LinkFoto and t.Codigo = ?;");
            preparedStatement.setString(1, codigo);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                texto.addProperty("LinkFoto", resultSet.getString("LinkFoto"));
                texto.addProperty("IDTexto", resultSet.getString("IDTexto"));
                texto.addProperty("Titulo", resultSet.getString("Titulo"));
                texto.addProperty("Ano", resultSet.getInt("Ano"));
                texto.addProperty("Codigo", resultSet.getString("Codigo"));
                texto.addProperty("NumPaginas", resultSet.getInt("NumPaginas"));
                texto.addProperty("Ubicacion", resultSet.getString("Ubicacion"));
                texto.addProperty("Resena", resultSet.getString("Resena"));
                texto.addProperty("Tipo", resultSet.getString("Tipo"));

                texto.addProperty("Nombre", resultSet.getString("Nombre"));
                texto.addProperty("Paterno", resultSet.getString("Paterno"));
                texto.addProperty("Materno", resultSet.getString("Materno"));
                texto.addProperty("IDAutor", resultSet.getString("IDAutor"));
                return texto;
            }else{
                return null;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
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


}