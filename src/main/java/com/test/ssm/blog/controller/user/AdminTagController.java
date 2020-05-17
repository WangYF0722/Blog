package com.test.ssm.blog.controller.user;

import com.test.ssm.blog.entity.Tag;
import com.test.ssm.blog.service.ArticleService;
import com.test.ssm.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/tag")
public class AdminTagController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    //后台列表显示
    @RequestMapping(value = "")
    public ModelAndView index(){
        ModelAndView modelAndView =new ModelAndView();
        List<Tag> tagList=tagService.listTagWithCount();
        modelAndView.addObject("tagList",tagList);
        modelAndView.setViewName("Admin/Tag/index");
        return modelAndView;
    }

    //删除标签
    @RequestMapping(value = "/delete/{id}")
    public String deleteTag(@PathVariable("id")Integer id){
        Integer count=articleService.countArticleByTagId(id);
        if(count==0)
        {
            tagService.deleteTag(id);
        }
        return "redirect:/admin/tag";
    }

}
