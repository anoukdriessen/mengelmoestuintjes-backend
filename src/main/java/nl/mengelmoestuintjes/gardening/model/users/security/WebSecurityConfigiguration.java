package nl.mengelmoestuintjes.gardening.model.users.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity( prePostEnabled = true )
public class WebSecurityConfigiguration extends
        WebSecurityConfigurerAdapter {
    final DataSource dataSource;
    private final JwtRequestFilter jwtRequestFilter = new JwtRequestFilter();

    public WebSecurityConfigiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        "SELECT username, password, enabled" +
                                " FROM users" +
                                " WHERE username=?"
                )
                .authoritiesByUsernameQuery(
                        "SELECT username, authority" +
                                " FROM authorities" +
                                " WHERE username=?");
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .httpBasic()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/users/**").hasRole("ADMIN")
//                .antMatchers("/customers/**").hasAnyRole("USER", "ADMIN")
//                .antMatchers("/info/**").hasAnyRole("USER", "ADMIN")
//                                                .antMatchers(HttpMethod.GET, "/**").authenticated()
//                .and()
//                .cors()
//                .and()
//                .csrf().disable()
//                .formLogin().disable()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }
//        http
//                .httpBasic()
//                .and()
//                .authorizeRequests()
//                    .antMatchers("/", "/home").permitAll()
//                    .anyRequest().authenticated();
    }
}
