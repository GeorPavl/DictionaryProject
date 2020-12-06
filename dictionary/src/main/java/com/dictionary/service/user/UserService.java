package com.dictionary.service.user;

import com.dictionary.dto.UserDTO;
import com.dictionary.model.User;

import java.util.List;

public interface UserService {

    User dtoToEntity(UserDTO userDTO);

    List<UserDTO> findAll();

    UserDTO findUser(Long userId);

    UserDTO createUser(UserDTO newUserDTO);

    UserDTO updateUser(UserDTO newUserDTO);

    void deleteUser(Long userId);

    UserDTO writeUserDTO(User user);
}
