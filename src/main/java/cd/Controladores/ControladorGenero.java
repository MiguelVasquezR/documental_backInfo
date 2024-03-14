package cd.Controladores;

import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import cd.DAO.DAOGenero;
import cd.Modelo.Genero;
import spark.Request;
import spark.Response;

public class ControladorGenero {

    private static Gson gson = new Gson();
    private static DAOGenero daoGenero = new DAOGenero();

    public static String obtenerIDGenero(Request req, Response res){
        String nombre = req.params("nombre");        
        Genero genero = daoGenero.obtenerIDGenero(nombre);
        if(genero == null){
            return "No se encontro el genero";
        }else{
            return gson.toJson(genero);
        }        
    }

    public static JsonObject crearGenero(Request req, Response res){
        Genero genero = gson.fromJson(req.body(), Genero.class);
        genero.setID(UUID.randomUUID().toString());
        String mensaje = "";
        if(daoGenero.crearGenero(genero)){            
            mensaje = "Genero creado";
        }else{            
            mensaje = "Error al crear genero";
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("mensaje", mensaje);
        return jsonObject;
    }
    
}
