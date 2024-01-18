package fr.eni.tp.encheres.DAO;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import fr.eni.tp.encheres.bo.ArticleVendu;
import fr.eni.tp.encheres.dal.ArticleVenduDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class PersonneDAOTests {
	@Autowired
	ArticleVenduDAO dao;
	
	/**
	 * Test autour d'un scénario
	 * J'utilise cela pour développer
	 */
	@Test
	@Transactional
	void generalTest() {
		ArticleVendu p = new ArticleVendu("Jean");
		dao.insert(p);
		System.out.println(p);
		dao.findAll().forEach(System.out::println);
	}
	
	
	/**
	 * Test unitaire automatisé
	 */
	@Test
	@Transactional
	void insertTest() {
		ArticleVendu p = new ArticleVendu("Jean-Poil");
		dao.insert(p);
		assertNotNull(p.getNo_article());// si une clé a été générée alors c'est que la personne est insérée
	}
	
	/**
	 * Test unitaire automatisé
	 */
	@Test
	@Transactional
	void getAllTest() {
		ArticleVendu p = new ArticleVendu("Jeanpeuplus");
		dao.insert(p);
		List<ArticleVendu> lst = dao.findAll();
		if(!lst.contains(p)) {
			fail();
		}
	}
	
	

}
