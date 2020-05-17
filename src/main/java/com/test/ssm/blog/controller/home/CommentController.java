package com.test.ssm.blog.controller.home;

import cn.hutool.http.HtmlUtil;
import com.test.ssm.blog.dto.JsonResult;
import com.test.ssm.blog.entity.Article;
import com.test.ssm.blog.entity.Comment;
import com.test.ssm.blog.enums.ArticleStatus;
import com.test.ssm.blog.enums.Role;
import com.test.ssm.blog.service.ArticleService;
import com.test.ssm.blog.service.CommentService;
import com.test.ssm.blog.util.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 博客页面进行回复评论
 */

@Controller
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;

    //添加评论
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public JsonResult insertComment(HttpServletRequest servletRequest, Comment comment){
        //添加评论
        comment.setCommentCreateTime(new Date());
        comment.setCommentIp(MyUtils.getIpAddr(servletRequest));
        if(servletRequest.getSession().getAttribute("user")!=null){
            //如果是在登录的情况下，创建的comment是以博主身份创建的
            comment.setCommentRole(Role.ADMIN.getValue());
        }else{
            comment.setCommentRole(Role.VISITOR.getValue());
        }
        //根据Email创建头像
        comment.setCommentAuthorAvatar(MyUtils.getGravatar(comment.getCommentAuthorEmail()));

        //过滤字符:HtmlUtil
        comment.setCommentContent(HtmlUtil.escape(comment.getCommentContent()));
        comment.setCommentAuthorName(HtmlUtil.escape(comment.getCommentAuthorName()));
        comment.setCommentAuthorEmail(HtmlUtil.escape(comment.getCommentAuthorEmail()));
        comment.setCommentAuthorUrl(HtmlUtil.escape(comment.getCommentAuthorUrl()));

        try{
            commentService.insertComment(comment);
            Article article=articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(),comment.getCommentArticleId());
            articleService.updateCommentCount(article.getArticleId());
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult().fail();
        }
        return new JsonResult().ok();

    }
}

