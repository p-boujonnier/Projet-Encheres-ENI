package fr.eni.lesobjetssontnosamis.dao;

import fr.eni.lesobjetssontnosamis.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class DAOUtilisateursSQL implements DAOUtilisateurs{

    private final String INSERT = "INSERT INTO UTILISATEUR (email, nom, prenom) VALUES (:email, :nom, :prenom)";
    private final String FIND_BY_EMAIL = "SELECT email, nom, prenom FROM UTILISATEUR WHERE EMAIL = :email";
    private final String FIND_ALL = "SELECT email, nom, prenom FROM UTILISATEUR";
    private final String UPDATE = "UPDATE UTILISATEUR SET nom = :nom, prenom = :prenom WHERE email = :email";
    private final String compteUnique = "SELECT COUNT(email) FROM UTILISATEUR WHERE email = :email";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Utilisateur> findAll() {
        return namedParameterJdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(Utilisateur.class));
    }


    @Override
    public Utilisateur read(String emailUtilisateur) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("email", emailUtilisateur);

        return namedParameterJdbcTemplate.queryForObject(FIND_BY_EMAIL, namedParameters,
                new BeanPropertyRowMapper<>(Utilisateur.class));
    }

    @Override
    public void create(Utilisateur utilisateur) {

    }

    @Override
    public void update(Utilisateur utilisateur) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("pseudo", utilisateur.getPseudo());
        namedParameters.addValue("nom", utilisateur.getNom());
        namedParameters.addValue("prenom", utilisateur.getPrenom());
        namedParameters.addValue("email", utilisateur.getEmail());
        namedParameters.addValue("telephone", utilisateur.getTelephone());
        namedParameters.addValue("rue", utilisateur.getRue());
        namedParameters.addValue("code_postal", utilisateur.getCodePostal());
        namedParameters.addValue("ville", utilisateur.getVille());
        namedParameters.addValue("credit", utilisateur.getCredit());

        namedParameterJdbcTemplate.update(UPDATE, namedParameters);
    }

    @Override
    public void delete(Utilisateur utilisateur) {

    }

    @Override
    public Utilisateur compteUnique(String email) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("email", email);
        return namedParameterJdbcTemplate.queryForObject(compteUnique, namedParameters, Utilisateur.class);
    }

}
