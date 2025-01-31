package fr.eni.lesobjetssontnosamis.bll;

import fr.eni.lesobjetssontnosamis.bo.Categorie;

import java.util.List;

public interface CategorieService {

    void addCategorie(Categorie categorie);
    List<Categorie> findAllCategories();
    Categorie findCategorieById(int id);
    void updateCategorie(Categorie categorie);
    void deleteCategorieById(Long id);
}
