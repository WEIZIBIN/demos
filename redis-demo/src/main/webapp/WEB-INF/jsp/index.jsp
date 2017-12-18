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
		Background: <a href="${ctx }/background/login">login</a>
		User Index: <a href="${ctx }/background/user/index">goto</a>
		Admin Index: <a href="${ctx }/background/admin/index">goto</a>
		Logout: <form method="post" action="${ctx }/background/logout">
		<input type="submit" value="submit"/></form>
	</center>
</body>
</html>