package cd.Modelo;

import java.util.Date;

public class Prestamo {
    private String ID;
    private String fechaPrestamo;
    private String fechaRegreso;
    private String IDEstudiante;
    private String IDTexto;
    private String Estado;

    public Prestamo() {
    }

    
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getFechaRegreso() {
        return fechaRegreso;
    }

    public void setFechaRegreso(String fechaRegreso) {
        this.fechaRegreso = fechaRegreso;
    }

    public String getIDEstudiante() {
        return IDEstudiante;
    }

    public void setIDEstudiante(String IDEstudiante) {
        this.IDEstudiante = IDEstudiante;
    }

    public String getIDTexto() {
        return IDTexto;
    }

    public void setIDTexto(String IDTexto) {
        this.IDTexto = IDTexto;
    }

    public void setEstado(String Estado){
        this.Estado = Estado;
    }

    public String getEstado(){
        return Estado;
    }

    @Override
    public String toString() {
        return "Prestamo{" + "ID=" + ID + ", fechaPrestamo=" + fechaPrestamo + ", fechaRegreso=" + fechaRegreso + ", IDEstudiante=" + IDEstudiante + ", IDTexto=" + IDTexto + ", Estado=" + Estado + '}';
    }
    
}
