<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

${lecture.name } is New lecture. Thank U <br/>
	<P>
		<a href="${pageContext.request.contextPath}/lectures">click here to view current lectures </a>
	</P>
</body>
</html>