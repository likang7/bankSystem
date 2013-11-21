<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error...</title>
</head>
<body>
<%
	String errorMsg = (String)request.getAttribute("msg");
	String returnLink = (String)request.getAttribute("returnLink");
	out.println("<br>" + errorMsg);
	out.println("<br> <a href=" + returnLink + "> return </a>");
%>
</body>
</html>