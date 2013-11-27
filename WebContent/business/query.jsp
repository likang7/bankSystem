<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html">
<html>
<jsp:include page="businessHeader.jsp"/>
<body>
<%session.setAttribute("businesstype", "query");%>
<h2>
<%= (String)request.getAttribute("title")%>业务：查询
</h2>
<form method="post" action="<%=request.getContextPath()%>/businessservice.action">
	<ul>
	    <li>
        	<label for="userid">身份证号:</label>
            <input type="text" size="40" name = "userid" required="required"/>
        </li>
        <li>
        	<label for="cardid">账号:</label>
            <input type="number" size="40" name = "cardid" required="required"/>
        </li>
        <li>
        	<label for="password">密码:</label>
            <input type="password" size="40" name = "password" required="required"/>
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
        <a class = "right" href="<%= (String)request.getAttribute("returnLink") %>">返回</a>
    </p>
</form>
</body>
</html>