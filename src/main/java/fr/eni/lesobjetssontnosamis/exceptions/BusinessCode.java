package fr.eni.lesobjetssontnosamis.exceptions;

public class BusinessCode {

    // CONTRAINTES D'AJOUT DE FILM :
    public static final String VALIDATION_NOM_NULL = "validation.nom.null";
    public static final String VALIDATION_NOM_TOO_LONG = "validation.nom.too.long";
    public static final String VALIDATION_DESCRIPTION_NULL = "validation.description.null";
    public static final String VALIDATION_DESCRIPTION_TOO_LONG = "validation.description.too.long";
    public static final String VALIDATION_DATE_DEBUT_NULL = "validation.date-debut.null";
    public static final String VALIDATION_DATE_FIN_NULL = "validation.date-fin.null";
    public static final String VALIDATION_DATE_FIN_AVANT_DEBUT = "validation.date-fin.avant.debut";
    public static final String VALIDATION_MISE_A_PRIX_INFERIEUR_ZERO = "validation.mise-a-prix.inferieur.zero";

    public static final String BLL_ARTICLE_CREATE_ERREUR = "bll.article.update.erreur";

}

