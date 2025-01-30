package fr.eni.lesobjetssontnosamis.dal;

import fr.eni.lesobjetssontnosamis.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UtilisateursDAOImpl implements UtilisateursDAO {

    private final String INSERT = "INSERT INTO UTILISATEURS (email, nom, prenom) VALUES (:email, :nom, :prenom)";
    private final String FIND_BY_EMAIL = "SELECT pseudo, nom, prenom, email, telephone, rue, code_postal, ville FROM UTILISATEURS WHERE email = :email";
    private final String FIND_ALL = "SELECT email, nom, prenom FROM UTILISATEURS";
    private final String UPDATE = "UPDATE UTILISATEURS SET nom = :nom, prenom = :prenom WHERE email = :email";
    private final String compteUnique = "SELECT COUNT(email) FROM UTILISATEURS WHERE email = :email";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Utilisateur> findAll() {
        return jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(Utilisateur.class));
    }


    @Override
    public Utilisateur read(String emailUtilisateur) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("email", emailUtilisateur);

        return jdbcTemplate.queryForObject(FIND_BY_EMAIL, namedParameters,
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

        jdbcTemplate.update(UPDATE, namedParameters);
    }

    @Override
    public void delete(Utilisateur utilisateur) {

    }

    @Override
    public Utilisateur compteUnique(String email) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("email", email);
        return jdbcTemplate.queryForObject(compteUnique, namedParameters, Utilisateur.class);
    }

}
