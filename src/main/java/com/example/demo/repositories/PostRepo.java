package com.example.demo.repositories;
import com.example.demo.entities.Category;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer>
{
    List<Post> findByUser(User user);
    //for getting all the post of the user
    List<Post> findByCategory(Category category);
    //for getting all posts by the category

    //for searching the post by the title
    List<Post> findByTitleContaining(String title);
}
