package fr.eni.lesobjetssontnosamis.bll;

import fr.eni.lesobjetssontnosamis.bo.Utilisateur;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UtilisateurService {

    void addUtilisateur(Utilisateur utilisateur);


    Utilisateur findUtilisateurById(long id);

    Utilisateur findUtilisateurByEmail(String email);

    List<Utilisateur> findAllUtilisateurs();

    // ---------------------------------------

    void updateUtilisateur(Utilisateur utilisateur);

    void deleteUtilisateur(String email);

}
