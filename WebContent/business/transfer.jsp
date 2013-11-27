<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html">
<html>
<jsp:include page="businessHeader.jsp"/>
<body>
<% session.setAttribute("businesstype", "transfer"); %>
<h2>
<%= (String)request.getAttribute("title")%>业务：取款
业务：转账</h2>
<form method="post" action="<%=request.getContextPath()%>/businessservice.action">
	<ul>
	    <li>
        	<label for="outuserid">身份证号:</label>
            <input type="text" size="40" name = "outuserid" required="required"/>
        </li>
        <li>
        	<label for="outusername">转出用户名称:</label>
            <input type="text" size="40" name = "outusername" required="required"/>
        </li>  
        <li>
        	<label for="outcardid">转出账号:</label>
            <input type="number" size="40" name = "outcardid" required="required"/>
        </li>
        <li>
        	<label for="password">转出密码:</label>
            <input type="password" size="40" name = "password" required="required"/>
        </li>
        <li>
        	<label for="incardid">转入账号:</label>
            <input type="number" size="40" name = "incardid" required="required"/>
        </li>  
        <li>
        	<label for="inusername">转入用户名称:</label>
            <input type="text" size="40" name = "inusername" required="required"/>
        </li>       
        <li>
        	<label for="money">转出金额:</label>
            <input type="number" step = "0.01" size="40" name = "money" required="required"/>
        </li>  
	</ul>
    <p>
        <button type="submit" class="action">提交</button>
        <button type="reset" class="middle">重置</button>
        <a class = "right" href="<%= (String)request.getAttribute("returnLink") %>">返回</a>
    </p>
</form>
</body>
</html>