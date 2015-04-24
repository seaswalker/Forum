<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!--居中div-->
   	<div class="l_d" id="hidden_div">
   	<!-- 注册组件的登录函数 -->
	<script type="text/javascript">
		//显示登录界面
		function show_login() {
			document.getElementById('hidden_div').style.display = 'block';
		}
		function hide_login() {
			document.getElementById('hidden_div').style.display = 'none';
		}
    	function register_login() {
   		    var form = document.getElementById("register_login");
            var username = form.username.value.trim();
            var password = form.password.value.trim();
   		    var url = "login.html";
   	        var autologin = form.autologin.checked ? "1" : "0";
   	        var params = "username=" + username + "&password=" + password + "&autologin=" + autologin;
   	        ajax.post(url, false, params, _re_lo_result);
    	 }
        //处理结果
        function _re_lo_result(data) {
            var span = document.getElementById("re_lo_error");
            var json = eval("(" + data + ")");
            if(json.result == 1) {
            	//刷新
                window.location.reload();
            }else {
                span.innerHTML = json.message;
            }
        }
	</script>
            <div class="l_d_t" id="l_d_t">
               	<span id="l_d_t_t">欢迎登录:</span>
                <!--关闭按钮-->
           <span class="l_d_t_btn" onclick="hide_login();">X</span>
       </div>
       <div class="l_d_c">
       		<form id="register_login">
             <table align="center" width="60%">
                 <tr>
                     <td>用户名:</td>
                     <td>
                         <input type="text" name="username">
                         <a href="register.html" class="l_d_r">立即注册</a>
                     </td>
                 </tr>
                 <tr>
                     <td>密码:</td>
                     <td>
                         <input type="password" name="password">
                     </td>
                 </tr>
                 <tr>
                     <td></td>
                     <td>
                         <input type="checkbox" name="autologin" id="autologin">
                         <label for="autologin" style="font-size:12px;">自动登录</label>
                     </td>
                 </tr>
                 <tr>
                     <td>
                         <input type="button" value="登录" onclick="register_login();">
                     </td>
                     <td>
                         <span class="error" id="re_lo_error"></span>
                     </td>
                 </tr>
             </table>       
         </form>            
       </div>
   </div>