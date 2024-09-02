package com.example.lab12security.Config;

import com.example.lab12security.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final MyUserDetailsService myuserDetailsService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myuserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("api/v1/user/register-user").permitAll()
                .requestMatchers("api/v1/user/get-all-users").hasAuthority("ADMIN")
                .requestMatchers("api/v1/user/update-user","api/v1/blog/add-blog",
                        "api/v1/blog/update-blog").hasAuthority("USER")
                .requestMatchers("api/v1/user/delete-user/",
                        "api/v1/blog/get-all-user-blogs",
                        "api/v1/blog/delete-blog/",
                        "api/v1/blog/get-blog-by-id/",
                        "api/v1/blog/get-blog-by-title/").hasAnyAuthority("ADMIN", "USER")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("api/v1/user/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
return http.build();
    }
}
