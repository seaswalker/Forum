	/*
	 * 事件监听工具类
	 * object是添加事件的对象,handler是指处理函数
	 * 此函数不应在window.onload函数体内被调用，
	 * 因为此时已经load完了，再添加onload事件还有什么意义
	 */ 
	var ListenerUtil = {
		addListener : function(object, listenerName, handler,  useCapture) {
			if(object.addEventListener) {
				object.addEventListener(listenerName, handler, useCapture);
			}else {
				object.attachEvent("on" + listenerName, handler);
			}	
		}
	}