// $('.myPageSide').click(function(){
//     if($(this))
//     $(this).add('on')
// })

// $('.myPageSide').click(function(){
//     let clickSide = $(this).children('.myPageA').css('color');
//     if(clickSide == '#1db954' ){
//         $('.myPageSide').removeClass('on');
//         $(this).addClass('on');
//     } else{
//         $('.myPageSide').removeClass('on');
//         $(this).addClass('on');
//     }

// });
$('.myPageSide').click(function(){
    // 선택한 a 태그의 색상을 가져옵니다.
    let clickSideColor = $(this).css('color');
    let targetColor = 'rgb(29, 185, 84)';

    if (clickSideColor === targetColor) {
        $('.myPageSide').removeClass('on');
        $(this).addClass('on');

    } else {
        $('.myPageSide').removeClass('on');
        $(this).addClass('on');
    }
});