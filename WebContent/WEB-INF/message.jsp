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
	<title>提示信息</title>
	<link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/head.css">
    <script type="text/javascript">
        window.onload = function() {
            setTimeout(jump, 3000);  
        };
        function jump() {
            window.location.href = "index.html";
        }
    </script>
</head>
<body>
	<!--主题区域-->
    <div class="main">
        <!--头部区域-->
		<jsp:include page="share/head.jsp"></jsp:include>
        
        <!--主体部分-->
        <div class="m_main">
          <div class="m_content">
               <img src="images/success.png">
               &nbsp;
               ${message == null ? '注册成功' : message}
               <br><br>
               <span style="font-size:16px;">
                   3秒后跳转到主页，<a href="index.html" class="j_link">等不及的点这里...</a>
               </span>
          </div>
        </div>
        
    </div>
   
   <!-- 引入尾巴 -->
   <jsp:include page="share/foot.jsp"></jsp:include>
</body>
</html>