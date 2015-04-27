var shield = {
    user_id : 0,
    section_id : 0
};

/*
	检查是否登录，没有登录
	显示登录div
*/
function _checkLogin() {
	var isLogin = document.getElementById("login");
	if(isLogin.value == "0") {
		//修改登录div title
		var title = document.getElementById("l_d_t_t");
		title.innerHTML = "您必须先登录才能继续执行本操作:";
		show_login();
		return false;
	}
	return true;
}

//改变帖子类型
function changeType(pid, type) {
	if(_checkLogin()) {
		var url = "view/type.html?pid=" + pid + "&type=" + type + "&sid=" + sid;
		window.location.href = url;
	}
}

//删除帖子
function _deleteArticle(pid) {
	if(_checkLogin() && _confirm("您确定删除此帖子及以下所有回复?")) {
		window.location.href = "view/delete.html?pid=" + pid + "&sid=" + sid;
	}
}

/*
                      回复
  isReply --如果是回复楼层true
      楼主 false
  span --点击的按钮所在的span
*/
function _reply(isReply, span) {
	if(_checkLogin()) {
        if(isReply) {
            //获取同楼层下回复所在的td
            var td = span.parentNode.parentNode.previousElementSibling.children[1];
            //应该引用的内容所在元素
            var contentArea;
            if(td.children[0] == undefined) {
                contentArea = td;
            }else if(td.children[0].nodeName == "BLOCKQUOTE") {
                contentArea = td.children[1];
            }else {
                contentArea = td.children[0];
            }
            //设置到ckeditor的引用文字
            var quote = "<blockquote>" + contentArea.innerHTML + "</blockquote>";
            CKEDITOR.instances.ckeditor.setData(quote);
        }
		document.getElementById("reply_table").scrollIntoView();
	}
}

//删除回复
function _deleteReply(rid, pid) {
	if(_checkLogin()) {
		var url = "reply/delete.html?rid=" + rid + "&pid=" + pid + "&sid=" + sid;
		window.location.href = url;
	}
}

//帖子修改
function _editReply(pid) {
	if(_checkLogin()) {
		var url = "view/edit.html?pid=" + pid + "&sid=" + sid;
		window.location.href = url;
	}
}

/**
 * [[显示小黑屋天数窗]]
 * @param {[[Number]]} user_id    [[被封禁的用户的id]]
 * @param {[[Number]]} section_id [[被哪个板块封禁]]
 */
function show_shield(user_id, section_id) {
    shield.section_id = section_id;
    shield.user_id = user_id;
    document.getElementById("shield_window").style.display = "block";
}

/**
 * [[关闭封禁天数窗]]
 */
function close_shield() {
    document.getElementById("shield_days_value").value = "";
    document.getElementById("shield_error").innerHTML = "";
    document.getElementById("shield_window").style.display = "none";
}

/**
 * [[执行封禁]]
 */
function do_shield() {
    var input = document.getElementById("shield_days_value");
    var input_value = input.value.trim();
    var error_span = document.getElementById("shield_error");
    var pattern = new RegExp("(^0{0,1}[1-9])|(^10$)");
    if(input_value == "") {
        error_span.innerHTML = "请输入天数";
        input.focus();
        return;
    }
    if(!input_value.match(pattern)) {
        error_span.innerHTML = "格式非法";
        input.focus();
    }else {
        
    }
}