package com.minka.tunel.controller;

import com.minka.tunel.domain.model.Tag;
import com.minka.tunel.domain.model.User;
import com.minka.tunel.domain.service.UserService;
import com.minka.tunel.resource.SaveTagResource;
import com.minka.tunel.resource.SaveUserResource;
import com.minka.tunel.resource.TagResource;
import com.minka.tunel.resource.UserResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UsersController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Page<UserResource> getAllUsers(Pageable pageable) {
        List<UserResource> users = userService.getAllUsers(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int usersCount = users.size();
        return new PageImpl<>(users, pageable, usersCount);
    }

    @GetMapping("/users/{userId}")
    public UserResource getUserById(
            @PathVariable Long userId) {
        return convertToResource(userService.getUserById(userId));
    }

    @PostMapping("/users")
    public UserResource createUser(
            @Valid @RequestBody SaveUserResource resource){
        return convertToResource(userService.createUser(convertToEntity(resource)));
    }

    @PutMapping("/users/{userId}")
    public UserResource updateUser(
            @PathVariable Long userId,
            @Valid @RequestBody SaveUserResource resource) {
        return convertToResource(userService.updateUser(userId, convertToEntity(resource)));
    }

    private User convertToEntity(SaveUserResource resource) {
        return mapper.map(resource, User.class);
    }

    private UserResource convertToResource(User entity) {
        return mapper.map(entity, UserResource.class);
    }
}
