package com.minka.tunel.controller;

import com.minka.tunel.domain.model.Tag;
import com.minka.tunel.domain.model.User;
import com.minka.tunel.domain.service.UserService;
import com.minka.tunel.resource.SaveTagResource;
import com.minka.tunel.resource.SaveUserResource;
import com.minka.tunel.resource.TagResource;
import com.minka.tunel.resource.UserResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UsersController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserService userService;

    @Operation(summary = "Get Users", description = "Get All Users", tags = {"users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Users returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/users")
    public Page<UserResource> getAllUsers(Pageable pageable) {
        List<UserResource> users = userService.getAllUsers(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int usersCount = users.size();
        return new PageImpl<>(users, pageable, usersCount);
    }

    @Operation(summary = "Get User by ID", description = "Get a specific User by its ID", tags = {"users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/users/{userId}")
    public UserResource getUserById(
            @PathVariable Long userId) {
        return convertToResource(userService.getUserById(userId));
    }

    @Operation(summary = "Create an User", description = "Create an User", tags = {"users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/user-register")
    public UserResource createUser(
            @Valid @RequestBody SaveUserResource resource){
        return convertToResource(userService.createUser(convertToEntity(resource)));
    }

    @Operation(summary = "Update an User", description = "Update an User", tags = {"users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/users/{userId}")
    public UserResource updateUser(
            @PathVariable Long userId,
            @Valid @RequestBody SaveUserResource resource) {
        return convertToResource(userService.updateUser(userId, convertToEntity(resource)));
    }

    @Operation(summary = "Delete an User", description = "Delete an User", tags = {"users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }

    private User convertToEntity(SaveUserResource resource) {
        return mapper.map(resource, User.class);
    }

    private UserResource convertToResource(User entity) {
        return mapper.map(entity, UserResource.class);
    }
}
