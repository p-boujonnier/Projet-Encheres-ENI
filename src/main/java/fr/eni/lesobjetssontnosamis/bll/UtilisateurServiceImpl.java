package fr.eni.lesobjetssontnosamis.bll;


import fr.eni.lesobjetssontnosamis.bo.Utilisateur;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class UtilisateurServiceImpl implements UtilisateurService {

    @Override
    public void addUtilisateur(Utilisateur utilisateur) {
    }

    @Override
    public List<Utilisateur> getUtilisateurs() {
        return List.of();
    }

    @Override
    public Utilisateur findByEmail(String emailUtilisateur) {
        return null;
    }

    @Override
    public void updateUtilisateur(Utilisateur utilisateur) {
    }

    @Override
    public Utilisateur findUtilisateurById(Long id) {
        return null;
    }
}
