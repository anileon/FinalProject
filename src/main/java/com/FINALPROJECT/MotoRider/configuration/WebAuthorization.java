package com.FINALPROJECT.MotoRider.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@EnableWebSecurity
@Configuration
class WebAuthorization {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {



        http.authorizeRequests()

                .antMatchers("/rest", "h2-console").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/api/admin/moto", "/api/admin/product"
                , "/api/admin/eliminarMoto", "/api/admin/eliminarProduct").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST,"/api/admin/moto", "/api/admin/product").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/comprar").hasAnyAuthority("CLIENT", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/products", "/api/motorcycles").permitAll()
                .antMatchers(HttpMethod.GET, "/api/pdf/generate").hasAnyAuthority("CLIENT")
                .antMatchers(HttpMethod.POST, "/api/clients").permitAll()
                .antMatchers("/web/**").permitAll()
            ;






    http.formLogin()

            .usernameParameter("email")

            .passwordParameter("password")

            .loginPage("/api/login");



    http.logout().logoutUrl("/api/logout");


    http.csrf().disable();

    http.headers().frameOptions().disable();

    http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

    http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

    http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

    http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());

    return http.build();
}

    private void clearAuthenticationAttributes(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {

            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        }

    }


}