package com.test.ssm.blog.controller.user;

import com.test.ssm.blog.entity.Category;
import com.test.ssm.blog.service.ArticleService;
import com.test.ssm.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    //分类列表显示（管理员只有列表显示和删除)
    @RequestMapping(value = "")
    public ModelAndView categoryList(){
        ModelAndView modelAndView=new ModelAndView();
        List<Category> categoryList=categoryService.listCategoryWithCount();
        modelAndView.addObject("categoryList",categoryList);
        modelAndView.setViewName("Admin/Category/index");
        return modelAndView;
    }

    //删除分类
    @RequestMapping(value = "/delete/{id}")
    public String deleteCategory(@PathVariable("id")Integer id)
    {
        //明确是不可以删除有文章的分类
        int count=articleService.countArticleByCategoryId(id);
        if(count==0)
        {
            categoryService.deleteCategory(id);
        }
        return "redirect:/admin/category";


    }
}
