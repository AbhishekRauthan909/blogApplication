package com.example.demo.payloads;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Empty;

@NoArgsConstructor
@Getter
@Setter
public class UserDto
{
    private int id;

    @NotEmpty
    @Size(min=4,message="username must be min of characters")
    private String name;

    @Email(message="email address is not valid!!")
    private String email;

    @NotEmpty
    @Size(min=3,max=4,message="Password must be of 3 characters and maximum of 4 characters")
    private String password;

    @NotNull
    private String about;
}
