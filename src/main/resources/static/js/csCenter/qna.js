$('.main5 .d1 .m1').click(function(){
    let d = $(this).siblings('.sub').css('display');
    if(d == 'block'){
        $('.main5 .d1').removeClass('active');
        $('.main5 .d1 .sub').slideUp();
    } else {
        $('.main5 .d1').removeClass('active');
        $('.main5 .d1 .sub').slideUp();
        $(this).parent('.d1').addClass('active');
        $(this).siblings('.sub').slideDown();
    }
    return false;
});




// $('.qna_d1').click(function(){
//     let qna = $(this).siblings('.qna_d1').css('display');
//     if(qna == 'block'){

//     }else{


//     }
//     return false;
// });