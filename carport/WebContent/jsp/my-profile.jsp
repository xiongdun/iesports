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
						<h1>我的资料 <small>修改信息</small></h1>
					</div>
					<form class="form-horizontal" action="/carport/user/modify#user" method="POST">
						<fieldset>
							<div class="control-group">
								<label class="control-label" for="name">用户名</label>
								<div class="controls">
									<input type="text" class="input-xlarge" id="name" value="Admin" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="email">邮箱</label>
								<div class="controls">
									<input type="text" class="input-xlarge" id="email" value="travis@provider.com" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="pnohe">电话</label>
								<div class="controls">
									<input type="text" class="input-xlarge" id="phone" value="xxx-xxx-xxxx" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="city">城市</label>
								<div class="controls">
									<input type="text" class="input-xlarge" id="city" value="My City" />
								</div>
							</div>	
							<div class="control-group">
								<label class="control-label" for="role">角色</label>
								<div class="controls">
									<select id="role">
										<option value="admin" selected>管理员</option>
										<option value="mod">审核人</option>
										<option value="user">普通用户</option>
									</select>
								</div>
							</div>	
							<div class="control-group">
								<label class="control-label" for="active">是否可用</label>
								<div class="controls">
									<input type="checkbox" id="active" value="1" checked />
								</div>
							</div>
							<div class="form-actions">
								<input type="submit" class="btn btn-success btn-large" value="保存修改" />
								<a class="btn" href="/carport/user/all#user">取消</a>
							</div>					
						</fieldset>
					</form>
		  		</div>
        	</div>
      	</div>
      	<hr>
      	<jsp:include page="/jsp/common/copyright.jsp"></jsp:include>
    </div>
	<jsp:include page="/jsp/common/bottom.jsp"></jsp:include>
</body>
</html>