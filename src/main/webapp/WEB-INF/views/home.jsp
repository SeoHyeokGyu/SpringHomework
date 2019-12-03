<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<!DOCTYPE html>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1></h1>

	<P>
		<a href="${pageContext.request.contextPath}/lectures">show current
			lecture </a>
	</P>
	<P>
		<a href="${pageContext.request.contextPath}/createlecture">add a new
			lecture </a>
	</P>
	<P>
		<a href="${pageContext.request.contextPath}/specifier?year=2020&semester=1">add a new
			lecture list </a>
	</P>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<c:url var="logoutUrl" value="/logout" />

		<form class="form-inline" action="${logoutUrl}" method="post">
			<input type="submit" value="Log out" /> <input type="hidden"
				name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>

	</c:if>

</body>
</html>