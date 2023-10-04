// main.js

// 상단배너 닫기
$('.topBn_close').click(function(){
    // $('#topBn').hide();
    $('#topBn').animate({
        marginTop : '-90px'
    });
    return false;
});