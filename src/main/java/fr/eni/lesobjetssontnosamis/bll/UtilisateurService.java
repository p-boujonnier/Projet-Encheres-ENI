package fr.eni.lesobjetssontnosamis.bll;

import fr.eni.lesobjetssontnosamis.bo.Utilisateur;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UtilisateurService {

    void addUtilisateur(Utilisateur utilisateur);

    void updateUtilisateur(Utilisateur utilisateur);

    Utilisateur findUtilisateurById(Long id);

    Utilisateur findUtilisateurByEmail(String email);

    List<Utilisateur> findAllUtilisateurs();
}
