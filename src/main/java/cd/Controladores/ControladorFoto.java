package cd.Controladores;

import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import cd.DAO.DAOFoto;
import cd.Modelo.Foto;
import spark.Request;
import spark.Response;

public class ControladorFoto {
    private static DAOFoto daoFoto = new DAOFoto();
    private static Gson gson = new Gson();

    public static String obtenerFoto(Request req, Response res){
        String ID = req.params("ID");
        Foto foto = daoFoto.obtenerFoto(ID);
        return gson.toJson(foto);
    }

    public static JsonObject crearFoto(Request req, Response res){
        Foto foto = gson.fromJson(req.body(), Foto.class);
        foto.setID(UUID.randomUUID().toString());

        JsonObject jsonObject = new JsonObject();
        String mensaje = "";
        String ID = "";


        if (daoFoto.crearFoto(foto).equals("Foto Creada")) {
            mensaje = "Foto Creada";
            ID = foto.getID();

        }else{
            mensaje = "Error en CFoto";
        }

        jsonObject.addProperty("mensaje", mensaje);
        jsonObject.addProperty("ID", ID);
        return jsonObject;
    }

    public static JsonObject eliminarFoto(Request req, Response res){
        String ID = req.params("ID");
        JsonObject jsonObject = new JsonObject();
        String mensaje = "";

        if (daoFoto.eliminarFoto(ID).equals("Foto Eliminada")) {
            mensaje = "Foto Eliminada";
        }else{
            mensaje = "Error en EFoto";
        }

        jsonObject.addProperty("mensaje", mensaje);
        return jsonObject;
    }
    
}
