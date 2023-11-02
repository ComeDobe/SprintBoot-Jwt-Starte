package com.dobe.sprintbootjwtstarte.Controller;


import com.dobe.sprintbootjwtstarte.dto.AuthenticationRequest;
import com.dobe.sprintbootjwtstarte.dto.AuthenticationResponse;
import com.dobe.sprintbootjwtstarte.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        return userService.authenticate(authenticationRequest);
    }

}
