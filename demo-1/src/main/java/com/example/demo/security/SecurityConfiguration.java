package com.example.demo.security;

import com.example.demo.security.detailsService.CustomUserDetailsService;
import com.example.demo.security.jwt.AuthEntryPointJwt;
import com.example.demo.security.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private CustomUserDetailsService detailsService;
    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public JwtRequestFilter authenticationJwtTokenFilter() {
        return new JwtRequestFilter();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(detailsService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/user/signup","/auth/user/signin",
                                "/swagger-ui/**","/v3/api-docs/**","/swagger-ui.html").permitAll()
                .requestMatchers(HttpMethod.GET,
                        "/home","/productos/**","/categorias/**","/ciudades/**","/imagenes/**",
                        "/caracteristicas/**","/puntuaciones/**","/roles/**").permitAll()
                .requestMatchers("/reservas/add","/reservas","/reservas/{id}","/reservas/user/{id}",
                        "/auth/user/{id}", "/auth/user/list").hasAnyRole("USER","ADMIN","MODERATOR")
                        // TODO .requestMatchers("/auth/user/list").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,
                     "/productos/**","/categorias/**","/ciudades/**","/imagenes/**"
                     ,"/caracteristicas/**","/puntuaciones/**").hasAnyRole("ADMIN","MODERATOR")
                .requestMatchers(HttpMethod.PUT,
                                "/productos/**","/categorias/**","/ciudades/**","/imagenes/**",
                                "/caracteristicas/**","/puntuaciones/**","/reservas/**").hasAnyRole("ADMIN","MODERATOR")
                .requestMatchers(HttpMethod.DELETE,
                        "/productos/**","/categorias/**","/ciudades/**","/imagenes/**",
                        "/caracteristicas/**","/puntuaciones/**","/reservas/**").hasRole("MODERATOR")

                .anyRequest().authenticated());

        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
/*
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/swagger-ui/**","/v3/api-docs/**");
    }
 */

}


