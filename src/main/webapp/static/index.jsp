<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--титульная страничка приложения-->
<!DOCTYPE html>
<html>
  <head>
    <title>Angular 2 QuickStart</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
  <!-- 3. Display the application -->
  <body>
  <my-app>Loading...</my-app>
  </body>
</html>