package com.example.demo.controllers;

import com.example.demo.payloads.ApiResponse;
import com.example.demo.payloads.UserDto;
import com.example.demo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController
{

   @Autowired
   private UserService userService;

    //POST CREATE USER
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
    {
        UserDto createUserDto=this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    //POST DELETE USER
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser( @PathVariable("userId") Integer uId)
    {
        this.userService.deleteUser(uId);
        return  new ResponseEntity<>(new ApiResponse("user Deleted successfully",true),HttpStatus.OK);
    }

    //PUT UPDATE USER
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer uId)
    {
        UserDto updatedUser=this.userService.updateUser(userDto,uId);
        return ResponseEntity.ok(updatedUser);
    }

    //GET -> GET ALL THE USER
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers()
    {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    //GET-GET THE USER
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userId") Integer uId)
    {
        return ResponseEntity.ok(this.userService.getUserById(uId));
    }
}
