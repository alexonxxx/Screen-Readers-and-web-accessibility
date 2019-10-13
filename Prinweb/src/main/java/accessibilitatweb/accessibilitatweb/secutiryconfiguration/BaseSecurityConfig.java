package accessibilitatweb.accessibilitatweb.secutiryconfiguration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class BaseSecurityConfig extends WebSecurityConfigurerAdapter {



    @Override
    public void configure(WebSecurity web) throws Exception {
       web.ignoring().antMatchers("/resources/**");
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http

            .authorizeRequests()

                .antMatchers("/static/**").permitAll()
                .mvcMatchers("/login").permitAll()
                .mvcMatchers("/registration").permitAll()
                .mvcMatchers("/").permitAll()
                .mvcMatchers("/gestion-lectores").authenticated()
                .mvcMatchers("/nuevo-lector").authenticated()
                .mvcMatchers("/editar-lector").authenticated()
                .mvcMatchers("/gestion-evaluaciones").authenticated()
                .mvcMatchers("/nuevo-evaluacion").authenticated()
                .mvcMatchers("/editar-evaluacion").authenticated()
                .mvcMatchers("/nueva-comanda").authenticated()

                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()

            .logout()
                    .logoutUrl("/logout")
                .logoutSuccessUrl("/login"); //where to go when logout is successful


        http
            .csrf().disable()
            .headers()
            .frameOptions().disable();
    }


}