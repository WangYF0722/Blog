package com.test.ssm.blog.interceptor;

//拦截器

import com.test.ssm.blog.entity.Article;
import com.test.ssm.blog.entity.Category;
import com.test.ssm.blog.entity.Menu;
import com.test.ssm.blog.entity.Options;
import com.test.ssm.blog.enums.ArticleStatus;
import com.test.ssm.blog.enums.LinkStatus;
import com.test.ssm.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Component
public class HomeResourceInterceptor implements HandlerInterceptor {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;
    @Autowired
    private LinkService linkService;
    @Autowired
    private OptionsService optionsService;
    @Autowired
    private MenuService menuService;

    /**
     * 在请求处理之前，该方法主要是用于准备资源数据的，然后可以把他它们当做请求属性放在WebRequest
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
      //菜单显示

        List<Menu> menuList=menuService.listMenu();
        httpServletRequest.setAttribute("menuList",menuList);

         //分类
        List<Category> categoryList=categoryService.listCategory();
        httpServletRequest.setAttribute("allCategoryList",categoryList);

        //获得网站概况
        List<String> siteBasicStatistics=new ArrayList<>();
        siteBasicStatistics.add(articleService.countArticle(ArticleStatus.PUBLISH.getValue())+"");
        siteBasicStatistics.add(articleService.countArticleComment()+"");
        siteBasicStatistics.add(categoryService.countCategory()+"");
        siteBasicStatistics.add(tagService.countTag()+"");
        siteBasicStatistics.add(linkService.countLink(LinkStatus.NORMAL.getValue())+"");
        siteBasicStatistics.add(articleService.countArticleView()+"");
        httpServletRequest.setAttribute("siteBasicStatistics",siteBasicStatistics);

        //最后更新的文章
        Article lastUpdateArticle=articleService.getLastUpdateArticle();
        httpServletRequest.setAttribute("lastUpdateArticle",lastUpdateArticle);

        //页脚显示（显示博客基本信息Options）
        Options options=optionsService.getOptions();
        httpServletRequest.setAttribute("options",options);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
