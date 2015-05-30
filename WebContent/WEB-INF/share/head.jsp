<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 是否已经登录 -->
<c:set var="isLogined" value="${sessionScope.user != null}" scope="page"></c:set>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>头部区域</title>
<!-- 没有登录才需要引入 -->
<c:if test="${!pageScope.isLogined}">
	<script src="script/ajax.js"></script>
	<script src="script/login.js"></script>
	<script src="script/ListenerUtil.js"></script>
	<script src="script/tips.js"></script>
	<!-- 注册登录事件 -->
	<script type="text/javascript">
		ListenerUtil.addListener(window, "load", login, false);
	</script>
</c:if>
</head>
<body>
	<div class="head-title">
		<!-- 错误提示窗体 -->
		<div id="e_w"></div>

		<div class="title-logo">
			<img src="images/logo.png">
		</div>
		<div class="title-font">Forum of skywalker</div>

		<c:if test="${pageScope.isLogined}">
			<!--显示个人信息(已登录)-->
			<div class="p_info">
				<table align="right">
					<tr>
						<td class="tr_set"><span class="strong-font">${sessionScope.user.username}</span>
						</td>
						<td class="main-font">&nbsp;|&nbsp;</td>
						<td><a href="logout.html" class="main-font">退出</a></td>
						<td>&nbsp;</td>
						<td rowspan="2"><img src="${sessionScope.user.avatar}"
							style="width: 80xp; height: 80px;"></td>
					</tr>
					<tr>
						<td class="main-font tr_set">
							<!-- 后台入口 --> <c:if
								test="${sessionScope.user.isAdmin || sessionScope.user.isManager}">
								<a href="admin/index.html">进入后台</a>
							</c:if>
						</td>
						<td class="main-font">&nbsp;|</td>
						<td class="tr_set main-font"><a href="user/set.html">设置</a></td>
					</tr>
				</table>
			</div>
		</c:if>

		<c:if test="${!pageScope.isLogined}">
			<!-- 引入找回密码 -->
			<jsp:include page="find_password.jsp"></jsp:include>
			<!--登录部分-->
			<div class="title-login">
				<form action="login.html" method="post" id="head_login_form">
					<table class="main-font">
						<tr>
							<td>用户名:</td>
							<td><input type="text" name="username"></td>
							<td class="border"><input type="checkbox" name="autologin"
								id="checkbox"> <label for="checkbox">自动登录&nbsp;</label>
							</td>
							<td><a href="javascript:void(0);" onclick="show_find_password();">找回密码</a></td>
						</tr>
						<tr>
							<td>密码:</td>
							<td><input type="password" name="password"></td>
							<td class="border"><input type="button" value="登录"
								onclick="checkLogin();" style="width: 72px;"></td>
							<td><a href="register.html">立即注册</a></td>
						</tr>
						<tr>
							<td></td>
							<td colspan="3"><span class="error" id="login-error"></span>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</c:if>
	</div>
</body>
</html>