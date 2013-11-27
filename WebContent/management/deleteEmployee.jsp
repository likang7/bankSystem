<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<link href='http://fonts.googleapis.com/css?family=Engagement' rel='stylesheet' type='text/css'>
<title>后台管理：删除雇员</title>
</head>
<body>
<%session.setAttribute("businesstype", "deleteemployee");%>
<h2>后台管理：删除雇员</h2>
<form method="post" action="<%=request.getContextPath()%>/departmentservice">
	<ul>
		<li>
			<label for="username">雇员用户名：</label>
			<input type="text" name = "username" size="40" required = "required"/>
		</li>
	</ul>
	<p>
        <button type="submit" class="action">提交</button>
        <button type="reset" class="middle">重置</button>
        <a class = "right" href="<%=request.getContextPath()%>/service/departmentService.jsp">返回</a>
    </p>
</form>
</body>
</html>