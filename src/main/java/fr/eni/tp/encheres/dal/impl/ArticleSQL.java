package fr.eni.tp.encheres.dal.impl;

import fr.eni.tp.encheres.bo.ArticleVendu;
import fr.eni.tp.encheres.bo.Categorie;
import fr.eni.tp.encheres.dal.ArticleVenduDAO;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository

public class ArticleSQL implements ArticleVenduDAO {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ArticleSQL(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    public final static String SELECT_ALL_ARTICLEVENDU = "SELECT * FROM ARTICLES_VENDUS";
    public final static String SELECT_ARTICLEVENDU_BY_ID = "SELECT * FROM ARTICLES_VENDUS WHERE no_article = :no_article";

    public final static String SELECT_ARTICLEVENDU_BY_NOM_ARTICLE = "SELECT * FROM ARTICLES_VENDUS WHERE nom-article = :nom-article";

//    public final static String INSERT_ARTICLEVENDU = "INSERT INTO ARTICLES_VENDUS(no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie)";

    public final static String UPDATE_ARTICLEVENDU = "UPDATE ARTICLES_VENDUS SET nom_article=:nom_article, description=:description, date_debut_encheres=:date_debut_encheres WHERE no_article=:no_article";

    public final static String DELETE_ARTCILEVENDU = "delete ARTICLES_VENDUS where no_article = :no_article";


    //lire un article
    @Override
    public ArticleVendu read(Integer no_article) {
        ArticleVendu src = new ArticleVendu(no_article);
        ArticleVendu articleVendu = namedParameterJdbcTemplate.queryForObject(SELECT_ARTICLEVENDU_BY_ID, new BeanPropertySqlParameterSource(src), new BeanPropertyRowMapper<>(ArticleVendu.class));
        return articleVendu;
    }

    @Override
    public ArticleVendu readArticleByNom(String nom_article) {
        ArticleVendu src = new ArticleVendu(nom_article);
        ArticleVendu articleVendu = namedParameterJdbcTemplate.queryForObject(SELECT_ARTICLEVENDU_BY_NOM_ARTICLE, new BeanPropertySqlParameterSource(src), new BeanPropertyRowMapper<>(ArticleVendu.class));
        return articleVendu;
    }


    @Override
    public List<ArticleVendu> findAll() {
        List<ArticleVendu> articleVendus;
        articleVendus = namedParameterJdbcTemplate.query(SELECT_ALL_ARTICLEVENDU, new BeanPropertyRowMapper<>(ArticleVendu.class));
        return articleVendus;
    }

    @Override
    public void update(ArticleVendu articleVendu) {
        String UPDATE_ARTICLEVENDU = "UPDATE ARTICLES_VENDUS SET nom_article=:nom_article, description=:description, date_debut_encheres=:date_debut_encheres WHERE no_article=:no_article";
        BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(articleVendu);
        namedParameterJdbcTemplate.update(UPDATE_ARTICLEVENDU, namedParameters);
    }

    //créer un article
    @Override
    public void insert(ArticleVendu articleVendu) {
        String INSERT_ARTICLEVENDU = "INSERT INTO ARTICLES_VENDUS(no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie)";
        BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(articleVendu);
        namedParameterJdbcTemplate.update(INSERT_ARTICLEVENDU, namedParameters);
    }

//supprimer un article
    @Override
    public void delete(Integer no_article) {
        ArticleVendu src = new ArticleVendu(no_article);
        namedParameterJdbcTemplate.update(DELETE_ARTCILEVENDU, new BeanPropertySqlParameterSource(src));


    }
}
