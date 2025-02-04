package fr.eni.lesobjetssontnosamis.controller;

import ch.qos.logback.core.net.SMTPAppenderBase;
import fr.eni.lesobjetssontnosamis.bll.UtilisateurService;

import fr.eni.lesobjetssontnosamis.bo.Utilisateur;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller

public class ConnexionController {
    protected final Log logger = LogFactory.getLog(getClass());


        @Autowired
        public UserDetailsManager userDetailsManager;

    private UtilisateurService utilisateurService;
    Utilisateur utilisateur;
    //private final PasswordEncoder passwordEncoder;
    //public String encodedPassword;

// PasswordEncoder passwordEncoder
    public ConnexionController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
        //this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    String login() {
       // boolean isPasswordValid = passwordEncoder.matches("password", encodedPassword);
        // logger.info("login");
        return "login";
    }

    @PostMapping("/login")
    String loginPost(@RequestParam String email, @RequestParam String password, Model model) {
        if (utilisateur != null && utilisateur.getMotDePasse().equals(password)) {
            return "/view-article";  // Rediriger vers la page de profil si l'authentification est réussie
        } else {
            model.addAttribute("error", "Email ou mot de passe incorrect");
        return "/view-article-create";
    }
}}
