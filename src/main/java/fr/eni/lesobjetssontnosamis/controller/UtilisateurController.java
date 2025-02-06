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

    // à supprimer après
    @GetMapping("/get-profil")
    public String getProfil(Model model) {
        model.addAttribute("utilisateurProvisoire", new Utilisateur());
        return "view-ask-profil-provisory";
    }

    // à supprimer après
    @PostMapping("/get-profil")
    public String rechercherUtilisateur(@RequestParam("email") String email, Model model, RedirectAttributes redirectAttributes) {
        Utilisateur utilisateur = utilisateurService.findUtilisateurByEmail(email);
        redirectAttributes.addAttribute("email", email);
        return "redirect:/detail";
    }


    // ---------------- AFFICHER un PROFIL a partir de l'email
    @GetMapping("/detail")
    public String afficherProfil(@RequestParam("email") String email, Model model, RedirectAttributes redirectAttributes) {
        Utilisateur utilisateur = utilisateurService.findUtilisateurByEmail(email);

        if (utilisateur == null) {
            // Ajout d'un message flash si l'email n'existe pas
            redirectAttributes.addFlashAttribute("errorMessage", "Aucun profil trouvé pour l'email : " + email);
            return "redirect:/profil/get-profil"; // Redirection vers la page de recherche
        }

        model.addAttribute("utilisateur", utilisateur);
        return "view-profil-detail";
    }




    @GetMapping("/creer")
    public String creerProfil(Model model, Principal principal) {
        model.addAttribute("utilisateur", new Utilisateur());
        model.addAttribute("action", "/encheres");

        return "view-profil-create";
    }

    @PostMapping("/creer")
    @Transactional
    public String creerProfil(@Valid @ModelAttribute Utilisateur utilisateur, BindingResult bindingResult) {
        utilisateurService.addUtilisateur(utilisateur);
        //return "redirect:/login"; //à mettre une fois qu'on aura la page qui fonctionne
        return "redirect:/encheres";
    }
    // creerProfil dans le Get & Post => ok le même nom (= c'est normal)

    // --------------------------------------------
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
    public String enregistrerModifications(@ModelAttribute Utilisateur utilisateur, RedirectAttributes redirectAttributes) {
        utilisateurService.updateUtilisateur(utilisateur);

        redirectAttributes.addFlashAttribute("message", "Profil mis à jour avec succès.");
        return "redirect:/profil/detail?email=" + utilisateur.getEmail(); // Retour au profil
    }




}
