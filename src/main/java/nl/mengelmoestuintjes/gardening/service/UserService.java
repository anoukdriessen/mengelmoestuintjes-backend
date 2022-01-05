//package nl.mengelmoestuintjes.gardening.service;
//
//import nl.mengelmoestuintjes.gardening.dto.UserRequestDto;
//import nl.mengelmoestuintjes.gardening.exceptions.*;
//import nl.mengelmoestuintjes.gardening.model.users.Authority;
//import nl.mengelmoestuintjes.gardening.model.users.User;
//import nl.mengelmoestuintjes.gardening.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//import java.util.Set;
//
//@Service
//public class UserService {
//    private final UserRepository repository;
//    private final PasswordEncoder pwEncoder;
//
//    @Autowired
//    public UserService(UserRepository repository, PasswordEncoder pwEncoder) {
//        this.repository = repository;
//        this.pwEncoder = pwEncoder;
//    }
//
//    private String getCurrentUsername() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        return ((UserDetails) auth.getPrincipal()).getUsername();
//    }
//
//    public boolean isUser( String username ) {
//        return repository.existsById(username);
//    }
//
//    public String newUser(UserRequestDto toAdd) {
//        try {
//            String encryptedPassword = pwEncoder.encode( toAdd.getPassword() );
//
//            User u = new User();
//            u.setUsername( toAdd.getUsername() );
//            u.setPassword( encryptedPassword );
//            u.setEmail( toAdd.getEmail() );
//            u.addAuthority( "ROLE_USER" );
//            u.setEnabled( true );
//            u.setMemberSince( LocalDateTime.now() );
//
//            for ( String auth : toAdd.getAuthorities() ) {
//                if ( !auth.startsWith("ROLE_" ) ) {
//                    auth = "ROLE_" + auth;
//                }
//                auth = auth.toUpperCase();
//                if ( !auth.equals( "ROLE_USER") ) {
//                    u.addAuthority( auth );
//                }
//            }
//            User newUser = repository.save( u );
//            return "added " + newUser.getUsername();
//        } catch ( Exception e ) {
//            throw new BadRequestException( "Cannot create user" );
//        }
//    }
//
//    public Iterable<User> getAll() {
//        return repository.findAll();
//    }
//
//    public User getById( String username ) {
//        Optional<User> toFind = repository.findById( username );
//        boolean userFound = toFind.isPresent();
//        if ( userFound ) {
//            return toFind.get();
//        } else {
//            throw new UserNotFoundException(username);
//        }
//    }
//
//    public String update( String username, User modified ){
//        Optional<User> toFind = repository.findById( username );
//        boolean userNotFound = toFind.isEmpty();
//        if ( userNotFound ) {
//            throw new UserNotFoundException(username);
//        } else {
//            User u = toFind.get();
//            u.setPassword(pwEncoder.encode(modified.getPassword()));
//            u.setEnabled(modified.isEnabled());
//            u.setEmail(modified.getEmail());
//            u.setLvl(modified.getLvl());
//            u.setXp(modified.getXp());
//            u.setLevelUpLimit(modified.getLevelUpLimit());
//            u.setName(modified.getName());
//            u.setBirthday(modified.getBirthday());
//            u.setProvince(modified.getProvince());
//            u.setMilestones(modified.getMilestones());
//            u.setPosts(modified.getPosts());
//            u.setFavoritePosts(modified.getFavoritePosts());
//            u.setTasks(modified.getTasks());
//            u.setGardens(modified.getGardens());
//            u.setFavoritePlants(modified.getFavoritePlants());
//            u.setLastActivity();
//            repository.save(u);
//            return "updated user " + u.getUsername();
//        }
//    }
//
//    public String delete( String username ) {
//        if ( repository.existsById( username ) ) {
//            repository.deleteById( username );
//            return "Deleted user with username " + username;
//        } else {
//            throw new UserNotFoundException(username);
//        }
//    }
//
//    public Set<Authority> getAuthorities( String username ) {
//        Optional<User> toFind = repository.findById( username );
//        boolean userNotFound = toFind.isEmpty();
//        if ( userNotFound ) {
//            throw new UserNotFoundException(username);
//        } else {
//            User found = toFind.get();
//            return found.getAuthorities();
//        }
//    }
//
//    public String addAuthority( String username, String auth ) {
//        Optional<User> toFind = repository.findById( username );
//        boolean userNotFound = toFind.isEmpty();
//        if ( userNotFound ) {
//            throw new UserNotFoundException(username);
//        } else {
//            User found = toFind.get();
//            found.addAuthority( auth );
//            repository.save( found );
//            return "added authority " + auth + " for user " + found.getUsername();
//        }
//    }
//
//    public String removeAuthority( String username, String auth ) {
//        Optional<User> toFind = repository.findById( username );
//        boolean userNotFound = toFind.isEmpty();
//        if ( userNotFound ) {
//            throw new UserNotFoundException(username);
//        }
//        else {
//            User found = toFind.get();
//            found.removeAuthority( auth );
//            repository.save( found );
//            return "removed authority " + auth + " for user " + found.getUsername();
//        }
//    }
//
//    private boolean isValidPassword( String password ) {
//        final int MIN_LENGTH = 8;
//        final int MIN_DIGITS = 2;
//        final int MIN_LOWER = 2;
//        final int MIN_UPPER = 2;
//        final int MIN_SPECIAL = 1;
//        final String SPECIAL_CHARS = "@#$%&*!()+=-_";
//
//        long countDigit = password.chars().filter(ch -> ch >= '0' && ch <= '9').count();
//        long countLower = password.chars().filter(ch -> ch >= 'a' && ch <= 'z').count();
//        long countUpper = password.chars().filter(ch -> ch >= 'A' && ch <= 'Z').count();
//        long countSpecial = password.chars().filter(ch -> SPECIAL_CHARS.indexOf(ch) >= 0).count();
//
//        boolean validPassword = true;
//        if (password.length() < MIN_LENGTH) validPassword = false;
//        if (countLower < MIN_LOWER) validPassword = false;
//        if (countUpper < MIN_UPPER) validPassword = false;
//        if (countDigit < MIN_DIGITS) validPassword = false;
//        if (countSpecial < MIN_SPECIAL) validPassword = false;
//
//        return validPassword;
//    }
//
//    public void setPassword( String username, String password ) {
//        if ( username.equals( getCurrentUsername() )) {
//            if ( isValidPassword( password ) ) {
//                Optional<User> toFind = repository.findById( username );
//                boolean userIsFound = toFind.isPresent();
//                if ( userIsFound ) {
//                    User found = toFind.get();
//                    found.setPassword( pwEncoder.encode( password ));
//                    repository.save( found );
//                } else { throw new UserNotFoundException(username); }
//            } else { throw new InvalidPasswordException(); }
//        } else { throw new NotAuthorizedException(); }
//    }
//}
