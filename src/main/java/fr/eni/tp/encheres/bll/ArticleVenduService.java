package fr.eni.tp.encheres.bll;

import fr.eni.tp.encheres.bo.ArticleVendu;
import fr.eni.tp.encheres.bo.Utilisateur;

import java.util.List;

public interface ArticleVenduService {

    void delete(Integer no_article);

    void update(ArticleVendu articleVendu);

    void createArticleVendu (ArticleVendu articleVendu);

    ArticleVendu read (Integer no_article);

    ArticleVendu readArticleByNom (String nom_article);

    List<ArticleVendu> findAll ();





}