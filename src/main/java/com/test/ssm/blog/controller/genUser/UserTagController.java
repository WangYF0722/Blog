package com.test.ssm.blog.controller.genUser;


import com.test.ssm.blog.entity.Tag;
import com.test.ssm.blog.entity.User;
import com.test.ssm.blog.service.ArticleService;
import com.test.ssm.blog.service.TagService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/genUser/tag")
public class UserTagController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    //后台标签列表显示
    @RequestMapping(value = "")
    public ModelAndView index(HttpSession session){
        User user=(User)session.getAttribute("user");
        ModelAndView modelAndView=new ModelAndView();
        List<Tag> tagList=tagService.listTagByUserId(user.getUserId());
        modelAndView.addObject("tagList",tagList);
        modelAndView.setViewName("User/Tag/index");
        return modelAndView;
    }


    //添加标签
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertTagSubmit(Tag tag,HttpSession session)
    {
        User user=(User)session.getAttribute("user");
        tag.setTagUserId(user.getUserId());
        tagService.insertTag(tag);
        return "redirect:/genUser/tag";
    }

    //删除标签
    @RequestMapping(value = "/delete/{id}")
    public String deleteTag(@PathVariable("id")Integer id){
        Integer count=articleService.countArticleByTagId(id);
        if(count==0){
            tagService.deleteTag(id);
        }
        return "redirect:/genUser/tag";
    }

    //编辑
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editTagView(@PathVariable("id")Integer id,HttpSession session)
    {
        ModelAndView modelAndView=new ModelAndView();
        Tag tag=tagService.getTagById(id);
        modelAndView.addObject("tag",tag);
        User user=(User)session.getAttribute("user");
        List<Tag> tagList=tagService.listTagByUserId(user.getUserId());
        modelAndView.addObject("tagList",tagList);
        modelAndView.setViewName("User/Tag/edit");
        return modelAndView;

    }

    //编辑提交
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editTagSubmit(Tag tag){
        tagService.updateTag(tag);
        return "redirect:/genUser/tag";
    }



}
