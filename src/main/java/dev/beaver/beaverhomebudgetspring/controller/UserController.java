package dev.beaver.beaverhomebudgetspring.controller;

import dev.beaver.beaverhomebudgetspring.dto.UserDTO;
import dev.beaver.beaverhomebudgetspring.service.UserService;
import dev.beaver.beaverhomebudgetspring.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;


    @PostMapping
    public UserDTO createUser(UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @PutMapping("/{userId}")
    public UserDTO updateUser(UUID userId, UserDTO userDTO) {
        return userService.updateUser(userId, userDTO);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(UUID userId){
        userService.deleteUser(userId);
    }


    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getAllUsers();
    }
}
