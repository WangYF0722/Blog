package com.test.ssm.blog.controller.home;

import com.test.ssm.blog.entity.Article;
import com.test.ssm.blog.entity.Notice;
import com.test.ssm.blog.service.ArticleService;
import com.test.ssm.blog.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeNoticeController {
    @Autowired
    private NoticeService noticeService;

    @Autowired
    private ArticleService articleService;


    //公告详情页
    @RequestMapping(value = "/notice/{noticeId}")
    public String NoticeDetailView(@PathVariable("noticeId")Integer noticeId, Model model){
        //公告内容和信息显示
        Notice notice=noticeService.getNoticeById(noticeId);
        model.addAttribute("notice",notice);

        //侧边栏
        //热评文章
        List<Article> mostCommentArticleList=articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList",mostCommentArticleList);
        return "Home/Page/noticeDetail";


    }
}
