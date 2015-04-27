<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
	<title>论坛</title>
	<link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/forum.css">
    <link rel="stylesheet" href="css/head.css">
    <link rel="stylesheet" href="css/reply.css">
    <link rel="stylesheet" href="css/page.css">
    <link rel="stylesheet" href="css/ck_tips.css">
    <script src="ckeditor/ckeditor.js"></script>
    <script src="script/ListenerUtil.js"></script>
    <script src="script/ajax.js"></script>
    <script src="script/post.js"></script>
    <script type="text/javascript">
    	//设置跳转函数url
    	ListenerUtil.addListener(window, "load", config, false);
    	//加载类别
    	ListenerUtil.addListener(window, "load", loadCg, false);
    	
    	//全局变量 记录ckeditor
    	var ckeditor = null;
    	
    	function config() {
            //配置分页url
            var sid = document.getElementById("sid").value;
            var type = document.getElementById("type").value;
            var category = document.getElementById("cg").value;
            var url = "forum.html?sid=" + sid + "&type=" + type + "&cg=" + category + "&pn=";
            Jump.url = url;
        };
    </script>
</head>
<body>
	 <!--主题区域-->
     <div class="main">
     
     	<!-- 引入登录窗体 -->
     	<jsp:include page="share/register_login.jsp"></jsp:include>
     
     	<!-- 是否登录 -->
     	<input type="hidden" id="login" value="${sessionScope.user == null ? '0' : '1'}">
     	
         <!--头部区域-->
         <jsp:include page="share/head.jsp"></jsp:include>
         
         <!--导航条-->
         <jsp:include page="share/navi.jsp"></jsp:include>
         
         <!--主体-->
         <div class="forum-main">
            <!--向js暴露sid-->
            <input type="hidden" id="sid" value="${sid}">
            <!-- 排序规则 -->
            <input type="hidden" id="type" value="${type}">
            <!-- 帖子类别 -->
            <input type="hidden" id="cg" value="${cg}">
             <!--位置-->
             <jsp:include page="share/addr.jsp"></jsp:include>
             
             <!-- 引入分页 -->
             <jsp:include page="share/page.jsp"></jsp:include>
             <!--类别导航-->
             <div class="category" id="category">
             </div>
             
             <!--排序方式-->
             <table class="order">
                <tr style="line-height:30px;">
                    <td width="60%">
                         &nbsp;&nbsp;
                         <span class="main-font">排序方式:</span>
                         &nbsp;&nbsp;
                         <c:choose>
	                         <c:when test="${type == null || type == '' || type == 'new' }">
	                         	<span class="active">最新</span>
	                         </c:when>
	                         <c:otherwise>
		                         <a href="forum.html?sid=${sid}" class="blue-font">最新</a>
	                         </c:otherwise>
                         </c:choose>
                         &nbsp;
                         <c:choose>
                         	<c:when test="${type == 'hot'}">
	                         	<span class="active">热门</span>
                         	</c:when>
                         	<c:otherwise>
		                         <a href="forum.html?sid=${sid}&type=hot" class="blue-font">热门</a>
                         	</c:otherwise>
                         </c:choose>
                         &nbsp;
                         <c:choose>
                         	<c:when test="${type == 'elite'}">
	                         	<span class="active">精华</span>
                         	</c:when>
                         	<c:otherwise>
		                         <a href="forum.html?sid=${sid}&type=elite" class="blue-font">精华</a>
                         	</c:otherwise>
                         </c:choose>
                    </td>
                    <td>
                        <span class="main-font">作者</span>
                    </td>
                    <td>
                         <span class="main-font">回复/点击</span>
                    </td>
                    <td>
                         <span class="main-font">最后发表</span>
                    </td>
                </tr>
             </table>
                
                <!--帖子迭代开始-->
                <c:forEach items="${pageBean.records}" var="article">
	                <div class="f_p forum-font">
	                     <div class="p_t">
	                     	<!-- 根据帖子的类型决定图片的路径 -->
		                     <img src="images/type/${article.type}.png" class="forum_icon">
		                     <a href="forum.html?sid=${sid}&cg=${article.category.id}" class="blue-font">
			                     [${article.category.name}]
		                     </a>
		                     <!-- 帖子标题 -->
		                     <a href="view.html?pid=${article.id}&sid=${article.sectionid}" target="_blank">${article.title}</a>
	                     </div>
	                     <div class="a_t">
	                     	<span class="main-font">${article.author.username}</span>
	                        <br>
	                        <span class="gray-font">
	                        	<fmt:formatDate value="${article.createtime}" pattern="YYYY-MM-dd"/>
	                        </span>
	                     </div>
	                     <div class="c_r blue-font">
	                     	${article.replycount} / ${article.clickcount}
	                     </div>
	                     <div class="l_r">
	                     	<a class="main-font" href="#">${article.lastreplyname}</a>
	                        <br>
	                        <span class="gray-font">${article.lastreplytime}</span>
	                     </div>
	                </div>
	            </c:forEach>
                
             <!-- 引入分页 -->
             <jsp:include page="share/page.jsp"></jsp:include>
             
         <!--快速发帖-->
         <div>
             <div class="reply_title">
               	  发表新帖：
             </div>
             <form action="forum/post.html" method="post" id="post_form">
             	<!-- 提交板块id -->
             	<input type="hidden" name="sid" value="${sid}">
	             <table class="reply_table">
	                 <tr class="main-font">
	                     <td>
	                     	主题分类:
	                         <select id="select" name="cg">
	                             <option value="0">请选择主题分类...</option>
	                         </select>
	                     </td>
	                     <td>
	                     	标题:
	                         <input type="text" name="title" id="post_title" style="width:200px;">
	                     </td>
	                     <td>
	                        	 还可以输入<span style="font-weight: bold;" id="title_count">80</span>个字符
	                     </td>
	                 </tr>
	                 <jsp:include page="share/ckeditor.jsp"></jsp:include>
	                 <tr><td>&nbsp;</td></tr>
	             </table>
	            </form>
             </div>
         </div>
     </div>
     
     <!-- 尾巴 -->
     <jsp:include page="share/foot.jsp"></jsp:include>
</body>
</html>