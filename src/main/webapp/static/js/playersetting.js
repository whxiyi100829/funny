
function setplayer(options){
	var videoPath = options.videopath;
	var ctx = options.ctx;
	var flashvars={
		f:videoPath,
		c:0,
		b:0,
		//d:'${ctx}/resource/ads/pause_ad2.swf|http://www.ckplayer.com/down/adv6.1_1.swf',/*多个用|隔开*/
		//l:'${ctx}/resource/ads/pause_ad1.swf|http://www.ckplayer.com/down/adv6.1_2.swf',//前置广告，swf/图片/视频，多个用竖线隔开，图片和视频要加链接地址
		//r:'',//前置广告的链接地址，多个用竖线隔开，没有的留空
		//t:'5|5'//视频开始前播放swf/图片时的时间，多个用竖线隔开
		};
	var params={bgcolor:'#FFF',allowFullScreen:true,allowScriptAccess:'always'};
	CKobject.embedSWF(ctx+'/static/js/ckplayer/ckplayer.swf','screen','ckplayer_screen','600','400',flashvars,params);
	/*
	CKobject.embedSWF(播放器路径,容器id,播放器id/name,播放器宽,播放器高,flashvars的值,其它定义也可省略);
	下面三行是调用html5播放器用到的
	*/
	var video=[videoPath+'->video/mp4'];
	var support=['iPad','iPhone','ios','android+false','msie10+false'];
	CKobject.embedHTML5('video','ckplayer_screen',320,215,video,flashvars,support);
}