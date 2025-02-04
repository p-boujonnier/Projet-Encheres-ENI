package fr.eni.lesobjetssontnosamis.controller;

import fr.eni.lesobjetssontnosamis.bll.UtilisateurService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



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
       // boolean isPasswordValid = passwordEncoder.matches("password", encodedPassword);
        // logger.info("login");
        return "login";
    }

}
