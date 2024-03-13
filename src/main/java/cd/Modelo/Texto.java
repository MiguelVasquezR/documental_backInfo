package cd.Modelo;

public class Texto {
    private String ID;
    private String Codigo;
    private String LinkFoto;
    private String Titulo;
    private String IDAutor;
    private int NumPaginas;
    private String Resena;
    private int Ano;
    private String Disponibilidad;
    private String Ubicacion;
    private String Tipo;
    private String Atributos;

    public Texto(){}
    
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getLinkFoto() {
        return LinkFoto;
    }

    public void setLinkFoto(String LinkFoto) {
        this.LinkFoto = LinkFoto;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public int getAno() {
        return Ano;
    }

    public void setAno(int Ano) {
        this.Ano = Ano;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getIDAutor() {
        return IDAutor;
    }

    public void setIDAutor(String IDAutor) {
        this.IDAutor = IDAutor;
    }

    public int getNumPaginas() {
        return NumPaginas;
    }

    public void setNumPaginas(int NumPaginas) {
        this.NumPaginas = NumPaginas;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String Ubicacion) {
        this.Ubicacion = Ubicacion;
    }

    public String getDisponibilidad() {
        return Disponibilidad;
    }

    public void setDisponibilidad(String Disponibilidad) {
        this.Disponibilidad = Disponibilidad;
    }

    public String getResena() {
        return Resena;
    }

    public void setResena(String Resena) {
        this.Resena = Resena;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getAtributos() {
        return Atributos;
    }

    public void setAtributos(String Atributos) {
        this.Atributos = Atributos;
    }

    @Override
    public String toString() {
        return "Texto{" + "ID=" + ID + ", Codigo=" + Codigo + ", LinkFoto=" + LinkFoto + ", Titulo=" + Titulo + ", IDAutor=" + IDAutor + ", NumPaginas=" + NumPaginas + ", Resena=" + Resena + ", Ano=" + Ano + ", Disponibilidad=" + Disponibilidad + ", Ubicacion=" + Ubicacion + ", Tipo=" + Tipo + ", Atributos=" + Atributos + '}';
    }   

}