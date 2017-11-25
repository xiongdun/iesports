<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>管理员登录</title>
<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
<link rel="stylesheet" href="/carport/js/bootstrap/css/bootstrap-3.3.4.min.css">
<link rel="stylesheet" href="/carport/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="/carport/css/form-elements.css">
<link rel="stylesheet" href="/carport/css/style.css">
<link rel="shortcut icon" href="/carport/ico/favicon.png">
<link rel="apple-touch-icon-precomposed" sizes="144x144" href="/carport/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114" href="/carport/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72" href="/carport/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed" href="/carport/ico/apple-touch-icon-57-precomposed.png">
</head>
<body>
	<!-- Top content -->
	<div class="top-content">
	    <div class="inner-bg">
	        <div class="container">
	            <div class="row">
	                <div class="col-sm-8 col-sm-offset-2 text">
	                    <h1><strong>车位管理平台</strong></h1>
	                </div>
	            </div>
	            <div class="row">
	                <div class="col-sm-6 col-sm-offset-3 form-box">
	                	<div class="form-top">
	                		<div class="form-top-left">
	                			<h3>登录窗口</h3>
	                    		<p>请输入您的用户名和密码用于登录:</p>
	                		</div>
	                		<div class="form-top-right">
	                			<i class="fa fa-key"></i>
	                		</div>
	                    </div>
	                    <div class="form-bottom">
			               <form role="form" action="/carport/user/login#stats-index" method="post" class="login-form">
			               		<div class="form-group">
			               			<label class="sr-only" for="form-username">Username</label>
			                   		<input type="text" name="admin_name" placeholder="Username..." class="form-username form-control" id="form-username">
			                   </div>
			                   <div class="form-group">
			                   		<label class="sr-only" for="form-password">Password</label>
			                   		<input type="password" name="admin_pwd" placeholder="Password..." class="form-password form-control" id="form-password">
			                   </div>
			                   <button type="submit" class="btn">登录</button>
			               </form>
	              		</div>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
	<script src="/carport/js/jquery-1.11.1.min.js"></script>
	<script src="/carport/js/bootstrap/js/bootstrap.min.js"></script>
	<script src="/carport/js/jquery.backstretch.min.js"></script>
	<script src="/carport/js/scripts.js"></script>
</body>
</html>