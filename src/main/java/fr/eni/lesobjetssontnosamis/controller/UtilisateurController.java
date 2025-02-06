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

    // -------- Afficher le profil ---------
    @GetMapping("/detail")
    public String afficherProfil(Principal principal, Model model, RedirectAttributes redirectAttributes) {
        Utilisateur utilisateur = utilisateurService.findUtilisateurByEmail(principal.getName());
        model.addAttribute("utilisateur", utilisateur);
            return "view-profil-detail";
        }


    // ------------- Supprimer Profil -------------
    @PostMapping("/supprimer")
    public String supprimerProfil(@RequestParam("email") String email, Model model) {
        utilisateurService.deleteUtilisateur(email);

        // Ajouter un message flash pour la confirmation
//        redirectAttributes.addFlashAttribute("message", "Le compte a été supprimé avec succès.");

        return "redirect:/profil/get-profil";
    }


    // --------------------------------------------
    // ----------- Modifier Profil ------------------

    @GetMapping("/modifier")
    public String modifierProfil(Model model, Principal principal) {
        var utilisateur = principal.getName();
        model.addAttribute("utilisateur", utilisateurService.findUtilisateurByEmail(utilisateur));
        return "view-profil-modify";
    }

    @GetMapping("/modifier-admin")
    public String modifierProfil(@RequestParam("email") String email, Model model) {
        Utilisateur utilisateur = utilisateurService.findUtilisateurByEmail(email);

        if (utilisateur == null) {
            model.addAttribute("errorMessage", "Aucun profil trouvé avec cet email.");
            return "view-profil-detail"; // Retourner à la page précédente si l'email n'existe pas
        }

        model.addAttribute("utilisateur", utilisateur);
        return "view-profil-modify"; // Charge la page de modification
    }

    @PostMapping("/modifier")
    public String enregistrerModifications(@ModelAttribute Utilisateur utilisateur, RedirectAttributes
            redirectAttributes) {
        utilisateurService.updateUtilisateur(utilisateur);

        redirectAttributes.addFlashAttribute("message", "Profil mis à jour avec succès.");
        return "redirect:/profil/detail?email=" + utilisateur.getEmail(); // Retour au profil
    }


}
