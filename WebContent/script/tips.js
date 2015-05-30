	/**
	 * 弹出提示、错误、成功窗
	 * 依赖jquery
	 */
	 var tips = {
	 	//是否初始化
	 	isInit: false
	 }
	 
	 /**
	  * 初始化，加载所需的jquery和css
	  */
	 function _init() {
	 	var css = document.createElement("link");
	 	css.rel = "stylesheet";
	 	css.href = "css/tips.css";
	 	var head = document.getElementsByTagName("head")[0];
	 	head.appendChild(css);
	 	tips.isInit = true;
	 }
	/**
	 * [show_messgae 显示提示窗体]
	 * @param  {[type]} message [description]
	 */
	function show_messgae(message) {
		_show_window_div(message, "message_window");
	}

	/**
	 * [show_error 显示错误提示窗]
	 * @param  {[type]} message [错误信息]
	 */
	function show_error(message) {
		_show_window_div(message, "error_window");
	}

	/**
	 * [show_success 正确信息提示窗]
	 * @param  {[type]} message [信息]
	 */
	function show_success(message) {
		_show_window_div(message, "success_window");
	}

	/**
	 * [_show_window_div 显示提示窗]
	 * @param  {[字符串]} message    [信息]
	 * @param  {[字符串]} class_name [提示窗的class，从而决定了类型]
	 */
	function _show_window_div(message, class_name) {
		if(!tips.isInit) {
			_init();
		}
		var window_div = _get_window_div();
		window_div.innerHTML = message;
		//动态根据文本长度计算信息条宽度
		var width = 300 / 12 * message.length;
		window_div.style.width = width + "px";
		window_div.style.marginLeft = "-" + width / 2 + "px";
		window_div.className = class_name;
		window_div.style.display = "block";
		_close_window(window_div, 3);
	}

	/**
	 * [_get_message_window 获得信息提示div]
	 * @return {[jquery对象]} [description]
	 */
	function _get_window_div() {
		var wd = document.getElementById("window_div");
		var window_div;
		if(wd != null && wd != undefined) {
			window_div = wd;
		}else {
			window_div = document.createElement("div");
			window_div.id = "window_div";
			document.body.appendChild(window_div);
		}
		return window_div;
	}

	/**
	 * [_close_window 定时关闭窗口]
	 * @param  {[object]} object  [需要关闭的窗体]
	 * @param  {[ Number]} seconds [等待的秒数]
	 * @return {[void]}         [description]
	 */
	function _close_window(object, seconds) {
		setTimeout(
			function(){
				object.style.display = "none";
			},
			seconds * 1000
		);
	}