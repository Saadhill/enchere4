package fr.eni.tp.encheres.dal.impl;

import fr.eni.tp.encheres.bo.ArticleVendu;
import fr.eni.tp.encheres.bo.Enchere;
import fr.eni.tp.encheres.bo.Utilisateur;
import fr.eni.tp.encheres.dal.EnchereDAO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EnchereSQL implements EnchereDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbcTemplate;

    public EnchereSQL(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(Enchere enchere) {
        String sql = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (:no_utilisateur, :no_article, :date_enchere, :montant_enchere)";
        BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(enchere);

        namedParameterJdbcTemplate.update(sql, namedParameters);

    }

    @Override
    public void update(Enchere enchere) {
        String sql = "UPDATE ENCHERES SET  date_enchere = :date_enchere, montant_enchere = :montant_enchere WHERE no_utilisateur = :no_utilisateur";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("no_utilisateur",enchere.getUtilisateur().getNo_utilisateur());
		namedParameters.addValue("date_enchere", enchere.getDate_enchere());
		namedParameters.addValue("montant_enchere", enchere.getMontant_enchere());

        namedParameterJdbcTemplate.update(sql, namedParameters);
    }



    @Override
    public List<Enchere> findAll() {
        String sql = "SELECT * FROM ENCHERES;";
        List<Enchere> encheres = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Enchere.class));
        return encheres;
    }
}