package com.test.ssm.blog.controller.home;

import com.test.ssm.blog.entity.Article;
import com.test.ssm.blog.entity.Category;
import com.test.ssm.blog.entity.Page;
import com.test.ssm.blog.entity.Tag;
import com.test.ssm.blog.service.ArticleService;
import com.test.ssm.blog.service.CategoryService;
import com.test.ssm.blog.service.PageService;
import com.test.ssm.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomePageController {

    @Autowired
    private PageService pageService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;

    //页面详情页
    @RequestMapping(value = "/{key}")
    public String pageDetail(@PathVariable("key")String key, Model model){
        Page page=pageService.getPageByKey(1,key);
        if(page==null){
            return "redirect:/404";
        }
        model.addAttribute("page",page);
        //侧边栏
        //热评文章
        List<Article> mostCommentArticleList=articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList",mostCommentArticleList);
        return "Home/Page/page";
    }

    //文章归档页面显示
    @RequestMapping(value = "/articleFile")
    public String articleFile(Model model){
        List<Article> articleList=articleService.listAllNotWithContent();
        model.addAttribute("articleList",articleList);

        //热评文章
        List<Article> mostCommentArticleList=articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList",mostCommentArticleList);
        return "Home/Page/articleFile";
    }

    //站点地图显示
    @RequestMapping(value = "/map")
    public String siteMap(Model model)
    {
        //文章
        List<Article> articleList=articleService.listAllNotWithContent();
        model.addAttribute("articleList",articleList);
        //分类
        List<Category> categoryList=categoryService.listCategory();
        model.addAttribute("categoryList",categoryList);
        //标签
        List<Tag> tagList=tagService.listTag();
        model.addAttribute("tagList",tagList);

        //侧边栏
        //热评
        List<Article> mostCommentArticleList=articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList",mostCommentArticleList);
        return "Home/Page/siteMap";
    }

    //留言板
    @RequestMapping(value = "/message")
    public String message(Model model)
    {
        //侧边栏
        List<Article> mostCommentArticleList=articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList",mostCommentArticleList);
        return "Home/Page/message";
    }
}
