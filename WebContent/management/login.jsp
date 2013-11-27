<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Bank System -- Log in</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<link href='http://fonts.googleapis.com/css?family=Engagement' rel='stylesheet' type='text/css'>
</head>
<body>
<h1>Welcome to Bank System</h1>
<form method = "Post" action = "<%=request.getContextPath()%>/login.action">
	<ul>
	    <li>
        	<label for="username">用户名:</label>
            <input type="text" size="40" name = "username" required="required"/>
        </li>
        	    <li>
        	<label for="password">密码:</label>
            <input type="password" size="40" name = "password" required="required"/>
        </li>
    </ul>
	<p>
		<button type="submit" class="action">登陆</button>
		<button type="reset" class="right">重置</button>
	</p>
	<p><sub>email : likang7@mail2.sysu.edu.cn</sub></p>
	
</form>
</body>
</html>