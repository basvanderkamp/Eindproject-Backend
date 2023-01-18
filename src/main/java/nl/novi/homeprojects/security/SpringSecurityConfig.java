package nl.novi.homeprojects.security;


import nl.novi.homeprojects.services.CustomUserDetailsService;
import nl.novi.homeprojects.filter.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    /*inject customUserDetailService en jwtRequestFilter*/
    private CustomUserDetailsService customUserDetailsService;
    private JwtRequestFilter jwtRequestFilter;

    public SpringSecurityConfig(CustomUserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain filter (HttpSecurity http) throws Exception {

        //JWT token authentication
        http
                .csrf().disable()
                .httpBasic().disable().cors().and()
                .authorizeRequests()

                //----------------------------------------Endpoints User--------------------------------------
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers(HttpMethod.GET,"/users").hasAnyRole("ADMIN","USER")
                .antMatchers(HttpMethod.POST,"/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")


                //----------------------------------------Endpoints Clients--------------------------------------
                 .antMatchers(HttpMethod.POST,"/clients").permitAll()
                .antMatchers(HttpMethod.POST,"/clients/**").permitAll()
                 .antMatchers(HttpMethod.GET,"/clients").hasAnyRole("ADMIN","USER")
                 .antMatchers(HttpMethod.DELETE,"/clients/**").hasRole("ADMIN")
                 .antMatchers(HttpMethod.PUT,"/clients/**").hasAnyRole("ADMIN","USER")


                //----------------------------------------Endpoints Assignments--------------------------------------
                .antMatchers(HttpMethod.POST,"/assignments").hasAnyRole("ADMIN","USER")
                .antMatchers(HttpMethod.GET,"/assignments").hasAnyRole("ADMIN","USER")
                .antMatchers(HttpMethod.DELETE,"/assignments/**").hasAnyRole("ADMIN","USER")
                .antMatchers(HttpMethod.PUT,"/assignments/**").hasAnyRole("ADMIN","USER")


                //----------------------------------------Endpoints Uploads--------------------------------------
                /*voeg de antmatchers toe voor admin(post en delete) en user (overige)*/





                .antMatchers("/authenticated").authenticated()
                .antMatchers("/authenticate").permitAll()/*allen dit punt mag toegankelijk zijn voor niet ingelogde gebruikers*/
                .anyRequest().permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}