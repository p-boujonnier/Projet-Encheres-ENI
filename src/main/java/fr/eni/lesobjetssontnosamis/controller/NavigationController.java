package fr.eni.lesobjetssontnosamis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {
        @GetMapping("/")
        public String index() { // Vérifiez les noms et valeurs
            return "index";
        }
}
