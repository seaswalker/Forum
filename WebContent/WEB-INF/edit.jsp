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
	<title>帖子修改</title>
	<link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/head.css">
    <link rel="stylesheet" href="css/edit.css">
    <script src="ckeditor/ckeditor.js"></script>
    <script type="text/javascript">
    	var ckeditor;
    	//检查表单
    	function _check_reply(form) {
    		if(ckeditor.document.getBody().getText() == "") {
				alert("请输入帖子内容");
				return false;
    		}
    		return true;
    	}
    </script>
</head>
<body>
	<!--主题区域-->
    <div class="main">
        <!--头部区域-->
		<jsp:include page="share/head.jsp"></jsp:include>
        
        <!--主体区域-->
        <div class="edit-main">
        	<form action="view/save.html" method="post" onsubmit="_check_reply(this);">
        	<input type="hidden" name="pid" value="${article.id}">
        	<input type="hidden" name="sid" value="${article.sectionid}">
        	<table class="r_e_t">
        		<tr>
        			<td style="text-align: center;">
        				<h3>帖子内容:</h3>
        			</td>
        		</tr>
        		<tr>
        			<td>
        				<textarea name="content" id="content">${article.content}</textarea>
      					<script type="text/javascript">
							ckeditor = CKEDITOR.replace("content");
						</script>
        			</td>
        		</tr>
        		<tr>
        			<td style="text-align: center;">
        				<br>
        				<input type="submit" value="保存">
        				&nbsp;&nbsp;
        				<input type="button" value="返回" onclick="window.history.back();">
        			</td>
        		</tr>
        	</table>
        	</form>
        	<div>&nbsp;</div>
        </div>
    </div>
   
   <!-- 引入尾巴 -->
   <jsp:include page="share/foot.jsp"></jsp:include>
</body>
</html>