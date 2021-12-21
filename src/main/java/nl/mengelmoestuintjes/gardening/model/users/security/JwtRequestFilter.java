package nl.mengelmoestuintjes.gardening.model.users.security;

import lombok.NonNull;
import nl.mengelmoestuintjes.gardening.service.JwtUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtilService jwtUtil;
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain ) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader( "Authorization" );

        String username = null;
        String jwt = null;

        if ( authorizationHeader != null && authorizationHeader.startsWith("Bearer ") ) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null ) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername( username );
            if ( jwtUtil.validateToken( jwt, userDetails ) ) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken( userDetails, null, userDetails.getAuthorities() );
                authToken.setDetails( new WebAuthenticationDetailsSource().buildDetails( request ) );
                SecurityContextHolder.getContext().setAuthentication( authToken );
            }
        }
        filterChain.doFilter( request, response );
    }
}
