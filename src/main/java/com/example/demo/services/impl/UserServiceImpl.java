package com.example.demo.services.impl;

import com.example.demo.entities.User;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.payloads.UserDto;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto)
    {
        User user=this.dtoToUser(userDto);
        User savedUser=this.userRepo.save(user);//saving the user
        return this.userToDto(savedUser);//this is for saving the user
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId)
    {
        User user=this.userRepo.findById(userId).orElseThrow( ()-> new ResourceNotFoundException("User","id",userId));
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User updateUser=this.userRepo.save(user);
        return this.userToDto(updateUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {

       User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
       return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users=this.userRepo.findAll();
        List<UserDto> userDtos= users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
        //This line of code transforms a List of User objects (users) into a List of UserDto objects (userDtos) by
        // applying the userToDto method to each User object using Java Streams.
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId)
    {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        this.userRepo.delete(user);
    }

    private User dtoToUser(UserDto userDto)
    {
        User user=this.modelMapper.map(userDto,User.class);
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setAbout(userDto.getAbout());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());

        return user;
    }

    public UserDto userToDto(User user)
    {
        UserDto userDto=this.modelMapper.map(user,UserDto.class);
//        userDto.setName(user.getName());//this is for getting the the name from the user and put it in user dto
//        userDto.setAbout(user.getAbout());//this is for getting the about from the user and put it in user dto
//        userDto.setEmail(user.getEmail());
//        userDto.setId(user.getId());
//        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
