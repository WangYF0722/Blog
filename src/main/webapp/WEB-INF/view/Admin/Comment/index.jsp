<%@ page import="java.security.MessageDigest" %>
<%@ page import="java.security.NoSuchAlgorithmException" %>
<%@ page import="com.test.ssm.blog.util.MyUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

<rapid:override name="title">
    - 评论列表
</rapid:override>
<rapid:override name="header-style">
    <style>
        /*覆盖 layui*/
        .layui-table {
            margin-top: 0;
        }
    </style>
</rapid:override>

<rapid:override name="content">
    <blockquote class="layui-elem-quote">
        <span class="layui-breadcrumb" lay-separator="/">
              <a href="/admin">首页</a>
              <a><cite>评论列表</cite></a>
        </span>
    </blockquote>
    <div class="layui-tab layui-tab-card">
        <table class="layui-table" lay-even lay-skin="nob">
            <colgroup>
                <col width="100">
                <col width="300">
                <col width=200">
                <col width="150">
                <col width="50">
            </colgroup>
            <thead>
            <tr>
                <th>作者</th>
                <th>评论内容</th>
                <th>回复至</th>
                <th>提交于</th>
                <th>ID</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageInfo.list}" var="comment">
                <tr>
                    <td>
                        <img src="${comment.commentAuthorAvatar}" alt="" width="64px">
                        <strong>${comment.commentAuthorName}</strong>
                        <br>
                            ${comment.commentAuthorUrl}<br>
                            ${comment.commentAuthorEmail}<br>
                            ${comment.commentIp}
                    </td>
                    <td class="dashboard-comment-wrap">
                        <c:if test="${comment.commentPid!=0}">
                            <span class="at">@</span><a href="${comment.commentAuthorUrl}">${comment.commentPname}</a>
                        </c:if>
                        ${comment.commentContent}
                        <div class="row-actions">
                            <span class="">
                                <a href="/admin/comment/reply/${comment.commentId}">
                                    回复
                                </a>
                            </span>
                            <span class="">
                                <a href="/admin/comment/edit/${comment.commentId}">
                                    编辑
                                </a>
                            </span>
                            <span class="">
                                  <a href="javascript:void(0)" onclick="deleteComment(${comment.commentId})">删除</a>
                            </span>
                        </div>
                    </td>
                    <td>
                        <a href="/article/${comment.article.articleId}"
                           target="_blank">
                            ${comment.article.articleTitle}
                        </a>
                    </td>
                    <td>
                        <fmt:formatDate value="${comment.commentCreateTime}" pattern="yyyy年MM月dd日"/>
                    </td>
                    <td>${comment.commentId}</td>
                </tr>


            </c:forEach>
            </tbody>

        </table>

        <div id="nav" style="">
            <%@ include file="../Public/paging.jsp" %>
        </div>
    </div>


</rapid:override>

<%@ include file="../Public/framework.jsp" %>
