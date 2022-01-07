package nl.mengelmoestuintjes.gardening.controller;

import nl.mengelmoestuintjes.gardening.dto.request.AuthenticationRequest;
import nl.mengelmoestuintjes.gardening.dto.response.AuthenticationResponse;
import nl.mengelmoestuintjes.gardening.service.UserAuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    UserAuthenticateService userAuthenticateService;

    @Autowired
    public AuthenticationController(UserAuthenticateService userAuthenticateService) {
        this.userAuthenticateService = userAuthenticateService;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse authenticationResponse = userAuthenticateService.authenticateUser(authenticationRequest);
        return ResponseEntity.ok(authenticationResponse);
    }
}
