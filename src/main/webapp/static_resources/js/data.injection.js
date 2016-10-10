

$(document).ready(function(){

    $('#select_faculties .select_items div').click(function(){

        var fac_id = parseInt($(this).attr("fac_id"));
        $('#select_courses .select_items').empty();
        //подгрузка курсов теперь
        $.get("api/get_courses?fac_id="+fac_id, function(data){
            
            //id, number
            for(var i=0; i < data.length; i++){
                var course = data[i];
                var option = '<div course_id='+course.id + '>' + course.number + '</div>';
                $('#select_courses .select_items').append(option);                
            }
            //привязать событие выборки
            $('#select_courses .select_items div').click(function()
            {
                $(this).parent().slideUp(100);
                $(this).parent().parent().find('input').val($(this).text());

                $.get("api/get_groups?course_id="+parseInt($(this).attr("course_id")), function(data){
                    console.log(data);
                });
            });
        });
    });
    //
   
});

