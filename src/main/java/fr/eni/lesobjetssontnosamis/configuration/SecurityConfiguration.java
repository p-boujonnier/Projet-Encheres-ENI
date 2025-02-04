package fr.eni.lesobjetssontnosamis.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
//
@EnableWebSecurity
@Configuration
public class SecurityConfiguration {


    @Bean
    UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcuserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcuserDetailsManager.setUsersByUsernameQuery("select pseudo,mot_de_passe,1 from Utilisateurs where pseudo=?");
        jdbcuserDetailsManager.setAuthoritiesByUsernameQuery("select pseudo,administrateur from roles where pseudo=?");

        return jdbcuserDetailsManager;

    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(auth -> {


            auth.requestMatchers(HttpMethod.GET, "/").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/login").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/view-profil/").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/view-profil/get-profil").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/articles").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/view-articles/").permitAll();
            auth.requestMatchers(HttpMethod.POST, "/view-articles/").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/view-articles/get-article").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/view-ask-profil-provisory").permitAll();


            auth.requestMatchers("/css/*").permitAll();
            auth.requestMatchers("/images/*").permitAll();
            auth.anyRequest().permitAll();
        });


            http.formLogin(form -> form
                            .loginPage("/login")
                            .permitAll())
                    .logout(logout -> logout
                            .logoutUrl("/logout")  // URL de déconnexion (quand l'utilisateur clique pour se déconnecter)
                            .logoutSuccessUrl("/")  // Page de redirection après la déconnexion
                            .invalidateHttpSession(true)
                            .deleteCookies("JSESSIONID")// Invalider la session HTTP après la déconnexion
                            .clearAuthentication(true)    // Effacer l'authentification
                            .permitAll()                  // Permet à tout le monde d'accéder à la déconnexion
                    );

            return http.build();
        }
    }

