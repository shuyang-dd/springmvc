package com.springboot.blog.payload;

import com.springboot.blog.entity.Comment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@ApiModel(description = "post model")
public class PostDto {
    @ApiModelProperty(value = "id")
    private long id;

    @NotEmpty
    @Size(min=2,message="Post title len")
    private String title;

    @NotEmpty
    @Size(min=10,message="description len")
    private String description;

    @NotEmpty
    private String content;

    private Set<CommentDto> comments;
}
