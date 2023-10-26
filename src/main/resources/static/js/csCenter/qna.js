$('.qna_d1').click(function(){
    let answer = $(this).children('.qna_answer').css('display');
    if(answer == 'block'){
        $('.qna_d1').removeClass('active');
        $('.qna_answer').slideUp();
    }else{
        $('.qna_d1').removeClass('active');
        $('.qna_answer').slideUp();
        $(this).children('.qna_answer').slideDown();
        $(this).children('.qna_answer').addClass('active');
    }
    return false;
});