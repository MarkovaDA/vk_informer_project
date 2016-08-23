<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Welcome</title>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
        <script type="text/javascript" src='<c:url value="js/semantic.min.js"/>'></script>
	<link href="<c:url value='css/semantic.min.css' />" rel="stylesheet"></link>
        <link href="<c:url value='css/custom_style.css' />" rel="stylesheet"></link>
</head>
<body>
    <br><br>
    <div class="ui segment" style="margin: 0 auto; width: 90%;">
        <a class="ui orange right ribbon label" style="font-size: 1.2em !important;" href="entrance">Login</a>
        <p></p>  
        <div class="ui five column grid">
            <div class="row"> 
                 <img style="margin: 0 auto;" src = "<c:url value='images/logo.jpg'/>">            
            </div>
            <div class="row">
                <div class="ui segment orange_top" style="width:90%; margin:0 auto;">
                    <h3 class="ui header">
                        <img src="<c:url value='images/icon.png'/>" class="ui circular image">
                        О нашей клинике
                    </h3>                        
                        <p>
                           Ветеринарная клиника «Центр» располагается в дореволюционном особняке на Цветном бульваре. 
                        </p>
                        <p>
                            Врачи ветеринарной клиники «Центр», вот уже 25 лет лечат собак, кошек и других питомцев, и каждый год нашей работы – это бесценный опыт, новые знания, а главное — благодарные звери и их хозяева
                        </p>
                        <p>
                            В клинике работают 9 кандидатов наук. Наши врачи являются лекторами Ассоциации практикующих ветеринарных врачей РФ, принимают участие в обучении студентов в различных вузах страны. Силами нашей клиники выпущено несколько научных трудов, атласов, опубликовано множество статей в профессиональных журналах.
                        </p>
                        <p>
                            Коллектив клиники – сплоченная команда увлеченных профессионалов. В большинстве случаев нам удается выходить и «поставить на лапы» пациентов с самыми сложными заболеваниями и травмами.
                        </p>
                        
                        <p>
                            В клинике есть все, что необходимо для точной диагностики и успешного лечения: первоклассное диагностическое оборудование, собственная лаборатория, выполняющая полный спектр исследований, хирургический блок, отделение интенсивной терапии.
                        </p>
                </div>
            </div>
            <div class="row" style="width:auto !important; margin: 0 auto !important;">
                <div class="column">
                    <div class="ui circular segment" style="float: right !important;">
                        <img src = "<c:url value='images/preview1.jpg'/>" class="preview_img">
                    </div>
                </div>
                <div class="column">
                    <div class="ui circular segment" style="float: right !important;">
                        <img src = "<c:url value='images/preview2.jpg'/>" class="preview_img">
                    </div>
                </div>
                <div class="column">
                    <div class="ui circular segment" style="float: right !important;">
                        <img src = "<c:url value='images/preview3.jpg'/>" class="preview_img">
                    </div>
                </div>
                <div class="column">
                    <div class="ui circular segment" style="float: right !important;">
                        <img src = "<c:url value='images/preview4.jpg'/>" class="preview_img">
                    </div>
                </div>
                <div class="column">
                    <div class="ui circular segment" style="float: right;">
                        <img src = "<c:url value='images/preview5.jpg'/>" class="preview_img">
                    </div>
                </div>
            </div>
            
        </div>
        <div class="ui four column grid">
            <div class="row">
            </div>
        </div>
        
    </div>
</body>
</html>