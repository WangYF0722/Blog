<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

<rapid:override name="title">
    - 链接列表
</rapid:override>
<rapid:override name="header-style">
    <style>
        /*覆盖 layui*/

        .layui-table {
            margin-top: 0;
        }

        .layui-btn {
            margin: 2px 0!important;
        }
    </style>
</rapid:override>

<rapid:override name="content">
    <blockquote class="layui-elem-quote">
        <span class="layui-breadcrumb" lay-separator="/">
              <a href="/admin">首页</a>
              <a><cite>链接列表</cite></a>
        </span>
    </blockquote>

    <table class="layui-table" >
        <colgroup>
            <col width="100">
            <col width=50">
            <col width="100">
            <col width="100">
            <col width="50">
            <col width="50">
            <col width="100">
            <col width="50">
        </colgroup>
        <thead>
        <tr>
            <th>名称</th>
            <th>URL</th>
            <th>联系方式</th>
            <th>创建时间</th>
            <th>Order</th>
            <th>状态</th>
            <th>操作</th>
            <th>ID</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${linkList}" var="link">
            <tr>
                <td>
                    ${link.linkName}
                </td>
                <td>
                    <a href="${link.linkUrl}" target="_blank">${link.linkUrl}</a>
                </td>
                <td>
                    ${link.linkOwnerContact}
                </td>
                <td>
                    <fmt:formatDate value="${link.linkUpdateTime}" pattern="yyyy年MM月dd日"/>
                </td>
                <td>
                    ${link.linkOrder}
                </td>
                <td>
                    <c:choose>
                        <c:when test="${link.linkStatus==1}">
                            显示
                        </c:when>
                        <c:otherwise>
                            <span style="...">隐藏</span>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <a href="/admin/link/edit/${link.linkId}" class="layui-btn layui-btn-mini">编辑</a>
                    <a href="/admin/link/delete/${link.linkId}" class="layui-btn layui-btn-danger layui-btn-mini" onclick="return confirmDelete()">删除</a>
                </td>
                <td>
                    ${link.linkId}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>





</rapid:override>
<rapid:override name="footer-script">
    <script>

    </script>
</rapid:override>

<%@ include file="../Public/framework.jsp"%>
