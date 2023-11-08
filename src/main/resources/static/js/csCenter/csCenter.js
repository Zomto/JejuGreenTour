$('.m2_qna').click(function(){
    let answer = $(this).children('.m2_qna_answer').css('display');
    if(answer == 'block'){
        $('.m2_qna').removeClass('active');
        $('.m2_qna_answer').slideUp();
    }else{
        $('.m2_qna').removeClass('active');
        $('.m2_qna_answer').slideUp();
        $(this).children('.m2_qna_answer').slideDown();
        $(this).addClass('active');
    }
    return false;
});



