package com.example.demo.services;

import com.example.demo.entities.Post;
import com.example.demo.payloads.PostDto;
import com.example.demo.payloads.PostResponse;

import java.util.List;

public interface PostService
{
    //create
    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    //update
    PostDto updatePost(PostDto postDto,Integer postId);

    //delete
    void deletePost(Integer postId);

    //get all posts
    PostResponse getAllPost(Integer pageNumber, Integer PageSize,String sortBy,String sortDir);

    //get single post
    PostDto getPostById(Integer postId);

    //get all Post By Category
    List<PostDto> getPostsByCategory(Integer categoryId);

    //get all Post By User
    List<PostDto> getPostsByUser(Integer userId);

    //search posts
    List<PostDto> searchPosts(String keyword);

}
