<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<tr>
	<td colspan="3">
		<!--富文本编辑器--> 
		<textarea id="ckeditor" name="content"></textarea> 
		<script type="text/javascript">
			ckeditor = CKEDITOR.replace("ckeditor");
			var login = document.getElementById("login");
			//没有登录，禁止发帖
			if (login.value == "0") {
				CKEDITOR.on('instanceReady', function(ev) {
					editor = ev.editor;
					editor.setReadOnly(true);
				});
			}
		</script>
	</td>
</tr>
<tr>
	<td>
		<img src="images/post.png" class="post_btn" id="post_btn"onclick="checkPost();"> &nbsp;
		 <!-- 错误信息 --> 
		 <span class="error post_error" id="post_error"></span> 
		 <script type="text/javascript">
			//如果没有登录，显示提示信息
			var login = document.getElementById("login");
			var btn = document.getElementById("post_btn");
			var span = document.getElementById("post_error");
			if (login.value == "0") {
				span.className = "p_l_tips";
				span.innerHTML = "您不能发表帖子，请先<a href='javascript:show_login();'>登录</a>或"
						+ "<a href='register.html'>注册</a>";
				btn.onclick = null;
			}
		</script>
	</td>
</tr>