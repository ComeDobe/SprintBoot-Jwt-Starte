package com.dobe.sprintbootjwtstarte.service;



import com.dobe.sprintbootjwtstarte.dto.AuthenticationRequest;
import com.dobe.sprintbootjwtstarte.dto.AuthenticationResponse;
import com.dobe.sprintbootjwtstarte.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request);
    List<User> findAll();

    void deleteById(Integer userId);

}
