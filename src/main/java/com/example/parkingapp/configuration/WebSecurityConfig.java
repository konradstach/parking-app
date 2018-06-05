package com.example.parkingapp.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("dataSource")
    @Autowired
    DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;


    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {

        PasswordEncoder encoder = new BCryptPasswordEncoder();

        auth.
                jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests().mvcMatchers("/rest/**").permitAll()
                .antMatchers("/css/**", "/js/**", "/img/**", "/fonts/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/parkingapp/mylogin").permitAll().defaultSuccessUrl("/parkingapp/parking")
                .and()
                .httpBasic()
                .and()
                .logout().permitAll().invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .and()
//                .rememberMe().key("secret-key").rememberMeParameter("remember-me").tokenValiditySeconds(3600)
//                .and()
                .sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(false).expiredUrl("/parkingapp/mylogin?logout")
                .sessionRegistry(sessionRegistry());
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
}
