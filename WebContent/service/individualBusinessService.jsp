<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<jsp:include page="serviceHeader.jsp"/>
<html>
<head>
<title>普通个人业务</title>
</head>
<body>
<br><br>
<!-- <center> -->
<br> 当前业务类型：普通个人业务

<br><br>
<%
session.setAttribute("usertype", "individual");
%>

<div>
<ul id = "navlist">
<li><a href="<%=request.getContextPath()%>/business/openAccount.jsp">开户</a></li>
<li><a href="<%=request.getContextPath()%>/business/deposit.jsp">存款</a></li>
<li><a href="<%=request.getContextPath()%>/business/withdraw.jsp">取款</a></li>
<li><a href="<%=request.getContextPath()%>/business/query.jsp">查询</a></li>
<li><a href="<%=request.getContextPath()%>/business/transfer.jsp">转账</a></li>
<li><a href="<%=request.getContextPath()%>/business/changepasswd.jsp">改密码</a></li>
<li><a href="<%=request.getContextPath()%>/business/closeaccount.jsp">销户</a></li>
</ul>
</div>
<!--</center>  -->


</body>
</html>