package br.com.fiap.motoconnect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll() // Permite acesso à página de login e recursos estáticos
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Apenas usuários com perfil ADMIN acessam /admin/**
                        .requestMatchers("/perfil").authenticated() // Apenas usuários autenticados podem acessar /perfil
                        .anyRequest().authenticated() // Todas as outras requisições exigem autenticação
                )
                .formLogin(form -> form
                        .loginPage("/login") // Define a página de login customizada
                        .defaultSuccessUrl("/home", true) // Página para onde o usuário é redirecionado após o login
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL para fazer logout
                        .logoutSuccessUrl("/login") // Redireciona para a página de login com um parâmetro
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Usa BCrypt para criptografar as senhas. É muito importante nunca salvar senhas em texto plano!
        return new BCryptPasswordEncoder();
    }
}
