<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Client page</title>
<!--<link rel="stylesheet" href='<spring:url value="css/styles.css"/>' />
<script type="text/javascript" src='<spring:url value="js/app.js"/>'></script>
<script type="text/javascript">
</script>-->
<link rel="stylesheet" href="css/style.css">
    <!-- 1. Load libraries -->
    <!-- Polyfill(s) for older browsers -->
    <script src="<c:url value='node_modules/core-js/client/shim.min.js'/>"></script>
    <script src="<c:url value='node_modules/zone.js/dist/zone.js'/>"></script>
    <script src="<c:url value='node_modules/reflect-metadata/Reflect.js'/>"></script>
    <script src="<c:url value='node_modules/systemjs/dist/system.src.js'/>"></script>
    <!-- 2. Configure SystemJS -->
    <script src="<c:url value='js/systemjs.config.js'/>"></script>
    <script>
      System.import('app').catch(function(err){ console.error(err); });
    </script>
</head>
<body>
	<!--<h2>Client Home Page</h2>
        <p>Welcome, ${user.firstName} ${user.lastName}</p>
        <a href="<c:url value="/logout" />">Logout</a> -->
        <client_page>Loading...</client_page>
</body>
</html>
