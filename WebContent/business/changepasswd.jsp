<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改密码</title>
<link rel="stylesheet" href="../css/style.css" />
<link href='http://fonts.googleapis.com/css?family=Engagement' rel='stylesheet' type='text/css'>
</head>
<body>
<%
session.setAttribute("businesstype", "changepasswd");
%>
<h2>修改密码</h2>
<form method="post" action="../businessservice">
	<ul>
		<li>
        	<label for="userid">身份证号:</label>
            <input type="text" size="40" name = "userid" required="required"/>
        </li>
        <li>
        	<label for="cardid">账号:</label>
            <input type="number" size="40" name = "cardid" required="required"/>
        </li>
        <li>
        	<label for="oldpasswd">原始密码:</label>
            <input type="password" size="40" name = "oldpasswd" required="required"/>
        </li>
        <li>
        	<label for="newpasswd">新的密码:</label>
            <input type="password" size="40" name = "newpasswd" required="required"/>
        </li>       
        <li>
        	<label for="newpasswd2">确认新的密码:</label>
            <input type="password" size="40" name = "newpasswd2" required="required"/>
        </li>   
	</ul>
    <p>
        <button type="submit" class="action">提交</button>
        <button type="reset" class="middle">重置</button>
        <a class = "right" href="javascript:history.back(-1);">返回</a>
    </p>
</form>
</body>
</html>