package com.example.demo.controllers;
import com.example.demo.config.AppConstants;
import com.example.demo.entities.Post;
import com.example.demo.payloads.ApiResponse;
import com.example.demo.payloads.PostDto;
import com.example.demo.payloads.PostResponse;
import com.example.demo.services.FileService;
import com.example.demo.services.PostService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
@RestController
@RequestMapping("/api/")
public class PostController
{

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;


    @Value("${project.images}")
    private String path;


    //create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                             @PathVariable Integer userId,
                                             @PathVariable Integer categoryId)
    {
        PostDto createPost=this.postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
    }

    //get by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId)
    {
        List<PostDto> post=this.postService.getPostsByUser(userId);
        return new ResponseEntity<List<PostDto>>(post,HttpStatus.OK);
    }

    //get by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId)
    {
        List<PostDto> post=this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(post,HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto>getPostsById(@PathVariable Integer postId)
    {
        PostDto post=this.postService.getPostById(postId);
        return new ResponseEntity<PostDto>(post,HttpStatus.OK);
    }

    @GetMapping("/post")
    public ResponseEntity<PostResponse>getPosts(@RequestParam(value="pageNumber",defaultValue= AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                @RequestParam(value="pageSize",defaultValue =AppConstants.PAGE_SIZE,required=false)
                                                 Integer pageSize,
                                                @RequestParam(value="sortBy",defaultValue =AppConstants.SORT_BY,required = false)String sortBy,
                                                @RequestParam(value="sortDir",defaultValue =AppConstants.SORT_DIR,required=false)String sortDir
                                                )
    {
        return ResponseEntity.ok(this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir));
    }

    //deleting the post
    @DeleteMapping("/post/{postId}")
    public ResponseEntity<ApiResponse>deletePost(@PathVariable Integer postId)
    {
        this.postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post Delete successfully",true),HttpStatus.OK);
    }

    //updating the post
    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId)
    {
        PostDto updatedPost=this.postService.updatePost(postDto,postId);
        return ResponseEntity.ok(updatedPost);
    }


    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords)
    {
        List<PostDto>posts=this.postService.searchPosts(keywords);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);//this we are returning the posts
    }


    //post image upload
    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(
            @RequestParam( "image" ) MultipartFile image,
            @PathVariable Integer postId
            ) throws IOException
    {
        PostDto postDto=this.postService.getPostById(postId);
        String fileName=this.fileService.uploadImage(path,image);
        System.out.println(fileName);
        postDto.setImageName(fileName);
        PostDto updatePost=this.postService.updatePost(postDto,postId);
        return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
    }

    //method to serve files
    @GetMapping(value="post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException
    {
        InputStream resource=this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }
}
