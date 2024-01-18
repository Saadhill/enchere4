package fr.eni.tp.encheres.securite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import javax.sql.DataSource;

@Configuration
public class EniSecuriteConfig {


    @Autowired
    private DataSource dataSource;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



@Bean
    UserDetailsManager userDetailsManager(DataSource dataSource) {
        System.out.println(dataSource);
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery("select pseudo, mot_de_passe, 1 from UTILISATEURS where pseudo = ? ");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select pseudo, role from UTILISATEURS where pseudo = ? ");
        return jdbcUserDetailsManager;
    }




    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            auth
                    // Permettre aux visiteurs d'accéder à la liste des films
                    .requestMatchers(HttpMethod.GET, "/encheres").permitAll()
                    // Permettre aux visiteurs d'accéder au détail d'un film
                    .requestMatchers(HttpMethod.GET, "/profil").permitAll()
                    // Accès à la vue principale
                    .requestMatchers(HttpMethod.GET, "/details").permitAll()
                    .requestMatchers(HttpMethod.GET, "/recherche").permitAll()
                    .requestMatchers(HttpMethod.GET, "/login/inscription").permitAll()
                    .requestMatchers(HttpMethod.POST, "/login/register").permitAll()
                    .requestMatchers(HttpMethod.GET, "/creerarticle").authenticated()
                    .requestMatchers(HttpMethod.GET, "/article").permitAll().requestMatchers(HttpMethod.GET, "/error")
                    .permitAll().requestMatchers(HttpMethod.POST, "/creationarticle").permitAll()
                    .requestMatchers(HttpMethod.POST, "/encherir").permitAll().requestMatchers("/").permitAll()

                    // Permettre à tous d'afficher correctement ressources
                    .requestMatchers("/css/*").permitAll().requestMatchers("/Images/*").permitAll()
                    .requestMatchers("/javascript/*").permitAll()
                    // Il faut être connecté pour toutes autres URLs
                    .anyRequest().authenticated();
        });


        http.formLogin(form -> {
            //Configuration de la page login
            form.loginPage("/login").permitAll()
//                    .usernameParameter("username")
//                    .passwordParameter(passwordEncoder().encode("password"))
                    .defaultSuccessUrl("/enchere"); //page afficher par défaut lors de la connexion
        });


        http.logout(logout ->
                logout.invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/")
                        .permitAll()
        );

        return http.build();

    }

}

