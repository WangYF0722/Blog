package com.test.ssm.blog.controller.user;

import com.test.ssm.blog.entity.Article;
import com.test.ssm.blog.entity.Comment;
import com.test.ssm.blog.entity.User;
import com.test.ssm.blog.service.ArticleService;
import com.test.ssm.blog.service.CommentService;
import com.test.ssm.blog.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.test.ssm.blog.util.MyUtils.getIpAddr;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @RequestMapping("/admin")
    //转到管理员后台主页
    public String index(Model model){
        List<Article> articleList=articleService.listRecentArticle(5);
        model.addAttribute("articleList",articleList);

        List<Comment> commentList = commentService.listRecentComment(5);
        model.addAttribute("commentList",commentList);
        return "Admin/AdminIndex";
    }

    //转到个人的后台页面
    @RequestMapping("/genUser")
    public String userIndex(Model model)
    {
        return "User/Article/insert";
    }


    //登录页面显示
    @RequestMapping("/login")
    public String loginPage()
    {
        return "login";
    }


    //注册页面显示
    @RequestMapping("/register")
    public String registerPage(){
        return "register";
    }


    //注册验证
    @RequestMapping(value = "/registerVerify",method = RequestMethod.POST)
    @ResponseBody
    public String registerVerify(HttpServletRequest request,HttpServletResponse response){
        String username = request.getParameter("username");
        System.out.println(username);
        String password = request.getParameter("password");
        String rememberme = request.getParameter("rememberme");
        User user=userService.getUserByName(username);
        Map<String,Object> map=new HashMap<>();
        if(user!=null)
        {
            map.put("code",0);
            map.put("msg","用户已经存在");
        }
        else{
            User insertUser=new User();
            insertUser.setUserName(username);
            insertUser.setUserPass(password);
            insertUser.setUserRegisterTime(new Date());
            insertUser.setUserStatus(1);
            insertUser.setUserAccess(1);
            request.getSession().setAttribute("user", user);
            if(rememberme!=null){
                //创建两个Cookie对象
                Cookie nameCookie = new Cookie("username", username);
                //设置Cookie的有效期为3天
                nameCookie.setMaxAge(60 * 60 * 24 * 3);
                Cookie pwdCookie = new Cookie("password", password);
                pwdCookie.setMaxAge(60 * 60 * 24 * 3);
                response.addCookie(nameCookie);
                response.addCookie(pwdCookie);
            }
            userService.insertUser(insertUser);
            map.put("code",1);
            map.put("msg","用户创建成功");
        }
        String result = new JSONObject(map).toString();
        return result;

    }


    //登录验证
    @RequestMapping(value = "/loginVerify",method = RequestMethod.POST)
    @ResponseBody
    public String loginVerify(HttpServletRequest request, HttpServletResponse response)  {
        Map<String, Object> map = new HashMap<String, Object>();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberme = request.getParameter("rememberme");
        User user = userService.getUserByNameOrEmail(username);
        if(user==null) {
            map.put("code",0);
            map.put("msg","用户名无效！");
        } else if(!user.getUserPass().equals(password)) {
            map.put("code",0);
            map.put("msg","密码错误！");
        } else {
            //登录成功
            if(user.getUserAccess()==0){
                map.put("code",1);
            }
            else{
                map.put("code",2);
            }
            map.put("msg","");
            //添加session
            request.getSession().setAttribute("user", user);
            //添加cookie
            if(rememberme!=null) {
                //创建两个Cookie对象
                Cookie nameCookie = new Cookie("username", username);
                //设置Cookie的有效期为3天
                nameCookie.setMaxAge(60 * 60 * 24 * 3);
                Cookie pwdCookie = new Cookie("password", password);
                pwdCookie.setMaxAge(60 * 60 * 24 * 3);
                response.addCookie(nameCookie);
                response.addCookie(pwdCookie);
            }
            user.setUserLastLoginTime(new Date());
            user.setUserLastLoginIp(getIpAddr(request));
            userService.updateUser(user);

        }
        String result = new JSONObject(map).toString();
        return result;
    }

    //退出登录
    @RequestMapping(value ="/admin/logout" )
    public String logout(HttpSession session){
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/login";
    }
}
