<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表页</title>
<style type="text/css">
	.content {
		width:1000px;
		height:auto;
		background:lightblue;
	}
	.td_width1{
		width:200px;
	}
	.td_width1.mid {
		text-align: center;
	}
</style>
</head>
<body>
	<center>
		<div class="content">
			<table border="2">
				<tr>
					<td class="td_width1 mid">
						用户名
					</td>
					<td class="td_width1 mid">
						密码
					</td>
					<td>
						年龄
					</td>
					<td class="td_width1 mid">
						电话号码
					</td>
					<td>
						性别
					</td>
					<td class="td_width1 mid">
						爱好
					</td>
					<td>
						<a href="toadd">添加</a>
					</td>
				</tr>
				<c:forEach items="${list }" var="test">
					<tr>
						<td class="td_width1 mid">
							${test.name }
						</td>
						<td class="td_width1 mid">
							${test.password }
						</td>
						<td>
							${test.age }
						</td>
						<td class="td_width1 mid">
							${test.phone }
						</td>
						<td>
							${test.sex }
						</td>
						<td class="td_width1 mid">
							${test.hobby }
						</td>
						<td>
							<a href="detail?id=${test.id}">修改</a>
						</td>
						<td>
							<a href="delete?id=${test.id}">删除</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</center>
</body>
</html>