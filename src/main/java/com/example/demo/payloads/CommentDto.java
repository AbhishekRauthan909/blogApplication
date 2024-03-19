package com.example.demo.payloads;

import com.example.demo.entities.Post;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.internal.bytebuddy.dynamic.loading.InjectionClassLoader;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto
{
    private int id;
    private String content;
}
