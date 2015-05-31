<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	pageContext.setAttribute("basePath", basePath);
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
<script src="script/login.js"></script>
<script src="script/ajax.js"></script>
<script src="script/ListenerUtil.js"></script>
<title>重置密码</title>
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
				<span class="strong-font">重置密码：</span> <a
					href="javascript:show_login();" class="blue-font"
					style="float: right; margin-right: 10px;">已有帐号?现在登录</a>
			</div>

			<div>
				<!-- 校验 -->
				<script type="text/javascript">
                    function check(form) {
                        var password = form.password;
                        var cp_error = document.getElementById("cp_error");
                        var password_value = password.value.trim();
                        if(password_value == "") {
                            cp_error.innerHTML = "请输入新密码";
                            password.focus();
                            return false;
                        }
                        if(password_value.length < 5) {
                            cp_error.innerHTML = "密码最少5个字符";
                            password.focus();
                            return false;
                        }
                        var repassword = form.repassword;
                        var repassword_value = repassword.value.trim();
                        if(repassword_value != password_value) {
                            cp_error.innerHTML = "两次密码不一致";
                            repassword.focus();
                            return false;
                        }
                        return true;
                    }
				</script>
				<form action="user/cp/save.html" onsubmit="return check(this);"
					method="post" id="cp_form">
					<input type="hidden" name="userid" value="${verify.userid}">
					<input type="hidden" name="id" value="${verify.id}">
					<table align="center" id="register-table">
						<tr style="color: #336699;">
							<td>用户名:</td>
							<td>${verify.username}</td>
						</tr>
						<tr>
							<td>请输入新密码:</td>
							<td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td>确认密码:</td>
							<td><input type="password" name="repassword"></td>
						</tr>
						<tr>
							<td style="text-align: center;">
								<input type="submit" value="提交">
							</td>
							<td>
							    <span class="error" id="cp_error"></span>
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