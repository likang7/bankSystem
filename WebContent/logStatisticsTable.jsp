<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="css/style.css" />
<title>统计结果</title>
</head>
<body>
<!-- Table goes in the document BODY -->
<center>
<h1><font color="green">统计结果</font></h1>
<table class="gridtable">
<%
//输出起始时间以及结束时间
String start = (String)request.getAttribute("start");
String end = (String)request.getAttribute("end");
out.print("<p>日期：" + start +  " --- ");
out.print("" + end +  " </p>");
%>
<tr>
	<th>操作类型</th>
	<th>次数</th>
	<th>收入</th>
	<th>支出</th>
</tr>
<%
	String msg = (String)request.getAttribute("msg");
	String[] logs = msg.split("<br>");
	if(msg != null && !msg.equals("")){
		for(String log : logs){
			out.print("<tr>");
			String[] pairs = log.split(",");
			for(String pair : pairs){
				String[] values = pair.split("=");
				out.print("<td>" + values[1] + "</td>");
			}
			out.print("</tr>");
		}
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