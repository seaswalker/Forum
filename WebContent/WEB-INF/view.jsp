<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "skywalker" uri="skywalker" %>
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
	<title>帖子浏览</title>
	<link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/head.css">
    <link rel="stylesheet" href="css/reply.css">
    <link rel="stylesheet" href="css/view.css">
    <link rel="stylesheet" href="css/page.css">
    <link rel="stylesheet" href="css/ck_tips.css">
    <script src="ckeditor/ckeditor.js"></script>
    <script src="script/ListenerUtil.js"></script>
    <script src="script/post.js"></script>
    <script src="script/view.js"></script>
    <script type="text/javascript">
    	ListenerUtil.addListener(window, "load", config, false);
	    //全局变量 记录ckeditor
	  	var ckeditor = null;
		//全局变量记录板块id
		var sid = 0;
		
		function config() {
			//配置分页url
		    var pid = ${pid};
		    var url = "view.html?pid=" + pid + "&pn=";
		    Jump.url = url;
		    //设置sid
		    sid = document.getElementById("sid").value;
		};
    </script>
</head>
<body>
	 <!--主题区域-->
     <div class="main">
		 <!-- 登录div -->
		 <jsp:include page="share/register_login.jsp"></jsp:include>
         <!--头部区域-->
         <jsp:include page="share/head.jsp"></jsp:include>
         
         <!--导航条-->
         <jsp:include page="share/navi.jsp"></jsp:include>
         
         <!-- 是否登录 -->
     	<input type="hidden" id="login" value="${sessionScope.user == null ? '0' : '1'}">
     	<input type="hidden" id="sid" value="${article.sectionid}">
         
         <!--主体-->
         <div class="view-main">
                <!--位置-->
                <jsp:include page="share/addr.jsp"></jsp:include>
                
                <!--分页以及发帖-->
                <jsp:include page="share/page.jsp"></jsp:include>
                
                <!--内容区域-->
                <div class="content">
                   <!-- 楼主 ，当pn为1时显示-->
                   <c:if test="${pageBean.currentPage == 1}">
	                    <table class="floor_lz">
	                       <!--楼层左侧个人信息-->
	                       <tr>
								<td class="c_r">
	                                <span class="gray-font">点击:</span>
	                                <span class="red-font"> ${article.clickcount}</span>
	                                <span class="gray-font">&nbsp;&nbsp;|&nbsp;&nbsp;回复:</span>
	                                <span class="red-font"> ${article.replycount}</span>
								</td>
								<td class="title">
									<a href="forum.html?sid=${article.sectionid}&cg=${article.category.id}">[技术交流]</a>
									${article.title}
									<!-- 显示类型图片 -->
									<c:choose>
										<c:when test="${article.type == 2}">
											<img src="images/elite_font.png" class="type_icon">
										</c:when>
										<c:when test="${article.type == 3}">
											<img src="images/top_font.png" class="type_icon">
										</c:when>
									</c:choose>
								</td>
	                       </tr>
	                       <tr>
	                       		<td class="author main-font">
	                       			&nbsp;&nbsp;
	                                <a href="#" class="author_link">${article.author.username}</a>
	                       		</td>
	                       		<td class="main-font info">
	                       			<img src="images/info.gif">&nbsp;&nbsp;
	                                	发表于 : <fmt:formatDate value="${article.createtime}" pattern="y-M-d HH:mm"/>
	                                <span class="floor_info">楼主</span>
	                       		</td>
	                       </tr>
	                       <tr>
	                       		<td class="avatar_td" rowspan="2">
	                                <img src="${article.author.avatar}">
	                    		</td>
	                       		<td class="post_content">
	                       			${article.content}
	                       		</td>
	                       </tr>
	                       <tr>
	                       		<td class="btn">
	                       			<img src="images/fastreply.gif">
	                                <span class="main-font" style="cursor:pointer;" onclick="_reply(false);">回复</span>
	                                <!-- 检查权限 -->
	                                <skywalker:authorOrManager sid="${article.sectionid}" name="${article.author.username}">
		                                <img src="images/delete.png">
		                                <span class="main-font" style="cursor:pointer" onclick="_deleteArticle(${article.id});">删除</span>
		                                <img src="images/edit.png">
		                                <span class="main-font" style="cursor:pointer;" onclick="_editReply(${article.id});">修改</span>
	                                </skywalker:authorOrManager>
	                                <skywalker:manager sid="${article.sectionid}"></skywalker:manager>
	                                <c:if test="${isManager}">
		                                <c:if test="${article.type != 2}">
			                                <img src="images/elite.png">
			                                <span class="main-font" style="cursor:pointer;" onclick="changeType(${article.id}, 2);">加精</span>
			                            </c:if>
			                            <c:if test="${article.type != 3}">
			                                <img src="images/top.png">
			                                <span class="main-font" style="cursor:pointer;" onclick="changeType(${article.id}, 3);">置顶</span>
			                            </c:if>
			                             <c:if test="${article.type != 1}">
			                                <img src="images/normal.png">
			                                <span class="main-font" style="cursor:pointer;" onclick="changeType(${article.id}, 1);">恢复默认</span>
			                            </c:if>
		                                <img src="images/shield.png">
		                                <span class="main-font" style="cursor:pointer;" onclick="show_shield(${article.author.id}, ${article.sectionid});">小黑屋</span>
	                                </c:if>
			                         
	                       		</td>
	                       </tr>
	                    </table>
	                 </c:if>
                    
                    <!-- 迭代回复 -->
                    <c:forEach items="${pageBean.records}" var="reply" varStatus="status">
	                    <table class="floor_reply">
	                    	<tr>
	                    		<td class="author main-font">
	                    			&nbsp;&nbsp;
	                                <a href="#" class="author_link">${reply.author.username}</a>
	                    		</td>
	                    		<td class="main-font info">
	                                <img src="images/info.gif">&nbsp;&nbsp;
	                                	发表于 : <fmt:formatDate value="${reply.createtime}" pattern="y-M-d HH:mm"/>
	                                <span class="floor_info">#${(pageBean.currentPage - 1) * pageBean.pageSize + status.index + 2}楼</span>
	                    		</td>
	                    	</tr>
                  			<c:choose>
                          		<c:when test="${reply.visible && reply.author.visible}">
	                    		<tr>
		                    		<td class="avatar_td" rowspan="2">
		                                <img src="${reply.author.avatar}">
		                    		</td>
		                    		<td class="post_content">
				                         ${reply.content}
		                    		</td>
		                    	</tr>
		                    	<tr>
		                    		<td class="btn">
		                                <img src="images/fastreply.gif">
		                                <span class="main-font" style="cursor:pointer;" onclick="_reply(true, this);">回复</span>
		                                <c:if test="${isManager}">
			                                <img src="images/delete.png">
			                                <span class="main-font" style="cursor:pointer" onclick="_deleteReply(${reply.id}, ${article.id});">删除</span>
			                                <img src="images/shield.png">
		                                	<span class="main-font" style="cursor:pointer;" onclick="show_shield(${reply.author.id}, ${article.sectionid});">小黑屋</span>
		                                </c:if>
		                    		</td>
		                    	</tr>
		                    	</c:when>
                           		<c:otherwise>
                           			<tr>
			                    		<td class="avatar_td" rowspan="2">
			                                <img src="${reply.author.avatar}">
			                    		</td>
			                    		<td class="post_content">
		                           			<span style="color: red;font-family: '宋体'">此回复已被删除或此用户被禁言</span>
			                    		</td>
			                    	</tr>
			                    	<tr>
			                    		<td class="btn"></td>
			                    	</tr>
                           		</c:otherwise>
	                       </c:choose>
	                    </table>
	                 </c:forEach>
                    <!-- 回复迭代结束 -->
                </div>
                
                <!--分页-->
                <jsp:include page="share/page.jsp"></jsp:include>
                
            <div>
                <div class="reply_title">
                	    发表回复
                </div>
                <form action="reply.html" method="post" id="post_form" onsubmit="checkPost();">
                	<input type="hidden" name="pid" value="${pid}">
                	<input type="hidden" name="sid" value="${article.sectionid}">
	                <table class="reply_table" id="reply_table">
	                    <jsp:include page="share/ckeditor.jsp"></jsp:include>
	                    <tr><td>&nbsp;</td></tr>
	                </table>
	            </form>
            </div>
        </div>
     </div>
     <!-- 小黑屋天数 -->
     <div id="shield_window">
     	<table>
     		<tr>
     			<td>小黑屋天数:</td>
     			<td>
     				<input type="text" id="shield_days_value">
     			</td>
     		</tr>
     		<tr>
     			<td></td>
     			<td class="gray-font">
     				*(1-10天)
     			</td>
     		</tr>
     		<tr>
     			<td></td>
     			<td><span id="shield_error" class="error"></span></td>
     		</tr>
     		<tr>
     			<td></td>
     			<td>
     				<button onclick="do_shield();">确认</button>
     				<button onclick="close_shield();">取消</button>
     			</td>
     		</tr>
     	</table>
     </div>
     
     <!-- 尾巴 -->
     <jsp:include page="share/foot.jsp"></jsp:include>
</body>
</html>