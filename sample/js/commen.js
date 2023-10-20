// commen.js

// * 브라우저 스크롤 이벤트 시작
$(window).scroll(function(){
    // 브라우저 세로 스크롤의 현재 위치값 저장하는 변수
    let st = $(window).scrollTop();

    // 헤더 고정하기
    if(st >= 40){
        $('#header').addClass('fixed');
    } else {
        $('#header').removeClass('fixed');
    }
}); // 브라우저 스크롤 이벤트 끝

// 검색창 열기
$('.h_search .open').click(function(){
    $('.h_search_wrap').show();
    $('html').css({
        overflow : 'hidden'
    });
    return false;
});

// 검색창 닫기
$('.h_search .close').click(function(){
    $('.h_search_wrap').hide();
    $('html').css({
        overflow : 'visible'
    });
});

// 검색창 안의 탭
$('.h_search .lank_btn p').click(function(){
    let i = $(this).index();
    $('.h_search .lank_btn p').removeClass('active');
    $(this).addClass('active');
    $('.h_search .lank_box').removeClass('active');
    $('.h_search .lank_box').eq(i).addClass('active');
});

// 모바일 사이드 열기
$('.h_menu .open').click(function(){
    $('#side').animate({
        right : 0
    });
    $('html').css({
        overflow : 'hidden'
    });
    return false;
});

// 모바일 사이드 닫기
$('#side .close').click(function(){
    $('#side').animate({
        right : '-100%'
    });
    $('html').css({
        overflow : 'visible'
    });
});

// pc 로그인 사이드 열기 닫기는 모바일 사이드와 통일
$('.h_login_menu .open').click(function(){
    $('#side').animate({
        right : 0
    });
    $('html').css({
        overflow : 'hidden'
    });
    return false;
});

//모바일 사이드 비밀번호 가리기, 보이기
$(document).ready(function() {
    // '비밀번호 보이기' 버튼 클릭 시 동작
    $('#pw_off').click(function(e) {
      e.preventDefault(); // 링크 클릭 시 기본 동작 방지(preventDefault = 기본동작을 취소하는 기능을수행)
      $('#password-input').attr('type', 'text'); // 비밀번호 입력 필드의 type 속성을 'text'로 변경하여 비밀번호를 보여줌
      $(this).addClass('hide'); // '비밀번호 보이기' 버튼에 'hide' 클래스 추가하여 숨김
      $('#pw_on').removeClass('hide'); // '비밀번호 가리기' 버튼에서 'hide' 클래스 제거하여 표시
    });
  
    // '비밀번호 가리기' 버튼 클릭 시 동작
    $('#pw_on').click(function(e) {
      e.preventDefault(); // 링크 클릭 시 기본 동작 방지
      $('#password-input').attr('type', 'password'); // 비밀번호 입력 필드의 type 속성을 'password'로 변경하여 비밀번호를 가림
      $(this).addClass('hide'); // '비밀번호 가리기' 버튼에 'hide' 클래스 추가하여 숨김
      $('#pw_off').removeClass('hide'); // '비밀번호 보이기' 버튼에서 'hide' 클래스 제거하여 표시
    });
  });

  //체크박스 스위치 작동시 테두리 적용
  $(document).ready(function() {
    // 체크박스 변화(스위치 동작) 감지
    $('#toggle1').change(function() {
      if ($(this).is(':checked')) {
        // 체크박스가 선택된 경우 스타일 적용
        $(this).parent('.check').css({
          'border': '3px solid #fff',
          'margin': '3px',
          'border-radius': '15px'
        });
      }
    });
  });
    // 다른 곳 클릭 시 스타일 제거
    $(document).click(function(event) {
        if (!$(event.target).closest('.check').length) {
          $('.check').css({
            'border': 'none',
            'margin': '0'
          });
        }
      });
  