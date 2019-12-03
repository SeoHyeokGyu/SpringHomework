<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>
<head>
<style>
table, th, td {
	border: 1px solid black;
	text-align: center;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>
	<table>
		<tr>
			<td>년도</td>
			<td>학기</td>
			<td>이수 학점</td>
			<td>상세보기</td>
		</tr>
		<c:forEach var="offer" items="${offers}">
			<tr>
				<td><c:out value="${offer.year}">
					</c:out></td>
				<td><c:out value="${offer.semester}">
					</c:out></td>
				<td><c:out value="${offer.point}">
					</c:out></td>
				<td>
					 <a href="${pageContext.request.contextPath }/specifier?year=${offer.year}&semester=${offer.semester}  " >상세보기	</a></td>

			</tr>
		</c:forEach>

	</table>


</body>
</html>