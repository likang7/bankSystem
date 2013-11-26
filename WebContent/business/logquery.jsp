<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html ">
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
<%session.setAttribute("businesstype", "logquery");%>
<h2>日志查询</h2>
<form method="post" action="departmentservice">
<ul>
<li>
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
	out.println("<tr>");
	String[] pairs = log.split(",");
	String employeeName = "";
	boolean first = true;
	for(String pair : pairs){
		String[] values = pair.split("=");
		out.println("<td>" + values[1] + "</td>");
		if(first == true){
			employeeName = values[1];
			first = false;
		}
	}
	out.println("<td><input type='checkbox' id='selectedEmployee' name='selectedEmployee' value='" + employeeName + "'></td>");
	out.println("</tr>");
}
%>
</table>
</li>
    <li>
    	<label for="start">起始时间:</label>
        <input type="date" size="40" name = "startdate" required="required"/>
    </li>  
    <li>
    	<label for="end">结束时间:</label>
        <input type="date" size="40" name = "enddate" required="required"/>
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