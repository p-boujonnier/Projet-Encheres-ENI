package fr.eni.lesobjetssontnosamis.bo;

import java.time.LocalDateTime;

public class Enchere {

    //--------------------------------
    // ATRIBUTS
    //--------------------------------

    private ArticleVendu article;
    private Categorie categorie;
    private LocalDateTime dateEnchere;
    private int montant_enchere;

    //--------------------------------
    // CONSTRUCTEURS
    //--------------------------------
    public Enchere() {
    }

    public Enchere(Categorie categorie) {
        this.categorie = categorie;
    }

    public Enchere(ArticleVendu article, Categorie categorie) {
        this.article = article;
        this.categorie = categorie;
    }

    public Enchere(ArticleVendu article, Categorie categorie, LocalDateTime dateEnchere, int montant_enchere) {
        this(); // appel du constructeur par défaut

        this.article = article;
        this.categorie = categorie;
        this.dateEnchere = dateEnchere;
        this.montant_enchere = montant_enchere;
    }

    //--------------------------------
    // GETTERS & SETTERS
    //--------------------------------

    public LocalDateTime getDateEnchere() {
        return dateEnchere;
    }
    public void setDateEnchere(LocalDateTime dateEnchere) {
        this.dateEnchere = dateEnchere;
    }

    public int getMontant_enchere() {
        return montant_enchere;
    }
    public void setMontant_enchere(int montant_enchere) {
        this.montant_enchere = montant_enchere;
    }

    public ArticleVendu getArticle() {
        return article;
    }
    public void setArticle(ArticleVendu article) {
        this.article = article;
    }

    public Categorie getCategorie() {
        return categorie;
    }
    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    //--------------------------------
    // METHODE toString
    //--------------------------------
    @Override
    public String toString() {
        return "Enchere{" +
                "dateEnchere=" + dateEnchere +
                ", montant_enchere=" + montant_enchere +
                '}';
    }
}
