package fr.eni.lesobjetssontnosamis.controller;


import fr.eni.lesobjetssontnosamis.bll.UtilisateurService;
import fr.eni.lesobjetssontnosamis.bo.Utilisateur;
import jakarta.validation.constraints.Email;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.security.Principal;


@Controller

public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    RedirectAttributes redirectAttributes;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/get-profil")
    public String getProfil(Model model) {
        model.addAttribute("utilisateurProvisoire", new Utilisateur());
        return "view-ask-profil-provisory";
    }

    @PostMapping("/get-profil")
    public String rechercherUtilisateur(@RequestParam("email") String email, Model model, RedirectAttributes redirectAttributes) {
        Utilisateur utilisateur = utilisateurService.findUtilisateurByEmail(email);
        redirectAttributes.addAttribute("email", email);
        return "redirect:/view-profil/detail";
    }

    @GetMapping("/profil")
    public String afficherProfil(@RequestParam("email") String email, Model model) {
        Utilisateur utilisateur = utilisateurService.findUtilisateurByEmail(email);
        model.addAttribute("utilisateur", utilisateur);
        return "view-profil/profil";
    }

    @GetMapping("/profil-modifier")
    public String modifierProfil(Model model, Principal principal) {
        var utilisateur = principal.getName();
        model.addAttribute("utilisateur", utilisateurService.findByEmail(utilisateur));
        return "view-profil-modifier";

    }


}
