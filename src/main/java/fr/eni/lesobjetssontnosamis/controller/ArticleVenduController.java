package fr.eni.lesobjetssontnosamis.controller;

import fr.eni.lesobjetssontnosamis.bll.ArticleVenduService;
import fr.eni.lesobjetssontnosamis.bll.CategorieService;
import fr.eni.lesobjetssontnosamis.bll.UtilisateurService;
import fr.eni.lesobjetssontnosamis.bo.ArticleVendu;
import fr.eni.lesobjetssontnosamis.bo.Categorie;
import fr.eni.lesobjetssontnosamis.bo.Utilisateur;
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
    private UtilisateurService utilisateurService;

    public ArticleVenduController(ArticleVenduService articleVenduService, CategorieService categorieService, UtilisateurService utilisateurService) {
        this.articleVenduService = articleVenduService;
        this.categorieService = categorieService;
        this.utilisateurService = utilisateurService;
    }

    @GetMapping
    public String afficherArticles(Model model) {
        List<ArticleVendu> articleVendus = articleVenduService.getArticleVendus();
        model.addAttribute("articleVendus", articleVendus);
        return "view-article";
    }

    @GetMapping("/creer")
    public String creerArticleVendu(Model model) {
        model.addAttribute("article", new ArticleVendu());
        List<Categorie> categoriesList = categorieService.readAllCategories();
        model.addAttribute("categoriesList", categoriesList);// Liste des catégories
        List<Utilisateur> utilisateurList = utilisateurService.findAllUtilisateurs();
        model.addAttribute("utilisateurList", utilisateurList);// Liste des utilisateurs (provisoirement nécessaire)
        return "view-article-create";
    }

    // Création d'un nouvel article
    @PostMapping("/creer")
    @Transactional
    public String creerArticleVendu(@Valid @ModelAttribute ArticleVendu articleVendu, BindingResult bindingResult) {
        articleVenduService.addArticleVendu(articleVendu);
        return "redirect:/view-article";
    }
}
