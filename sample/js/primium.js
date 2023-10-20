$('.topBn_close').click(function(){
    $('#topBn_in').animate({
        marginTop : '-20px'
    },function(){
        // $(this).hide();
        $(this).remove();
    });
});

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