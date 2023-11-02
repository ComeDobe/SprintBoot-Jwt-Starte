package com.dobe.sprintbootjwtstarte.service.impl;

import com.dobe.sprintbootjwtstarte.config.JwtUtils;
import com.dobe.sprintbootjwtstarte.dto.AuthenticationRequest;
import com.dobe.sprintbootjwtstarte.dto.AuthenticationResponse;
import com.dobe.sprintbootjwtstarte.entity.User;
import com.dobe.sprintbootjwtstarte.repository.UserRepository;
import com.dobe.sprintbootjwtstarte.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;


    @Override
    public ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request) {
        final User user;

        try{
            user= (User) userRepository.findByEmail(request.getEmail()).get();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        } catch (AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("fullName", user.getPrenom() + " " + user.getNom());
        final String token = jwtUtils.generateToken(user,claims);

        return ResponseEntity.ok( AuthenticationResponse.builder().token(token).build());
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(Integer userId) {
        userRepository.deleteById(userId);
    }
}
