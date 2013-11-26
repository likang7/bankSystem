<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
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
业务：添加操作人
</title>
<link rel="stylesheet" href="../css/style.css" />
<link href='http://fonts.googleapis.com/css?family=Engagement' rel='stylesheet' type='text/css'>
</head>
<body>
<%session.setAttribute("businesstype", "addoperator");%>
<h2>
<%
if(title != null)
	out.print(title);
%>
业务：开户
</h2>
<form method="post" action="../businessservice">
	<ul>
        <li>
        	<label for="userid">身份证号：</label>
            <input type="text" name = "userid" size="40" required="required"/>
        </li>
        <li>
        	<label for="cardid">账号:</label>
            <input type="number" size="40" name = "cardid" required="required"/>
        </li>
        <li>
        	<label for="password">密码:</label>
            <input type="password" size="40" name = "password" required="required"/>
        </li>
        <li>
        	<label for="newuserid">新增操作人身份证号：</label>
            <input type="text" name = "newuserid" size="40" required="required"/>
        </li>
        <li>
        	<label for="newusername">新增操作人姓名：</label>
            <input type="text" name = "newusername" size="40" required="required"/>
        </li>
        <li>
        	<label for="newpassword">新增操作人初始密码:</label>
            <input type="password" name = "newpassword" size="40" required="required"/>
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