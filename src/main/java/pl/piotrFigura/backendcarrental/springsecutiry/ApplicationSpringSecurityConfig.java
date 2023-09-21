package pl.piotrFigura.backendcarrental.springsecutiry;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.piotrFigura.backendcarrental.auth.ApplicationUserService;
import pl.piotrFigura.backendcarrental.jwt.JwtConfig;
import pl.piotrFigura.backendcarrental.jwt.JwtTokenVerifier;
import pl.piotrFigura.backendcarrental.jwt.JwtUsernameAndPasswordAuthenticationFilter;

import javax.crypto.SecretKey;

import static pl.piotrFigura.backendcarrental.springsecutiry.ApplicationUserPermission.*;
import static pl.piotrFigura.backendcarrental.springsecutiry.ApplicationUserRole.*;

////@RequiredArgsConstructor
////@Configuration
////@EnableWebSecurity
////@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class ApplicationSpringSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final PasswordEncoder passwordEncoder;
//    private final ApplicationUserService applicationUserService;
//    private final SecretKey secretKey;
//    private final JwtConfig jwtConfig;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
//                .addFilterAfter(new JwtTokenVerifier(jwtConfig, secretKey), JwtUsernameAndPasswordAuthenticationFilter.class)
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/api/v1/car**").hasAuthority(GET_CAR.getPermission())
//                .antMatchers(HttpMethod.POST, "/api/v1/car**").hasAuthority(ADD_CAR.getPermission())
//                .antMatchers(HttpMethod.PUT, "/api/v1/car**").hasAnyRole(ADD_CAR.getPermission())
//                .antMatchers(HttpMethod.DELETE, "/api/v1/car**").hasAnyRole(ADMIN.name(), BRANCH.name())
//                .anyRequest()
//                .authenticated();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(passwordEncoder);
//        provider.setUserDetailsService(applicationUserService);
//        return provider;
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }
//}
