<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--居中div-->
<div class="l_d" id="find_password_div">
	<!-- 对输入的邮箱以及用户名进行验证 -->
	<script type="text/javascript">
		//显示登录界面
		function show_find_password() {
   		    var form = document.getElementById("find_password_form");
   		    form.username.value = "";
   		    form.email.value = "";
   		    var error_span = document.getElementById("find_password_error");
   		    error_span.innerHTML = "";
			document.getElementById('find_password_div').style.display = 'block';
		}
		function hide_find_password() {
			document.getElementById('find_password_div').style.display = 'none';
		}
    	function find_password() {
   		    var form = document.getElementById("find_password_form");
   		    //错误显示
   		    var error_span = document.getElementById("find_password_error");
            var username = form.username;
            var email = form.email;
            var email_value = email.value.trim();
            if(email_value == "") {
            	error_span.innerText = "请输入邮箱";
            	email.focus();
            	return false;
            }
            error_span.style.color = "black";
           	error_span.innerText = "正在校验...";
   		    var url = "user/find.html";
   	        var params = "username=" + username.value.trim() + "&email=" + email_value;
   	        ajax.post(url, false, params, _handle_find_password);
    	 }
        //处理结果
        function _handle_find_password(data) {
            var json = eval("(" + data + ")");
            if(json.result == 1) {
            	hide_find_password();
            	show_success(json.message);
            }else {
	            var span = document.getElementById("find_password_error");
	            span.style.color = "red";
                span.innerHTML = json.message;
            }
        }
	</script>
	<div class="l_d_t" id="l_d_t">
		<span id="l_d_t_t">找回密码:</span>
		<!--关闭按钮-->
		<span class="l_d_t_btn" onclick="hide_find_password();">X</span>
	</div>
	<div class="l_d_c">
		<form id="find_password_form">
			<table align="center" width="60%">
				<tr>
					<td>用户名:</td>
					<td style="width: 212px;">
						<input type="text" name="username"> 
					</td>
				</tr>
				<tr>
					<td><span style="color: red;">*</span>邮箱:</td>
					<td><input type="text" name="email"></td>
				</tr>
				<tr>
					<td>
						<input type="button" value="提交" onclick="find_password();">
					</td>
					<td><span class="error" id="find_password_error"></span></td>
				</tr>
			</table>
		</form>
	</div>
</div>