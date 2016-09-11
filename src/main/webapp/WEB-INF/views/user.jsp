<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User tools</title>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
        <script type="text/javascript" src='<c:url value="static_resources/js/semantic.min.js"/>'></script>
        <link href="<c:url value='static_resources/css/semantic.min.css' />" rel="stylesheet"></link>
    </head>
    <body>
        <div class="ui centered grid raised segment" style="width: 80%; margin: 0px auto !important; margin-top:50px !important;" >               
            <div class="sixteen wide column">
                <div class="ui violet clearing segment">
                    <div class="ui left floated segment" style="margin: 0px;padding:0px; border:none; box-shadow: none;">
                        <i class="large user icon"></i>
                        Вы вошли в систему как
                        <a style="font-size: 1.1em !important; color: #4f20b5;">${user}</a>
                    </div>
                    <div class="ui right floated segment" style="margin: 0px;padding:0px; border:none; box-shadow: none;">
                        <i class="large setting icon"></i>
                         <a style="font-size: 1.1em !important; color: #4f20b5;">Settings</a>
                    </div>                   
                </div>
            </div>
                <div class="eight wide column">
                </div>
                <div class="eight wide column">
                    <textarea style="width:100%;" rows="10"></textarea>
                    <br>
                    <button class="ui violet button">отправить</button>
                </div>                                    
        </div>
    </body>
</html>
