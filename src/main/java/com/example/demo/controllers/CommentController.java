package com.example.demo.controllers;
import com.example.demo.entities.Comment;
import com.example.demo.payloads.ApiResponse;
import com.example.demo.payloads.CommentDto;
import com.example.demo.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController
{

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment, @PathVariable Integer postId)
    {
        CommentDto newComment=this.commentService.createComment(comment,postId);
        return new ResponseEntity<CommentDto>(newComment, HttpStatus.CREATED);
    }
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId)
    {
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Successfully",true),HttpStatus.OK);
    }



}
