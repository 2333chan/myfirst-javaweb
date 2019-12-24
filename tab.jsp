<%--
  Created by IntelliJ IDEA.
  User: 向莲
  Date: 2019/12/21
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
    <meta charset="UTF-8">
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <script src="<%=basePath%>js/jquery-3.2.1.js"></script>
    <script src="<%=basePath%>js/bootstrap.min.js"></script>

</head>
<body>
<!-- 引入header文件 -->
<%@ include file="header.jsp"%>

<div style="width: 70%;margin:1% 2% 1% 5%;float: left;">
    <div class="panel panel-default"  >
        <div class="panel-heading" style="background-color: black;color: white">
            板块管理
        </div>
            <ul class="list-group" style="width: 100%">
                <li class="list-group-item" >
                    <div style="width: 20%;float: left">板块ID</div>
                    <div style="width: 20%;float: left">板块名</div>
                    <div style="width: 20%;float: left">板块英文名</div>
                    <div>&nbsp;</div>
                </li>
                <c:forEach items="${tabs}" var="tab">
                    <li class="list-group-item">
                        <div style="width: 5%;float: left;text-align: center;font-size: 12px;color: black">
                            <a class="button btn-default" href="<%=basePath%>t/${tab.tabNameEn}">${tab.id}</a>
                        </div>
                        <div style="width: 28%;float: left;text-align: center;font-size: 12px;color: black">
                            <a class="button btn-default">${tab.tabName}</a>
                        </div>
                        <div style="width: 20%;float: left;text-align: center;font-size: 12px;color: black">
                            <a class="button btn-default">${tab.tabNameEn}</a>
                        </div>
                        <div style="width: 5%;float: right;text-align: center;font-size: 12px;color: black">
                            <a class="button btn-default" href="<%=basePath%>tabhuixian/${tab.tabNameEn}">修改板块</a>
                        </div>
                        <div style="width: 5%;float: right;text-align: center;font-size: 12px;color: black">
                            <a class="button btn-default" href="<%=basePath%>tabdelete/${tab.id}">删除板块</a>
                        </div>
                        <br>
                    </li>
                </c:forEach>
                <li class="list-group-item" >
                    <a style="float: right;" class="button btn-default" href="<%=basePath%>newtab">增加板块</a><br><br>
                </li>

            </ul>

        </div>
</div>
        <!-- 引入侧边栏文件 -->
        <%@ include file="side.jsp"%>
<%@ include file="footer.jsp"%>

</body>
</html>