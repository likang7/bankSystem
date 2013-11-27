<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html">
<html>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<link href='http://fonts.googleapis.com/css?family=Engagement' rel='stylesheet' type='text/css'>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>
<%
String usertype = (String)session.getAttribute("usertype");
String returnLink = "javascript:history.back(-1);";
String title = null;
if(usertype.equals("individual")){
	title = "普通个人";
	returnLink = request.getContextPath() + "/service/individualBusinessService.jsp";
}
else if(usertype.equals("vip")){
	title = "VIP个人";
	returnLink = request.getContextPath() + "/service/VIPBusinessService.jsp";
}
else if(usertype.equals("enterprise")){
	title = "企业用户";
	returnLink = request.getContextPath() + "/service/enterpriseBusinessService.jsp";
}
if(title != null)
	out.print(title);

request.setAttribute("returnLink", returnLink);
request.setAttribute("title", title);
request.setAttribute("usertype", usertype);
%>
业务：取款</title>
</head>