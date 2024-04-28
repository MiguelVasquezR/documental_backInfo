package cd.Modelo;

public class Foto {
    private String ID;
    private String LinkFoto;

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public void setLinkFoto(String LinkFoto) {
        this.LinkFoto = LinkFoto;
    }

    public String getLinkFoto() {
        return LinkFoto;
    }

    @Override
    public String toString() {
        return "Foto{" +
                "ID='" + ID + '\'' +
                ", LinkFoto='" + LinkFoto + '\'' +
                '}';
    }
    
}
