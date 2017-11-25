<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/jsp/common/top.jsp"></jsp:include>
<style type="text/css">
	.pagination a:hover, .pagination .active a {
	background-color: #eceaea;
}
</style>
</head>
<body>
	<div class="navbar navbar-fixed-top">
      	<jsp:include page="/jsp/common/nav.jsp"></jsp:include>
    </div>

    <div class="container-fluid">
      	<div class="row-fluid">
        	<div class="span3">
          		<jsp:include page="/jsp/common/sidenav.jsp"></jsp:include>
        	</div>
        	<div class="span9">
		  		<div class="row-fluid">
					<div class="page-header">
						<h1>设置管理 <small>Settings...</small></h1>
					</div>
					<table class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>ID</th>
								<th>用户名</th>
								<th>邮箱</th>
								<th>电话号码</th>
								<th>城市</th>
								<th>角色</th>
								<th>状态</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<tr class="list-users">
								<td>1</td>
								<td>Admin</td>
								<td>travis@provider.com</td>
								<td>xxx-xxx-xxxx</td>
								<td>My City</td>
								<td>Admin</td>
								<td><span class="label label-success">可用</span></td>
								<td>
									<div class="btn-group">
										<a class="btn btn-mini dropdown-toggle" data-toggle="dropdown" href="#">操作 <span class="caret"></span></a>
										<ul class="dropdown-menu">
											<li><a href="#"><i class="icon-pencil"></i> 修改</a></li>
											<li><a href="#"><i class="icon-trash"></i> 删除</a></li>
											<li><a href="#"><i class="icon-user"></i> 详情</a></li>
											<li class="nav-header">任务</li>
											<li><a href="#"><i class="icon-lock"></i> Make <strong>Admin</strong></a></li>
											<li><a href="#"><i class="icon-lock"></i> Make <strong>Moderator</strong></a></li>
											<li><a href="#"><i class="icon-lock"></i> Make <strong>User</strong></a></li>
										</ul>
									</div>
								</td>
							</tr>
							<tr class="list-users">
								<td>2</td>
								<td>Jose E. Jones</td>
								<td>joseejones@provider.com</td>
								<td>801-xxx-xxxx</td>
								<td>Morgan, UT</td>
								<td>Moderator</td>
								<td><span class="label label-success">Active</span></td>
								<td>
									<div class="btn-group">
										<a class="btn btn-mini dropdown-toggle" data-toggle="dropdown" href="#">Actions <span class="caret"></span></a>
										<ul class="dropdown-menu">
											<li><a href="#"><i class="icon-pencil"></i> Edit</a></li>
											<li><a href="#"><i class="icon-trash"></i> Delete</a></li>
											<li><a href="#"><i class="icon-user"></i> Details</a></li>
											<li class="nav-header">Permissions</li>
											<li><a href="#"><i class="icon-lock"></i> Make <strong>Admin</strong></a></li>
											<li><a href="#"><i class="icon-lock"></i> Make <strong>Moderator</strong></a></li>
											<li><a href="#"><i class="icon-lock"></i> Make <strong>User</strong></a></li>
										</ul>
									</div>
								</td>
							</tr>
							<tr class="list-users">
								<td>3</td>
								<td>George F. Green</td>
								<td>georgefgreen@provider.com</td>
								<td>443-xxx-xxxx</td>
								<td>Baltimore, MD</td>
								<td>User</td>
								<td><span class="label label-important">Inactive</span></td>
								<td>
									<div class="btn-group">
										<a class="btn btn-mini dropdown-toggle" data-toggle="dropdown" href="#">Actions <span class="caret"></span></a>
										<ul class="dropdown-menu">
											<li><a href="#"><i class="icon-pencil"></i> Edit</a></li>
											<li><a href="#"><i class="icon-trash"></i> Delete</a></li>
											<li><a href="#"><i class="icon-user"></i> Details</a></li>
											<li class="nav-header">Permissions</li>
											<li><a href="#"><i class="icon-lock"></i> Make <strong>Admin</strong></a></li>
											<li><a href="#"><i class="icon-lock"></i> Make <strong>Moderator</strong></a></li>
											<li><a href="#"><i class="icon-lock"></i> Make <strong>User</strong></a></li>
										</ul>
									</div>
								</td>
							</tr>
							<tr class="list-users">
								<td>4</td>
								<td>Leticia E. Bonk</td>
								<td>leticiaebonk@provider.com</td>
								<td>615-xxx-xxxx</td>
								<td>Lafayette, TN</td>
								<td>User</td>
								<td><span class="label label-important">Inactive</span></td>
								<td>
									<div class="btn-group">
										<a class="btn btn-mini dropdown-toggle" data-toggle="dropdown" href="#">Actions <span class="caret"></span></a>
										<ul class="dropdown-menu">
											<li><a href="#"><i class="icon-pencil"></i> Edit</a></li>
											<li><a href="#"><i class="icon-trash"></i> Delete</a></li>
											<li><a href="#"><i class="icon-user"></i> Details</a></li>
											<li class="nav-header">Permissions</li>
											<li><a href="#"><i class="icon-lock"></i> Make <strong>Admin</strong></a></li>
											<li><a href="#"><i class="icon-lock"></i> Make <strong>Moderator</strong></a></li>
											<li><a href="#"><i class="icon-lock"></i> Make <strong>User</strong></a></li>
										</ul>
									</div>
								</td>
							</tr>
							<tr class="list-users">
								<td>5</td>
								<td>Darrell J. Ezzell</td>
								<td>darrelljezzell@provider.com</td>
								<td>937-xxx-xxxx</td>
								<td>Yellow Springs, OH</td>
								<td>User</td>
								<td><span class="label label-important">Inactive</span></td>
								<td>
									<div class="btn-group">
										<a class="btn btn-mini dropdown-toggle" data-toggle="dropdown" href="#">Actions <span class="caret"></span></a>
										<ul class="dropdown-menu">
											<li><a href="#"><i class="icon-pencil"></i> Edit</a></li>
											<li><a href="#"><i class="icon-trash"></i> Delete</a></li>
											<li><a href="#"><i class="icon-user"></i> Details</a></li>
											<li class="nav-header">Permissions</li>
											<li><a href="#"><i class="icon-lock"></i> Make <strong>Admin</strong></a></li>
											<li><a href="#"><i class="icon-lock"></i> Make <strong>Moderator</strong></a></li>
											<li><a href="#"><i class="icon-lock"></i> Make <strong>User</strong></a></li>
										</ul>
									</div>
								</td>
							</tr>
							<tr class="list-users">
								<td>6</td>
								<td>Gene A. Lawhorn</td>
								<td>genealawhorn@provider.com</td>
								<td>901-xxx-xxxx</td>
								<td>Memphis, TN</td>
								<td>User</td>
								<td><span class="label label-important">Inactive</span></td>
								<td>
									<div class="btn-group">
										<a class="btn btn-mini dropdown-toggle" data-toggle="dropdown" href="#">Actions <span class="caret"></span></a>
										<ul class="dropdown-menu">
											<li><a href="#"><i class="icon-pencil"></i> Edit</a></li>
											<li><a href="#"><i class="icon-trash"></i> Delete</a></li>
											<li><a href="#"><i class="icon-user"></i> Details</a></li>
											<li class="nav-header">Permissions</li>
											<li><a href="#"><i class="icon-lock"></i> Make <strong>Admin</strong></a></li>
											<li><a href="#"><i class="icon-lock"></i> Make <strong>Moderator</strong></a></li>
											<li><a href="#"><i class="icon-lock"></i> Make <strong>User</strong></a></li>
										</ul>
									</div>
								</td>
							</tr>
							<tr class="list-users">
								<td>7</td>
								<td>Timothy R. Nichols</td>
								<td>timothyrnichols@provider.com</td>
								<td>212-xxx-xxxx</td>
								<td>New York, NY</td>
								<td>User</td>
								<td><span class="label label-important">Inactive</span></td>
								<td>
									<div class="btn-group">
										<a class="btn btn-mini dropdown-toggle" data-toggle="dropdown" href="#">Actions <span class="caret"></span></a>
										<ul class="dropdown-menu">
											<li><a href="#"><i class="icon-pencil"></i> Edit</a></li>
											<li><a href="#"><i class="icon-trash"></i> Delete</a></li>
											<li><a href="#"><i class="icon-user"></i> Details</a></li>
											<li class="nav-header">Permissions</li>
											<li><a href="#"><i class="icon-lock"></i> Make <strong>Admin</strong></a></li>
											<li><a href="#"><i class="icon-lock"></i> Make <strong>Moderator</strong></a></li>
											<li><a href="#"><i class="icon-lock"></i> Make <strong>User</strong></a></li>
										</ul>
									</div>
								</td>
							</tr>
							<tr class="list-users">
								<td>8</td>
								<td>Samuel I. Heim</td>
								<td>samueliheim@provider.com</td>
								<td>508-xxx-xxxx</td>
								<td>Marlboro, MA</td>
								<td>User</td>
								<td><span class="label label-important">Inactive</span></td>
								<td>
									<div class="btn-group">
										<a class="btn btn-mini dropdown-toggle" data-toggle="dropdown" href="#">Actions <span class="caret"></span></a>
										<ul class="dropdown-menu">
											<li><a href="#"><i class="icon-pencil"></i> Edit</a></li>
											<li><a href="#"><i class="icon-trash"></i> Delete</a></li>
											<li><a href="#"><i class="icon-user"></i> Details</a></li>
											<li class="nav-header">Permissions</li>
											<li><a href="#"><i class="icon-lock"></i> Make <strong>Admin</strong></a></li>
											<li><a href="#"><i class="icon-lock"></i> Make <strong>Moderator</strong></a></li>
											<li><a href="#"><i class="icon-lock"></i> Make <strong>User</strong></a></li>
										</ul>
									</div>
								</td>
							</tr>
							<tr class="list-users">
								<td>9</td>
								<td>Dennis B. Kim</td>
								<td>dennisbkim@provider.com</td>
								<td>407-xxx-xxxx</td>
								<td>Orlando, FL</td>
								<td>User</td>
								<td><span class="label label-important">Inactive</span></td>
								<td>
									<div class="btn-group">
										<a class="btn btn-mini dropdown-toggle" data-toggle="dropdown" href="#">Actions <span class="caret"></span></a>
										<ul class="dropdown-menu">
											<li><a href="#"><i class="icon-pencil"></i> Edit</a></li>
											<li><a href="#"><i class="icon-trash"></i> Delete</a></li>
											<li><a href="#"><i class="icon-user"></i> Details</a></li>
											<li class="nav-header">Permissions</li>
											<li><a href="#"><i class="icon-lock"></i> Make <strong>Admin</strong></a></li>
											<li><a href="#"><i class="icon-lock"></i> Make <strong>Moderator</strong></a></li>
											<li><a href="#"><i class="icon-lock"></i> Make <strong>User</strong></a></li>
										</ul>
									</div>
								</td>
							</tr>
							<tr class="list-users">
								<td>10</td>
								<td>Joni D. Soto</td>
								<td>jonidsoto@provider.com</td>
								<td>215-xxx-xxxx</td>
								<td>Philadelphia, PA</td>
								<td>User</td>
								<td><span class="label label-important">Inactive</span></td>
								<td>
									<div class="btn-group">
										<a class="btn btn-mini dropdown-toggle" data-toggle="dropdown" href="#">Actions <span class="caret"></span></a>
										<ul class="dropdown-menu">
											<li><a href="#"><i class="icon-pencil"></i> Edit</a></li>
											<li><a href="#"><i class="icon-trash"></i> Delete</a></li>
											<li><a href="#"><i class="icon-user"></i> Details</a></li>
											<li class="nav-header">Permissions</li>
											<li><a href="#"><i class="icon-lock"></i> Make <strong>Admin</strong></a></li>
											<li><a href="#"><i class="icon-lock"></i> Make <strong>Moderator</strong></a></li>
											<li><a href="#"><i class="icon-lock"></i> Make <strong>User</strong></a></li>
										</ul>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
					<div class="pagination">
						<ul>
							<li><a href="#">上一页</a></li>
							<li class="active">
								<a href="#">1</a>
							</li>
							<li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#">4</a></li>
							<li><a href="#">下一页</a></li>
						</ul>
					</div>
					<a href="new-user.jsp" class="btn btn-success">新建用户</a>
		  		</div>
        	</div>
      	</div>
      	<hr>
		<jsp:include page="/jsp/common/copyright.jsp"></jsp:include>
    </div>
    <jsp:include page="/jsp/common/bottom.jsp"></jsp:include>
    <script>
		$(document).ready(function() {
			$('.dropdown-menu li a').hover(
			function() {
				$(this).children('i').addClass('icon-white');
			},
			function() {
				$(this).children('i').removeClass('icon-white');
			});
			if($(window).width() > 760) {
				$('tr.list-users td div ul').addClass('pull-right');
			}
		});
	</script>
</body>
</html>