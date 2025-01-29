package fr.eni.lesobjetssontnosamis.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {


    @Bean
    UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcuserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcuserDetailsManager.setAuthoritiesByUsernameQuery("select pseudo,role from roles where pseudo=?");
        jdbcuserDetailsManager.setUsersByUsernameQuery("select pseudo,password,1 from utilisateur where pseudo=?");

        return jdbcuserDetailsManager;

    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(auth -> {


            auth.requestMatchers(HttpMethod.GET, "/index").permitAll();
            auth.requestMatchers("/Login").permitAll();


        auth.requestMatchers("/css/*").permitAll();
        auth.requestMatchers("/images/*").permitAll();
        auth.anyRequest().denyAll();
    });

        http.formLogin(form -> form
                        .loginPage("/login")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")  // URL de déconnexion (quand l'utilisateur clique pour se déconnecter)
                        .logoutSuccessUrl("/index")  // Page de redirection après la déconnexion
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")// Invalider la session HTTP après la déconnexion
                        .clearAuthentication(true)    // Effacer l'authentification
                        .permitAll()                  // Permet à tout le monde d'accéder à la déconnexion
                );

        return http.build();
    }
}
