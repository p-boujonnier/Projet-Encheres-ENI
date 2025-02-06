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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLOutput;
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
        for (ArticleVendu articleVendu : articleVendus) {
            long noUtilisateur = articleVendu.getVendeur().getNoUtilisateur();
            articleVendu.getVendeur().setPseudo(utilisateurService.findUtilisateurById(noUtilisateur).getPseudo());
        }
        model.addAttribute("articleVendus", articleVendus);
        List<Categorie> categoriesList = categorieService.readAllCategories();
        model.addAttribute("categoriesList", categoriesList);// Liste des catégories

        return "view-article";
    }

    @PostMapping
    public String actionEncheres(@RequestParam(name = "noArticle") long noArticleVendu, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("noArticle", noArticleVendu);
        return "redirect:/view-article-detail";
    }

    @GetMapping("/details")
    public String afficherDetail(@RequestParam(name = "noArticle") long noArticleVendu, Model model) {
        var article = articleVenduService.findArticleVendu(noArticleVendu);
        model.addAttribute("article", article);
        return "view-article-detail";
    }

    @GetMapping("/creer")
    public String creerArticleVendu(Model model) {
        model.addAttribute("article", new ArticleVendu());
        List<Categorie> categoriesList = categorieService.readAllCategories();
        model.addAttribute("categoriesList", categoriesList);
        List<Utilisateur> utilisateurList = utilisateurService.findAllUtilisateurs();
        model.addAttribute("utilisateurList", utilisateurList);
        return "view-article-create";
    }

    // Création d'un nouvel article
    @PostMapping("/creer")
    @Transactional
    public String creerArticleVendu(
            @ModelAttribute("article") ArticleVendu articleVendu,
            BindingResult bindingResult) {
        articleVenduService.addArticleVendu(articleVendu);
        return "redirect:/view-article";
    }
}
