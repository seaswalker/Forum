/*
    帖子类别工具
    a) 加载到select选项
    b) 加载到分类浏览菜单
    c) 标题还可以输入字符数目提示
*/
function loadCg() {
	//a) b)
    var url = "cg/list.html";
    ajax.post(url, true, null, _generate);
    // c)
    var title_input = document.getElementById("post_title");
    if("\v" == "v") {
        title_input.onpropertychange = _judgeCount;
    }else {
        title_input.addEventListener("input", _judgeCount, false);
    }
}
    
//判断剩余字符
function _judgeCount() {
    var title_input = document.getElementById("post_title");
    var span = document.getElementById("title_count");
    var value = title_input.value.trim();
    if(value.length >= 80) {
        title_input.value = value.substring(0, 80);
        span.innerHTML = 0;
    }else {
        span.innerHTML = 80 - value.length;
    }
}

/*
    生成dom元素
*/
function _generate(data) {
    //下拉列表元素
    var select = document.getElementById("select");
    var navi = document.getElementById("category");
    //获取板块id
    var sid = document.getElementById("sid").value;
    //当前的类别
    var cg = document.getElementById("cg").value;
    var option = null;
    var json = eval("(" + data + ")");
    var ele = null;
    //下一个类别导航栏元素
    var next = null;
    var url = "forum.html?sid=" + sid;
    //添加全部选项
    if(cg == null || cg == "") {
        next = document.createElement("span");
        next.className = "cg_active";
    }else {
        next = document.createElement("a");
        next.href = url;
    }
    next.innerHTML = "全部";
    navi.appendChild(next);
    for(var i = 0;i < json.length;i ++) {
        ele = json[i];
        option = document.createElement("option");
        option.value = ele.id;
        option.innerHTML = ele.name;
        select.appendChild(option);
        //设置类别导航栏
        if(ele.id == cg) {
            //如果是当前样式，创建span
            next = document.createElement("span");
            next.className = "cg_active";
        }else {
            next = document.createElement("a");
            next.href = url + "&cg=" + ele.id;
        }
        next.innerHTML = ele.name;
        navi.appendChild(next);
    }
}

/*
    发帖内容验证
*/
function checkPost() {
    var form = document.getElementById("post_form");
    var error = document.getElementById("post_error");
    //设为错误样式
    error.className = "error post_error";
    error.innerHTML = "";
    var cg = form.cg;
    var title = form.title;
    if(cg != undefined && cg.selectedIndex == 0) {
        error.innerHTML = "请选择帖子类别";
        return;
    }
    if(title != undefined && title != "" && title.value.trim() == "") {
        error.innerHTML = "请输入帖子标题";
        return;
    }
    if(ckeditor.document.getBody().getText() == "") {
        error.innerHTML = "请输入帖子内容";
        return;
    }
    form.submit();
 }