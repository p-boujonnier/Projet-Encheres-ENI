package fr.eni.lesobjetssontnosamis.bll;

import fr.eni.lesobjetssontnosamis.bo.Categorie;
import fr.eni.lesobjetssontnosamis.dal.CategorieDAO;
import fr.eni.lesobjetssontnosamis.dal.UtilisateursDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class CategorieServiceImpl implements CategorieService{
    @Autowired
    private CategorieDAO categorieDAO;

    @Override
    public void addCategorie(Categorie categorie) {
    }

    @Override
    public List<Categorie> findAllCategories() {
        return categorieDAO.readAll();
    }

    @Override
    public Categorie findCategorieById(int id) {
        Categorie categorie = categorieDAO.readById(id);
        return categorie;
    }

    @Override
    public void updateCategorie(Categorie categorie) {

    }

    @Override
    public void deleteCategorieById(Long id) {

    }
}
