// main.js

// 탑배너 닫기
$('.topBn_close').click(function(){
    $('#topBn').animate({
        marginTop : '-40px'
    },function(){
        // $(this).hide();
        $(this).remove();
    });
});

//Play list 슬라이드
$('.list').slick({
  mobileFirst: true,
  arrows: false,
  slidesToShow: 1,
  responsive: [
    {
      breakpoint: 767,
      settings: {
        slidesToShow: 2
      }
    },
    {
      breakpoint: 1280,
      settings: {
        slidesToShow : 8
      }
    }
  ]
});



/*
    * hide() 명령 
    - 태그의 diplay값을 none처리. 
    - display:none 처리 된 영역은 화면에 없는 태그 취급하지만 검사도구에서 확인해보면 여전히 남아 있는 상태. 
    * remove() 명령
    - 태그를 문서상에서 삭제함. 
    - 검사도구에서 확인해보면 해당 태그 자체가 사라지게 됨.
*/ 