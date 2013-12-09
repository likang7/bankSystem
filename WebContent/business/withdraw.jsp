<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html">
<html>
<jsp:include page="businessHeader.jsp"/>
<body>
<%session.setAttribute("businesstype", "withdraw");%>
<h2>
<%= (String)request.getAttribute("title")%>业务：取款
</h2>
<form method="post" action="<%=request.getContextPath()%>/businessservice.action">
	<ul>
        <li>
        	<label for="cardid">账号:</label>
            <input type="number" size="40" name = "cardid" required="required"/>
        </li>
        <li>
        	<label for="password">密码:</label>
            <input type="password" size="40" name = "password" required="required"/>
        </li>
        <li>
        	<label for="money">取款金额:</label>
            <input type="number"  min = "0" step = "0.01" size="40" name = "money" required="required"/>
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