package com.test.ssm.blog.controller.genUser;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.HtmlUtil;
import com.github.pagehelper.PageInfo;
import com.test.ssm.blog.dto.ArticleParam;
import com.test.ssm.blog.entity.Article;
import com.test.ssm.blog.entity.Category;
import com.test.ssm.blog.entity.Tag;
import com.test.ssm.blog.entity.User;
import com.test.ssm.blog.service.ArticleService;
import com.test.ssm.blog.service.CategoryService;
import com.test.ssm.blog.service.TagService;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 现在少写文章和更改文章
 */

@Controller
@RequestMapping("/genUser/article")
public class UserArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    //后台文章列表
    @RequestMapping(value = "")
    public String index(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                        @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                        @RequestParam(required = false) String status , Model model,HttpSession session ) {

        User user=(User)session.getAttribute("user");
        System.out.println("user");
        HashMap<String,Object> criteria=new HashMap<>(2);
        if(status==null)
        {
            criteria.put("userId",user.getUserId());
            model.addAttribute("pageUrlPrefix", "/admin/article?pageIndex");
        }
        else {
            criteria.put("userId",user.getUserId());
            criteria.put("status", status);
            model.addAttribute("pageUrlPrefix", "/admin/article?status=" + status + "&pageIndex");
        }
        PageInfo<Article> articlePageInfo = articleService.pageArticle(pageIndex, pageSize, criteria);
        model.addAttribute("pageInfo", articlePageInfo);
        return "User/Article/index";
    }

    //删除文章
    @RequestMapping(value = "/delete/{id}")
    public void deleteArticle(@PathVariable("id")Integer id){
        articleService.deleteArticle(id);
    }


    //添加文章
    @RequestMapping(value = "/insert")
    public String insertArticleView(Model model,HttpSession session){
        User user=(User)session.getAttribute("user");
        //在添加文章界面显示的都是这个用户添加的分类和标签
        List<Category> categoryList=categoryService.listCategoryWithCountByUserId(user.getUserId());
        List<Tag> tagList=tagService.listTagByUserId(user.getUserId());
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("tagList",tagList);
        return "User/Article/insert";
    }

    //添加文章提交
    @RequestMapping(value = "insertSubmit",method = RequestMethod.POST)
    public String insertArticleSubmit(HttpSession session, ArticleParam articleParam){
        Article article=new Article();
        User user=(User)session.getAttribute("user");
        //设置文章作者ID
        if(user!=null)
        {
            article.setArticleUserId(user.getUserId());
        }
        //设置文章标题
        article.setArticleTitle(articleParam.getArticleTitle());
        //设置文章摘要(根据自定义的长度从文章截取内容）
        int summaryLength=150;
        String summaryText= HtmlUtil.cleanHtmlTag(articleParam.getArticleContent());
        if(summaryText.length()>summaryLength){
            String summary=summaryText.substring(0,summaryLength);
            article.setArticleSummary(summary);
        }
        else {
            article.setArticleSummary(summaryText);
        }

        //设置文章内容
        article.setArticleContent(articleParam.getArticleContent());

        //设置文章状态
        article.setArticleStatus(articleParam.getArticleStatus());

        //设置文章分类
        List<Category> categoryList=new ArrayList<>();
        //获得分类子分类不为空
        if(articleParam.getArticleParentCategoryId()!=null)
        {
            categoryList.add(new Category(articleParam.getArticleParentCategoryId()));
        }
        if(articleParam.getArticleChildCategoryId()!=null)
        {
            categoryList.add(new Category(articleParam.getArticleChildCategoryId()));
        }
        article.setCategoryList(categoryList);

        //设置文章标签
        List<Tag> tagList=new ArrayList<>();
        if(articleParam.getArticleTagIds()!=null){
            for(int i=0;i<articleParam.getArticleTagIds().size();i++){
                Tag tag=new Tag(articleParam.getArticleTagIds().get(i));
                tagList.add(tag);
            }
        }
        article.setTagList(tagList);
        //文章和文章分类关联的，文章和文章标签关联的添加部分都在Service实现类中
        articleService.insertArticle(article);
        return "redirect:/genUser/article";

    }


    //编辑文章
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editArticleView(@PathVariable("id")Integer id,HttpSession session){
        User user=(User)session.getAttribute("user");
        ModelAndView modelAndView=new ModelAndView();
        Article article=articleService.getArticleByStatusAndId(null,id);
        modelAndView.addObject("article",article);

        List<Category> categoryList=categoryService.listCategoryWithCountByUserId(user.getUserId());
        modelAndView.addObject("categoryList",categoryList);

        List<Tag> tagList=tagService.listTagByUserId(user.getUserId());
        modelAndView.addObject("tagList",tagList);

        modelAndView.setViewName("User/Article/edit");
        return modelAndView;
    }

    //编辑提交
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editArticleSubmit(ArticleParam articleParam){
        Article article = new Article();
        article.setArticleId(articleParam.getArticleId());
        article.setArticleTitle(articleParam.getArticleTitle());
        article.setArticleContent(articleParam.getArticleContent());
        article.setArticleStatus(articleParam.getArticleStatus());
        //文章摘要
        int summaryLength = 150;
        String summaryText = HtmlUtil.cleanHtmlTag(article.getArticleContent());
        if (summaryText.length() > summaryLength) {
            String summary = summaryText.substring(0, summaryLength);
            article.setArticleSummary(summary);
        } else {
            article.setArticleSummary(summaryText);
        }
        //填充分类
        List<Category> categoryList = new ArrayList<>();
        if (articleParam.getArticleParentCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleParentCategoryId()));
        }
        if (articleParam.getArticleChildCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleChildCategoryId()));
        }
        article.setCategoryList(categoryList);
        //填充标签
        List<Tag> tagList = new ArrayList<>();
        if (articleParam.getArticleTagIds() != null) {
            for (int i = 0; i < articleParam.getArticleTagIds().size(); i++) {
                Tag tag = new Tag(articleParam.getArticleTagIds().get(i));
                tagList.add(tag);
            }
        }
        article.setTagList(tagList);
        articleService.updateArticleDetail(article);
        return "redirect:/genUser/article";

    }

}
