	/*
	 * 个人信息设置相关函数 
	 */
	function _check_mp(form) {
        //检查密码修改表单
        var error = document.getElementById("mp_error");
        var old_password = form.old_password;
        if(old_password.value.trim() == "") {
            error.innerHTML = "请输入旧密码";
            old_password.focus();
            return false;
        }
        var password = form.password;
        if(password.value.trim() == "") {
            error.innerHTML = "请输入新密码";
            password.focus();
            return false;
        }
        var repassword = form.repassword;
        if(repassword.value.trim() == "") {
            error.innerHTML = "请输入确认密码";
            repassword.focus();
            return false;
        }
        if(repassword.value.trim() != password.value.trim()) {
            error.innerHTML = "两次密码不一致";
            repassword.focus();
            return false;
        }
        return true;
    }    
    
    //显示选择的图片
    function _show_ci(file) {
        var file_name = file.value;
        if(file_name != "") {
            var error = document.getElementById("ma_error");
            if(!_isImage(file_name)) {
                error.innerHTML = "只支持jpg/jpeg/png/bum/gif格式";
            }else {
                var choose_img = document.getElementById("choose_img");
                //上传
                $.ajaxFileUpload({
                    url: 'upload.html', 
                    type: 'post',
                    secureuri: false,
                    fileElementId: 'file',
                    dataType: 'HTML',
                    success: function(data, status){  
                        var json = eval("(" + data + ")");
                        if(json.result == "0") {
                            error.innerHTML = json.message;
                        }else if(json.result == "1") {
                            $("#choose_img").empty().append($("<img src='" + json.message + "'>"));
                            $("#new_avatar").val(json.message);
                        }
                    },
                    error: function(data, status, e){ 
                        console.log(e);
                    }
                });
            }
        }
    }
    
    //获取选择文件的扩展名
    function _getExtend(file_name) {
        return file_name.substring(file_name.lastIndexOf(".") + 1, file_name.length);
    }
    
    //是否是图片
    function _isImage(file_name) {
        var extend = _getExtend(file_name);
        return (extend == "jpg" || extend == "jpeg" || extend == "png" || extend == "gif" || extend == "bmp");
    }
    
    //检查头像修改表单
    function _check_ma(form) {
        var avatar = form.avatar.value;
        var error = document.getElementById("ma_error");
        if(avatar == "") {
            error.innerHTML = "请选择新头像";
            return false;
        }
        if(!_isImage(avatar)) {
            error.innerHTML = "只支持jpg/jpeg/png/bum/gif格式";
            return false;
        }
        return true;
    }
    
    function _helper(i, tabs, divs) {
        return function() {
            for(var j = 0;j < tabs.length;j ++) {
                if(j != i) {
                    tabs[j].className = "";
                    divs[j].style.display = "none";
                }else {
                    tabs[j].className = "active_tab";
                    divs[j].style.display = "block";
                }
            }  
        }
    }
    
    //添加选项卡事件处理
    function _tab_switch() {
        var tabs = document.getElementsByName("tab");
        var divs = document.getElementsByName("tab_div");
        var tab, div;
        for(var i = 0;i < tabs.length;i ++) {
            tab = tabs[i];
            tab.onclick = _helper(i, tabs, divs);
        }
    }