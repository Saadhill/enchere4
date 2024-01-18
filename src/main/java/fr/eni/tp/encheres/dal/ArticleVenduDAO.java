package fr.eni.tp.encheres.dal;

import fr.eni.tp.encheres.bo.ArticleVendu;
import fr.eni.tp.encheres.bo.Utilisateur;

import java.util.List;

public interface ArticleVenduDAO {

    public ArticleVendu read(Integer id);
    public ArticleVendu readArticleByNom(String nom_article);
    public List<ArticleVendu> findAll();
    public void update(ArticleVendu articleVendu );
    public void insert (ArticleVendu articleVendu);
    public void delete (Integer id);

    //TODO insérer méthodes pour lier avec les autres tables (Utilisateur, Enchère, Retrait, Catégorie)

}
