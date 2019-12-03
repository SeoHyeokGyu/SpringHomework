<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/main.css">
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
		<c:forEach var="lecture" items="${lectures}">
			<tr>
				<td><c:out value="${lecture.year}">
					</c:out></td>
				<td><c:out value="${lecture.semester}">
					</c:out></td>
				<td><c:out value="${lecture.point}">
					</c:out></td>
				<td><a
					href="${pageContext.request.contextPath }/specifier?year=${lecture.year}&semester=${lecture.semester}  ">상세보기
				</a></td>

			</tr>
		</c:forEach>
		<tr>
			<td colspan="4"><input type="button" class="button"
				onclick="location.href = '${pageContext.request.contextPath }/' "
				value="home"></td>
		</tr>
	</table>


</body>
</html>