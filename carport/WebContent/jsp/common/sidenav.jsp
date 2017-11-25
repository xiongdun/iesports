<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="well sidebar-nav">
   	<ul class="nav nav-list">
	    <li class="nav-header"><i class="icon-wrench"></i> 管理</li>
		<li class="user"><a href="/carport/user/all#user">用户</a></li>
		<li class="role"><a href="/carport/role/all#role">角色</a></li>
		<li class="apartment"><a href="/carport/apartment/all#apartment">部门</a></li>
		<li class="org"><a href="/carport/org/all#org">机构</a></li>
		<li class="carport"><a href="/carport/carset/all#carport">车位</a></li>
		<li class="nav-header"><i class="icon-signal"></i> 统计</li>
		<li class="stats-index"><a href="/carport/stats/index#stats-index">通用</a></li>
		<li class="stats-carhistory"><a href="/carport/stats/carhistory#stats-carhistory">车位</a></li>
		<li class="stats-user"><a href="/carport/stats/user#stats-user">用户</a></li>
		<li class="stats-visitor"><a href="/carport/stats/visitor#stats-visitor">访问者</a></li>
		<li class="nav-header"><i class="icon-user"></i> 资料</li>
		<li class="user-info"><a href="/carport/user/detail?id=${userInfo.user_id }#user-info">我的资料</a></li>
		<li class="settings"><a href="/carport/plat/tosettings#settings">设置</a></li>
		<li><a href="/carport/user/logout" onclick="JavaScript:return confirm('确定退出吗？')">退出</a></li> 
	</ul>
</div>