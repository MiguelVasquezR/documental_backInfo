package cd.Controladores;

import spark.Request;
import spark.Response;

import java.util.UUID;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import cd.DAO.DAOEstudiantes;
import cd.Modelo.Estudiante;

public class ControladorEstudiante {

    private static Gson gson = new Gson();
    private static DAOEstudiantes controladorEstudiante = new DAOEstudiantes();

    public static JsonObject crearEstudiante(Request req, Response res) {
        JsonObject jsonObject = new JsonObject();
        Estudiante estudiante = gson.fromJson(req.body(), Estudiante.class);        
        estudiante.setID(UUID.randomUUID().toString());
        String mensaje = controladorEstudiante.crearEstudiante(estudiante);
        jsonObject.addProperty("mensaje", mensaje);     
        return jsonObject;
    }

    public static String obtenerEstudiantes(Request req, Response res) {        
        return gson.toJson(controladorEstudiante.getEstudiantes());
    }

    public static JsonObject eliminarEstudiante(Request req, Response res){
        String id = req.queryParams("id");                
        JsonObject jsonObject = new JsonObject();
        String mensaje;
        if (controladorEstudiante.eliminarEstudiante(id)) {
            mensaje = "Estudiante eliminado";            
        }else{
            mensaje = "Error al eliminar estudiante";
        }
        jsonObject.addProperty("mensaje", mensaje);
        return jsonObject;        
    }

    public static JsonObject editarEstudiante(Request req, Response res) {
        JsonObject jsonObject = new JsonObject();
        Estudiante estudiante = gson.fromJson(req.body(), Estudiante.class);        
        String mensaje = "";
        if (controladorEstudiante.editarEstudiante(estudiante)) {
            mensaje = "Estudiante editado";
        } else {
            mensaje = "Error al editar estudiante";
        }                   
        jsonObject.addProperty("mensaje", mensaje);     
        return jsonObject;
    }

    public static String obtenerEstudianteByMatricula(Request req, Response res) {
        String matricula = req.queryParams("matricula");
        Estudiante estudiante = controladorEstudiante.obtenerEstudianteByMatricula(matricula);
        return gson.toJson(estudiante);
    }

    public static String obtenerEstudianteByID(Request req, Response res) {
        String id = req.queryParams("id");
        Estudiante estudiante = controladorEstudiante.getEstudiante(id);
        return gson.toJson(estudiante);
    }

}
