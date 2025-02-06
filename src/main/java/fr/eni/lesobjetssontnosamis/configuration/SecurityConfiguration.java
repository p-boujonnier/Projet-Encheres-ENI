package fr.eni.lesobjetssontnosamis.configuration;


import fr.eni.lesobjetssontnosamis.bll.UtilisateurService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    UserDetailsManager userdetailsManager;
    private final UtilisateurService utilisateurService;

    public SecurityConfiguration(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


@Bean
public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
    auth.userDetailsService(utilisateurService)
            .passwordEncoder(passwordEncoder());

    return auth.build();
}


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(auth -> {


            auth.requestMatchers(HttpMethod.GET, "/encheres").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/login").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/view-profil/").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/articles").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/view-articles/").permitAll();
            auth.requestMatchers(HttpMethod.POST, "/view-articles/").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/view-articles/get-article").permitAll();


            auth.requestMatchers("/css/*").permitAll();
            auth.requestMatchers("/images/*").permitAll();
            auth.anyRequest().permitAll();
        });

//
            http.formLogin(form -> form
                            .loginPage("/login")
                            .usernameParameter("email")
                            .loginProcessingUrl("/login")
                            .permitAll()
            )
                    .logout(logout -> logout.logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    );
                 // Permet à tout le monde d'accéder à la déconnexion


           return http.build();

    }



}

