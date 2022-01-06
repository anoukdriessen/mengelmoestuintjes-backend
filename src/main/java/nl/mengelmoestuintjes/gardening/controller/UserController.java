package nl.mengelmoestuintjes.gardening.controller;

import nl.mengelmoestuintjes.gardening.dto.UserRequestDto;
import nl.mengelmoestuintjes.gardening.dto.UserResponseDto;
import nl.mengelmoestuintjes.gardening.exceptions.BadRequestException;
import nl.mengelmoestuintjes.gardening.model.users.Authority;
import nl.mengelmoestuintjes.gardening.model.users.User;
import nl.mengelmoestuintjes.gardening.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/gebruikers")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping
    public UserResponseDto newUser(@RequestBody UserRequestDto toAdd ) {
        String newUsername = service.newUser( toAdd );;
        return UserResponseDto.fromUser(service.getByUsername( newUsername ) );
    }

    @PostMapping(value = "/{username}/authorities")
    public String addUserAuthority( @PathVariable("username") String username,
                                             @RequestBody Map<String, Object> fields ) {
        try {
            String auth = (String) fields.get("authority");
            return service.addAuthority( username, auth );
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        List<UserResponseDto> all = new ArrayList<>();
        Iterable<User> users = service.getAll();

        for ( User u : users) {
            all.add(
                    UserResponseDto.fromUser( u )
            );
        }

        return all;
    }

    @GetMapping(value = "/{username}")
    public UserResponseDto getById(@PathVariable( "username" ) String username) {
        User toFind = service.getByUsername( username );
        return UserResponseDto.fromUser( toFind );
    }

    @GetMapping(value = "/{username}/authorities")
    public List<Authority> getUserAuthorities(@PathVariable( "username" ) String username ) {
        return service.getAuthorities( username );
    }

    @PutMapping(value = "/{username}")
    public UserResponseDto update( @PathVariable( "username" ) String username, @RequestBody User modified ) {
        service.update( username, modified );
        return UserResponseDto.fromUser( modified );
    }

    @PatchMapping(value = "/{username}/password")
    public String setPassword( @PathVariable("username") String username,
                               @RequestBody String password ) {
        service.setPassword( username, password );
        return "set new password for user " + username;
    }

    @DeleteMapping(value = "/{username}")
    public UserResponseDto delete( @PathVariable( "username" ) String username ) {
        return UserResponseDto.fromUser( service.getByUsername( username ) );
    }

    @DeleteMapping(value = "/{username}/authorities/{authority}")
    public String deleteUserAuthority( @PathVariable("username") String username,
                                       @PathVariable("authority") String auth ) {
        return service.removeAuthority( username, auth );
    }


}
