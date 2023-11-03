$('.m2_qna').click(function(){
    let answer = $(this).children('.m2_qna_answer').css('display');
    if(answer == 'block'){
        $('.m2_qna_answer').removeClass('active');
        $('.m2_qna_answer').slideUp();
    }else{
        $('.m2_qna_answer').removeClass('active');
        $('.m2_qna_answer').slideUp();
        $(this).children('.m2_qna_answer').slideDown();
        $(this).children('.m2_qna_answer').addClass('active');
    }
    return false;
});



