package com.dobe.sprintbootjwtstarte.Controller;

import com.dobe.sprintbootjwtstarte.entity.User;
import com.dobe.sprintbootjwtstarte.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping("/{user-id}")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<Void> deleteById(@PathVariable("user-id") Integer userId){
        userService.deleteById(userId);
        return ResponseEntity.ok().build();
    }

}
