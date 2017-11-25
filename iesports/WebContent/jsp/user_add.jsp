<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户添加页</title>
</head>
<body>
	<center>
		<h3>测试数据添加</h3>
		<form action="add" method="post">
			<table>
				<tr>
					<td>
						姓名：
					</td>
					<td>
						<input type="text" name="name"/>
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
						男<input value="男" type="radio" name="sex"/>
						女<input value="女" type="radio" name="sex"/>
					</td>
				</tr>
				<tr>
					<td>
						爱好：
					</td>
					<td>
						英语<input value="english" type="checkbox" name="hobby"/>
						看书<input value="reading" type="checkbox" name="hobby"/>
						购物<input value="shopping" type="checkbox" name="hobby"/>
					</td>
				</tr>
				<!-- <tr>
					<td>
						生日：
					</td>
					<td>
						<input type="text" name="birthday"/>
					</td>
				</tr> -->
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