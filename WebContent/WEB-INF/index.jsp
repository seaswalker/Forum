<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
	<title>首页</title>
	<link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/head.css">
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
	<!--主题区域-->
    <div class="main">
        <!--头部区域-->
		<jsp:include page="share/head.jsp"></jsp:include>
		<!--导航条-->
        <jsp:include page="share/navi.jsp"></jsp:include>
        
         <!--主页正文-->
          <div class="index-main">
              <!--搜索-->
              <div class="main-search">
                 <table>
                     <tr>
                        <td>&nbsp;&nbsp;</td>
                         <td>
                              <input type="text" class="s_i" name="search" placeholder="请输入搜索内容..." style="height:30px;width:400px;">
                          </td>
                          <td>
                              <button class="search-btn">
                                  <img src="images/search.png" style="height:26px;width:26px;">
                              </button>
                          </td>
                      </tr>
                  </table>
              </div>
              
              <!-- 引入位置条 -->
              <jsp:include page="share/addr.jsp"></jsp:include>
              
              <!--板块-->
              <div class="main-section">
                 
                 <!--迭代总板块-->
                 <c:forEach items="${sections}" var="top">
	                  <!--总板块名称-->
	                  <div class="section-title strong-font">
	                      <a href="#">${top.name}</a>
	                  </div>
	                  <!-- 子版块 -->
		               <div class="child-section">
		                   <table cellspacing = "10">
		                       <!--迭代子版块开始-->
		                 	<c:forEach items="${top.children}" var="child">
		                       <tr class="child-section-tr">
		                           <td>
		                               <img src="images/section.gif">
		                           </td>
		                           <td class="second-td">
		                               <p>
		                                   <a href="forum.html?sid=${child.id}" class="strong-font">${child.name}</a>
		                               </p>
		                               <p class="main-font">
		                                  	 版主:${child.manager}
		                               </p>
		                           </td>
		                           <td class="num-td">
		                               <span class="blue-font">${child.articlecount}</span>
		                               &nbsp;/&nbsp;
		                               <span class="main-font">${child.clickcount}</span>
		                           </td>
		                           <!--最后一片帖子的信息-->
		                           <td class="last-td">
		                               <p>
		                                   <a href="view.html?pid=${child.lastreply.id}&sid=${child.id}" target="_blank" class="blue-font">${child.lastreply.title}</a>
		                               </p>
		                               <p class="main-font">
	                                   		<fmt:formatDate value="${child.lastreply.createtime}" pattern="M月d日 HH:mm"/>
		                               </p>
		                               <p class="main-font">
	                                   		<a href="#">${child.lastreply.author.username}</a>
		                               </p>
		                           </td>
		                       </tr>
		                       <tr>
		                           <td colspan="4">
		                               <hr style="border:1px #78a8af dashed;">
		                           </td>
		                       </tr>
		                       <!--迭代子版块结束-->
		                       </c:forEach>
		                   </table>
		               </div>
                   <!--迭代总版块结束--> 
		           </c:forEach>
          </div>
      </div>
   </div>
   <!-- 引入尾巴 -->
   <jsp:include page="share/foot.jsp"></jsp:include>
</body>
</html>