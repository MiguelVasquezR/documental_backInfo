package cd.Modelo;

public class Autor {
    private String ID;
    private String Nombre;
    private String Paterno;
    private String Materno;

    public Autor(){
        
    }

    // Constructor
    public Autor(String ID, String Nombre, String Paterno, String Materno) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Paterno = Paterno;
        this.Materno = Materno;
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

    public String getPaterno() {
        return Paterno;
    }

    public void setPaterno(String Paterno) {
        this.Paterno = Paterno;
    }

    public String getMaterno() {
        return Materno;
    }

    public void setMaterno(String Materno) {
        this.Materno = Materno;
    }
}
