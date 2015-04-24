	/**
	 * 弹出提示、错误、成功窗
	 * 依赖jquery
	 */
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
		var window_div = _get_window_div();
		window_div.html(message).addClass(class_name).show();
		_close_window(window_div, 3);
	}

	/**
	 * [_get_message_window 获得信息提示div]
	 * @return {[jquery对象]} [description]
	 */
	function _get_window_div() {
		var wd = $("#window_div");
		var window_div;
		if(wd.length > 0) {
			window_div = wd;
		}else {
			window_div = $("<div id='window_div'></div>");
			$("body").append(window_div);
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
				object.hide();
			},
			seconds * 1000
		);
	}