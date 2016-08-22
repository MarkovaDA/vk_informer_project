<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My pets</title>
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
        <pets>Loading pets...</pets>
    </body>
</html>
