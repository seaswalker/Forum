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
    <link rel="stylesheet" href="css/set.css">
    <link rel="stylesheet" href="css/head.css">
    <script src="script/jquery-1.11.1.min.js"></script>
    <script src="script/upload/ajaxfileupload.js"></script>
    <script src="script/set.js"></script>
    <script src="script/ListenerUtil.js"></script>
    <!-- 加入登陆和注册事件监听器 -->
    <script type="text/javascript">
   		ListenerUtil.addListener(window, "load", _tab_switch, false);
    </script>
	<title>个人设置</title>
</head>
<body>
	<!--主题区域-->
       <div class="main">
          
           <!--头部区域-->
           <jsp:include page="share/head.jsp"></jsp:include>
           
           <!--主体区域-->
			<div class="set-main">
               <!-- 左侧tab -->
               <ul class="left_tab">
                   <!--标题-->
                   <li class="tab_title">
                      &nbsp;设置:
                   </li>
                   <li class="active_tab" name="tab">
                      &nbsp;修改密码
                   </li>
                   <li name="tab">
                       &nbsp;修改头像
                   </li>
               </ul>
               <!--修改密码-->
               <div class="m_p" name="tab_div">
                   <form action="user/mp.html" method="post" id="mp_form" onsubmit="return _check_mp(this);">
                       <table class="mp_table">
                           <tr>
                               <td width="40%">
                                   	请输入旧密码:
                               </td>
                               <td>
                                   <input type="password" name="old_password">
                               </td>
                           </tr>
                           <tr>
                               <td>
                                  	 请输入新密码:
                               </td>
                               <td>
                                   <input type="password" name="password">
                               </td>
                           </tr>
                           <tr>
                               <td>
                                  	 新密码确认:
                               </td>
                               <td>
                                   <input type="password" name="repassword">
                               </td>
                           </tr>
                           <tr>
                               <td></td>
                               <td>
                                   <input type="submit" value="保存">
                                   &nbsp;
                                   <input type="button" value="返回" onclick="window.history.back();">
                                   &nbsp;
                                   <span id="mp_error" class="error">${error}</span>
                               </td>
                           </tr>
                       </table>
                    </form>
               </div>
               
               <!--头像修改-->
               <div class="m_a" name="tab_div">
                   <form id="ma_form" action="user/ma.html" method="post" onsubmit="return _check_ma(this);">
                   	   <!-- 隐藏字段提交新头像地址 -->
                   	   <input type="hidden" name="new_avatar" id="new_avatar">
                       <table class="ma_table">
                          <tr>
                              <td>
                                  &nbsp;旧头像:
                              </td>
                              <td>
                                  &nbsp;请选择您的新头像:
                                  &nbsp;&nbsp;
                                   <input type="file" id="file" name="file" onchange="_show_ci(this);">
                              </td>
                          </tr>
                           <tr>
                               <td>
                                   <img src="${sessionScope.user.avatar}" class="old_avatar">
                               </td>
                               <td style="text-align:center;line-height:150px;" id="choose_img">
                                   <!--<img src="C:\Users\xsdwe_000\Desktop\demo3.jpg" id="choose_img">-->
                                   <span class="gray-font">图片预览...</span>
                               </td>
                           </tr>
                           <tr>
                               <td>
                               </td>
                               <td>
                                  &nbsp;
                                   <input type="submit" value="保存">
                                   &nbsp;&nbsp;
                                   <input type="button" value="返回" onclick="window.history.back();">
                                   &nbsp;&nbsp;
                                   <span class="error" id="ma_error">${error}</span>
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