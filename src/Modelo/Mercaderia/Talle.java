package Modelo.Mercaderia;

public enum Talle {
    XS("EXTRA SMALL"),S("SMALL"),M("MEDIUM"),
    L("LARGE"),XL("EXTRA LARGE"),XXL("DOUBLE EXTRA LARGE");

    private String talles;

    private Talle(String talles){
        this.talles=talles;
    }

    public String getTalles() {
        return talles;
    }
}

