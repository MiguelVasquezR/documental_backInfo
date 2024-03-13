package cd.Modelo;

public class Pelicula {

    private String ID;
    private String Codigo;
    private String Titulo;
    private String Director;    
    private int Ano;    
    private String Proviene;
    private String Genero;
    private String LinkFoto;

    public Pelicula() {}

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String Director) {
        this.Director = Director;
    }

    public int getAno() {
        return Ano;
    }

    public void setAno(int Ano) {
        this.Ano = Ano;
    }

    public String getProviene() {
        return Proviene;
    }

    public void setProviene(String Proviene) {
        this.Proviene = Proviene;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    public String getLinkFoto() {
        return LinkFoto;
    }

    public void setLinkFoto(String LinkFoto) {
        this.LinkFoto = LinkFoto;
    }

    @Override
    public String toString() {
        return "Pelicula{" + "ID=" + ID + ", Codigo=" + Codigo + ", Titulo=" + Titulo + ", Director=" + Director + ", Ano=" + Ano + ", Proviene=" + Proviene + ", Genero=" + Genero + ", LinkFoto=" + LinkFoto + '}';
    }

}