<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% 
	String path = request.getContextPath(); 
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
	pageContext.setAttribute("basePath",basePath); 
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="shortcut icon" href="ico/icon.ico">
	<title>杯具啦</title>
	<link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/head.css">
    <link rel="stylesheet" href="css/error.css">
</head>
<body>
	<!--主题区域-->
    <div class="main">
        <!--头部区域-->
		<jsp:include page="share/head.jsp"></jsp:include>
        
        <!--主体区域-->
        <div class="error_main">
            <div style="height:80px;">&nbsp;</div>
            <div class="e_c">
                <img src="images/shield_error.png">
                <br>
                <a href="index.html">
                	哎呀，您被关小黑屋啦，预计<fmt:formatDate value="${shield.endTime}" pattern="yyyy年MM月dd日HH时mm分"/>解封。到别处看看吧
                </a>
            </div>
        </div>
    </div>
   
   <!-- 引入尾巴 -->
   <jsp:include page="share/foot.jsp"></jsp:include>
</body>
</html>