package com.springboot.blog.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data

public class CommentDto {

    private long id;
    @NotEmpty(message="name empty")
    private String name;

    @Email(message="empty email")
    @NotEmpty
    private String email;

    @NotEmpty
    @Size(min=10,message="body size")
    private String body;
}
