package com.dictionary.controller;

import com.dictionary.dto.UserDTO;
import com.dictionary.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    /** INJECT SERVICES **/

    @Autowired
    private UserService userService;

    /** GET MAPPINGS **/

    @GetMapping("/find-all")
    public List<UserDTO> findAll(){
        return userService.findAll();
    }

    @GetMapping("/find-user")
    public UserDTO findUser(@RequestParam("userId") Long userId){
        return userService.findUser(userId);
    }

    /** POST MAPPINGS **/

    @PostMapping("/create-user")
    public UserDTO createUser(@RequestBody UserDTO newUserDTO){
        return userService.createUser(newUserDTO);
    }

    /** PUT MAPPINGS **/

    @PutMapping("/update-user")
    public UserDTO updateUser(@RequestBody UserDTO newUserDTO){
        return userService.updateUser(newUserDTO);
    }

    /** DELETE MAPPINGS **/

    @DeleteMapping("/delete-user")
    public void deleteUser(@RequestParam("userId") Long userId){
        userService.deleteUser(userId);
    }

}
