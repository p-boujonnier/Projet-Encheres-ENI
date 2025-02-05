package fr.eni.lesobjetssontnosamis.controller;

import ch.qos.logback.core.net.SMTPAppenderBase;
import fr.eni.lesobjetssontnosamis.bll.UtilisateurService;

import fr.eni.lesobjetssontnosamis.bo.Utilisateur;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;


@Controller

public class ConnexionController {
   protected final Log logger = LogFactory.getLog(getClass());

    private UtilisateurService utilisateurService;
    private PasswordEncoder passwordEncoder;



    public ConnexionController(UtilisateurService utilisateurService, PasswordEncoder passwordEncoder) {
        this.utilisateurService = utilisateurService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    String login() {

        return "login";
    }


//    @PostMapping("/logout")
//    public String performLogout(HttpServletRequest request) {
//        if (SecurityContextHolder.getContext().getAuthentication() != null) {
//            request.getSession().invalidate();
//            SecurityContextHolder.clearContext();
//        }
//        return "redirect:/encheres";
//
//    }

    @GetMapping("/creer")
    public String creerProfil(Model model, Principal principal) {
        model.addAttribute("utilisateur", new Utilisateur());
        model.addAttribute("action", "/encheres");

        return "view-profil-create";
    }

    @PostMapping("/creer")
    public String creerProfil(@Valid @ModelAttribute Utilisateur utilisateur, BindingResult bindingResult, HttpServletRequest request) //ajout méthode email mdp = login
    {
        var password = (utilisateur.getPassword());       //garde mdp clair
        utilisateur.setMotDePasse(passwordEncoder.encode(password)); //encode
        utilisateurService.addUtilisateur(utilisateur);
        //return "redirect:/login"; //à mettre une fois qu'on aura la page qui fonctionne
        return "redirect:/encheres";
    }
}
