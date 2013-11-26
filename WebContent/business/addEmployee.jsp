<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../css/style.css" />
<link href='http://fonts.googleapis.com/css?family=Engagement' rel='stylesheet' type='text/css'>
<title>后台管理：添加雇员</title>
</head>
<body>
<%session.setAttribute("businesstype", "addemployee");%>
<h2>后台管理：添加雇员</h2>
<form method="post" action="../departmentservice">
	<ul>
		<li>
        	<label for="username">用户名:</label>
            <input type="text" size="40" name = "username" required="required"/>
        </li>
        <li>
        	<label for="passwd">密码:</label>
            <input type="password" size="40" name = "password" required="required"/>
        </li>
        <li>  
        	<label>部门名称：</label>
     		  		       		
       		<label> 
           		<input type="radio" size="40" name = "departmentId" value = "1" required="required"/>
           		个人业务部门
           </label>
	       
	       <label>
	       		<input type="radio" size = "40" name = "departmentId" value = "2" required = "required"/>
	       		企业业务部门
	       </label>	                
        </li> 
           
        <li>
            <label for="position">职位:</label>
            <select name = "position">
				<option value = "1">业务主管</option>
				<option value = "2">部门经理</option>
				<option value = "3">前台操作员</option>
            </select>
        </li>
        
        <li>
        	<label for="superiorid">上级用户名：</label>
        	<input type = "text" size = "40" name = "superiorid" required="required"/>
        </li>  
	</ul>
    <p>
        <button type="submit" class="action">提交</button>
        <button type="reset" class="middle">重置</button>
        <a class = "right" href="departmentService.jsp">返回</a>
    </p>
</form>
</body>
</html>