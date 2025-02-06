package fr.eni.lesobjetssontnosamis.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class Utilisateur implements UserDetails {
    //--------------------------------
    // ATTRIBUTS
    //--------------------------------
    private static final long serialVersionUID = 1L;
    private int noUtilisateur;

    // @NotBlank
    // @Size(min = 2, max=250, message = "Pseudo non valide")
    private String pseudo;

    // @NotBlank
    // @Size(min = 2, max=250, message = "Le nom doit avoir au moins 2 caractères")
    private String nom;

    // @NotBlank
    // @Size(min = 2, max=250)
    private String prenom;

    // @NotBlank
    // @Email
    // @Pattern(regexp = "^[\\w\\-\\+]+(\\.[\\w\\-]+)*@[\\w\\-]+(\\.[\\w\\-]+)*\\.[\\w\\-]{2,4}$", message= "Email non valide")
    private String email;

    // @Pattern(regexp="^([\\+]?33[-]?|[0])?[1-9][0-9]{8}$", message="Le numéro de téléphone n'est pas valide")
    private String telephone;
    private String rue;

    // @NotBlank
    // @Pattern(regexp = "^(?:[0-8]\\d|9[0-8])\\d{3}$")
    private int codePostal;

    // @NotBlank
    private String ville;

    // @NotBlank
    // @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "Le mot de passe saisi n'est pas assez sécurisé. Il doit contenir au moins une lettre majuscule, une lettre minuscule, au moins un chiffre, au moins un caractère spécial (par exemple, #, ?, !, $, %, ^, &, *) et doit avoir une longueur minimale de 8 caractères.")
    private String motDePasse;
    private int credit = 0;
    private boolean administrateur = false;

    // L'utilisateur peut avoir plusieurs articles à vendre
    private List<ArticleVendu> articlesVendus;

    // L'utilisateur peut avoir plusieurs articles à vendre
    private List<ArticleVendu> articlesAchetes;

    // --------------------------------
    // CONSTRUCTEURS
    // --------------------------------
    public Utilisateur() {
    }

    public Utilisateur(List<ArticleVendu> articlesVendus) {
        this.articlesVendus = articlesVendus;
    }

    public Utilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone, String rue, int codePostal, String ville, String motDePasse, int credit, boolean administrateur) {
        this(); // appel du constructeur par défaut
        this.noUtilisateur = noUtilisateur;
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.motDePasse = motDePasse;
        this.credit = credit;
        this.administrateur = administrateur;
    }

    // --------------------------------
    // GETTERS & SETTERS
    // --------------------------------

    public int getNoUtilisateur() {
        return noUtilisateur;
    }
    public void setNoUtilisateur(int noUtilisateur) {
        this.noUtilisateur = noUtilisateur;
    }

    public String getPseudo() {
        return pseudo;
    }
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRue() {
        return rue;
    }
    public void setRue(String rue) {
        this.rue = rue;
    }

    public int getCodePostal() {
        return codePostal;
    }
    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }
    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getMotDePasse() {
        return motDePasse;
    }
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public int getCredit() {
        return credit;
    }
    public void setCredit(int credit) {
        this.credit = credit;
    }

    public boolean isAdministrateur() {
        return administrateur;
    }
    public void setAdministrateur(boolean administrateur) {
        this.administrateur = administrateur;
    }

    public List<ArticleVendu> getArticlesVendus() {
        return articlesVendus;
    }
    public void setArticlesVendus(List<ArticleVendu> articlesVendus) {
        this.articlesVendus = articlesVendus;
    }

    //--------------------------------
    // METHODE toString
    //--------------------------------
    @Override
    public String toString() {
//        StringBuilder sb = new StringBuilder();

        return "Utilisateur{" +
                "noUtilisateur=" + noUtilisateur +
                ", pseudo='" + pseudo + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", rue='" + rue + '\'' +
                ", codePostal=" + codePostal +
                ", ville='" + ville + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", credit=" + credit +
                ", administrateur=" + administrateur +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return getMotDePasse();
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}