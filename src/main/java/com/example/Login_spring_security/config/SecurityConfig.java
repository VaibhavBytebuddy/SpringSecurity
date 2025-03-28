package com.example.Login_spring_security.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(customizer->customizer.disable());// disable csrf token
//        http.authorizeHttpRequests(request ->request.anyRequest().authenticated());// authenticate all request , after authenticate all request 403 error is occurs when we try to fetch the pages
//        http.formLogin(Customizer.withDefaults());// for solve forbidden  403 error use this line for webrowser
//        http.httpBasic(Customizer.withDefaults());//for postman


//        Customizer<CsrfConfigurer<HttpSecurity>> custCsrf=new Customizer<CsrfConfigurer<HttpSecurity>>() {
//            @Override
//            public void customize(CsrfConfigurer<HttpSecurity> httpSecurityCsrfConfigurer) {
//                httpSecurityCsrfConfigurer.disable();
//            }
//        };

//        http.csrf(custCsrf);

        //this all for this one line , Customizer is functional interface so we can used lambda expression
        //alternative for all above line , csrf disable
        //http.csrf(customizer->customizer.disable());



        http
                .csrf(Customizer ->Customizer.disable())
                .authorizeHttpRequests(request-> request
                        .requestMatchers("login","register").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());
                //.formLogin(Customizer.withDefaults());
                //we can make session stateless , the side effect is whenever we try to login , it will ask username and password if you enter corect then again show same form with blank field because their no things to manage session,
            //    .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


                return http.build();

    }

    //you can create user and password using this below method also , but this is also hard code we have anather way to create use

//    @Bean
//    public UserDetailsService userDetailsService()
//    {
//        UserDetails user1= User
//                .withDefaultPasswordEncoder()
//                .username("om")
//                .password("123")
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user1);
//    }

    //New Methos which store data in postgresql

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);

        return provider;
    }


      @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
      {
          return config.getAuthenticationManager();
      }
}