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
        return "redirect:/view-profil/profil";
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
        model.addAttribute("utilisateur", utilisateurService.findByEmail(utilisateur));
        return "view-profil-modify";

    }

    @GetMapping("/creer")
    public String creerProfil(Model model, Principal principal) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "view-profil-create";
    }

    @PostMapping("/creer")
    @Transactional
    public String creerProfil(@Valid @ModelAttribute Utilisateur utilisateur, BindingResult result) {
        utilisateurService.addUtilisateur(utilisateur);
        //return "redirect:/login"; //à mettre une fois qu'on aura la page qui fonctionne
        return "redirect:/encheres";
    }
    // creerProfil dans le Get & Post => ok le même nom (= c'est normal)

}
