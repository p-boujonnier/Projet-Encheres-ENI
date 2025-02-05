package fr.eni.lesobjetssontnosamis.controller;


import fr.eni.lesobjetssontnosamis.bll.UtilisateurService;
import fr.eni.lesobjetssontnosamis.bo.Utilisateur;
import jakarta.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.security.Principal;


@Controller
@RequestMapping("/profil")
public class UtilisateurController {
    protected final Log logger = LogFactory.getLog(getClass());
    private final UtilisateurService utilisateurService;
    RedirectAttributes redirectAttributes;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/detail")
    public String afficherProfil(@RequestParam("email") String email, Model model) {
        Utilisateur utilisateur = utilisateurService.findUtilisateurByEmail(email);
        model.addAttribute("utilisateur", utilisateur);
        return "view-profil-detail";
    }

    @GetMapping("/modifier")
    public String modifierProfil(Model model, Principal principal) {
        var utilisateur = principal.getName();
        model.addAttribute("utilisateur", utilisateurService.findUtilisateurByEmail(utilisateur));
        return "view-profil-modify";

    }

}
