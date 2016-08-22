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
    <br><br>
    <div class="ui segment" style="margin: 0 auto; width: 90%;">
        <a class="ui orange right ribbon label" style="font-size: 1.2em !important;" href="logout">Выход</a>
        <p></p> 
        <div class="ui  grid">
            <div class="row"> 
                 <img style="margin: 0 auto;" src = "<c:url value='images/logo.jpg'/>">            
            </div>
            <div class="row">
                <!--navigation-->
                <div class="three wide column">

                    <div class="ui compact menu" style="display: inline-block !important;width:100%;">
                        <a class="item">
                          <i class="icon paw"></i> My pets 
                          <div class="floating ui orange label">5</div>
                        </a>
                        <a class="item">
                          <i class="icon alarm"></i> My issues
                          <div class="floating ui orange label">12</div>
                        </a>
                    </div>
                </div>
                <!--main content-->
                <div class="twelve wide column ui segment orange_top">                 
                     <h2 style="text-align: center;">Client Home Page</h2>
                    
                    <p>Welcome, ${user.firstName} ${user.lastName}</p>

                    <div class="ui divided selection list">
                        <c:choose>
                            <c:when test="${not empty issue}">
                            <a class="item">
                                <div  class="ui orange horizontal label">Последнее обращение от: </div> ${issue.date}
                            </a>
                            <a class="item">
                                <div class="ui orange horizontal label">доктор: </div>${issue.employee.lastName} ${issue.employee.lastName}</i></p>
                            </a>
                            <a class="item">
                                <div class="ui orange horizontal label">статус обращения:</div> <b>${issue.status.title}</b>
                            </a>
                            <a class="item">
                            <b>Питомцы:</b>
                                <ul>
                                    <c:forEach var="pet" items="${pets}">
                                        <li>${pet.name}</li>                            
                                    </c:forEach>
                                </ul>
                            </a>
                            <a class="item">
                            <textarea rows="5" style="width:100%;">Текст обращения, изъятый из другой базы</textarea>
                            </a>
                            </c:when>
                            <c:otherwise>
                                У Вас еще нет обращений...
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
	
</body>
</html>
