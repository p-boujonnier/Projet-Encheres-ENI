package fr.eni.lesobjetssontnosamis.controller;


import fr.eni.lesobjetssontnosamis.bll.UtilisateurService;
import fr.eni.lesobjetssontnosamis.bo.Utilisateur;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UtilisateurController {
    protected final Log logger = LogFactory.getLog(getClass());
    private Utilisateur utilisateur;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateur = utilisateur;
    }

}
