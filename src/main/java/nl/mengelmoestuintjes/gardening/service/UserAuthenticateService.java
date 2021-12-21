package nl.mengelmoestuintjes.gardening.service;

import nl.mengelmoestuintjes.gardening.dto.AuthenticationRequestDto;
import nl.mengelmoestuintjes.gardening.dto.AuthenticationResponseDto;
import nl.mengelmoestuintjes.gardening.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticateService {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserDetailsService detailsService;

    @Autowired
    JwtUtil jwtUtil;

    public AuthenticationResponseDto authenticateUser( AuthenticationRequestDto authDto ) {
        String username = authDto.getUsername();
        String password = authDto.getPassword();

        try {
            manager.authenticate( new UsernamePasswordAuthenticationToken( username, password ) );
        } catch ( BadCredentialsException e) {
            throw new UsernameNotFoundException( "username or password is incorrect" );
        }

        final UserDetails details = detailsService.loadUserByUsername( username );
        final String jwt = jwtUtil.generateToken( details );

        return new AuthenticationResponseDto( jwt );
    }
}
