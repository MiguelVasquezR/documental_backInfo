package cd.Modelo;

import java.util.Date;

public class Prestamo {
    private String ID;
    private Date fechaPrestamo;
    private Date fechaRegreso;
    private String IDEstudiante;
    private String IDTexto;

    public Prestamo() {
    }
    
    public Prestamo(String ID, Date fechaPrestamo, Date fechaRegreso, String IDEstudiante, String IDTexto) {
        this.ID = ID;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaRegreso = fechaRegreso;
        this.IDEstudiante = IDEstudiante;
        this.IDTexto = IDTexto;
    }
    
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaRegreso() {
        return fechaRegreso;
    }

    public void setFechaRegreso(Date fechaRegreso) {
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
    
}
