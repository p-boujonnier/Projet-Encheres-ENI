package fr.eni.lesobjetssontnosamis.controller;

import fr.eni.lesobjetssontnosamis.bll.UtilisateurService;
import fr.eni.lesobjetssontnosamis.bo.Utilisateur;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller

public class ConnexionController {
    protected final Log logger = LogFactory.getLog(getClass());

//    @Autowired
//        public UserDetailsManager userDetailsManager;
    private final UtilisateurService utilisateurService;
    private final PasswordEncoder passwordEncoder;
    public String encodedPassword;

    public ConnexionController(UtilisateurService utilisateurService, PasswordEncoder passwordEncoder) {
        this.utilisateurService = utilisateurService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    String login() {
        boolean isPasswordValid = passwordEncoder.matches("password", encodedPassword);
        logger.info("login");
        return "login";
    }

}
