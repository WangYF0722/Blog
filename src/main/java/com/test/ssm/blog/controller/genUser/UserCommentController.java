package com.test.ssm.blog.controller.genUser;

import com.github.pagehelper.PageInfo;
import com.test.ssm.blog.entity.Article;
import com.test.ssm.blog.entity.Comment;
import com.test.ssm.blog.entity.User;
import com.test.ssm.blog.enums.ArticleStatus;
import com.test.ssm.blog.service.ArticleService;
import com.test.ssm.blog.service.CommentService;
import com.test.ssm.blog.util.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.test.annotation.Commit;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/genUser/comment")
public class UserCommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "")
    public String commentListView(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                  @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                  Model model, HttpSession session){
        User user=(User)session.getAttribute("user");
        List<Integer> articleIds=articleService.listArticleIdByUserId(user.getUserId());
        PageInfo<Comment> commentPageInfo = commentService.listCommentByArticleIds(pageIndex,pageSize,articleIds);
        model.addAttribute("pageInfo", commentPageInfo);
        model.addAttribute("pageUrlPrefix","/genUser/comment?pageIndex");
        return "User/Comment/index";
    }


    //添加评论
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public void insertComment(HttpServletRequest request, Comment comment)
    {
        comment.setCommentIp(MyUtils.getIpAddr(request));
        comment.setCommentCreateTime(new Date());
        commentService.insertComment(comment);
        Article article=articleService.getArticleByStatusAndId(null,comment.getCommentArticleId());
        articleService.updateCommentCount(article.getArticleId());
    }

    //删除评论
    @RequestMapping(value = "/delete/{id}")
    public void deleteComment(@PathVariable("id")Integer id)
    {
        Comment comment=commentService.getCommentById(id);
        commentService.deleteComment(id);
        List<Comment> childComment=commentService.listChildComment(id);
        for(int i=0;i<childComment.size();i++)
        {
            commentService.deleteComment(childComment.get(i).getCommentId());
        }
        Article article = articleService.getArticleByStatusAndId(null, comment.getCommentArticleId());
        articleService.updateCommentCount(article.getArticleId());
    }

    @RequestMapping("/edit/{id}")
    public String editCommentView(@PathVariable("id")Integer id,Model model){
        Comment comment=commentService.getCommentById(id);
       model.addAttribute("comment",comment);
       return "User/Comment/edit";
    }

    /**
     * 编辑评论提交
     *
     * @param comment
     * @return
     */
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editCommentSubmit(Comment comment) {
        commentService.updateComment(comment);
        return "redirect:/genUser/comment";
    }


    /**
     * 回复评论页面显示
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/reply/{id}")
    public String replyCommentView(@PathVariable("id") Integer id, Model model) {
        Comment comment = commentService.getCommentById(id);
        model.addAttribute("comment", comment);
        return "User/Comment/reply";
    }

    /**
     * 回复评论提交
     *
     * @param request
     * @param comment
     * @return
     */
    @RequestMapping(value = "/replySubmit", method = RequestMethod.POST)
    public String replyCommentSubmit(HttpServletRequest request, Comment comment) {
        //文章评论数+1
        Article article = articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(), comment.getCommentArticleId());
        article.setArticleCommentCount(article.getArticleCommentCount() + 1);
        articleService.updateArticle(article);
        //添加评论
        comment.setCommentCreateTime(new Date());
        comment.setCommentIp(MyUtils.getIpAddr(request));
        commentService.insertComment(comment);
        return "redirect:/genUser/comment";
    }



}
