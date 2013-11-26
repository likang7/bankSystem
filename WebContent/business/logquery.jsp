<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="css/style.css" />
<%
String returnLink = "javascript:history.back(-1);";
%>
<title>日志查询</title>
</head>
<body>
<center>
<p>日志查询</p>
<form method="post" action="../departmentService.jsp">
<table class="gridtable">
<tr>
	<th>员工</th>
	<th>职称</th>
	<th>部门</th>
	<th>上级</th>
	<th>选择</th>
</tr>
<%
String msg = (String)request.getAttribute("employees");
String[] logs = msg.split("<br>");
for(String log : logs){
	out.print("<tr>");
	String[] pairs = log.split(",");
	for(String pair : pairs){
		String[] values = pair.split("=");
		out.print("<td>" + values[1] + "</td>");
	}
	out.print("<td><input type='checkbox' id='selectedEmployee' name='selectedEmployee' value=''></td>");
	out.print("</tr>");
}
%>
</table>
    <p>
        <button type="submit" class="action">提交</button>
        <button type="reset" class="middle">重置</button>
        <a class = "right" href=<% out.print(returnLink); %>>返回</a>
    </p>
</form>
</center>
</body>
</html>