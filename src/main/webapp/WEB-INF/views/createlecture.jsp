<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/main.css">
</head>
<body>
	<!-- contextroot 와 매칭 
 -->
	<sf:form method="post"
		action="${pageContext.request.contextPath }/docreate"
		modelAttribute="lecture">
		<table class="formtable">
			<tr>
				<td>수강년도 :</td>
				<td><sf:input class="control" type="text" path="year" /> <br />
					<sf:errors path="year" class="error"></sf:errors></td>
			</tr>
			<tr>
				<td>학기 :</td>
				<td><sf:input class="control" type="text" path="semester" /><br />
					<sf:errors path="semester" class="error"></sf:errors></td>
			</tr>
			<tr>
				<td>교과코드 :</td>
				<td><sf:input class="control" type="text" path="code" /><br />
					<sf:errors path="code" class="error"></sf:errors></td>
			</tr>
			<tr>
				<td>교과목명 :</td>
				<td><sf:input class="control" type="text" path="name" /><br />
					<sf:errors path="name" class="error"></sf:errors></td>
			</tr>
			<tr>
				<td>구분 :</td>
				<td><sf:input class="control" type="text" path="division" /><br />
					<sf:errors path="division" class="error"></sf:errors></td>
			</tr>
			<tr>
				<td>학점 :</td>
				<td><sf:input class="control" type="text" path="point" /><br />
					<sf:errors path="point" class="error"></sf:errors></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="reset" class="button" value="Reset" />
					<input type="submit" class="button" value="수강 신청하기" />
					<input type="button" class="button" onclick="location.href = '${pageContext.request.contextPath }/' "value="home">
					</td>
			</tr>
		</table>
	</sf:form>

</body>
</html>