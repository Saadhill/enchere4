package fr.eni.tp.encheres;

import fr.eni.tp.encheres.bll.ArticleVenduService;
import fr.eni.tp.encheres.bll.impl.ArticleVenduServiceImpl;
import fr.eni.tp.encheres.bo.ArticleVendu;
import fr.eni.tp.encheres.dal.ArticleVenduDAO;

import java.util.List;

public class MainTest {

    public static void main(String[] args) {
        // Créez une instance de la classe de DAO que vous utilisez réellement (par exemple, ArticleVenduDAOImpl)
        ArticleVenduDAO articleVenduDAO = (ArticleVenduDAO) new ArticleVendu("Chaise"); // Remplacez par votre implémentation réelle
        ArticleVenduService articleService = new ArticleVenduServiceImpl(articleVenduDAO);

        // Testez vos méthodes ici

        // Exemple de test pour createArticleVendu
        ArticleVendu articleToAdd = new ArticleVendu("Chaise");
        // Initialisez articleToAdd avec des valeurs appropriées
        articleService.createArticleVendu(articleToAdd);
        System.out.println("Article ajouté avec succès.");

        // Exemple de test pour read
        int articleIdToRead = 1; // Remplacez par un ID existant dans votre base de données
        ArticleVendu readArticle = articleService.read(articleIdToRead);
        System.out.println("Lecture de l'article avec ID " + articleIdToRead + ": " + readArticle);

        // Exemple de test pour readArticleByNom
        String articleNomToRead = "Nom de l'article à rechercher"; // Remplacez par un nom existant dans votre base de données
        ArticleVendu readArticleByNom = articleService.readArticleByNom(articleNomToRead);
        System.out.println("Lecture de l'article avec nom " + articleNomToRead + ": " + readArticleByNom);

        // Exemple de test pour findAll
        List<ArticleVendu> allArticles = articleService.findAll();
        System.out.println("Liste de tous les articles :");
        for (ArticleVendu article : allArticles) {
            System.out.println(article);
        }

        // Exemple de test pour delete
        int articleIdToDelete = 2; // Remplacez par un ID existant dans votre base de données
        articleService.delete(articleIdToDelete);
        System.out.println("Article avec ID " + articleIdToDelete + " supprimé avec succès.");

        // Exemple de test pour update
        ArticleVendu articleToUpdate = new ArticleVendu("Chaise");
        // Initialisez articleToUpdate avec des valeurs appropriées
        articleService.update(articleToUpdate);
        System.out.println("Article mis à jour avec succès.");
    }
}

