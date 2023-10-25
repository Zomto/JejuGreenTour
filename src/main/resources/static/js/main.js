// main.js

// 상단배너 닫기
$('.topBn_close').click(function(){
    // $('#topBn').hide();
    $('#topBn').animate({
        marginTop : '-90px'
    });
    return false;
});

//메인 베너 slick
$(document).ready(function () {
    $('.m_slide_list').slick({
        autoplay: true,
        autoplaySpeed: 5000,
        speed: 1000,
        dots: true,
        prevArrow: '.slide_prev',
        nextArrow: '.slide_next',
        appendDots: '.m_pager',
    });

    // 배경색을 변경하는 함수
    function changeBackgroundColor(currentSlide) {
        // Define background colors for each slide
        const backgroundColors = ['rgba(74, 147, 216, 1)', 'rgba(33, 84, 202, 1)', 'rgba(185, 217, 242, 1)', 'rgba(0, 144, 230, 1)'];
        
        // Get the #Visual_Main element
        const visualMain = $('#Visual_Main');

        // Change the background color based on the current slide index
        visualMain.css('background-color', backgroundColors[currentSlide]);
    }

    // After each slide change
    $('.m_slide_list').on('afterChange', function (event, slick, currentSlide) {
        changeBackgroundColor(currentSlide);
    });

    // Set initial background color
    changeBackgroundColor(0);

    // Play and pause controls
    $('.m_play').click(function () {
        $('.m_slide_list').slick('slickPlay');
        $(this).hide();
        $('.m_pause').show();
    });
    $('.m_pause').click(function () {
        $('.m_slide_list').slick('slickPause');
        $(this).hide();
        $('.m_play').show();
    });
});


    
    
    
    
    
    