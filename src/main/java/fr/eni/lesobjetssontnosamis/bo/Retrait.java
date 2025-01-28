package fr.eni.lesobjetssontnosamis.bo;

public class Retrait {

    //--------------------------------
    // ATRIBUTS
    //--------------------------------
    private String rue;
    private String code_postal;
    private String ville;

    //--------------------------------
    // CONSTRUCTEURS
    //--------------------------------

    public Retrait() {
    }

    public Retrait(String rue, String code_postal, String ville) {
        this(); // appel du constructeur par défaut

        this.rue = rue;
        this.code_postal = code_postal;
        this.ville = ville;
    }

    //--------------------------------
    // GETTERS & SETTERS
    //--------------------------------

    public String getRue() {
        return rue;
    }
    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCode_postal() {
        return code_postal;
    }
    public void setCode_postal(String code_postal) {
        this.code_postal = code_postal;
    }

    public String getVille() {
        return ville;
    }
    public void setVille(String ville) {
        this.ville = ville;
    }

    //--------------------------------
    // METHODE toString
    //--------------------------------
    @Override
    public String toString() {
        return "Retrait{" +
                "rue='" + rue + '\'' +
                ", code_postal='" + code_postal + '\'' +
                ", ville='" + ville + '\'' +
                '}';
    }
}
