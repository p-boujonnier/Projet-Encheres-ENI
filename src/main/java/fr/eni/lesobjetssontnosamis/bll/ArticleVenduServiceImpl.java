package fr.eni.lesobjetssontnosamis.bll;

import fr.eni.lesobjetssontnosamis.bo.*;
import fr.eni.lesobjetssontnosamis.dal.*;
//import fr.eni.lesobjetssontnosamis.exceptions.BusinessCode;
//import fr.eni.lesobjetssontnosamis.exceptions.BusinessException;
import fr.eni.lesobjetssontnosamis.exceptions.BusinessCode;
import fr.eni.lesobjetssontnosamis.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    public void addArticleVendu(ArticleVendu articleVendu) {
        BusinessException be = new BusinessException();
        boolean isValid = true;
        isValid &= validerNom(articleVendu, be);
        isValid &= validerDescription(articleVendu, be);
        isValid &= validerDateDebutEncheres(articleVendu, be);
        isValid &= validerDateFintEncheres(articleVendu, be);
        isValid &= validerMiseAPrix(articleVendu, be);

        if (isValid) {
            try {
                articleVenduDAO.create(articleVendu);
            } catch (DataAccessException e) {
                be.add(BusinessCode.BLL_ARTICLE_CREATE_ERREUR);
                throw be;
            }
        } else {
            throw be;
        }
    }

    // Validation des BOs
    private boolean validerNom(ArticleVendu av, BusinessException be) {
        if (av.getNomArticle() == null) {
            be.add(BusinessCode.VALIDATION_NOM_NULL);
            return false;
        }
        if (av.getNomArticle().length() > 250) {
            be.add(BusinessCode.VALIDATION_NOM_TOO_LONG);
            return false;
        }
        return true;
    }

    private boolean validerDescription(ArticleVendu av, BusinessException be) {
        if (av.getDescription() == null) {
            be.add(BusinessCode.VALIDATION_DESCRIPTION_NULL);
            return false;
        }
        if (av.getDescription().length() > 2500) {
            be.add(BusinessCode.VALIDATION_DESCRIPTION_TOO_LONG);
            return false;
        }
        return true;
    }

    private boolean validerDateDebutEncheres(ArticleVendu av, BusinessException be) {
        if (av.getDateDebutEncheres() == null) {
            be.add(BusinessCode.VALIDATION_DATE_DEBUT_NULL);
        }
        if (av.getDateDebutEncheres().isBefore(LocalDate.now())) {
            be.add(BusinessCode.VALIDATION_DATE_DEBUT_DEPACEE);
        }
        return true;
    }

    private boolean validerDateFintEncheres(ArticleVendu av, BusinessException be) {
        if (av.getDateFinEncheres() == null) {
            be.add(BusinessCode.VALIDATION_DATE_FIN_NULL);
        }
        if (av.getDateFinEncheres().isBefore(av.getDateDebutEncheres())) {
            be.add(BusinessCode.VALIDATION_DATE_FIN_AVANT_DEBUT);
        }
        return true;
    }

    private boolean validerMiseAPrix(ArticleVendu av, BusinessException be) {
        if (av.getMiseAPrix() < 0) {
            be.add(BusinessCode.VALIDATION_MISE_A_PRIX_INFERIEUR_ZERO);
        }
        return true;
    }

}