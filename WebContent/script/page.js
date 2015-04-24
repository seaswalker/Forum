//分页跳转链接
//Jump对象，封装url，需要每个分页页面设置url
var Jump = {
    url : null
};

/*
    页面跳转函数
    pageCount代表总页数
*/
function go(pageCount) {
    var pageCode = document.getElementById("pagecode");
    var value = pageCode.value;
    var pattern = new RegExp("^(-)*[0-9]+$");
    if(!value.trim().match(pattern)) {
        pageCode.value = "";
        pageCode.focus();
    }else {
        var pn = 1;
        if(value > pageCount) {
            pn = pageCount;
        }else if(value < 1) {
            pn = 1;
        }else {
            pn = value;
        }
        //发送请求
        jump(pn);
    }
}

/*
      链接函数
      代替分页中的链接
*/
function jump(pn) {
    var url = Jump.url + pn;
    window.location.href = url;
}