package dev.beaver.beaverhomebudgetspring.service;

import dev.beaver.beaverhomebudgetspring.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {

     UserDTO createUser(UserDTO userDTO);

     UserDTO updateUser(UUID userId, UserDTO userDTO);

    void deleteUser(UUID userId);

    List<UserDTO> getAllUsers();


}


