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
            av.setNoArticle(rs.getInt("no_article"));
            av.setNomArticle(rs.getString("nom_article"));
            av.setDescription(rs.getString("description"));
            av.setDateDebutEncheres(LocalDate.parse(rs.getString("date_debut_encheres")));
            av.setDateFinEncheres(LocalDate.parse(rs.getString("date_fin_encheres")));
            av.setMiseAPrix(rs.getInt("prix_initial"));
            av.setPrixVente(rs.getInt("prix_vente"));

            // Liste des enchères pour un article
            var listEncheres = new ArrayList<Enchere>();
            av.setEncheres(listEncheres);

            // Association a un vendeur
            var vendeur = new Utilisateur();
            vendeur.setNoUtilisateur(rs.getInt("no_utilisateur"));
            av.setVendeur(vendeur);

            // Association a une catégorie
            var categorie = new Categorie();
            categorie.setNoCategorie(rs.getInt("no_categorie"));
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
        namedParameters.addValue("dateDebutEncheres", articleVendu.getDateDebutEncheres());
        namedParameters.addValue("dateFinEncheres", articleVendu.getDateFinEncheres());
        namedParameters.addValue("miseAPrix", articleVendu.getMiseAPrix());
        namedParameters.addValue("prixVente", articleVendu.getPrixVente());

        jdbcTemplate.update(INSERT, namedParameters, keyHolder);

        if (keyHolder != null && keyHolder.getKey() != null) {
            // Mise à jour de l'identifiant du film auto-généré par la base
            articleVendu.setNoArticle(keyHolder.getKey().intValue());

        }
    }
}