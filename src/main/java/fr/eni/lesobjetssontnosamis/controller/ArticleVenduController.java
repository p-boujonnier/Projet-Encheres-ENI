package fr.eni.lesobjetssontnosamis.controller;

import fr.eni.lesobjetssontnosamis.bll.ArticleVenduService;
import fr.eni.lesobjetssontnosamis.bll.CategorieService;
import fr.eni.lesobjetssontnosamis.bo.ArticleVendu;
import fr.eni.lesobjetssontnosamis.bo.Categorie;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/encheres")
public class ArticleVenduController {
    // Dépendance
    private ArticleVenduService articleVenduService;
    private CategorieService categorieService;

    public ArticleVenduController(ArticleVenduService articleVenduService, CategorieService categorieService) {
        this.articleVenduService = articleVenduService;
        this.categorieService = categorieService;
    }


    @GetMapping
    public String afficherArticles(Model model) {
        List<ArticleVendu> articleVendus = articleVenduService.getArticleVendus();
        model.addAttribute("articleVendus", articleVendus);
        return "view-articles";
    }

    @GetMapping("/creer")
    public String creerArticleVendu(Model model) {
        List<Categorie> categoriesList = categorieService.findAllCategories();
        model.addAttribute("article", new ArticleVendu());
        model.addAttribute("categoriesList", categoriesList);  // Liste des catégories
        return "view-articles-create";
    }

    // Création d'un nouvel article
    @PostMapping("/creer")
    @Transactional
    public String creerArticleVendu(@Valid @ModelAttribute ArticleVendu articleVendu, BindingResult bindingResult) {
        articleVenduService.addArticleVendu(articleVendu);
        return "redirect:/view-articles";
    }
}
