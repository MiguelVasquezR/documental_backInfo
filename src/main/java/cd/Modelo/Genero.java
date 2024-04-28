package cd.Modelo;

public class Genero {

    private String ID;
    private String Nombre;
    private String IDPelicula;

    public Genero(){

    }
    public String getIDPelicula() {
        return IDPelicula;
    }

    public void setIDPelicula(String IDPelicula) {
        this.IDPelicula = IDPelicula;
    }

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

    @Override
    public String toString() {
        return "Genero{" +
                "ID='" + ID + '\'' +
                ", Nombre='" + Nombre + '\'' +
                ", IDPelicula='" + IDPelicula + '\'' +
                '}';
    }
}
