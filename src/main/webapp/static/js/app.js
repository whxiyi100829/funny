var Tip = function(dom){
	this.dom = $(dom);
};

Tip.prototype.show = function(duration){
	if(this.hideCountDown)
		clearTimeout(this.hideCountDown);
	
	this.dom.css('opacity',1);
	
	if(duration){
		that = this;
		this.hideCountDown = setTimeout(function(){that.hide()},duration);
	}
	return this;
};

Tip.prototype.text = function(text){
	this.dom.text(text);
	return this;
}

Tip.prototype.hide = function(){
	this.dom.css('opacity',0);
	return this;
}

$(function(){
	window.tip = new Tip($("#global-tip"));
	
	// 列表项点击后打开新页面
	$('ul[id$="-list"]').on('click','li',function(){
		window.open($(this).attr('href'),"_blank");
	});
});

// scrollLoad 插件
$.fn.scrollLoad = function(options){
	
	var status = this.slStatus = {
			loading : false,
			allLoaded : false
	};
	var that = this;
	
	function loadNextPage(){
		if(status.loading || status.allLoaded)
			return;
		status.loading = true;
		tip.text('加载中...').show();
		$('<div></div>').load(options.url,options.params(),function(){
			status.loading = false;
			tip.hide();

			if($(this).children().length == 0){
				status.allLoaded = true;
				return;
			}
			$(this).children().appendTo(that);
		});
	}
	
	$(window).scroll(function() {   
	   if($(window).scrollTop() + $(window).height() == $(document).height()) {
	       loadNextPage();
	   }
	});
	
	loadNextPage();
	
	return this;
};
