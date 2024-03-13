package cd.Controladores;

import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import cd.DAO.DAOAutor;
import cd.Modelo.Autor;
import spark.Response;
import spark.Request;

public class ControladorAutor {

    private static DAOAutor daoAutor = new DAOAutor();
    private static Gson gson = new Gson();

    public static String obtenerAutores(Request req, Response res){
        return gson.toJson(daoAutor.obtenerAutores());
    }

    public static JsonObject crearAutor(Request req, Response res){
        Autor autor = gson.fromJson(req.body(), Autor.class);
        autor.setID(UUID.randomUUID().toString());
        String mensaje = "";
        if(daoAutor.crearAutor(autor)){
            mensaje = "Autor Creado";
        }else{
            mensaje = "Error al crear autor";
        }        
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("mensaje", mensaje);                
        return jsonObject;
    }

    public static JsonObject eliminarAutor(Request req, Response res){
        String id = req.queryParams("id");
        String mensaje = "";
        if(daoAutor.eliminarAutor(id)){
            mensaje = "Autor eliminado";
        }else{
            mensaje = "Error al eliminar autor";
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("mensaje", mensaje);
        return jsonObject;
    }
    
    public static JsonObject editarAutor(Request req, Response res){
        Autor autor = gson.fromJson(req.body(), Autor.class);
        String mensaje = "";
        if(daoAutor.editarAutor(autor)){
            mensaje = "Autor editado";
        }else{
            mensaje = "Error al editar autor";
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("mensaje", mensaje);
        return jsonObject;
    }

}
