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
<title>日志统计</title>
</head>
<body>
<%session.setAttribute("businesstype", "logstatistics");%>
<h2>日志统计</h2>
<form method="post" action="departmentservice">
<ul>
<li>
<jsp:include page="employeeTable.jsp"/>
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