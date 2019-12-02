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
		modelAttribute="offer">
		<table class="formtable">
			<tr>
				<td class="label">Name :</td>
				<td><sf:input class="control" type="text" path="name" /> <br/> 
				<sf:errors path="name" class="error" ></sf:errors></td>
			</tr>
			<tr>
				<td class="label">Email :</td>
				<td><sf:input class="control" type="text" path="email" /><br/> 
				<sf:errors path="email" class="error"></sf:errors></td>
			</tr>
			<tr>
				<td class="label">Offer :</td>
				<td><sf:textarea class="control" path="text" rows="10"
						cols="10" /><br/> 
				<sf:errors path="text" class="error"></sf:errors></td>
			</tr>
			<tr>
				<td class="label"></td>
				<td><input class="control" type="submit" value="New Offer" /></td>
			</tr>

		</table>
	</sf:form>

</body>
</html>