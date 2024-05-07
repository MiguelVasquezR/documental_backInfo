package cd;

import static spark.Spark.*;
import cd.Controladores.ControladorAutor;
import cd.Controladores.ControladorEstudiante;
import cd.Controladores.ControladorFoto;
import cd.Controladores.ControladorGenero;
import cd.Controladores.ControladorTexto;
import cd.Controladores.ControladorPrestamo;
import cd.Controladores.ControladorPelicula;
import com.google.gson.JsonObject;

public class App {

    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }
            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
            return "OK";
        });
        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        // --------------------------------------- Rutas
        path("/estudiante", () -> {
            get("/listar", ControladorEstudiante::obtenerEstudiantes);
            get("/matricula", ControladorEstudiante::obtenerEstudianteByMatricula);
            get("/id", ControladorEstudiante::obtenerEstudianteByID);
            post("/crear", ControladorEstudiante::crearEstudiante);
            delete("/eliminar", ControladorEstudiante::eliminarEstudiante);
            put("/editar", ControladorEstudiante::editarEstudiante);
        });

        path("/texto", () -> {
            get("/listar", ControladorTexto::obtenerTextos);
            post("/crear", ControladorTexto::crearTexto);
            delete("/eliminar", ControladorTexto::eliminarTexto);
            put("/editar", ControladorTexto::editarTexto);
            get("/biblioteca", ControladorTexto::obtenerBiblioteca);
            get("/visualizar", ControladorTexto::visualizarTexto);
            get("/informacion-tabla", ControladorTexto::textoInformacionTabla);
            get("/codigo", ControladorTexto::obtenerTextoByCodigo);
            get("/prestamo", ControladorTexto::informacionPrestamo);


            //Métodos para editar el texto
            get("/by-codigo", ControladorTexto::obtenerTextoCodigo);
            put("/editar", ControladorTexto::editarTexto);
        });

        path("/autor", () -> {
            get("/listar", ControladorAutor::obtenerAutores);
            get("/autor/id", ControladorAutor::obtenerAutorByID);
            post("/crear", ControladorAutor::crearAutor);
            delete("/eliminar", ControladorAutor::eliminarAutor);
            put("/editar", ControladorAutor::editarAutor);

        });

        path("/prestamo", () -> {
            get("/listar", ControladorPrestamo::obtenerPrestamos);
            post("/crear", ControladorPrestamo::crearPrestamo);
            delete("/eliminar/:id", ControladorPrestamo::eliminarPrestamo);
            put("/editar", ControladorPrestamo::editarPrestamo);

            post("/devolver", ControladorPrestamo::devolverPrestamo);
            put("/confirmar-devolucion", ControladorPrestamo::editarDevolucion);
        });

        path("/pelicula", () -> {
            get("/listar", ControladorPelicula::obtenerPeliculas);
            post("/crear", ControladorPelicula::crearPelicula);
            delete("/eliminar", ControladorPelicula::eliminarPelicula);
            put("/editar", ControladorPelicula::editarPelicula);
            get("/by-codigo", ControladorPelicula::obtenerPorCodigo);
        });

        path("/genero", () -> {
            get("/listar", ControladorGenero::obtenerIDGenero);
            get("/listar-generos", ControladorGenero::listarGeneros);
            post("/crear", ControladorGenero::crearGenero);

        });

        path("/foto", () -> {
            get("/obtener", ControladorFoto::obtenerFoto);
            post("/crear", ControladorFoto::crearFoto);
            delete("/eliminar/:ID", ControladorFoto::eliminarFoto);
        });

        get("/correo", (req, res) -> {
           Notificacion notificacion = new Notificacion();
            JsonObject datos = new JsonObject();
            datos.addProperty("nombre", "Miguel Rosas");
            datos.addProperty("titulo", "El principito");
            datos.addProperty("fechaSolicitud", "2021-06-01");
            datos.addProperty("fechaDevolucion", "2021-06-15");
            notificacion.sendEmialDevolucion("mvrosas01@gmail.com", datos);
            return "Correo Enviado";
        });

    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }
}
