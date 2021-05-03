package com.qkm.TTMS.controller;

import com.qkm.TTMS.entity.MovieComment;
import com.qkm.TTMS.mapper.MovieCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public class CommentCon {

    private final MovieCommentMapper movieCommentMapper;

    public CommentCon(MovieCommentMapper movieCommentMapper) {
        this.movieCommentMapper = movieCommentMapper;
    }

    /**
     * 增加评论
     * @param movieComment
     * @return
     */
    public int addComment(@RequestBody MovieComment movieComment){
         return movieCommentMapper.insert(movieComment);
    }
}
