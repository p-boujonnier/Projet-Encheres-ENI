package fr.eni.lesobjetssontnosamis.controller;


import fr.eni.lesobjetssontnosamis.bll.UtilisateurService;
import fr.eni.lesobjetssontnosamis.bo.Utilisateur;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.event.LoggerListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.security.Principal;


@Controller
@RequestMapping("/view_profil")
public class UtilisateurController {
    protected final Log logger = LogFactory.getLog(getClass());
    private final UtilisateurService UtilisateurService;


    public UtilisateurController(UtilisateurService utilisateurService) {
        this.UtilisateurService = utilisateurService;
    }

    @GetMapping("/view_profil")
    public String afficherProfil(Model model, Principal principal) {
        var utilisateur = UtilisateurService.findByEmail(principal.getName());
        model.addAttribute("utilisateur", utilisateur);
        return "view_profil";
    }

    @GetMapping("/profil_modifier")
    public String modifierProfil(Model model, Principal principal) {
        var utilisateur = principal.getName();
        model.addAttribute("utilisateur", UtilisateurService.findByEmail(utilisateur));
        return "view_profil_modifier";

    }

}
