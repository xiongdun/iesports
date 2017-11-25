<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="navbar-inner">
      	<div class="container-fluid">
        		<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
			<span class="icon-bar"></span>
          		<span class="icon-bar"></span>
          		<span class="icon-bar"></span>
        		</a>
        		<a class="brand" href="/carport/plat/index#stats-index">车位管理系统</a>
         	<div class="btn-group pull-right">
			<a class="btn" href="/carport/user/detail?id=${userInfo.user_id }">
				<i class="icon-user"></i>${userInfo.user_name}</a>
           	<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
             		<span class="caret"></span>
           	</a>
           	<ul class="dropdown-menu">
		  		<li><a href="/carport/user/detail?id=${userInfo.user_id }#user">个人资料</a></li>
             		<li class="divider"></li>
             		<li><a href="/carport/user/logout" onclick="JavaScript:return confirm('确定退出吗？')">退出</a></li>
           	</ul>
         	</div>
		<div class="nav-collapse">
			<ul class="nav">
				<li><a href="/carport/plat/index#stats-index">首页</a></li>
            	<li class="dropdown">
            		<a href="#" class="dropdown-toggle" data-toggle="dropdown">用户 <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="/carport/user/toadd#user">新建用户</a></li>
						<li class="divider"></li>
						<li><a href="/carport/user/all#user">管理用户</a></li>
					</ul>
	  			</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">角色 <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="/carport/role/toadd#role">新建角色</a></li>
						<li class="divider"></li>
						<li><a href="/carport/role/all#role">管理角色</a></li>
					</ul>
	  			</li>
	  			<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">部门 <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="/carport/apartment/toadd#apartment">新建部门</a></li>
						<li class="divider"></li>
						<li><a href="/carport/apartment/all#apartment">管理部门</a></li>
					</ul>
	  			</li>
	  			<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">机构 <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="/carport/org/toadd#org">新建机构</a>
						</li><li class="divider"></li>
						<li><a href="/carport/org/all#org">管理机构</a></li>
					</ul>
	  			</li>
	  			<li><a href="/carport/carset/all#carport">车位</a></li>
	  			<li><a href="/carport/stats/index#stats-index">统计</a></li>
	  		</ul>
		</div>
	</div>
</div>