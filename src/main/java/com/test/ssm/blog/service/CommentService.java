package com.test.ssm.blog.service;

import com.github.pagehelper.PageInfo;
import com.test.ssm.blog.entity.Article;
import com.test.ssm.blog.entity.Comment;

import java.util.List;

public interface CommentService {

    //添加
    void insertComment(Comment comment);

    //根据文章ID获取评论列表
    List<Comment> listCommentByArticleId(Integer articleId);

    //根据ID获取
    Comment getCommentById(Integer id);


    /**
     * 获得所有评论
     * pageIndex 第几页开始
     * pageSize 一页显示的数量
     */
    PageInfo<Comment> listCommentByPage(Integer pageIndex,Integer pageSize);

    //获得评论列表
    List<Comment> listComment();

    //删除评论
    void deleteComment(Integer id);

    //更新
    void updateComment(Comment comment);

    //统计评论数
    Integer countComment();

    //获得最新评论
    List<Comment> listRecentComment(Integer limit);

    //获得评论的子评论
    List<Comment> listChildComment(Integer id);

    PageInfo<Comment> listCommentByArticleIds(Integer pageIndex,Integer pageSize,List<Integer> articleIds);

}
