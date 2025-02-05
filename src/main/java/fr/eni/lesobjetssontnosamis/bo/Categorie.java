package fr.eni.lesobjetssontnosamis.bo;

import java.util.List;

public class Categorie {

    //--------------------------------
    // ATRIBUTS
    //--------------------------------

    private long noCategorie;
    private String libelle;

    // Une catégorie par article et plusieurs articles pour chaque catégorie
    private List<ArticleVendu> articles;

    //--------------------------------
    // CONSTRUCTEURS
    //--------------------------------

    public Categorie() {
    }

    public Categorie(List<ArticleVendu> articles) {
        this.articles = articles;
    }

    public Categorie(int noCategorie, String libelle, List<ArticleVendu> articles) {
        this(); // appel du constructeur par défaut

        this.noCategorie = noCategorie;
        this.libelle = libelle;
        this.articles = articles;
    }

    //--------------------------------
    // GETTERS & SETTERS
    //--------------------------------

    public long getNoCategorie() {
        return noCategorie;
    }
    public void setNoCategorie(int noCategorie) {
        this.noCategorie = noCategorie;
    }

    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<ArticleVendu> getArticles() {
        return articles;
    }
    public void setArticles(List<ArticleVendu> articles) {
        this.articles = articles;
    }

    //--------------------------------
    // METHODE toString
    //--------------------------------

    @Override
    public String toString() {
        return "Categorie{" +
                "noCategorie=" + noCategorie +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}
