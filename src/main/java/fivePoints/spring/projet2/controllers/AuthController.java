package fivePoints.spring.projet2.controllers;

import fivePoints.spring.projet2.payload.requests.LoginRequest;
import fivePoints.spring.projet2.payload.requests.SingupRequest;
import fivePoints.spring.projet2.payload.responses.MessageResponse;
import fivePoints.spring.projet2.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins="*")
@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest){
        String message = authService.login(loginRequest);
        return ResponseEntity.ok().body(new MessageResponse(message));
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@Valid @RequestBody SingupRequest signupRequest){
        return authService.register(signupRequest);
    }
}
