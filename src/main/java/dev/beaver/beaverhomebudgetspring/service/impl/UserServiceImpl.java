package dev.beaver.beaverhomebudgetspring.service.impl;

import dev.beaver.beaverhomebudgetspring.dao.UserDAO;
import dev.beaver.beaverhomebudgetspring.dto.UserDTO;
import dev.beaver.beaverhomebudgetspring.exception.NotFoundException;
import dev.beaver.beaverhomebudgetspring.repository.UserRepository;
import dev.beaver.beaverhomebudgetspring.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserDTO createUser(UserDTO userDTO) {
        UserDAO dao = modelMapper.map(userDTO, UserDAO.class);
        return modelMapper.map(userRepository.save(dao), UserDTO.class);
    }

    public UserDTO updateUser(UUID userId, UserDTO userDTO) {
        UserDAO dao = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        dao.setName(userDTO.getName());
        return modelMapper.map(userRepository.save(dao), UserDTO.class);
    }

    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(dao -> modelMapper.map(dao, UserDTO.class)
                ).collect(Collectors.toList());
    }
}
