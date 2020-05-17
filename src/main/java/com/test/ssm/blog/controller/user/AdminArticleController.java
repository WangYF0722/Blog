package com.test.ssm.blog.controller.user;

import com.github.pagehelper.PageInfo;
import com.test.ssm.blog.entity.Article;
import com.test.ssm.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
@RequestMapping("/admin/article")
public class AdminArticleController {
    @Autowired
    private ArticleService articleService;

    //后台文章列表显示（这个是管理员的页面，只是显示文章列表和进行删除）
    @RequestMapping(value = "")
    public String index(@RequestParam(required = false,defaultValue = "1")Integer pageIndex,
                        @RequestParam(required = false,defaultValue = "10")Integer pageSize,
                        @RequestParam(required = false)String status, Model model){
        HashMap<String,Object> criteria=new HashMap<>(1);
        if(status==null)
        {
            model.addAttribute("pageUrlPrefix","/admin/article?pageIndex");
        }
        else
        {
            criteria.put("status",status);
            model.addAttribute("pageUrlPrefix", "/admin/article?status=" + status + "&pageIndex");
        }
        PageInfo<Article> articlePageInfo=articleService.pageArticle(pageIndex,pageSize,criteria);
        model.addAttribute("pageInfo",articlePageInfo);
        return "Admin/Article/index";
    }

    //删除文章
    @RequestMapping(value = "/delete/{id}")
    public void deleteArticle(@PathVariable("id")Integer id){
        articleService.deleteArticle(id);
    }
}
