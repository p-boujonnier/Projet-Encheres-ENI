package fr.eni.lesobjetssontnosamis.bll;

import fr.eni.lesobjetssontnosamis.bo.Categorie;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategorieService {

    void createCategorie(Categorie categorie);
    List<Categorie> readAllCategories();
    Categorie readCategorieById(int id);
    void updateCategorie(Categorie categorie);
    void deleteCategorieById(Long id);
}
