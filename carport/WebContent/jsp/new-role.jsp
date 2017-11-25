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
						<h1>角色管理 <small>add a new role</small></h1>
					</div>
					<form class="form-horizontal" action="/carport/role/add#role" method="POST">
						<fieldset>
							<div class="control-group">
								<label class="control-label" for="role">角色名</label>
								<div class="controls">
									<input type="text" class="input-xlarge" id="role" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="description">描述</label>
								<div class="controls">
									<textarea class="input-xlarge" id="description" rows="3"></textarea>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="slug">简写</label>
								<div class="controls">
									<input type="text" class="input-xlarge" id="slug" />
								</div>
							</div>
							<div class="form-actions">
								<input type="submit" class="btn btn-success btn-large" value="保存角色" /> 
								<a class="btn" href="/carport/role/all#role">取消</a>
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