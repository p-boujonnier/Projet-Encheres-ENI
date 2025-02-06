package fr.eni.lesobjetssontnosamis.dal;

import fr.eni.lesobjetssontnosamis.bo.ArticleVendu;

import java.util.List;

public interface ArticleVenduDAO {
    void create(ArticleVendu articleVendu);

    //    ArticleVendu read(int id);
    List<ArticleVendu> findAll();

    //    String findNomArticle(long noArticle); //String findTitre(long id);
//    boolean findNomArticle(String nomArticle); //boolean findTitre(String titre);
    ArticleVendu findArticleVendu(long noArticle);
}
