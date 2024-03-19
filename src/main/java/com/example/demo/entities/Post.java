package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.internal.bytebuddy.dynamic.loading.InjectionClassLoader;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Post")
@Getter
@Setter
@NoArgsConstructor
public class Post
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer postId;

    private String  title;

    private String content;

    private String imageName;

    private Date addedDate;

    @ManyToOne
    @JoinColumn(name="cId")
    private Category category;

    @ManyToOne
    @JoinColumn(name="uId")
    private User user;


    //ek post par many comments honge
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<Comment> comment=new ArrayList<>();
}
