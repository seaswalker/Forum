//登录对象
var Login = {
    username : null,
    password : null,
    span : null,
    autologin : null,
    e_w : null
};

//登录验证
function login() {
    var form = document.getElementById("head_login_form");
    Login.username = form.username;
    Login.password = form.password;
    Login.span = document.getElementById("login-error");
    Login.autologin = form.autologin;
    
    Login.username.onblur = function() {
        checkUsername();  
    };
    Login.password.onblur = function() {
        checkPassword();
    };
    Login.username.onfocus = function() {
        Login.span.innerHTML = "";  
    };
    Login.password.onfocus = function() {
        Login.span.innerHTML = "";  
    };
};

//简单检查用户名
function checkUsername() {
    if(Login.username.value.trim() == "") {
        Login.span.innerHTML = "请输入用户名";
        return false;
    }
    return true;
}

function checkPassword() {
    if(Login.password.value.trim() == "") {
        Login.span.innerHTML = "请输入密码";
        return false;
    }
    return true;
}

/*
    处理登录结果
*/
function _loginResult(data) {
    var json = eval("(" + data + ")");
    if(json.result == 1) {
        //刷新页面
        window.location.reload();
    }else {
        //弹出提示窗口
        _showLoginError(json.message);
    }
}

/*
    弹出错误窗体
*/
function _showLoginError(message) {
    Login.span.innerHTML = "";
    var e_w = document.getElementById("e_w");
    Login.e_w = e_w;
    e_w.innerHTML = message;
    e_w.style.display = "block";
    //3秒关闭
    setTimeout(_closeError, 3000);
}

/*
    关闭错误窗体
*/
function _closeError() {
    Login.e_w.innerHTML = "";
    Login.e_w.style.display = "none";
}

function checkLogin() {
    if(checkUsername() && checkPassword()) {
        //显示提示信息
        Login.span.innerHTML = "正在登录...";
        var url = "login.html";
        var autologin = Login.autologin.checked ? "1" : "0";
        var params = "username=" + Login.username.value + "&password=" + Login.password.value + "&autologin=" + autologin;
        ajax.post(url, false, params, _loginResult);
    }
}