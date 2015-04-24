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
	<link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/register.css">
    <link rel="stylesheet" href="css/head.css">
    <script src="script/register.js"></script>
    <script src="script/login.js"></script>
    <script src="script/ajax.js"></script>
    <script src="script/ListenerUtil.js"></script>
    <!-- 加入登陆和注册事件监听器 -->
    <script type="text/javascript">
   		ListenerUtil.addListener(window, "load", register, false);
    </script>
	<title>用户注册</title>
</head>
<body>
	<!--主题区域-->
       <div class="main">
          
           <!--头部区域-->
           <jsp:include page="share/head.jsp"></jsp:include>
           
           <!-- 引入注册-登录 -->
           <jsp:include page="share/register_login.jsp"></jsp:include>
           
           <!--主体区域-->
           <div class="register-main">
               <!--标题-->
               <div class="register-title">
                   <span class="strong-font">欢迎注册：</span>
                   <a href="javascript:show_login();" class="blue-font" style="float:right;margin-right:10px;">已有帐号?现在登录</a>
               </div>
               
               <div>
                  <form action="register/save.html" onsubmit="return check(this);" method="post" id="register_form">
                       <table align="center" id="register-table">
                           <tr>
                               <td width="22%">
                                   <span style="color:red;">*</span>
                                 	  用户名：
                               </td>
                               <td width="36%">
                                   <input type="text" name="username">
                               </td>
                               <td width="4%">
                                   &nbsp;&nbsp;
                               </td>
                               <td width="38%">
                                   <span class="main-font">用户名由3-10个字符组成</span>
                               </td>
                           </tr>
                           <tr>
                               <td>
                                   <span style="color:red;">*</span>
                                 	  密码：
                               </td>
                               <td>
                                   <input type="password" name="password">
                                </td>
                                <td>
                                    &nbsp;&nbsp;
                                </td>
                                <td>
                                   <span class="main-font">请填写密码，最小长度5个字符</span>
                                </td>
                           </tr>
                           <tr>
                               <td>
                                   <span style="color:red;">*</span>
                                   	确认密码：
                               </td>
                               <td>
                                   <input type="password" name="repassword">
                                </td>
                                <td>
                                    &nbsp;&nbsp;
                                </td>
                                <td>
                                   <span class="main-font">请输入确认密码</span>
                                </td>
                           </tr>
                           <tr>
                               <td>
                                   <span style="color:red;">*</span>
                                  	 邮箱：
                               </td>
                               <td>
                                   <input type="text" name="email">
                                </td>
                                <td>
                                    &nbsp;&nbsp;
                                </td>
                                <td>
                                   <span class="main-font">请输入电子邮箱</span>
                                </td>
                           </tr>
                           <tr>
                               <td>
                                   <span style="color:red;">*</span>
                                  	 验证码：
                               </td>
                               <td>
                                   <input type="text" name="verifycode" style="width:90px;">
                                	<img src = "image.jsp" onclick="image(this);" style="height:22px;vertical-align: text-top;">
                                </td>
                                <td>
                                    &nbsp;&nbsp;
                                </td>
                                <td>
                                   <span class="main-font">请输入验证码</span>
                                </td>
                           </tr>
                           <tr>
                               <td style="text-align:center;" colspan="4">
                                   <input type="submit" value="提交">
                               </td>
                           </tr>
                       </table>
                   </form>
               </div>
           </div>
   	 
   	 <!-- 引入尾部 -->
   	 <jsp:include page="share/foot.jsp"></jsp:include>
   	 
   	</div>
</body>
</html>