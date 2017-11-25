<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
						<h1>部门管理 <small>所有部门</small></h1>
					</div>
					<table class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>ID</th>
								<th>部门名称</th>
								<th>部门编号</th>
								<th>拥有车位号</th>
								<th>状态</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${apartments}" var="apartment">
								<tr class="list-users">
									<td>${apartment.apartment_id }</td>
									<td>${apartment.apartment_name }</td>
									<td>${apartment.apartment_no }</td>
									<td>${apartment.carport_nums }</td>
									<c:choose>
										<c:when test="${apartment.status == '1' }">
											<td><span class="label label-success">可用</span></td>
										</c:when>
										<c:otherwise>
											<td><span class="label label-important">不可用</span></td>
										</c:otherwise>
									</c:choose>
									<td>
										<div class="btn-group">
											<a class="btn btn-mini dropdown-toggle" data-toggle="dropdown" href="#">操作 <span class="caret"></span></a>
											<ul class="dropdown-menu">
												<li><a href="/carport/apartment/delete?id=${apartment.apartment_id }#apartment"><i class="icon-pencil"></i> 修改</a></li>
												<li><a href="/carport/apartment/delete?id=${apartment.apartment_id }#apartment" onclick="JavaScript:return confirm('确定删除吗？')"><i class="icon-trash"></i> 删除</a></li>
												<li><a href="/carport/apartment/detail?id=${apartment.apartment_id }#apartment"><i class="icon-user"></i> 详情</a></li>
											</ul>
										</div>
									</td>
								</tr>
							</c:forEach>
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
					<a href="/carport/apartment/toadd#apartment" class="btn btn-success">新增部门</a>
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