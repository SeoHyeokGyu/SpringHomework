<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	<table>
		<tr>
			<td>년도</td>
			<td>학기</td>
			<td>코드</td>
			<td>이름</td>
			<td>구분</td>
			<td>학점</td>
		</tr>
		<c:forEach var="lecture" items="${specifier}">
			<tr>
				<td><c:out value="${lecture.year}">
					</c:out></td>
				<td><c:out value="${lecture.semester}">
					</c:out></td>
				<td><c:out value="${lecture.code}">
					</c:out></td>
				<td><c:out value="${lecture.name}">
					</c:out></td>
				<td><c:out value="${lecture.division}">
					</c:out></td>
				<td><c:out value="${lecture.point}">
					</c:out></td>

			</tr>

		</c:forEach>
		<tr>
			<td colspan="6"><input type="button" class="button"
				onclick="location.href = '${pageContext.request.contextPath }/' "
				value="home"></td>
		</tr>
	</table>
</body>
</html>