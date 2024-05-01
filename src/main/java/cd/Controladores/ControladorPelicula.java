package cd.Controladores;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import cd.DAO.DAOPelicula;
import cd.Modelo.Pelicula;
import spark.Request;
import spark.Response;
import java.util.UUID;

public class ControladorPelicula {

    private static Gson gson = new Gson();
    private static DAOPelicula daoPelicula = new DAOPelicula();

    public static String obtenerPeliculas(Request request, Response response) {        
        return gson.toJson(daoPelicula.obtenerPeliculas());
    }

    public static JsonObject crearPelicula(Request request, Response response) {
        Pelicula p = gson.fromJson(request.body(), Pelicula.class);
        p.setID(UUID.randomUUID().toString());
        JsonObject jsonObject = new JsonObject();

        String mensaje = "";
        if (daoPelicula.crearPelicula(p)) {
            mensaje = "Pelicula creada";
            jsonObject.addProperty("IDPelicula",p.getID());
        } else {
            mensaje = "Error al crear pelicula";
        }

        jsonObject.addProperty(mensaje, "Pelicula creada");
        return jsonObject;
    }
    
    public static JsonObject eliminarPelicula(Request request, Response response) {
        String id = request.queryParams("id");
        JsonObject respuesta = new JsonObject();
        if (daoPelicula.eliminarPelicula(id)) {
            respuesta.addProperty("mensaje", "Pelicula eliminada");
        } else {
            respuesta.addProperty("mensaje", "Error al eliminar pelicula");
        }
        return respuesta;
    }

    public static JsonObject editarPelicula(Request request, Response response) {
        Pelicula p = gson.fromJson(request.body(), Pelicula.class);
        String mensaje = "";
        if (daoPelicula.editarPelicula(p)) {
            mensaje = "Pelicula editada";
        } else {
            mensaje = "Error al editar pelicula";
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(mensaje, "Pelicula editada");
        return jsonObject;
    }

    public static Object obtenerPorCodigo(Request request, Response response) {

        String codigo = request.queryParams("codigo");
        return gson.toJson(daoPelicula.obtenerPeliculaByCodigo(codigo));
    }
}
