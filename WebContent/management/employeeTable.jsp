<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<table class="gridtable">
<tr>
	<th>员工</th>
	<th>职称</th>
	<th>部门</th>
	<th>上级</th>
	<th>选择</th>
</tr>
<%
String msg = (String)request.getAttribute("employees");
String[] logs = msg.split("<br>");
for(String log : logs){
	out.println("<tr>");
	String[] pairs = log.split(",");
	String employeeName = "";
	boolean first = true;
	int cnt = 0;
	for(String pair : pairs){
		String[] values = pair.split("=");
		if(cnt == 0){
			employeeName = values[1];
		}
		if(cnt == 1){
			if(values[1].equals("Supervisor")){
				values[1] = "业务主管";
			}
			else if(values[1].equals("Manager")){
				values[1] = "银行经理";
			}
			else if(values[1].equals("Operator")){
				values[1] = "前台操作员";
			}
			else if(values[1].equals("Administrator")){
				values[1] = "系统管理员";
			}
		}
		if(cnt == 2){
			if(values[1].equals("1")){
				values[1] = "个人业务部";
			}
			else if(values[1].equals("2")){
				values[1] = "企业业务部"; 
			}
		}
		cnt++;
		out.println("<td>" + values[1] + "</td>");
	}
	out.println("<td><input type='checkbox' id='selectedEmployee' name='selectedEmployee' value='" + employeeName + "'></td>");
	out.println("</tr>");
}
%>
</table>
</body>
</html>