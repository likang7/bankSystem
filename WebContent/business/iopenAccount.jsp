<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人用户业务：开户</title>
<link rel="stylesheet" href="../css/style.css" />
<link href='http://fonts.googleapis.com/css?family=Engagement' rel='stylesheet' type='text/css'>
</head>
<body>
<%
session.setAttribute("businesstype", "openaccount");
%>
<h2>普通个人业务：开户</h2>
<form method="post" action="../businessservice">
	<ul>
        <li>
        	<label for="userid">身份证号:</label>
            <input type="text" size="40" required="required"/>
        </li>
        <li>
        	<label for="name">姓名:</label>
            <input type="text" size="40" required="required"/>
        </li>
        <li>
            <label for="type">账户类型:</label>
            <select id="car">
				<option>活期存款</option>
				<option>定期存款</option>
            </select>
        </li>
        <li>
        	<label for="money">存款金额:</label>
            <input type="text" size="40" required="required"/>
        </li>
        <li>
        	<label for="password">初始密码:</label>
            <input type="password" size="40" required="required"/>
        </li>        
	</ul>
    <p>
        <button type="submit" class="action">提交</button>
        <button type="reset" class="right">重置</button>
    </p>
</form>
</body>
</html>