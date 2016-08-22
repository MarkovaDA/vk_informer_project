<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Client page</title>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <script type="text/javascript" src='<c:url value="js/semantic.min.js"/>'></script>
    <link href="<c:url value='css/semantic.min.css' />" rel="stylesheet"></link>
    <link href="<c:url value='css/custom_style.css' />" rel="stylesheet"></link>
</head>
<body>
	<h2>Client Home Page</h2>
        <p>Welcome, ${user.firstName} ${user.lastName}</p>
        <a href="<c:url value="/logout" />">Logout</a> | <a href="client/pets">My pets</a>
        <div>
            <c:choose>
                <c:when test="${not empty issue}">
                <h3>Последнее обращение от: ${issue.date}</h3> 
                <p>доктор: <i>${issue.employee.lastName} ${issue.employee.lastName}</i></p>
                <p>статус обращения: <b>${issue.status.title}</b></p>
                <p>Питомцы:</p>
                    <ul>
                        <c:forEach var="pet" items="${pets}">
                            <li>${pet.name}</li>                            
                        </c:forEach>
                    </ul>
                <textarea rows="5" cols="30" disabled="true">Текст обращения, изъятый из другой базы</textarea>
                </c:when>
                <c:otherwise>
                    У Вас еще нет обращений...
                </c:otherwise>
            </c:choose>
        </div>
</body>
</html>
