package fr.eni.lesobjetssontnosamis.dal;

import fr.eni.lesobjetssontnosamis.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository

public class UtilisateurDAOImpl implements UtilisateurDAO {

    private final String CREATE = "INSERT INTO utilisateurs (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (:pseudo, :nom, :prenom, :email, :telephone, :rue, :codepostal, :ville, :motdepasse, :credit, :administrateur)";
    private final String READ_BY_EMAIL = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville FROM utilisateurs WHERE email = :email";
    private final String READ_ALL = "SELECT pseudo, nom, prenom, email, telephone, rue, code_postal, ville FROM utilisateurs";
    private final String UPDATE = "UPDATE utilisateurs SET nom = :nom, prenom = :prenom WHERE email = :email";
    private final String compteUnique = "SELECT COUNT(email) FROM utilisateurs WHERE email = :email";


//    @Autowired
//    public PasswordEncoder passwordEncoder;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public void create(Utilisateur utilisateur) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("pseudo", utilisateur.getPseudo());
        namedParameters.addValue("nom", utilisateur.getNom());
        namedParameters.addValue("prenom", utilisateur.getPrenom());
        namedParameters.addValue("email", utilisateur.getEmail());
        namedParameters.addValue("telephone", utilisateur.getTelephone());
        namedParameters.addValue("rue", utilisateur.getRue());
        namedParameters.addValue("codepostal", utilisateur.getCodePostal());
        namedParameters.addValue("ville", utilisateur.getVille());
        namedParameters.addValue("motdepasse", utilisateur.getMotDePasse());
        namedParameters.addValue("credit", utilisateur.getCredit());
        utilisateur.setAdministrateur(false);
        namedParameters.addValue("administrateur", utilisateur.isAdministrateur());
        jdbcTemplate.update(CREATE, namedParameters);
    }

    @Override
    public Utilisateur read(String emailUtilisateur) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("email", emailUtilisateur);

        return jdbcTemplate.queryForObject(READ_BY_EMAIL, namedParameters,
                new BeanPropertyRowMapper<>(Utilisateur.class));
    }

    @Override
    public List<Utilisateur> readAll() {
        return jdbcTemplate.query(READ_ALL, new BeanPropertyRowMapper<>(Utilisateur.class));
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
