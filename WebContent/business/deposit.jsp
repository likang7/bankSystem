<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>
<%
String usertype = (String)session.getAttribute("usertype");
String returnLink = "javascript:history.back(-1);";
String title = null;
if(usertype.equals("individual")){
	title = "普通个人";
	returnLink = "individualBusinessService.jsp";
}
else if(usertype.equals("vip")){
	title = "VIP个人";
	returnLink = "VIPBusinessService.jsp";
}
else if(usertype.equals("enterprise")){
	title = "企业用户";
	returnLink = "enterpriseBusinessService.jsp";
}
if(title != null)
	out.print(title);
%>
业务：存款
</title>
<link rel="stylesheet" href="../css/style.css" />
<link href='http://fonts.googleapis.com/css?family=Engagement' rel='stylesheet' type='text/css'>
</head>
<body>
<%
session.setAttribute("businesstype", "deposit");
%>
<h2>
<%
if(title != null)
	out.print(title);
%>
业务：存款
</h2>
<form method="post" action="../businessservice">
	<ul>
        <li>
        	<label for="cardid">账号:</label>
            <input type="number" size="40" name = "cardid" required="required"/>
        </li>
        <li>
        	<label for="password">密码:</label>
            <input type="password" size="40" name = "password" required="required"/>
        </li>
        <li>
        	<label for="money">存款金额:</label>
            <input type="number" step = "0.01" size="40" name = "money" required="required"/>
        </li>       
	</ul>
    <p>
        <button type="submit" class="action">提交</button>
        <button type="reset" class="middle">重置</button>
        <a class = "right" href=<% out.print(returnLink); %>>返回</a>
        
    </p>
</form>
</body>
</html>