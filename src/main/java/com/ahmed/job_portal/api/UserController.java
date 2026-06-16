package com.ahmed.job_portal.api;

import com.ahmed.job_portal.api.dto.UserDto;
import com.ahmed.job_portal.api.dto.UserResponseDto;
import com.ahmed.job_portal.api.mapper.UserMapper;
import com.ahmed.job_portal.model.User;
import com.ahmed.job_portal.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService,  UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public UserResponseDto createUser(@RequestBody UserDto dto) {
        User user = userMapper.toUser(dto);
        return userMapper.toUserResponseDto(userService.createUser(user));
    }

    @GetMapping(path = "/{id}")
    public UserResponseDto getUserById(@PathVariable Long id) {
        return userMapper.toUserResponseDto(userService.getUserById(id));
    }

    @GetMapping
    public List<UserResponseDto> getUsers() {
        return userMapper.toUserResponseDto(userService.listUsers());
    }

    @PutMapping (path = "/{id}")
    public UserResponseDto updateUser(@PathVariable Long id, @RequestBody UserDto user) {
        return userMapper.toUserResponseDto(userService.updateUser(id, user));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "User with ID " + id + " has been successfully deleted.");
        response.put("status", "SUCCESS");

        return ResponseEntity.ok(response);
    }

    @PatchMapping(path = "/{id}/company")
    public UserResponseDto changeCompany(@PathVariable Long id,@RequestBody String companyName) {
        return userMapper.toUserResponseDto(userService.assignCompany(id, companyName));
    }

}
