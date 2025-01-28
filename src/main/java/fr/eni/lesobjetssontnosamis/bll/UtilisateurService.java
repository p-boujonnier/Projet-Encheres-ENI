package fr.eni.lesobjetssontnosamis.bll;

import fr.eni.trocencheresjavaqueens.bo.Utilisateur;

import java.util.List;

public interface UtilisateurService {

    void addUtilisateur(Utilisateur utilisateur);

    List<Utilisateur> getUtilisateurs();

    Utilisateur findByEmail(String emailUtilisateur);

    void updateUtilisateur(Utilisateur utilisateur);
}
