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

        String mensaje = "";
        if (daoPelicula.crearPelicula(p)) {
            mensaje = "Pelicula creada";
        } else {
            mensaje = "Error al crear pelicula";
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(mensaje, "Pelicula creada");
        return jsonObject;
    }
    
    public static JsonObject eliminarPelicula(Request request, Response response) {
        String id = request.params("id");
        String mensaje = "";
        if (daoPelicula.eliminarPelicula(id)) {
            mensaje = "Pelicula eliminada";
        } else {
            mensaje = "Error al eliminar pelicula";
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(mensaje, "Pelicula eliminada");
        return jsonObject;
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

}
