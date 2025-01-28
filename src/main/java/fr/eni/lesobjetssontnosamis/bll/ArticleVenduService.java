package fr.eni.lesobjetssontnosamis.bll;



import fr.eni.lesobjetssontnosamis.bo.ArticleVendu;

import java.util.List;

public interface ArticleVenduService {

    List<ArticleVendu> getArticleVendus();

    ArticleVendu findArticleVendu(int noArticle);

}
