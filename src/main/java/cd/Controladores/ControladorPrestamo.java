package cd.Controladores;

import cd.DAO.Conexion.Conexion;
import cd.DAO.DAOTexto;
import cd.Modelo.Texto;
import cd.Notificacion;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import cd.DAO.DAOPrestamo;
import cd.Modelo.Prestamo;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ControladorPrestamo {

    private static Gson gson = new Gson();
    private static DAOPrestamo daoPrestamo = new DAOPrestamo();

    private static void enviarCorreo(){
        Conexion conexion = new Conexion();
        Connection con = conexion.getConexion();
        String sql = "select e.nombre, e.paterno, e.materno, t.titulo, e.correo, p.FechaPrestamo, p.FechaRegreso from Prestamo as p, Estudiante as e, Texto as t where p.IDEstudiante = e.ID and p.IDTexto = t.ID; ";
        JsonObject prestamo;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String fechaDevolucion = rs.getString("FechaRegreso");
                Date dateDevolucion = new SimpleDateFormat("dd/MM/yyyy").parse(fechaDevolucion);
                Date dateActual = new Date();
                if(dateActual.after(dateDevolucion) || dateActual.equals(dateDevolucion)){
                    prestamo = new JsonObject();
                    System.out.println("La fecha de devolucion es hoy o ya paso");
                    String nombre = rs.getString("nombre") + " " + rs.getString("paterno") + " " + rs.getString("materno");
                    prestamo.addProperty("nombre", nombre);
                    prestamo.addProperty("titulo", rs.getString("titulo"));
                    prestamo.addProperty("correo", rs.getString("correo"));
                    prestamo.addProperty("fechaSolicitud", rs.getString("FechaPrestamo"));
                    prestamo.addProperty("fechaDevolucion", rs.getString("FechaRegreso"));
                    Notificacion.sendEmialDevolucion(rs.getString("correo"), prestamo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                con.close();
                if (con.isClosed()){
                    conexion.cerrarConexion();
                }
            }catch (Exception e){e.printStackTrace();}
        }
    }


    public static String obtenerPrestamos(Request request, Response response) {
        enviarCorreo();
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

    public static String devolverPrestamo(Request request, Response response) {
        String codigo = request.queryParams("codigo");

        DAOTexto daoTexto = new DAOTexto();
        JsonObject texto = daoTexto.obtenerLibroByCodigo(codigo);
        Prestamo prestamo = daoPrestamo.devolucion(texto.get("IDTexto").getAsString());
        return gson.toJson(prestamo);
    }

    public static JsonObject editarDevolucion(Request request, Response response) {
        String id = request.queryParams("id");
        JsonObject jsonObject = new JsonObject();
        String mensaje = "";
        if(daoPrestamo.confirmarDevolucion(id)){
            mensaje = "Devolucion confirmada";
        }else{
            mensaje = "Error al confirmar devolucion";
        }
        jsonObject.addProperty("mensaje", mensaje);
        return jsonObject;
    }
}
