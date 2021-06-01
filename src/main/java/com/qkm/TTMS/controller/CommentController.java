package com.qkm.TTMS.controller;

import com.qkm.TTMS.entity.MovieComment;
import com.qkm.TTMS.mapper.MovieCommentMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    private final MovieCommentMapper movieCommentMapper;

    public CommentController(MovieCommentMapper movieCommentMapper) {
        this.movieCommentMapper = movieCommentMapper;
    }

    /**
     * 增加评论
     * @param movieComment  评论的信息
     * @return  是否增加成功
     */
    @PostMapping("/addComment")
    public int addComment(@RequestBody MovieComment movieComment){
         return movieCommentMapper.insert(movieComment);
    }
}
