<%@ page contentType="text/html;charset=UTF-8"%>
<!-- search -->
<div id="search-wrapper">
	<form action="${ctx}/${curNav}/search">
		<div class="input-group">
			<input type="text" class="form-control" name="q" value="${param.q}">
			<span class="input-group-btn">
		  		<button class="btn btn-default" type="submit">搜索</button>
			</span>
		</div>		
	</form>
</div>
