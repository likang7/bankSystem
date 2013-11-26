<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="css/style.css" />
<title>查询结果</title>
</head>
<body>
<!-- Table goes in the document BODY -->
<center>
<h1><font color="green">成功啦！</font></h1>
<table class="gridtable">
<tr>
	<th>时间</th>
	<th>操作类型</th>
	<th>操作人</th>
	<th>卡号</th>
	<th>账号</th>
	<th>收入</th>
	<th>支出</th>
	<th>余额</th>
</tr>
<%
	String msg = (String)request.getAttribute("msg");
	String[] logs = msg.split("<br>");
	for(String log : logs){
		out.print("<tr>");
		String[] pairs = log.split(",");
		for(String pair : pairs){
			String[] values = pair.split("=");
			out.print("<th>" + values[1] + "</th>");
		}
		out.print("</tr>");
	}
%>
</table>
<%
	String returnLink = (String)request.getAttribute("returnLink");
	out.println("<br> <a href=" + returnLink + "> 返回 </a>");
%>
</center>
</body>
</html>