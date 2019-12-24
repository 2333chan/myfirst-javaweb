<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String socketPath = request.getServerName()+":"+request.getServerPort()+path+"/";
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
    <div class="panel panel-default" id="main" style="">
        <div class="panel-heading" style="background-color: black;color: white">
            修改板块
        </div>

        <div class="panel-body">
                <form action="<%=basePath%>tabupdate/${tab.id}" method="post">


                    <div class="form-group">
                        <label for="pwd">板块名称</label>
                        <input type="text" class="form-control" id="pwd" name="tab_name" value="${tab.tabName}">
                    </div>
                    <div class="form-group" hidden>
                        <input type="text" class="form-control" id="username" name="id" value="${tab.id}">
                    </div>
                <input type="submit" class="btn btn-default" id="signup" style="margin-left: 17%">
            </form>
        </div>
    </div>
</div>

<!-- 引入侧边栏文件 -->
<%@ include file="side.jsp"%>
<%@ include file="footer.jsp"%>

</body>
</html>