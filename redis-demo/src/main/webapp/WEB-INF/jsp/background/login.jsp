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
		<form action="${ctx }/background/loginSubmit" method="post">
			<h2>Login</h2>
			<label for="username">username:</label><input type="text" name="username" /><br />
			<label for="password">password:</label><input type="password" name="password" /><br />
			<input type="submit" value="submit"/>
		</form>
	</center>
</body>
</html>