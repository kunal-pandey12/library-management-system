package library.management.books.securityconfig;
import library.management.books.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        //  Auth — sabko allow
                        .requestMatchers("/auth/**").permitAll()

                        //  Author Controller — /Author/**
                        .requestMatchers(HttpMethod.GET, "/Author/**")
                        .authenticated()
                        .requestMatchers(HttpMethod.POST, "/Author/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/Author/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/Author/**")
                        .hasRole("ADMIN")

                        //  Book Controller — /books/**
                        .requestMatchers(HttpMethod.GET, "/books/**")
                        .authenticated()
                        .requestMatchers(HttpMethod.POST, "/books/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/books/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/books/**")
                        .hasRole("ADMIN")

                        //  Issue Controller — /issue/**
                        .requestMatchers(HttpMethod.GET, "/issue/**")
                        .authenticated()
                        .requestMatchers(HttpMethod.POST, "/issue/**")
                        .authenticated()
                        .requestMatchers(HttpMethod.PUT, "/issue/**")
                        .authenticated()

                        //  User Controller — /User/**
                        // Sirf ADMIN dekh sakta hai users
                        .requestMatchers(HttpMethod.GET, "/User/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/User/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/User/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/User/**")
                        .hasRole("ADMIN")

                        // Baaki sab authenticated
                        .anyRequest().authenticated()
                )

                .httpBasic(basic ->
                        basic.realmName("Library Management System"));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}