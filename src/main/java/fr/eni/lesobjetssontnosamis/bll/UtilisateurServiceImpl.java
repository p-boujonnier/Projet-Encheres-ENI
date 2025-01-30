package fr.eni.lesobjetssontnosamis.bll;


import fr.eni.lesobjetssontnosamis.bo.Utilisateur;
import fr.eni.lesobjetssontnosamis.dao.DAOUtilisateurs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private DAOUtilisateurs daoUtilisateurs;

    @Override
    public void addUtilisateur(Utilisateur utilisateur) {
    }

    @Override
    public List<Utilisateur> getUtilisateurs() {
        return daoUtilisateurs.findAll();
    }

    @Override
    public Utilisateur findByEmail(String emailUtilisateur) {
        return null;
    }

    @Override
    public void updateUtilisateur(Utilisateur utilisateur) {

    }
}
