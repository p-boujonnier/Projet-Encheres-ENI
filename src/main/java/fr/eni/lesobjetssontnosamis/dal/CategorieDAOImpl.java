package fr.eni.lesobjetssontnosamis.dal;

import fr.eni.lesobjetssontnosamis.bo.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategorieDAOImpl implements CategorieDAO {
    private final String FIND_BY_ID = "SELECT no_categorie, libelle FROM categories WHERE no_categorie = :id";
    private final String FIND_ALL = "SELECT no_categorie, libelle FROM categories";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void create(Categorie categorie) {

    }

    @Override
    public List<Categorie> readAll() {
        return jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(Categorie.class));
    }

    @Override
    public Categorie readById(long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);
        return jdbcTemplate.queryForObject(FIND_BY_ID, namedParameters,
                new BeanPropertyRowMapper<>(Categorie.class));
    }


    @Override
    public void update(Categorie categorie) {

    }

    @Override
    public void delete(Categorie categorie) {

    }
}