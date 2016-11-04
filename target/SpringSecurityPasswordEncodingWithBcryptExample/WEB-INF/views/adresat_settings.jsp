<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--страничка настройки параметров адресата-->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User tools</title>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
        <script type="text/javascript" src='<c:url value="/static_resources/js/semantic.min.js"/>'></script>
        <script type="text/javascript" src='<c:url value="/static_resources/js/customer.select.js"/>'></script>
        <script type="text/javascript" src='<c:url value="/static_resources/js/data.injection.js"/>'></script>
        <link href="<c:url value='/static_resources/css/semantic.min.css' />" rel="stylesheet"></link>
    </head>
    <body>
        <div class="ui centered grid raised segment" style="width: 80%; margin: 0px auto !important; margin-top:50px !important;" >               
            <div class="sixteen wide column">
                <div class="ui violet clearing segment">
                    <div class="ui left floated segment" style="margin: 0px;padding:0px; border:none; box-shadow: none;">
                        <i class="large user icon"></i>
                        <!--Вы вошли в систему как
                        <a style="font-size: 1.1em !important; color: #4f20b5;">${user}</a>
                        (ваш идентификатор "вконтакте") -->
                        Пользовательские настройки 
                    </div>
                    <div class="ui right floated segment" style="margin: 0px;padding:0px; border:none; box-shadow: none;">
                        <i class="large home icon"></i>
                         <a style="font-size: 1.1em !important; color: #4f20b5;" 
                            href="<c:url value="/adresat"/>">Home                    
                         </a>
                    </div>                   
                </div>
            </div>
            <div class="twelve wide column" style="text-align: left !important;">               
                <p>получать уведомления:</p>
                <div class="ui checked checkbox">
                    <input type="checkbox" checked="">                    
                    <label> <i class="mail outline icon"></i> по почте</label>
                </div>
                <br><br>
                <div class="ui checked checkbox">
                    <input type="checkbox" checked="">
                    <label> <i class="vk outline icon"></i> через "вконтакте"</label>
                </div> 
                <br><br>
                <a href="" style="color: #4f20b5;">редактирвать почту</a><br>              
                <a href="" style="color: #4f20b5;">изменить пароль</a>
            </div>
         
            <div class="sixteen wide column" style="text-align:right !important;">
                <button class="ui violet button">применить</button>  
                <button class="ui violet basic button">отмена</button>
            </div>
        </div>     
    </body>
</html>


