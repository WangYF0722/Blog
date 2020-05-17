package com.test.ssm.blog.controller.user;

import com.test.ssm.blog.entity.Page;
import com.test.ssm.blog.enums.PageStatus;
import com.test.ssm.blog.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/admin/page")
public class PageController {

    @Autowired
    private PageService pageService;

    //后台列表显示
    @RequestMapping(value = "")
    public ModelAndView index()
    {
        ModelAndView modelAndView=new ModelAndView();
        List<Page> pageList=pageService.listPage(null);
        modelAndView.addObject("pageList",pageList);
        modelAndView.setViewName("Admin/Page/index");
        return modelAndView;
    }

    //后台添加页面显示
    @RequestMapping(value = "/insert")
    public ModelAndView insertPageView()
    {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("Admin/Page/insert");
        return modelAndView;
    }

    //提交添加
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertPageSubmit(Page page)
    {
        //判断页面别名存在
        Page checkPage=pageService.getPageByKey(null,page.getPageKey());
        if(checkPage==null)
        {
            page.setPageCreateTime(new Date());
            page.setPageUpdateTime(new Date());
            page.setPageStatus(PageStatus.NORMAL.getValue());
            pageService.insertPage(page);
        }
        return "redirect:/admin/page";
    }

    //删除
    @RequestMapping(value = "/delete/{id}")
    public String deletePage(@PathVariable("id")Integer id)
    {
        pageService.deletePage(id);
       return  "redirect:/admin/page";
    }

    //编辑页面
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editView(@PathVariable("id")Integer id)
    {
        ModelAndView modelAndView = new ModelAndView();

        Page page = pageService.getPageById(id);
        modelAndView.addObject("page", page);
        modelAndView.setViewName("Admin/Page/edit");
        return modelAndView;
    }

    //编辑页面提交
    @RequestMapping(value = "editSubmit",method = RequestMethod.POST)
    public String editPageSubmit(Page page)
    {
        Page checkPage=pageService.getPageByKey(null,page.getPageKey());
        if(Objects.equals(checkPage.getPageId(),page.getPageId())){
            page.setPageUpdateTime(new Date());
            pageService.updatePage(page);
        }
        return  "redirect:/admin/page";
    }
}
