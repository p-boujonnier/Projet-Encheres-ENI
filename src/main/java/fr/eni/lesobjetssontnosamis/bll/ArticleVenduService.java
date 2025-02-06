package fr.eni.lesobjetssontnosamis.bll;

import fr.eni.lesobjetssontnosamis.bo.ArticleVendu;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public interface ArticleVenduService {

    List<ArticleVendu> getArticleVendus();

    ArticleVendu findArticleVendu(long noArticle);

    void addArticleVendu(ArticleVendu articleVendu);
}
