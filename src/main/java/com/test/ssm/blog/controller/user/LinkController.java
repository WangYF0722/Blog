package com.test.ssm.blog.controller.user;

import com.test.ssm.blog.entity.Link;
import com.test.ssm.blog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    //后台链接列表显示
    @RequestMapping(value = "")
    public ModelAndView linkList(){
        ModelAndView modelAndView=new ModelAndView();
        List<Link> linkList=linkService.listLink(null);
        modelAndView.addObject("linkList",linkList);
        modelAndView.setViewName("Admin/Link/index");
        return modelAndView;
    }

    //添加链接页面显示
    @RequestMapping(value = "/insert")
    public  ModelAndView insertLinkView(){
        ModelAndView modelAndView=new ModelAndView();
        List<Link> linkList=linkService.listLink(null);
        modelAndView.addObject("linkList",linkList);
        modelAndView.setViewName("Admin/Link/insert");
        return modelAndView;
    }

    //添加链接提交
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertLinkSubmit(Link link)
    {
        link.setLinkCreateTime(new Date());
        link.setLinkUpdateTime(new Date());
        link.setLinkStatus(1);
        linkService.insertLink(link);
        return "redirect:/admin/link/insert";
    }

    //删除链接
    @RequestMapping(value = "/delete/{id}")
    public String deleteLink(@PathVariable("id")Integer id){
        linkService.deleteLink(id);
        return "redirect:/admin/link";
    }

    //编辑链接
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editLinkView(@PathVariable("id")Integer id){
        ModelAndView modelAndView=new ModelAndView();
        Link linkCustom=linkService.getLinkById(id);
        modelAndView.addObject("linkCustom",linkCustom);

        List<Link> linkList=linkService.listLink(null);
        modelAndView.addObject("linkList",linkList);

        modelAndView.setViewName("Admin/Link/edit");
        return modelAndView;
    }

    //编辑链接提交
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editSubmit(Link link)
    {
        link.setLinkUpdateTime(new Date());
        linkService.updateLink(link);
        return "redirect:/admin/link";
    }


}
