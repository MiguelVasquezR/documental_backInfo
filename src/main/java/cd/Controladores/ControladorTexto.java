package cd.Controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLOutput;
import java.util.UUID;

import cd.DAO.DAOPrestamo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import cd.DAO.DAOTexto;
import cd.Modelo.Texto;
import spark.Request;
import spark.Response;

public class ControladorTexto {

    private static DAOTexto daoTexto = new DAOTexto();
    private static Gson gson = new Gson();

    public static String obtenerTextos(Request req, Response res) {
        return gson.toJson(daoTexto.obtenerTextos());
    }

    public static JsonObject crearTexto(Request req, Response res) {
        JsonObject respuesta = new JsonObject();

        JsonObject jsonBody = JsonParser.parseString(req.body()).getAsJsonObject();
        JsonObject atributos = jsonBody.getAsJsonObject("Atributos");
        JsonObject ubicacion = jsonBody.getAsJsonObject("Ubicacion");

        Texto texto = new Texto();
        texto.setID(UUID.randomUUID().toString());
        texto.setCodigo(jsonBody.get("Codigo").getAsString());
        texto.setTitulo(jsonBody.get("Titulo").getAsString());
        texto.setNumPaginas(jsonBody.get("NumPaginas").getAsInt());
        texto.setTipo(jsonBody.get("Tipo").getAsString());
        texto.setAno(jsonBody.get("Ano").getAsInt());
        texto.setResena(jsonBody.get("Resena").getAsString());
        texto.setIDAutor(jsonBody.get("IDAutor").getAsString());
        texto.setLinkFoto(jsonBody.get("LinkFoto").getAsString());
        texto.setDisponibilidad(jsonBody.get("Disponibilidad").getAsString());
        texto.setUbicacion(ubicacion.toString());

        if (atributos != null){
            texto.setAtributos(atributos.toString());
        }

        String mensaje = "";

        String accion = daoTexto.crearTexto(texto);
        if (accion.equals("Creado")) {
            mensaje = "Texto creado";
        }else if(accion.equals("Existe")){
            mensaje = "Codigo Existente";
        }
        else {
            mensaje = "Error al crear texto";
        }

        respuesta.addProperty("mensaje", mensaje);

        return respuesta;
    }

    public static JsonObject eliminarTexto(Request req, Response res) {
        JsonObject respuesta = new JsonObject();
        String id = req.queryParams("id");
        String mensaje = "";
        if (daoTexto.eliminarTexto(id)) {
            mensaje = "Texto eliminado";
        } else {
            mensaje = "Error al eliminar texto";
        }
        respuesta.addProperty("mensaje", mensaje);
        return respuesta;
    }

    public static String obtenerBiblioteca(Request req, Response res){
        return gson.toJson(daoTexto.getBiblioteca());
    }

    public static JsonObject visualizarTexto(Request req, Response res){
        String id = req.queryParams("id");
        return daoTexto.VisualizarTexto(id);
    }

    public static String textoInformacionTabla(Request req, Response res){
        return gson.toJson(daoTexto.obtenerTextoParaTabla());
    }

    public static JsonObject obtenerTextoByCodigo(Request req, Response res){
        JsonObject respuesta = new JsonObject();
        String codigo = req.queryParams("codigo");
        DAOPrestamo daoPrestamo = new DAOPrestamo();
        JsonObject texto = daoTexto.obtenerLibroByCodigo(codigo);

        if(texto != null) {

            if (daoPrestamo.libroDisponible(codigo)) {
                respuesta.addProperty("disponible", true);
                respuesta.add("texto", texto);
            } else {
                respuesta.addProperty("disponible", false);
                respuesta.addProperty("mensaje", "El libro no esta disponible");
            }

        }else{
            respuesta.addProperty("mensaje", "No existe el libro");
        }

        return respuesta;
    }

    public static String informacionPrestamo(Request req, Response res){
        String id = req.queryParams("id");
        return gson.toJson(daoTexto.obtenerInfoPrestamo(id));
    }


    //Método para editar el texto
    public static JsonObject editarTexto(Request req, Response res){
        JsonObject respuesta = new JsonObject();
        Texto texto = gson.fromJson(req.body(), Texto.class);
        System.out.println(texto.toString());
        if (daoTexto.editarTexto(texto)){
         respuesta.addProperty("mensaje", "Texto editado");
        }else{
            respuesta.addProperty("mensaje", "Error al editar texto");

        }
        return respuesta;
    }

    public static String obtenerTextoCodigo(Request req, Response res){
        String codigo = req.queryParams("codigo");
        return gson.toJson(daoTexto.obtenerTextoCodigo(codigo));
    }


}
