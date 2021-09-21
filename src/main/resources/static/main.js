
    $('document').ready(function(){


        $('.table #modalTeacher').on('click', function(event) {

            event.preventDefault();

            		var href= $(this).attr('href');

            		$.get(href, function(course, status){
            			$('#idCourse').val(course.id);
            			$('#nameCourse').val(course.name);
            			$('#daw').val(course.description);
            			$('#nameCodawurse').val(course.videoURL);
            			$('#nameadwadCourse').val(course.videoExplanation);
            		});

            $('#addTeacherModal').modal();

        });

        $('#ex-basic').picker();

    });