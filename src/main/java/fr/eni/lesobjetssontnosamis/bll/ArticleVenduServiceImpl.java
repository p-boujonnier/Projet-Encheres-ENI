package fr.eni.lesobjetssontnosamis.bll;

import fr.eni.lesobjetssontnosamis.bo.*;
import fr.eni.lesobjetssontnosamis.dal.*;
//import fr.eni.lesobjetssontnosamis.exceptions.BusinessCode;
//import fr.eni.lesobjetssontnosamis.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Primary
public class ArticleVenduServiceImpl implements ArticleVenduService {

    // Injection des Repository
    @Autowired
    private ArticleVenduDAO articleVenduDAO;


    @Override
    public List<ArticleVendu> getArticleVendus() {
        return articleVenduDAO.findAll();
    }

    @Override
    public ArticleVendu findArticleVendu(int noArticle) {
        return null;
    }

    @Override
    @Transactional
    public void creerArticleVendu(ArticleVendu articleVendu) {
        articleVenduDAO.create(articleVendu);
//        BusinessException be = new BusinessException();
//        boolean isValid = true;
//        isValid &= validerNom(articleVendu, be);
//        isValid &= validerDescription(articleVendu, be);
//        isValid &= validerDateDebutEncheres(articleVendu, be);
//        isValid &= validerDateFintEncheres(articleVendu, be);
//        isValid &= validerMiseAPrix(articleVendu, be);
//        isValid &= validerPrixVente(articleVendu, be);
//
//        if (isValid) {
//            try {
//             ArticleVenduDAO.create(articleVendu);
//            } catch (DataAccessException e) {// Exception de la couche DAL
//                // Rollback automatique
//                be.add(BusinessCode.BLL_ARTICLEVENDU_CREATE_ERREUR);
//                throw be;
//            }
//        } else {
//            throw be;
//        }
    }
}