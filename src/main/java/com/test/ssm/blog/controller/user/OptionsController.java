package com.test.ssm.blog.controller.user;


import com.test.ssm.blog.entity.Options;
import com.test.ssm.blog.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/options")
public class OptionsController {

    @Autowired
    private OptionsService optionsService;

    //基本信息显示
    @RequestMapping(value = "")
    public ModelAndView index(){
        ModelAndView modelAndView=new ModelAndView();
        Options options=optionsService.getOptions();
        modelAndView.addObject("option",options);
        modelAndView.setViewName("Admin/Options/index");
        return modelAndView;
    }

    //编辑基本信息显示
    @RequestMapping("/edit")
    public ModelAndView editOptionView(){
        ModelAndView modelAndView=new ModelAndView();
        Options options=optionsService.getOptions();
        modelAndView.addObject("option",options);
        modelAndView.setViewName("Admin/Options/edit");
        return modelAndView;
    }

    //编辑基本信息提交
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editOptionSubmit(Options options){
        Options optionsCustom = optionsService.getOptions();
        if(optionsCustom.getOptionId()==null) {
            optionsService.insertOptions(options);
        } else {
            optionsService.updateOptions(options);
        }
        return "redirect:/admin/options";
    }
}
