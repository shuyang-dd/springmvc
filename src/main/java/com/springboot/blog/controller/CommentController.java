package com.springboot.blog.controller;

import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value="postId") long postId,
                                                    @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);


    }

    @PostMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentByPostId(@PathVariable(name="postId") Long postId){
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity getCommentById(
            @PathVariable(name="postId")long postId,@PathVariable(name="commentId")long commentId){
        CommentDto commentDto=commentService.getCommentById(postId,commentId);

        return new ResponseEntity<>(commentDto,HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comment/{commentId}")
    public ResponseEntity updateComment(@PathVariable(value="postId")long postId,
                                        @PathVariable(value="commentId") long commentId,
                                        @Valid @RequestBody CommentDto commentDto){
        CommentDto updatedComment=commentService.updateComment(postId,commentId,commentDto);

        return new ResponseEntity<>(updatedComment,HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comment/{commentId}")
    public ResponseEntity deleteComment(@PathVariable(value="postId")long postId,
                                        @PathVariable(value="commentId") long commentId){

        commentService.deleteComment(postId,commentId);

        return new ResponseEntity("deledted",HttpStatus.OK);
    }
}
