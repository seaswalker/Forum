    //自定义ajax工具类
    var ajax = {
        "getResponseData" : getResponseData,
        "post" : post,
        "get" : get
    }

    //创建异步请求对象
    function createXMLHttpRequest() {
        var xmlHq;
        if(window.XMLHttpRequest) {
            xmlHq = new XMLHttpRequest();
        }else {
            //IE7以下
            xmlHq = new ActiveXObject("Microsoft.XMLHTTP");
        }
        return xmlHq;
    }
    
    //请求并返回内容,并且交由指定的回掉函数处理
    //async为true或者空表示异步false同步
    //noCache为true---无缓存
    //method 提交的方式
    //content提交的内容
    function getResponseData(url, async, noCache, method, callbackFunction, param, content) {
        var xmlHq = createXMLHttpRequest();
        xmlHq.open(method, url, async);
        if(noCache) {
            //必须在open之后
            xmlHq.setRequestHeader("If-Modified-Since","0");
        }
        if(method == "post") {
        	xmlHq.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
        }
        if(callbackFunction != null && callbackFunction != undefined) {
	        xmlHq.onreadystatechange = function() {
	            if(xmlHq.readyState == 4 && xmlHq.status == 200) {
	                    callbackFunction(xmlHq.responseText, param);
	                }
	        };
        }
        //url += "&fresh=" + Math.random();
        xmlHq.send(content);
    }

    //post方式提交请求,async代表异步(true)
    function post(url, async, params, callbackFunction) {
        getResponseData(url, async, false, "post", callbackFunction, null, params);
    }

    function get(url, async, params, callbackFunction) {
        getResponseData(url, async, false, "get", callbackFunction, null, params);
    }
