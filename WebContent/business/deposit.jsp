<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>存款</title>
<link rel="stylesheet" href="../css/style.css" />
<link href='http://fonts.googleapis.com/css?family=Engagement' rel='stylesheet' type='text/css'>
</head>
<body>
<%
session.setAttribute("businesstype", "deposit");
%>
<h2>存款</h2>
<form method="post" action="../businessservice">
	<ul>
        <li>
        	<label for="cardid">账号:</label>
            <input type="text" size="40" required="required"/>
        </li>
        <li>
        	<label for="password">密码:</label>
            <input type="password" size="40" required="required"/>
        </li>
        <li>
        	<label for="money">存款金额:</label>
            <input type="text" size="40" required="required"/>
        </li>       
	</ul>
    <p>
        <button type="submit" class="action">提交</button>
        <button type="reset" class="right">重置</button>
    </p>
</form>
</body>
</html>