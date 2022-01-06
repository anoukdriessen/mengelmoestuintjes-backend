package nl.mengelmoestuintjes.gardening.config;

import nl.mengelmoestuintjes.gardening.security.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig
        extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource securitySource;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
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
    protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
        auth.jdbcAuthentication().dataSource( securitySource )
                .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?")
                .authoritiesByUsernameQuery("SELECT username, authority FROM authorities AS a WHERE username=?");
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/actuator/**").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .cors()
                .and()
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore( jwtRequestFilter, UsernamePasswordAuthenticationFilter.class );
    }

//    @Override
//    public void configure(HttpSecurity htppSecurty) throws Exception {
//
//        htppSecurty
//                .httpBasic()
//                .and()
//                .authorizeRequests()
//                        .antMatchers("/quotes/**", "/plants/**").permitAll()
//                    .antMatchers("/tuintjes/**", "/topic/**", "/tasks/**", "/posts/**").hasAnyRole(USER, MODERATOR, ADMIN)
//                    .antMatchers("/actuator/**", "/milestones/**").hasRole(ADMIN)
//                    .antMatchers(HttpMethod.POST).hasAnyRole(MODERATOR, ADMIN)
//                    .antMatchers(HttpMethod.PUT).hasAnyRole(MODERATOR, ADMIN)
//                .antMatchers("/api/v1/users/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET, "/api/v1/books/**").hasRole("USER")
//                .antMatchers("/api/v1/books/**").hasRole("ADMIN")
//                .antMatchers("/api/v1/copies/**").hasRole("ADMIN")
//                .antMatchers("/api/v1/members/**").hasRole("ADMIN")
//                .antMatchers("/api/v1/borrowedCopies/**").hasRole("USER")
//                .antMatchers(HttpMethod.POST, "/api/v1/authenticate").permitAll()
//                .antMatchers(HttpMethod.GET, "/api/v1").authenticated()
//                .anyRequest().denyAll()
//                .and()
//                .csrf().disable()
//                .formLogin().disable()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        htppSecurty.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//    }

}