//package nl.mengelmoestuintjes.gardening.controller;
//
//import nl.mengelmoestuintjes.gardening.dto.AuthenticationRequestDto;
//import nl.mengelmoestuintjes.gardening.dto.AuthenticationResponseDto;
//import nl.mengelmoestuintjes.gardening.service.UserAuthenticateService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class AuthenticationController {
//
//    UserAuthenticateService service;
//
//    @Autowired
//    public AuthenticationController( UserAuthenticateService service ) {
//        this.service = service;
//    }
//
//    @PostMapping(value = "/authenticate")
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDto authDto ) {
//        AuthenticationResponseDto authResponseDto = service.authenticateUser( authDto );
//        return ResponseEntity.ok( authResponseDto );
//    }
//}
