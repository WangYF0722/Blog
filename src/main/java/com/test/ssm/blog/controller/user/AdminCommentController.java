package com.test.ssm.blog.controller.user;

import com.github.pagehelper.PageInfo;
import com.test.ssm.blog.entity.Article;
import com.test.ssm.blog.entity.Comment;
import com.test.ssm.blog.service.ArticleService;
import com.test.ssm.blog.service.CommentService;
import com.test.ssm.blog.util.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/comment")
public class AdminCommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;

    //评论列表显示
    @RequestMapping(value = "")
    public String commentList(@RequestParam(required = false,defaultValue = "1")Integer pageIndex,
                              @RequestParam(required = false,defaultValue = "10")Integer pageSize,
                              Model model){
        PageInfo<Comment> commentPageInfo=commentService.listCommentByPage(pageIndex,pageSize);
        model.addAttribute("pageInfo",commentPageInfo);
        model.addAttribute("pageUrlPrefix","/admin/comment?pageIndex");
        return "Admin/Comment/index";
    }

    //添加评论
    @RequestMapping(value = "/insert",method = {RequestMethod.POST})
    @ResponseBody
    public void insertComment(HttpServletRequest request,Comment comment){
        comment.setCommentIp(MyUtils.getIpAddr(request));
        comment.setCommentCreateTime(new Date());
        //添加评论
        commentService.insertComment(comment);
        //更新文章评论数
        Article article=articleService.getArticleByStatusAndId(null,comment.getCommentArticleId());
        articleService.updateCommentCount(article.getArticleId());
    }

    //删除评论
    @RequestMapping(value = "/delete/{id}")
    public void deleteComment(@PathVariable("id")Integer id){
        //根据ID先获得评论
        Comment comment=commentService.getCommentById(id);
        //删除评论
        commentService.deleteComment(id);

        //删除评论下的子评论
        List<Comment> childCommentList=commentService.listChildComment(id);
        for(int i=0;i<childCommentList.size();i++)
        {
            commentService.deleteComment(childCommentList.get(i).getCommentId());
        }
        //更新文章评论数
        Article article=articleService.getArticleByStatusAndId(null,comment.getCommentArticleId());
        if(article!=null) {
            articleService.updateCommentCount(article.getArticleId());
        }

    }

    //编辑页面显示
    @RequestMapping(value = "/edit/{id}")
    public String editCommentView(@PathVariable("id")Integer id,Model model){
        Comment comment=commentService.getCommentById(id);
        model.addAttribute("comment",comment);
        return "Admin/Comment/edit";
    }

    //编辑评论提交
    @RequestMapping(value = "/editSubmit",method = {RequestMethod.POST})
    public String editCommentSubmit(Comment comment)
    {
        commentService.updateComment(comment);
        return "redirect:/admin/comment";
    }

    //回复评论页面显示
    @RequestMapping(value = "/reply/{id}")
    public String replyCommentView(@PathVariable("id")Integer id,Model model)
    {
        Comment comment=commentService.getCommentById(id);
        model.addAttribute("comment",comment);
        return "Admin/Comment/reply";
    }

    @RequestMapping(value = "/replySubmit",method = RequestMethod.POST)
    public String replyCommentSubmit(HttpServletRequest request,Comment comment){
        //文章评论数加一
        Article article=articleService.getArticleByStatusAndId(null,comment.getCommentArticleId());
        article.setArticleCommentCount(article.getArticleCommentCount()+1);
        articleService.updateArticle(article);

        //添加评论
        comment.setCommentCreateTime(new Date());
        comment.setCommentIp(MyUtils.getIpAddr(request));
        commentService.insertComment(comment);
        return "redirect:/admin/comment";
    }


}
