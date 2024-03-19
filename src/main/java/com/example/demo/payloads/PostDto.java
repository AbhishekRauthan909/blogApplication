package com.example.demo.payloads;
import com.example.demo.entities.Category;
import com.example.demo.entities.Comment;
import com.example.demo.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostDto
{
    private Integer id;
    private String  title;
    private String content;
    private String imageName;
    private Date addedDate;
    private CategoryDto category;
    private UserDto user;
    private List<CommentDto> comment=new ArrayList<>();
    //comment ke paas to post h par commentDto ke paas post nahi h
}
