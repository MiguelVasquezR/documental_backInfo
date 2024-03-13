package cd.Modelo;

public class Estudiante {
    private String ID;
    private String Nombre;
    private String Materno;
    private String Paterno;
    private String Matricula;
    private String Correo;
    private String Telefono;

    public Estudiante(){
        
    }

    // Constructor
    public Estudiante(String ID, String Nombre, String Materno, String Paterno, String Matricula, String Correo, String Telefono) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Materno = Materno;
        this.Paterno = Paterno;
        this.Matricula = Matricula;
        this.Correo = Correo;
        this.Telefono = Telefono;
    }

    // Getters and Setters
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getMaterno() {
        return Materno;
    }

    public void setMaterno(String Materno) {
        this.Materno = Materno;
    }

    public String getPaterno() {
        return Paterno;
    }

    public void setPaterno(String Paterno) {
        this.Paterno = Paterno;
    }

    public String getMatricula() {
        return Matricula;
    }

    public void setMatricula(String Matricula) {
        this.Matricula = Matricula;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }
}
