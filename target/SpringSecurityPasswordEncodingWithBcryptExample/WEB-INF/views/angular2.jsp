<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Angular2 test</title>
        <link rel="stylesheet" href="<c:url value='/static/css/style.css'/>">
        <!-- Polyfill(s) for older browsers -->
        <script src="<c:url value='/static/node_modules/core-js/client/shim.min.js'/>"></script>
        <script src="<c:url value='/static/node_modules/zone.js/dist/zone.js'/>"></script>
        <script src="<c:url value='/static/node_modules/reflect-metadata/Reflect.js'/>"></script>
        <script src="<c:url value='/static/node_modules/systemjs/dist/system.src.js'/>"></script>
        <!-- 2. Configure SystemJS -->
        <script src="<c:url value='/static/js/systemjs.config.js'/>"></script>
        <script>
            System.import('app').catch(function(err){ console.error(err); });
        </script>
    </head>
    <body>
       <my-app>Loading...</my-app>
    </body>
</html>
