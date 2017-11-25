<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表页</title>
<style type="text/css">
	
</style>
</head>
<body>
	<center>
		<h1>这是修改信息也</h1>
		<form action="modify" method="POST">
			<table>
				<tr>
					<td>
						姓名：
					</td>
					<td>
						<input type="text" value="${testInfo.name }" name="name"/>
					</td>
				</tr>
				<tr>
					<td>
						密码：
					</td>
					<td>
						<input type="password" name="password"/>
					</td>
				</tr>
				<tr>
					<td>
						年龄：
					</td>
					<td>
						<input type="text" name="age"/>
					</td>
				</tr>
				<tr>
					<td>
						电话号码：
					</td>
					<td>
						<input type="text" name="phone"/>
					</td>
				</tr>
				<tr>
					<td>
						性别：
					</td>
					<td>
						<input type="text" name="sex"/>
					</td>
				</tr>
				<tr>
					<td>
						爱好：
					</td>
					<td>
						<input type="text" name="hobby"/>
					</td>
				</tr>
				<tr>
					<td>
					</td>
					<td>
						<input type="submit" value="提交"/>
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>