<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<link href='http://fonts.googleapis.com/css?family=Engagement' rel='stylesheet' type='text/css'>
<title>处理结果</title>
</head>
<body>
<h1> 
<%
	String status = (String)request.getAttribute("status"); 
	out.print("<font ");
	if(status != null && status.equals("OK")){
		out.print("color='green'>");
		out.print("成功啦!");
	}
	else{
		out.print("color='red'>");
		out.print("出错啦！");
	}
	out.print("</font>");
%>
</h1>
<%
	String msg = (String)request.getAttribute("msg");
	String returnLink = (String)request.getAttribute("returnLink");
	out.println("<br>" + msg);
	out.println("<br> <a href=" + returnLink + "> 返回 </a>");
%>
</body>
</html>