package fr.eni.lesobjetssontnosamis.dal;

import fr.eni.lesobjetssontnosamis.bo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleVenduDAOImpl implements ArticleVenduDAO {
    private final String FIND_ALL = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM articles_vendus";

    private final String INSERT = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente)"
            + "VALUES(:nomArticle, :description, :dateDebutEncheres, :dateFinEncheres, :miseAPrix, :prixVente)";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<ArticleVendu> findAll() {
        return jdbcTemplate.query(FIND_ALL, new ArticleVenduRowMapper());
    }


    class ArticleVenduRowMapper implements RowMapper<ArticleVendu> {
        @Override
        public ArticleVendu mapRow(ResultSet rs, int rowNum) throws SQLException {
            // Création nouvel objet
            ArticleVendu av = new ArticleVendu();
            av.setNoArticle(rs.getLong("no_article"));
            av.setNomArticle(rs.getString("nom_article"));
            av.setDescription(rs.getString("description"));
            av.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
            av.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
            av.setMiseAPrix(rs.getInt("prix_initial"));
            av.setPrixVente(rs.getInt("prix_vente"));

            // Liste des enchères pour un article
            av.setEncheres(new ArrayList<>());

            // Association a un vendeur
            Utilisateur vendeur = new Utilisateur();
            vendeur.setNoUtilisateur(rs.getLong("no_utilisateur"));
            av.setVendeur(vendeur);

            // Association a une catégorie
            Categorie categorie = new Categorie();
            categorie.setNoCategorie(rs.getLong("no_categorie"));
            av.setCategorie(categorie);

            return av;
        }
    }
    @Override
    public void create(ArticleVendu articleVendu) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        namedParameters.addValue("nomArticle", articleVendu.getNomArticle());
        namedParameters.addValue("description", articleVendu.getDescription());
        namedParameters.addValue("dateDebutEncheres", java.sql.Date.valueOf(articleVendu.getDateDebutEncheres()));
        namedParameters.addValue("dateFinEncheres", java.sql.Date.valueOf(articleVendu.getDateFinEncheres()));
        namedParameters.addValue("prixInitial", articleVendu.getMiseAPrix()); // Correction du nom du paramètre
        namedParameters.addValue("prixVente", articleVendu.getPrixVente());
        namedParameters.addValue("no_utilisateur", articleVendu.getVendeur().getNoUtilisateur());
        namedParameters.addValue("no_categorie", articleVendu.getCategorie().getNoCategorie());

        jdbcTemplate.update(INSERT, namedParameters, keyHolder);
        if (keyHolder != null && keyHolder.getKey() != null) {
            articleVendu.setNoArticle(keyHolder.getKey().intValue());
        }
    }
}