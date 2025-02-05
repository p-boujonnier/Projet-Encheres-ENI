package fr.eni.lesobjetssontnosamis.dal;

import fr.eni.lesobjetssontnosamis.bo.Utilisateur;

import java.util.List;

public interface UtilisateurDAO {
    void create(Utilisateur utilisateur);

    Utilisateur read(String email);

    Utilisateur readByNo(long noUtilisateur);

    List<Utilisateur> readAll();

    void update(Utilisateur utilisateur);

    void delete(Utilisateur utilisateur);

    Utilisateur compteUnique(String Email);

}
