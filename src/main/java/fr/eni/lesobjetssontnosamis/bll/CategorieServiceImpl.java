package fr.eni.lesobjetssontnosamis.bll;

import fr.eni.lesobjetssontnosamis.bo.Categorie;
import fr.eni.lesobjetssontnosamis.dal.CategorieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class CategorieServiceImpl implements CategorieService {
    private CategorieDAO categorieDAO;

    public CategorieServiceImpl(CategorieDAO categorieDAO) {
        this.categorieDAO = categorieDAO;
    }

    @Override
    public void createCategorie(Categorie categorie) {
    }

    @Override
    public List<Categorie> readAllCategories() {
        return categorieDAO.readAll();
    }

    @Override
    public Categorie readCategorieById(int id) {
        return categorieDAO.readById(id);
    }

    @Override
    public void updateCategorie(Categorie categorie) {
    }

    @Override
    public void deleteCategorieById(Long id) {
    }
}
