package fr.eni.lesobjetssontnosamis.controller;

import fr.eni.lesobjetssontnosamis.bll.ArticleVenduService;
import fr.eni.lesobjetssontnosamis.bo.ArticleVendu;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleVenduController {
    // Dépendance
    ArticleVenduService articleVenduService;

    public ArticleVenduController(ArticleVenduService articleVenduService) {
        this.articleVenduService = articleVenduService;
    }


    @GetMapping
    public String afficherFilms(Model model) {
        List<ArticleVendu> articleVendus = articleVenduService.getArticleVendus();
        model.addAttribute("articleVendus", articleVendus);
        return "view-articles";
    }

    @GetMapping("/creer")
    public String creerArticleVendu(Model model) {
        model.addAttribute("article", new ArticleVendu());
        return "view-articles-create";
    }

    // Création d'un nouvel article
    @PostMapping("/creer")
    public String newArticleVendu(ArticleVendu articleVendu) {
        articleVenduService.addArticleVendu(articleVendu);
        return "view-articles";
    }
}
