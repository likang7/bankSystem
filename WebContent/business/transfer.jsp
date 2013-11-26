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
业务：转账
</title>
<link rel="stylesheet" href="../css/style.css" />
<link href='http://fonts.googleapis.com/css?family=Engagement' rel='stylesheet' type='text/css'>
</head>
<body>
<% session.setAttribute("businesstype", "transfer"); %>
<h2>
<%
if(title != null)
	out.print(title);
%>
业务：转账</h2>
<form method="post" action="../businessservice">
	<ul>
	    <li>
        	<label for="outuserid">身份证号:</label>
            <input type="text" size="40" name = "outuserid" required="required"/>
        </li>
        <li>
        	<label for="outusername">转出用户名称:</label>
            <input type="text" size="40" name = "outusername" required="required"/>
        </li>  
        <li>
        	<label for="outcardid">转出账号:</label>
            <input type="number" size="40" name = "outcardid" required="required"/>
        </li>
        <li>
        	<label for="password">转出密码:</label>
            <input type="password" size="40" name = "password" required="required"/>
        </li>
        <li>
        	<label for="incardid">转入账号:</label>
            <input type="number" size="40" name = "incardid" required="required"/>
        </li>  
        <li>
        	<label for="inusername">转入用户名称:</label>
            <input type="text" size="40" name = "inusername" required="required"/>
        </li>       
        <li>
        	<label for="money">转出金额:</label>
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