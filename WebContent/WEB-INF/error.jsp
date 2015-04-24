<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<title>出错啦</title>
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
                <img src="images/sorry.png">
                <br>
                <a href="index.html">${tips == null ? '哎呀，出错啦...到别处看看吧' : tips}</a>
            </div>
        </div>
    </div>
   
   <!-- 引入尾巴 -->
   <jsp:include page="share/foot.jsp"></jsp:include>
</body>
</html>