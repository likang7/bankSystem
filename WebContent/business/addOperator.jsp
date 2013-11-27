<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html">
<html>
<jsp:include page="businessHeader.jsp"/>
<body>
<%session.setAttribute("businesstype", "addoperator");%>
<h2>
<%= (String)request.getAttribute("title")%>业务：开户
</h2>
<form method="post" action="<%=request.getContextPath()%>/businessservice.action">
	<ul>
        <li>
        	<label for="userid">身份证号：</label>
            <input type="text" name = "userid" size="40" required="required"/>
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
        	<label for="newuserid">新增操作人身份证号：</label>
            <input type="text" name = "newuserid" size="40" required="required"/>
        </li>
        <li>
        	<label for="newusername">新增操作人姓名：</label>
            <input type="text" name = "newusername" size="40" required="required"/>
        </li>
        <li>
        	<label for="newpassword">新增操作人初始密码:</label>
            <input type="password" name = "newpassword" size="40" required="required"/>
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