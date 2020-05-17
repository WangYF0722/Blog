package com.test.ssm.blog.controller.genUser;

import com.test.ssm.blog.entity.Category;
import com.test.ssm.blog.entity.User;
import com.test.ssm.blog.service.ArticleService;
import com.test.ssm.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.portlet.MockActionRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/genUser/category")
public class UserCategoryController {


    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;


    //后台分类列表显示
    @RequestMapping(value = "")
    public ModelAndView categoryList(HttpSession session){
        User user=(User)session.getAttribute("user");
        ModelAndView modelAndView=new ModelAndView();
        List<Category> categoryList=categoryService.listCategoryWithCountByUserId(user.getUserId());
        modelAndView.addObject("categoryList",categoryList);
        modelAndView.setViewName("User/Category/index");
        return modelAndView;
    }


    //后台添加分类提交
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertCategorySubmit(Category category,HttpSession session){
        System.out.println(category.getCategoryPid());
        User user=(User)session.getAttribute("user");
        category.setCategoryUserId(user.getUserId());
        categoryService.insertCategory(category);
        return "redirect:/genUser/category";
    }

    //删除分类
    @RequestMapping(value = "/delete/{id}")
    public String deleteCategory(@PathVariable("id")Integer id){
        //禁止删除有文章的分类
        int count=articleService.countArticleByCategoryId(id);
        if(count==0)
        {
            categoryService.deleteCategory(id);
        }
        return "redirect:/genUser/category";
    }

    //编辑分类页面显示
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editCategoryView(@PathVariable("id")Integer id,HttpSession session)
    {
        ModelAndView modelAndView=new ModelAndView();
        Category category=categoryService.getCategoryById(id);
        modelAndView.addObject("category",category);
        User user=(User)session.getAttribute("user");
        List<Category> categoryList=categoryService.listCategoryWithCountByUserId(user.getUserId());
        modelAndView.addObject("categoryList",categoryList);
        modelAndView.setViewName("User/Category/edit");
        return modelAndView;
    }

    //编辑分类提交
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editCategorySubmit(Category category)
    {
        categoryService.updateCategory(category);
        return "redirect:/genUser/category";
    }



}
