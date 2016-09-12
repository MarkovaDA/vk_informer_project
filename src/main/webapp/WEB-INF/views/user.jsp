<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User tools</title>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
        <script type="text/javascript" src='<c:url value="static_resources/js/semantic.min.js"/>'></script>
        <script type="text/javascript" src='<c:url value="static_resources/js/customer.select.js"/>'></script>
        <link href="<c:url value='static_resources/css/semantic.min.css' />" rel="stylesheet"></link>
        <link href="<c:url value='static_resources/css/customer.select.css' />" rel="stylesheet"></link>
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
                <div class="eight wide column" style="text-align: left !important;">
                    <!-- выбор курса-->
                    <div class="wrapper_select">
                        <div class="select_header">
                            <input type="text" placeholder="выберите курс">
                            <button class="mini ui icon violet basic button">
                                <i class="angle down icon"></i>
                            </button>
                        </div>
                        <div class="select_items">
                            <div>1 курс</div>
                            <div>2 курс</div>
                            <div>3 курс</div>
                            <div>4 курс</div>
                            <div>5 курс (1 курс магистратуры)</div>
                            <div>6 курс (2 курс магистратуры)</div>
                       </div>
                    </div>
                    <br>
                    <!--выбор группы-->
                    <div class="wrapper_select">
                        <div class="select_header">
                            <input type="text" placeholder="выберите группу">
                            <button class="mini ui icon violet basic button">
                                <i class="angle down icon"></i>
                            </button>
                        </div>
                        <div class="select_items">
                            <div>1 группа</div>
                            <div>2 группа</div>
                            <div>3 группа</div>
                            <div>4 группа</div>
                       </div>
                    </div>
                    <br>
                    <div class="ui checkbox">
                        <input type="checkbox">
                        <label>Отправить только старосте</label>
                    </div>
                    <br> <br>
                    <div class="ui checkbox">
                        <input type="checkbox">
                        <label>Добавить подпись</label>                      
                    </div>
                    <br><br>
                    <button class="ui violet button">добавить</button>
                </div>
                <!--вторая колонка-->
                <div class="eight wide column" style="text-align: left !important;">
                    <textarea style="width:100%;" rows="9"></textarea>
                    <br>
                    <button class="ui violet button">отправить</button>
                    <button class="ui violet button" style="float:right !important;">прикрепить файл</button>
                </div>                                    
        </div>
    </body>
</html>
