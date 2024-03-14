package cd.Controladores;

import java.util.UUID;
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

    public static String obtenerTextos(Request req, Response res){        
        return gson.toJson(daoTexto.obtenerTextos());
    }

    public static JsonObject crearTexto(Request req, Response res) {
        JsonObject respuesta = new JsonObject();        
                
        JsonObject jsonBody = JsonParser.parseString(req.body()).getAsJsonObject();
        JsonObject atributos = jsonBody.getAsJsonObject("Atributos");        
            
        Texto texto = new Texto();
        texto.setID(UUID.randomUUID().toString());
        texto.setLinkFoto(jsonBody.get("LinkFoto").getAsString());
        texto.setTitulo(jsonBody.get("Titulo").getAsString());
        texto.setIDAutor(jsonBody.get("IDAutor").getAsString());
        texto.setNumPaginas(jsonBody.get("NumPaginas").getAsInt());
        texto.setResena(jsonBody.get("Resena").getAsString());
        texto.setAno(jsonBody.get("Ano").getAsInt());
        texto.setDisponibilidad(jsonBody.get("Disponibilidad").getAsString());
        texto.setUbicacion(jsonBody.get("Ubicacion").getAsString());
        texto.setTipo(jsonBody.get("Tipo").getAsString());     
        texto.setCodigo(jsonBody.get("Codigo").getAsString());   
        texto.setAtributos(atributos.toString());        
    
        String mensaje = "";
    
        if (daoTexto.crearTexto(texto)) {            
            mensaje = "Texto creado";
        }else{            
            mensaje = "Error al crear texto";            
        }
    
        respuesta.addProperty("mensaje", mensaje);
    
        return respuesta;
    }
    
    public static JsonObject editarTexto(Request req, Response res){
        JsonObject respuesta = new JsonObject();
        Texto texto = gson.fromJson(req.body(), Texto.class);
        String mensaje = "";
        if (daoTexto.editarTexto(texto)) {            
            mensaje = "Texto editado";
        }else{            
            mensaje = "Error al editar texto";            
        }
        respuesta.addProperty("mensaje", mensaje);        
        return respuesta;
    }

    public static JsonObject eliminarTexto(Request req, Response res){
        JsonObject respuesta = new JsonObject();
        String id = req.queryParams("id");
        String mensaje = "";
        if (daoTexto.eliminarTexto(id)) {            
            mensaje = "Texto eliminado";
        }else{            
            mensaje = "Error al eliminar texto";            
        }
        respuesta.addProperty("mensaje", mensaje);        
        return respuesta;
    }

}
