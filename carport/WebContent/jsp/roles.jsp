<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/jsp/common/top.jsp"></jsp:include>
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
						<h1>角色管理 <small>Manage roles...</small></h1>
					</div>
					<table class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>ID</th>
								<th>角色名</th>
								<th>描述</th>
								<th>简写</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<tr class="list-roles">
								<td>1</td>
								<td>Admin</td>
								<td>Aliquam erat volutpat. Vivamus molestie tempor pellentesque. Praesent lobortis, neque.</td>
								<td>admin</td>
								<td>
									<div class="btn-group">
										<a class="btn btn-mini dropdown-toggle" data-toggle="dropdown" href="#">操作 <span class="caret"></span></a>
										<ul class="dropdown-menu pull-right">
											<li><a href="#"><i class="icon-pencil"></i> 修改</a></li>
											<li><a href="#"><i class="icon-trash"></i> 删除</a></li>
										</ul>
									</div>
								</td>
							</tr>				
							<tr class="list-roles">
								<td>2</td>
								<td>Moderator</td>
								<td>Phasellus scelerisque, quam ac bibendum pulvinar, erat ligula pulvinar risus, in ultricies...</td>
								<td>mod</td>
								<td>
									<div class="btn-group">
										<a class="btn btn-mini dropdown-toggle" data-toggle="dropdown" href="#">Actions <span class="caret"></span></a>
										<ul class="dropdown-menu pull-right">
											<li><a href="#"><i class="icon-pencil"></i> Edit</a></li>
											<li><a href="#"><i class="icon-trash"></i> Delete</a></li>
										</ul>
									</div>
								</td>
							</tr>
							<tr class="list-roles">
								<td>3</td>
								<td>User</td>
								<td>Donec cursus, velit eu fermentum ullamcorper, libero est.</td>
								<td>user</td>
								<td>
									<div class="btn-group">
										<a class="btn btn-mini dropdown-toggle" data-toggle="dropdown" href="#">Actions <span class="caret"></span></a>
										<ul class="dropdown-menu pull-right">
											<li><a href="#"><i class="icon-pencil"></i> Edit</a></li>
											<li><a href="#"><i class="icon-trash"></i> Delete</a></li>
										</ul>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
					<a href="/carport/role/toadd#role" class="btn btn-success">新建角色</a>
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
		});
	</script>
</body>
</html>