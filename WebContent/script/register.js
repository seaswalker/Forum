//全局--存放注册验证
var Register = {
    elements : new Array(),
    usernamePassed : true,
    passwordPassed : true,
    repasswordPassed : true,
    emailPassed : true,
    verifycodePassed : true
};

//改变验证码
function image(img) {
	img.src = "image.jsp?" + Math.random();
}

//注册函数入口
function register() {
    var form = document.getElementById("register_form");
    var username = form.username;
    var password = form.password;
    var repassword = form.repassword;
    var email = form.email;
    var verifycode = form.verifycode;
    
    //需要验证的元素的数组
    Register.elements.push(username);
    Register.elements.push(password);
    Register.elements.push(repassword);
    Register.elements.push(email);
    Register.elements.push(verifycode);
    
    //转为对象数组
    _toElementsArray(Register.elements);
    
    //设置监听器
    _initListener(Register.elements);
};

//由所给的元素封装为Element对象
function _toElementsArray(elements) {
    var ele;
    var error_span;
    //封装好的对象
    var element;
    for(var i = 0;i < elements.length;i ++) {
        ele = elements[i];
        error_span = _getErrorSpan(ele);
        element = new Element(ele, ele.name + "Focus", ele.name + "Blur", error_span.firstChild.nodeValue,      error_span.className, error_span);
        elements[i] = element;
    }
}

/*
 element 需要检验的元素
 focus 获得焦点时执行的函数名
 blur 失去焦点时执行的函数名
 initial_message 错误信息span初始的信息
 error_span 错误信息显示span
 initial_class初始的class
*/
function Element(element, focus, blur, initial_message, initial_class, error_span) {
    this.element = element;
    this.focus = focus;
    this.blur = blur;
    this.initial_message = initial_message;
    this.initial_class = initial_class;
    this.error_span = error_span;
}

//获得待验证元素的相应错误信息显示元素，必要时应重写
function _getErrorSpan(element) {
    return element.parentElement.nextElementSibling.nextElementSibling.firstElementChild;
}

//监听器辅助函数
function _helper(e, isBlur) {
    return function() {
        if(isBlur) {
            eval(e.blur + "(e)");
        }else {
            eval("_focus (e)");
        }
    }
}

//设置失去和获得焦点的监听器
function _initListener(elements) {
    var object;
    for(var i = 0;i < elements.length;i ++) {
        object = elements[i];
        object.element.onblur = _helper(object, true);
        object.element.onfocus = _helper(object, false);
    }
}

//显示错误信息
function _showRegisterError(object, message) {
    object.error_span.innerHTML = message;
    object.error_span.className += " error";
    return false;
}

//检查用户名
function usernameBlur(object) {
    var value = object.element.value.trim();
    var pattern = new RegExp("\\s");
    if(value == "") {
        Register.usernamePassed = _showRegisterError(object, "请输入用户名");
    }else if(value.length < 3 || value.length > 10) {
        Register.usernamePassed = _showRegisterError(object, "长度在3-10之间");
    }else if(value.search(pattern) != -1) {
        Register.usernamePassed = _showRegisterError(object, "用户名不能含有空白字符");
    }else {
        object.error_span.innerHTML = "正在检查用户名...";
        var url = "register/us.html?username=" + value;
        getResponseData(url, false, false, "get", _checkUsername, object, null);
    }
}

//检查用户名是否重复
function _checkUsername(data, object) {
    var json = eval("(" + data + ")");
    if(json.result == 1) {
        Register.usernamePassed = _showRegisterError(object, json.message);
    }else {
        object.error_span.innerHTML = "";
        Register.usernamePassed = true;
    }
}

//内部方法，检查密码
function _checkPassword(object, isPassword) {
    var value = object.element.value.trim();
    var pattern = new RegExp("^\\S{5,}$");
    if(value.length < 5) {
        if(isPassword) {
            Register.passwordPassed = _showRegisterError(object, "最少5个字符");
        }else {
            Register.repasswordPassed = _showRegisterError(object, "最少5个字符");
        }
        return false;
    }else if(!value.match(pattern)) {
        if(isPassword) {
            Register.passwordPassed = _showRegisterError(object, "最多10个字符");
        }else {
            Register.repasswordPassed = _showRegisterError(object, "最多10个字符");
        }
        return false;
    }
    return true;
}

//检查密码
function passwordBlur(object) {
    if(_checkPassword(object, true)) {
        object.error_span.innerHTML = "";
        Register.passwordPassed = true;
    }
}

//检查确认密码
function repasswordBlur(object) {
    if(!_checkPassword(object, false)) {
        return;
    } 
    var repassword = object.element.value;
    var password = document.forms[1].password.value;
    if(repassword != password) {
        Register.passed = _showRegisterError(object, "密码不一致");
    }else {
        object.error_span.innerHTML = "";
        Register.repasswordPassed = true;
    }
}

//检查邮箱
function emailBlur(object) {
    var email = object.element.value.trim();
    if(email == "") {
        Register.passed = _showRegisterError(object, "请输入电子邮箱");
    }else {
        var pattern = new RegExp("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.[a-zA-Z0-9_-])+");
        if(!email.match(pattern)) {
            Register.passed = _showRegisterError(object, "邮箱格式错误");
        }else {
            object.error_span.innerHTML = "";
            Register.emailPassed = true;
        }
    }
}

//检查验证码
function verifycodeBlur(object) {
    var verifycode = object.element.value.trim();
    if(verifycode == "") {
        Register.passed = _showRegisterError(object, "请输入验证码");
    }else {
        var url = "register/verify.html?verifycode=" + verifycode;
        getResponseData(url, false, false, "get", _checkVerifyCode, object, null);
    }
}

//向服务器验证验证码
function _checkVerifyCode(data, object) {
    var json = eval("(" + data + ")");
    if(json.result == 0) {
        Register.passed = _showRegisterError(object, json.message);
    }else {
        object.error_span.innerHTML = "";
        Register.verifycodePassed = true;
    }
}

//获得焦点
function _focus(object) {
    object.error_span.className = object.initial_class;
    object.error_span.innerHTML = object.initial_message;
}

//注册验证，调用全部验证方法
function check() {
    var element;
    for(var i = 0;i < Register.elements.length;i ++) {
        element = Register.elements[i];
        //执行每一个校验函数
        eval(element.blur + "(element)");
    }
    return Register.usernamePassed && Register.passwordPassed && Register.repasswordPassed && Register.emailPassed && Register.verifycodePassed;
}
