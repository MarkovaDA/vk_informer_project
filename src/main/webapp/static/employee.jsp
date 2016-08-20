

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Employee page</title>
<link rel="stylesheet" href='<spring:url value="css/styles.css"/>' />
<script type="text/javascript" src='<spring:url value="js/app.js"/>'></script>
<script type="text/javascript">

</script>
</head>
<body>
	<h2>Employee Home Page</h2>
         <p>Welcome, ${user.firstName} ${user.lastName}</p>
         <a href="<c:url value="/logout" />">Logout</a>
</body>
</html>
