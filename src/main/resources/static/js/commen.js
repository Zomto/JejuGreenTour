// commen.js

// 헤더 - 여행지 키워드
$('.hk_current').slick({
    autoplay : true,
    vertical : true,
    arrows : false
});

// 헤더 - 전체메뉴 열기
$('.btn_open_allmenu').click(function(){
    $(this).addClass('on');
    $('.allmenu').show();
    return false;
});

// 헤더 - 전체메뉴 닫기
$('.allmenu_close').click(function(){
    $('.btn_open_allmenu').removeClass('on');
    $('.allmenu').hide();
    return false;
});

// 헤더 - gnb
$('.gnb .d1').mouseover(function(){
    $(this).find('.depth2').show();
    $('.gnb_bg').show();
}).mouseout(function(){
    $(this).find('.depth2').hide();
    $('.gnb_bg').hide();
});

$(document).ready(function(){
	
	$('.tab-link').click(function(){
		var tab_id = $(this).attr('data-tab');

		$('.tab-link').removeClass('current');
		$('.tab-content').removeClass('current');

		$(this).addClass('current');
		$("#"+tab_id).addClass('current');
	})

})