package fr.eni.lesobjetssontnosamis.bll;


import fr.eni.lesobjetssontnosamis.bo.Utilisateur;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import fr.eni.lesobjetssontnosamis.dal.UtilisateurDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
@Primary
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private UtilisateurDAO utilisateurDAO;

    @Override
    public void addUtilisateur(Utilisateur utilisateur) {
        utilisateurDAO.create(utilisateur);
    }

    @Override
    public List<Utilisateur> findAllUtilisateurs() {
        return utilisateurDAO.readAll();
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
        return utilisateurDAO.read(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return utilisateurDAO.read(username);
    }
}
