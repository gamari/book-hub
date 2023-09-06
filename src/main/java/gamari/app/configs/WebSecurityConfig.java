package gamari.app.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http.authorizeHttpRequests((requests) -> requests
                                .requestMatchers("/", "/home", "/signup")
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                                .formLogin((form) -> form
                                                .loginPage("/login")
                                                .permitAll())
                                .logout((logout) -> logout.permitAll());

                http.csrf(csrf -> csrf.disable());
                return http.build();
        }

        @Bean
        public WebSecurityCustomizer webSecurityCustomizer() {
                return web -> web.ignoring().requestMatchers("/css/**", "/js/**", "/images/**");
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

}
