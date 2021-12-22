package com.project.rest.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

    @Override // Configura as solicitações de acesso via HTTP
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable() // Desativa as configurações padrão de memória
                .authorizeRequests() //Permitir restrigir acessos.
                .antMatchers(HttpMethod.GET, "/").permitAll() //Qualquer usuario acessa a pagina inicial.
                .anyRequest().authenticated()
                .and().formLogin().permitAll() //Permite qualquer usuario
                .and().logout() // Mapeia a URL de Logout e invalida usuario autenticado
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }

    @Override // Cria autenticação do Usuario com banco de dados ou em memoria
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("pablo")
                .password("$2a$10$rZNbbXiojQaXHyZm4ayKI.DjV1guO/MNCvFkxtFTX05PMNk9nQNBy")
                .roles("ADMIN");
    }

    @Override //Ignora URL especificas
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/materialize/**");
    }
}
