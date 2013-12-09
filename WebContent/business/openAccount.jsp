<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html">
<html>
<jsp:include page="businessHeader.jsp"/>
<body>
<%session.setAttribute("businesstype", "openaccount");%>
<h2>
<%= (String)request.getAttribute("title")%>业务：开户
</h2>
<form method="post" action="<%=request.getContextPath()%>/businessservice.action">
	<ul>
	<%  String usertype = (String)request.getAttribute("usertype");
		if(usertype.equals("enterprise")){
			out.println("<li>");
			out.println("<label for = 'enterpriseid'>企业编号： </label>");
			out.println("<input type = 'text' name = 'enterpriseid' size = '40' required='required'/>");
			out.println("</li>");
			
			out.println("<li>");
			out.println("<label for = 'enterprisename'>企业名称：</label>");
			out.println("<input type = 'text' name = 'enterprisename' size = '40 required='required'/>");
			out.println("</li>");
		}
	%>
        <li>
        	<label for="userid">
        	<%
	        	if(usertype.equals("enterprise"))
	        		out.print("法人身份证号:");
        		else
        			out.print("身份证号:");
        	%>
        	</label>
            <input type="text" name = "userid" size="40" required="required"/>
        </li>
        <li>
        	<label for="name">
        	 <%
	        	if(usertype.equals("enterprise"))
	        		out.print("法人姓名:");
        		else
        			out.print("姓名:");
        	%>
        	</label>
            <input type="text" name = "name" size="40" required="required"/>
        </li>
        <li>
            <label for="type">账户类型:</label>
            <select name = "type">
				<option value = "current">活期存款</option>
				<option value = "fixed">定期存款</option>
            </select>
        </li>
        <li>
        	<label for="money">存款金额:</label>
            <input type="number"
            <% if(usertype.equals("vip")){
            		out.print("min='1000000'");
            } else{
            	out.print("min='0'");
            }
            
            %> step = "0.01" name = "money" size="40" required="required"/>
        </li>
        <li>
        	<label for="password">初始密码:</label>
            <input type="password" name = "password" size="40" required="required"/>
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