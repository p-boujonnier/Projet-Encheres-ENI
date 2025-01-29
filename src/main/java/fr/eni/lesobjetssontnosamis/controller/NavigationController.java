package fr.eni.lesobjetssontnosamis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping

public class NavigationController {
        @GetMapping("/")
        public String index() { // Vérifiez les noms et valeurs
            return "index";
        }

        @GetMapping("/login")
    public String login() {
            return "login";
        }
}
