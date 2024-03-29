package cd;

import static spark.Spark.*;
import cd.Controladores.ControladorAutor;
import cd.Controladores.ControladorEstudiante;
import cd.Controladores.ControladorGenero;
import cd.Controladores.ControladorTexto;
import cd.Controladores.ControladorPrestamo;
import cd.Controladores.ControladorPelicula;

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
            post("/crear", ControladorEstudiante::crearEstudiante);
            delete("/eliminar", ControladorEstudiante::eliminarEstudiante);
            put("/editar", ControladorEstudiante::editarEstudiante);
        });

        path("/texto", () -> {
            get("/listar", ControladorTexto::obtenerTextos);
            post("/crear", ControladorTexto::crearTexto);    
            delete("/eliminar", ControladorTexto::eliminarTexto);
            put("/editar", ControladorTexto::editarTexto);            
        });        

        path("/autor", () -> {
            get("/listar", ControladorAutor::obtenerAutores);
            post("/crear", ControladorAutor::crearAutor);
            delete("/eliminar", ControladorAutor::eliminarAutor);
            put("/editar", ControladorAutor::editarAutor);
        });
        
        path("/prestamo", ()->{
            get("/listar", ControladorPrestamo::obtenerPrestamos);
            post("/crear", ControladorPrestamo::crearPrestamo);
            delete("/eliminar/:id", ControladorPrestamo::eliminarPrestamo);
            put("/editar", ControladorPrestamo::editarPrestamo);
        });

        path("/pelicula", ()->{
            get("/listar", ControladorPelicula::obtenerPeliculas);
            post("/crear", ControladorPelicula::crearPelicula);
            delete("/eliminar/:id", ControladorPelicula::eliminarPelicula);
            put("/editar", ControladorPelicula::editarPelicula);
        });

        path("/genero", ()->{
            get("/listar", ControladorGenero::obtenerIDGenero);
            post("/crear", ControladorGenero::crearGenero);
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
