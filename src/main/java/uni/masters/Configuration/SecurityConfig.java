package uni.masters.Configuration;


import jakarta.servlet.Filter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import uni.masters.Security.UserDetailsServiceImpl;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    final String[] OPEN_ENDPOINTS = {
            "/",
            "/route",
            "/search",
            "/roles",
            "/role/**",
            "/register",
            "/login",
            "/route/**",
            "/location/**",
            "/user",
            "/css/**",
            "/images/**",
            "/fonts/**",
            "/js/**",
            "/sass/**",};



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(OPEN_ENDPOINTS).permitAll()
                        .anyRequest().authenticated()
                )
//                .formLogin((form) -> form
//                        .loginPage("/login")
//                        .permitAll()
//                        .defaultSuccessUrl("/",true)
//                )
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/",true)
                .and()
                .logout((logout) -> logout.permitAll().logoutSuccessUrl("/"));

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
        return authenticationManagerBuilder.build();
    }


}


