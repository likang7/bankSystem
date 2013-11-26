<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../css/style.css" />
<link href='http://fonts.googleapis.com/css?family=Engagement' rel='stylesheet' type='text/css'>
<title>Insert title here</title>
</head>
<body>
<form method="post" action="../departmentservice">
	<ul>
		<li>
        	<label for="username">用户名:</label>
            <input type="text" size="40" name = "username" required="required"/>
        </li>
        <li>
        	<label for="passwd">密码:</label>
            <input type="password" size="40" name = "password" required="required"/>
        </li>
        <li>
        	<label for="department">所属部门:</label>
            <input type="radio" size="40" name = "newpasswd" required="required"/>
        </li>       
	</ul>
    <p>
        <button type="submit" class="action">提交</button>
        <button type="reset" class="middle">重置</button>
        <a class = "right" href="business/departmentService.jsp">返回</a>
    </p>
</form>
</body>
</html>