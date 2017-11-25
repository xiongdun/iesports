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
						<h1>修改部门 <small>modify a apartment</small></h1>
					</div>
					<form class="form-horizontal" action="/carport/apartment/modify#apartment" method="POST">
						<fieldset>
							<div class="control-group">
								<label class="control-label" for="name">部门ID</label>
								<div class="controls">
									<input type="text" value="${apartment.apartment_id }" readonly="readonly" name="apartment_id" class="input-xlarge" id="name" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="name">部门名称</label>
								<div class="controls">
									<input type="text" value="${apartment.apartment_name }" name="apartment_name" class="input-xlarge" id="name" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="number">部门编号</label>
								<div class="controls">
									<input type="text" value="${apartment.apartment_no }" name="apartment_no" class="input-xlarge" id="number" />
								</div>	
							</div>
							<div class="control-group">
								<label class="control-label" for="carportno">车位号</label>
								<div class="controls">
									<input type="text" value="${apartment.carport_nums }" name="carport_nums" class="input-xlarge" id="carportno" />
								</div>	
							</div>
							<div class="control-group">
								<!-- 这里可以做一个判断 -->
								<label class="control-label" for="active">是否可用</label>
								<div class="controls">
									<input type="checkbox" value="${apartment.status }" name="status" id="active"/>
								</div>
							</div>
							<div class="form-actions">
								<input type="submit" class="btn btn-success btn-large" value="保存修改" /> 
								<a class="btn" href="/carport/apartment/all#apartment">取消</a>
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