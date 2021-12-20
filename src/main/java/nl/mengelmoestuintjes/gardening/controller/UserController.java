package nl.mengelmoestuintjes.gardening.controller;

import nl.mengelmoestuintjes.gardening.controller.dto.UserRequestDto;
import nl.mengelmoestuintjes.gardening.controller.dto.UserResponseDto;
import nl.mengelmoestuintjes.gardening.model.users.User;
import nl.mengelmoestuintjes.gardening.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin
public class UserController {
    private final UserService service;
    @Autowired
    public UserController( UserService service ) {
        this.service = service;
    }

    @PostMapping
    public UserResponseDto newUser( @RequestBody UserRequestDto toAdd ) {
        User user = service.newUser( toAdd.toUser() );
        return UserResponseDto.fromUser( user );
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

    @GetMapping(value = "/{id}")
    public UserResponseDto getById(@PathVariable( "id" ) String username) {
        User user = service.getById( username );
        return UserResponseDto.fromUser( user );
    }

    @PutMapping(value = "/{id}")
    public UserResponseDto update( @PathVariable( "id" ) String username, @RequestBody User modified ) {
        service.update( username, modified );
        return UserResponseDto.fromUser( modified );
    }

    @DeleteMapping(value = "/{id}")
    public void delete( @PathVariable( "id" ) String id ) {
        service.delete( id );
    }



}
