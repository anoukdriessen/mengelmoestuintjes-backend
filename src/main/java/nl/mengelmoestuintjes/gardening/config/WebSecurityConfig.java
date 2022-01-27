package nl.mengelmoestuintjes.gardening.config;

import nl.mengelmoestuintjes.gardening.security.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    WebSecurityConfig(DataSource dataSource, JwtRequestFilter jwtRequestFilter) {
        this.dataSource = dataSource;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?")
                .authoritiesByUsernameQuery("SELECT username, authority FROM authorities AS a WHERE username=?");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(GET, "/api/quotes/**").permitAll()
                .antMatchers(GET, "/api/berichten?published=TRUE&category=BLOG").permitAll()
                .antMatchers(GET, "/api/berichten?published=TRUE&category=POST").hasRole("USER")
                .antMatchers(GET,"/api/gebruikers/check/**").permitAll()
                .antMatchers(POST,"/authenticate").permitAll()
                .antMatchers(POST,"/api/gebruikers").permitAll()
                .antMatchers(GET, "/api/gebruikers/**").hasRole("USER")
                .antMatchers(POST,"/api/gebruikers/**").hasRole("USER")
                .antMatchers(PATCH,"/api/gebruikers/**").hasRole("USER")
                .antMatchers(PUT,"/api/gebruikers/**").hasRole("USER")

                .antMatchers("/api/quotes/**").hasAnyRole("MODERATOR", "ADMIN")
                .anyRequest().permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }

}
/**
 * //                .antMatchers("/").permitAll()
 * //                .antMatchers("/actuator/info").permitAll()
 * //                .antMatchers("/actuator/**").hasRole("DEVELOPER")
 * //                .antMatchers("/api/gebruikers/**").hasRole("ADMIN")
 * //                .antMatchers(POST, "/api/quotes").hasRole("MODERATOR")
 * //                .antMatchers(PATCH, "/api/quotes").hasRole("MODERATOR")
 * //                .antMatchers(DELETE, "/api/quotes").hasRole("MODERATOR")
 * //                .antMatchers(PATCH,"/gebruikers/{^[\\w]$}/password").authenticated()
 */
