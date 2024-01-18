package fr.eni.tp.encheres;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class Conf {
	@Bean
	public DataSource dataSource() {
	    return new EmbeddedDatabaseBuilder()
	      .setType(EmbeddedDatabaseType.H2)
	      .addScript("classpath:schema.sql").build();
	}
	
//    @Bean
//    public DataSource mysqlDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/springjdbc");
//        dataSource.setUsername("guest_user");
//        dataSource.setPassword("guest_password");
//
//        return dataSource;
//    }
}
