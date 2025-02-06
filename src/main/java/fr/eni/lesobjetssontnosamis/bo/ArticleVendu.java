package fr.eni.lesobjetssontnosamis.bo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArticleVendu implements Serializable {

    //--------------------------------
    // ATRIBUTS
    //--------------------------------

    private static final long serialVersionUID = 1L;

    private Utilisateur vendeur;

    // Liste d'enchères par article
    private List<Enchere> encheres;

    // Chaque article a une catégorie
    private Categorie categorie;

    // Chaque article a un lieu de retrait
    private Retrait lieuRetrait;
    private long noArticle;
    private String nomArticle;
    private String description;
    private LocalDate dateDebutEncheres;
    private LocalDate dateFinEncheres;
    private int miseAPrix;
    private int prixVente;
    private String etatVente; // Etat créée, en cours, terminée, retrait effectué

    //--------------------------------
    // CONSTRUCTEURS
    //--------------------------------
    public ArticleVendu() {
        this.vendeur = new Utilisateur();
        this.encheres = new ArrayList<Enchere>();
        this.categorie = new Categorie();
        this.lieuRetrait = new Retrait();
    }

    public ArticleVendu(Categorie categorie, Retrait lieuRetrait) {
        this.categorie = categorie;
        this.lieuRetrait = lieuRetrait;
    }

    public ArticleVendu(List<Enchere> encheres, Categorie categorie, Retrait lieuRetrait) {
        this();
        this.lieuRetrait = lieuRetrait;
    }

    public ArticleVendu(Utilisateur vendeur, List<Enchere> encheres, Categorie categorie, Retrait lieuRetrait) {
        this();
        this.vendeur = vendeur;
    }

    public ArticleVendu(Utilisateur vendeur, List<Enchere> encheres, Categorie categorie, Retrait lieuRetrait, int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int miseAPrix, int prixVente, String etatVente) {
        this();
        this.encheres = encheres;
        this.noArticle = noArticle;
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.miseAPrix = miseAPrix;
        this.prixVente = prixVente;
        this.etatVente = etatVente;
    }
    //--------------------------------
    // CONSTRUCTEURS TEST
    //--------------------------------
    public ArticleVendu(String nomArticle, String description, int miseAPrix, LocalDate dateDebutEncheres, LocalDate dateFinEncheres) {
        this.nomArticle = nomArticle;
        this.description = description;
        this.miseAPrix = miseAPrix;
        this.prixVente = miseAPrix;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        Utilisateur utiTest = new Utilisateur();
        utiTest.setNoUtilisateur(42);
        utiTest.setPseudo("TestUser");
        utiTest.setNom("TestNom");
        utiTest.setPrenom("TestPrenom");
        utiTest.setEmail("test@email.com");
        utiTest.setTelephone("0601020304");
        utiTest.setRue("123 Rue du Test");
        utiTest.setCodePostal(75000);
        utiTest.setVille("TestVille");
        utiTest.setMotDePasse("TestPass123");
        utiTest.setCredit(100);
        utiTest.setAdministrateur(false);
        this.vendeur = utiTest;
        Categorie catTest = new Categorie();
        catTest.setNoCategorie(99);
        catTest.setLibelle("TestCatégorie");
        this.categorie = catTest;
    }

    //--------------------------------
    // GETTERS & SETTERS
    //--------------------------------

    public long getNoArticle() {
        return noArticle;
    }
    public void setNoArticle(long noArticle) {
        this.noArticle = noArticle;
    }

    public String getNomArticle() {
        return nomArticle;
    }
    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateDebutEncheres() {
        return dateDebutEncheres;
    }
    public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
        this.dateDebutEncheres = dateDebutEncheres;
    }

    public LocalDate getDateFinEncheres() {
        return dateFinEncheres;
    }
    public void setDateFinEncheres(LocalDate dateFinEncheres) {
        this.dateFinEncheres = dateFinEncheres;
    }

    public int getMiseAPrix() {
        return miseAPrix;
    }
    public void setMiseAPrix(int miseAPrix) {
        this.miseAPrix = miseAPrix;
    }

    public int getPrixVente() {
        return prixVente;
    }
    public void setPrixVente(int prixVente) {
        this.prixVente = prixVente;
    }

    public String getEtatVente() {
        return etatVente;
    }
    public void setEtatVente(String etatVente) {
        this.etatVente = etatVente;
    }

    public Utilisateur getVendeur() {
        return vendeur;
    }
    public void setVendeur(Utilisateur vendeur) {
        this.vendeur = vendeur;
    }

    public List<Enchere> getEncheres() {
        return encheres;
    }
    public void setEncheres(List<Enchere> encheres) {
        this.encheres = encheres;
    }

    public Categorie getCategorie() {
        return categorie;
    }
    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Retrait getLieuRetrait() {
        return lieuRetrait;
    }
    public void setLieuRetrait(Retrait lieuRetrait) {
        this.lieuRetrait = lieuRetrait;
    }

    //--------------------------------
    // METHODE toString
    //--------------------------------

    @Override
    public String toString() {
        return "ArticleVendu{" +
                "noArticle=" + noArticle +
                ", nomArticle='" + nomArticle + '\'' +
                ", description='" + description + '\'' +
                ", dateDebutEncheres=" + dateDebutEncheres +
                ", dateFinEncheres=" + dateFinEncheres +
                ", miseAPrix=" + miseAPrix +
                ", prixVente=" + prixVente +
                ", etatVente='" + etatVente + '\'' +
                '}';
    }
}
