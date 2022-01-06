package nl.mengelmoestuintjes.gardening.service;

import nl.mengelmoestuintjes.gardening.dto.UserRequestDto;
import nl.mengelmoestuintjes.gardening.exceptions.BadRequestException;
import nl.mengelmoestuintjes.gardening.exceptions.InvalidPasswordException;
import nl.mengelmoestuintjes.gardening.exceptions.NotAuthorizedException;
import nl.mengelmoestuintjes.gardening.exceptions.UserNotFoundException;
import nl.mengelmoestuintjes.gardening.model.users.Authority;
import nl.mengelmoestuintjes.gardening.model.users.User;
import nl.mengelmoestuintjes.gardening.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    private String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ( ( UserDetails ) auth.getPrincipal() ).getUsername();
    }

    public boolean isUser( String username ) {
        return repository.existsById( username );
    }

    public String newUser( UserRequestDto toAdd ) {
        try {
            User u = new User();

            u.setUsername( toAdd.getUsername() );
            u.setPassword( encoder.encode( toAdd.getPassword() ) );
            u.setEnabled( true );

            u.addAuthority( "ROLE_USER" );
            for ( String role : toAdd.getAuthorities() ) {
                if ( !role.startsWith("ROLE_" ) ) {
                    role = "ROLE_" + role;
                }
                role = role.toUpperCase();
                if ( !role.equals( "ROLE_USER") ) {
                    u.addAuthority( role );
                }
            }

            User newUser = repository.save( u );
            return newUser.getUsername();
        } catch ( Exception e ) {
            throw new BadRequestException( "Cannot create user" );
        }
    }

    public Iterable<User> getAll() {
        return repository.findAll();
    }

    public User getByUsername( String username ) {
        Optional<User> toFind = repository.findById( username );
        boolean userFound = toFind.isPresent();
        if ( userFound ) {
            return toFind.get();
        } else {
            throw new UserNotFoundException(username);
        }
    }

    public String update( String username, User modified ){
        Optional<User> toFind = repository.findById( username );
        boolean userNotFound = toFind.isEmpty();
        if ( userNotFound ) {
            throw new UserNotFoundException(username);
        } else {
            User u = toFind.get();
            u.setPassword( encoder.encode( modified.getPassword() ));
            u.setEnabled( modified.isEnabled() );
            repository.save( u );
            return "updated user " + u.getUsername();
        }
    }

    public String delete( String username ) {
        if ( isUser( username ) ) {
            repository.deleteById( username );
            return "Deleted user with username " + username;
        } else {
            throw new UserNotFoundException( username );
        }
    }

    public List<Authority> getAuthorities( String username ) {
        Optional<User> toFind = repository.findById( username );
        boolean userNotFound = toFind.isEmpty();
        if ( userNotFound ) {
            throw new UserNotFoundException(username);
        } else {
            User found = toFind.get();
            return found.getAuthorities();
        }
    }

    public String addAuthority( String username, String role ) {
        Optional<User> toFind = repository.findById( username );
        boolean userNotFound = toFind.isEmpty();
        if ( userNotFound ) {
            throw new UserNotFoundException(username);
        } else {
            User found = toFind.get();
            found.addAuthority( role );
            repository.save( found );
            return "added authority " + role + " for user " + found.getUsername();
        }
    }

    public String removeAuthority( String username, String role ) {
        Optional<User> toFind = repository.findById( username );
        boolean userNotFound = toFind.isEmpty();
        if ( userNotFound ) {
            throw new UserNotFoundException(username);
        }
        else {
            User found = toFind.get();
            found.removeAuthority( role );
            repository.save( found );
            return "removed authority " + role + " for user " + found.getUsername();
        }
    }

    private boolean isValidPassword( String password ) {
        final int MIN_LENGTH = 8;
        final int MIN_DIGITS = 2;
        final int MIN_LOWER = 2;
        final int MIN_UPPER = 2;
        final int MIN_SPECIAL = 1;
        final String SPECIAL_CHARS = "@#$%&*!()+=-_";

        long countDigit = password.chars().filter(ch -> ch >= '0' && ch <= '9').count();
        long countLower = password.chars().filter(ch -> ch >= 'a' && ch <= 'z').count();
        long countUpper = password.chars().filter(ch -> ch >= 'A' && ch <= 'Z').count();
        long countSpecial = password.chars().filter(ch -> SPECIAL_CHARS.indexOf(ch) >= 0).count();

        boolean validPassword = true;
        if (password.length() < MIN_LENGTH) validPassword = false;
        if (countLower < MIN_LOWER) validPassword = false;
        if (countUpper < MIN_UPPER) validPassword = false;
        if (countDigit < MIN_DIGITS) validPassword = false;
        if (countSpecial < MIN_SPECIAL) validPassword = false;

        return validPassword;
    }

    public String setPassword( String username, String password ) {
        if ( username.equals( getCurrentUsername() )) {
            if ( isValidPassword( password ) ) {
                Optional<User> toFind = repository.findById( username );
                boolean userIsFound = toFind.isPresent();
                if ( userIsFound ) {
                    User found = toFind.get();
                    found.setPassword( encoder.encode( password ));
                    repository.save( found );
                    return "new password set for user with username " + username;
                } else { throw new UserNotFoundException( username ); }
            } else { throw new InvalidPasswordException( username ); }
        } else { throw new NotAuthorizedException( username ); }
    }
}
