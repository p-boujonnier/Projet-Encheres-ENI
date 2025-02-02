package fr.eni.lesobjetssontnosamis.dal;

import fr.eni.lesobjetssontnosamis.bo.Categorie;

import java.util.List;

public interface CategorieDAO {
    void create(Categorie categorie);
    List<Categorie> readAll();
    Categorie readById(int id);
    void update(Categorie categorie);
    void delete(Categorie categorie);
}
