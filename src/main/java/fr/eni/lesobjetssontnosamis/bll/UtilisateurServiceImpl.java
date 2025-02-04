package fr.eni.lesobjetssontnosamis.bll;


import fr.eni.lesobjetssontnosamis.bo.Utilisateur;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import fr.eni.lesobjetssontnosamis.dal.UtilisateursDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
@Primary
public class UtilisateurServiceImpl implements UtilisateurService {
    @Autowired
    private UtilisateursDAO utilisateursDAO;

    @Override
    public void addUtilisateur(Utilisateur utilisateur) {
        utilisateursDAO.create(utilisateur);
    }

    @Override
    public List<Utilisateur> getUtilisateurs() {
        return utilisateursDAO.findAll();
    }

    @Override
    public void updateUtilisateur(Utilisateur utilisateur) {
    }

    @Override
    public Utilisateur findUtilisateurById(Long id) {
        return null;
    }

    @Override
    public Utilisateur findUtilisateurByEmail(String email) {
        return utilisateursDAO.read(email);
    }
}
