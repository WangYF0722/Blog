package com.test.ssm.blog.controller.genUser;

import com.test.ssm.blog.entity.User;
import com.test.ssm.blog.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/genUser/user")
public class UserBackController {

    @Autowired
    private UserService userService;

    //用户基本信息显示
    @RequestMapping(value = "/profile")
    public ModelAndView userProfileView(HttpSession session){
        ModelAndView modelAndView=new ModelAndView();
        User user=(User)session.getAttribute("user");
        User user1=userService.getUserById(user.getUserId());
        modelAndView.addObject("user",user);
        modelAndView.setViewName("User/User/profile");
        return modelAndView;
    }


    /**
     * 检查用户名是否存在
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/checkUserName",method = RequestMethod.POST)
    @ResponseBody
    public String checkUserName(HttpServletRequest request)  {
        Map<String, Object> map = new HashMap<String, Object>();
        String username = request.getParameter("username");
        User user = userService.getUserByName(username);
        int id = Integer.valueOf(request.getParameter("id"));
        //用户名已存在,但不是当前用户(编辑用户的时候，不提示)
        if(user!=null) {
            if(user.getUserId()!=id) {
                map.put("code", 1);
                map.put("msg", "用户名已存在！");
            }
        } else {
            map.put("code",0);
            map.put("msg","");
        }
        String result = new JSONObject(map).toString();
        return result;
    }

    /**
     * 检查Email是否存在
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/checkUserEmail",method = RequestMethod.POST)
    @ResponseBody
    public String checkUserEmail(HttpServletRequest request)  {
        Map<String, Object> map = new HashMap<String, Object>();
        String email = request.getParameter("email");
        User user = userService.getUserByEmail(email);
        int id = Integer.valueOf(request.getParameter("id"));
        //用户名已存在,但不是当前用户(编辑用户的时候，不提示)
        if(user!=null) {
            if(user.getUserId()!=id) {
                map.put("code", 1);
                map.put("msg", "电子邮箱已存在！");
            }
        } else {
            map.put("code",0);
            map.put("msg","");
        }
        String result = new JSONObject(map).toString();
        return result;
    }


    /**
     * 编辑用户页面显示
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editUserView(@PathVariable("id") Integer id)  {
        ModelAndView modelAndView = new ModelAndView();

        User user =  userService.getUserById(id);
        modelAndView.addObject("user",user);

        modelAndView.setViewName("User/User/edit");
        return modelAndView;
    }


    /**
     * 编辑用户提交
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editUserSubmit(User user)  {
        userService.updateUser(user);
        return "redirect:/genUser/user/profile";
    }


}
