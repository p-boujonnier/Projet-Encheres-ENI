package fr.eni.lesobjetssontnosamis.controller;

import ch.qos.logback.core.net.SMTPAppenderBase;
import fr.eni.lesobjetssontnosamis.bll.UtilisateurService;

import fr.eni.lesobjetssontnosamis.bo.Utilisateur;
import jakarta.servlet.ServletException;
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
import org.springframework.web.bind.annotation.*;

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


    @RequestMapping("/logout")
    public String performLogout(HttpServletRequest request) throws ServletException {
    request.logout();
        return "redirect:/encheres";

    }

    @GetMapping("/creer")
    public String creerProfil(Model model, Principal principal) {
        model.addAttribute("utilisateur", new Utilisateur());
        model.addAttribute("action", "/encheres");

        return "view-profil-create";
    }

    @PostMapping("/creer")
    public String creerProfil(@Valid @ModelAttribute Utilisateur utilisateur, BindingResult bindingResult, HttpServletRequest request) throws ServletException //ajout méthode email mdp = login
    {
        var password = (utilisateur.getPassword());       //garde mdp clair
        utilisateur.setMotDePasse(passwordEncoder.encode(password)); //encode
        utilisateurService.addUtilisateur(utilisateur);
        request.login(utilisateur.getEmail(), password);
        //request.logout();
        //return "redirect:/login"; //à mettre une fois qu'on aura la page qui fonctionne
        System.out.println(utilisateur.getPassword());
        return "redirect:/encheres";
    }
}
