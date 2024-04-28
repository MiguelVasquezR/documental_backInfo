package cd.Modelo;

public class Pelicula {

    private String ID;
    private int ano;
    private String codigo;
    private String titulo;
    private String origen;
    private String portada;
    private String tipo;
    private String IDAutor;

    public Pelicula() {

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIDAutor() {
        return IDAutor;
    }

    public void setIDAutor(String IDAutor) {
        this.IDAutor = IDAutor;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "ID='" + ID + '\'' +
                ", ano=" + ano +
                ", codigo='" + codigo + '\'' +
                ", titulo='" + titulo + '\'' +
                ", origen='" + origen + '\'' +
                ", portada='" + portada + '\'' +
                ", tipo='" + tipo + '\'' +
                ", IDAutor='" + IDAutor + '\'' +
                '}';
    }
}