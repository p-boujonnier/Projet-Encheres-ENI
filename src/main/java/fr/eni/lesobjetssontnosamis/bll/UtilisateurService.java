package fr.eni.lesobjetssontnosamis.bll;

import fr.eni.lesobjetssontnosamis.bo.Utilisateur;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UtilisateurService extends UserDetailsService {

    void addUtilisateur(Utilisateur utilisateur);

    void updateUtilisateur(Utilisateur utilisateur);

    Utilisateur findUtilisateurById(Long id);

    Utilisateur findUtilisateurByEmail(String email);

    List<Utilisateur> findAllUtilisateurs();
}
