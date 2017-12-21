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
		<c:if test="${not empty msg}">
		System message : ${msg}
		</c:if>
		Your customer id : ${customerId}
		<table border="1">
			<thead>
			<tr>
				<th>#</th>
				<th>id</th>
				<th>name</th>
				<th>in stock</th>
				<th>start kill time</th>
				<th>stock</th>
				<th>action</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${killGoodsList}" var="killGoods" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${killGoods.id}</td>
					<td>${killGoods.name}</td>
					<td>${killGoods.inStock}</td>
					<td><fmt:formatDate value="${killGoods.startKillTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${killGoods.stock}</td>
					<td>
						<form method="post" action="${ctx}/kill">
							<input type="hidden" name="killGoodsId" value="${killGoods.id}" />
							<input type="submit" value="kill"  />
						</form>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<table border="1">
			<thead>
			<tr>
				<th>#</th>
				<th>id</th>
				<th>goods id</th>
				<th>customer id</th>
				<th>kill time</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${killGoodsRecordList}" var="killGoodsRecord" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${killGoodsRecord.id}</td>
					<td>${killGoodsRecord.goodsId}</td>
					<td>${killGoodsRecord.customerId}</td>
					<td><fmt:formatDate value="${killGoodsRecord.killTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</center>
</body>
</html>