package cd.Controladores;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import cd.DAO.DAOPrestamo;
import cd.Modelo.Prestamo;
import spark.Request;
import spark.Response;
import java.util.UUID;

public class ControladorPrestamo {

    private static Gson gson = new Gson();
    private static DAOPrestamo daoPrestamo = new DAOPrestamo();


    public static String obtenerPrestamos(Request request, Response response) {        
        return gson.toJson(daoPrestamo.obtenerPrestamos());
    }

    public static JsonObject crearPrestamo(Request request, Response response) {        
        Prestamo prestamo = new Gson().fromJson(request.body(), Prestamo.class);        
        prestamo.setID(UUID.randomUUID().toString());

        String mensaje = "";
        if (daoPrestamo.crearPrestamo(prestamo)) {
            mensaje = "Prestamo creado";
        } else {
            mensaje = "Error al crear prestamo";        
        }

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("mensaje", mensaje);

        return jsonObject;
    }

    public static JsonObject eliminarPrestamo(Request request, Response response) {
        String id = request.params("id");
        String mensaje = "";
        if (daoPrestamo.eliminarPrestamo(id)) {
            mensaje = "Prestamo eliminado";
        } else {
            mensaje = "Error al eliminar prestamo";            
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("mensaje", mensaje);
        return jsonObject;
    }

    public static JsonObject editarPrestamo(Request request, Response response) {
        Prestamo prestamo = new Gson().fromJson(request.body(), Prestamo.class);
        String mensaje = "";
        if (daoPrestamo.editarPrestamo(prestamo)) {
            mensaje = "Prestamo editado";
        } else {
            mensaje = "Error al editar prestamo";            
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("mensaje", mensaje);
        return jsonObject;
    }
    
}
