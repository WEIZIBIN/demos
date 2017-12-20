<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/background_include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<form action="${ctx}/add" method="post">
			<label>name:</label><input type="text" name="name" ><br>
			<label>stock:</label><input type="number" name="stock" min="0"><br>
			<label>kill time:</label><input type="text" name="startKillTimeStr" ><br>Example:2017-12-20 10:20:00<br>
			<input type="submit" value="add"></input>
		</form>
	</center>
</body>
</html>