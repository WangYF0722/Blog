package com.test.ssm.blog.mapper;

import com.test.ssm.blog.entity.Article;
import com.test.ssm.blog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    //根据ID删除
    int deleteById(Integer commentId);

    //添加
    int insert(Comment comment);

    //根据ID查询
    Comment getCommentById(Integer commentId);

    //更新
    int update(Comment comment);

    //根据文章ID获取评论列表
    List<Comment> listCommentByArticleId(@Param(value = "id")Integer id);

    //获得评论列表
    List<Comment> listComment();

    //根据文章列表ID获得评论列表
    List<Comment> listCommentByArticleIds(@Param(value = "articleIds")List<Integer> articleIds);

    //统计评论数
    Integer countComment();

    /**获得最新评论
     * @param limit 查询数量
     * @return
     */
    List<Comment> listRecentComment(@Param(value = "limit")Integer limit);

    //获得评论的子评论
    List<Comment> listChildComment(@Param(value="id")Integer id);
}
