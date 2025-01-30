package fr.eni.lesobjetssontnosamis.dal;

import fr.eni.lesobjetssontnosamis.bo.Utilisateur;

import java.util.List;

public interface UtilisateursDAO {

    List<Utilisateur> findAll();

    Utilisateur read(String email);


    void create(Utilisateur utilisateur);

    void update(Utilisateur utilisateur);

    void delete(Utilisateur utilisateur);

    Utilisateur compteUnique(String Email);
}
