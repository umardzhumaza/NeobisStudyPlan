package RestApp.src.main.java.ru.umar.onlinestore.restapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import RestApp.src.main.java.ru.umar.onlinestore.restapp.filter.JWTFilter;
import RestApp.src.main.java.ru.umar.onlinestore.restapp.services.UserDetailsService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final JWTFilter jwtFilter;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, JWTFilter jwtFilter) {this.userDetailsService = userDetailsService;
        this.jwtFilter = jwtFilter;
    }

    //Конфигурируем сам спринг секьюрити
    //Конфигурируем авторизацию

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/auth/login", "/auth/registration", "/error", "/hello").permitAll()
                .antMatchers(HttpMethod.GET, "/api/buyer/**", "/api/buyer/", "/api/order", "/api/order/**", "/api/t_shirt", "/api/t_shirt/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/t_shirt", "/api/order", "/api/buyer").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/t_shirt/update/**", "/api/buyer/update/**", "/api/order/update/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/t_shirt/delete/**", "/api/buyer/delete/**", "/api/order/delete/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/hello", true)
                .failureUrl("/auth/login?error")
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessUrl("/auth/login").and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
