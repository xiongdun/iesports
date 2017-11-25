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
						<h1>新建用户 <small>add a new user</small></h1>
					</div>
					<form class="form-horizontal" action="/carport/user/add#user" method="POST">
						<fieldset>
							<div class="control-group">
								<label class="control-label" for="name">用户名</label>
								<div class="controls">
									<input type="text" name="user_name" required="required" class="input-xlarge" id="name" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="password">用户密码</label>
								<div class="controls">
									<input type="text" name="user_pwd" required="required" class="input-xlarge" id="password" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="carno">车牌号</label>
								<div class="controls">
									<input type="text" name="car_no" required="required" class="input-xlarge" id="carno" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="jobnumber">工号</label>
								<div class="controls">
									<input type="text" name="job_number" required="required" class="input-xlarge" id="jobnumber" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="orgid">组织机构</label>
								<div class="controls">
									<input type="text" name="org_id" required="required" class="input-xlarge" id="orgid" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="apartmentid">部门</label>
								<div class="controls">
									<input type="text" name="apartment_id" required="required" class="input-xlarge" id="apartmentid" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="role">角色</label>
								<div class="controls">
									<select id="role" name="is_system">
										<option value="1">管理员</option>
										<option value="0" selected>普通用户</option>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="active">是否可用</label>
								<div class="controls">
									<input type="checkbox" name="status" id="active" value="1" />
								</div>
							</div>
							<div class="form-actions">
								<input type="submit" class="btn btn-success btn-large" value="保存用户" /> 
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