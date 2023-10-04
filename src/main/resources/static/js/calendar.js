
var startDate =null;
var startinfo=null;
var endDate = null;
document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        selectable: true,// 날짜 선택 가능하도록 설정
        select: function(info) {
          // 선택한 날짜 범위 가져오기
                if(startDate==null){
                    if(startinfo!=null){
                        calendar.removeAllEvents();
                    }
                    startinfo=info;
                startDate = info.startStr;
                    var event = {
                        id :'startDate',
                        title: '시작일', // 이벤트 제목
                        start: startDate, // 시작 날짜
                        end: startDate, // 끝나는 날짜
                        backgroundColor: 'red', // 배경색 설정
                    };
                    calendar.addEvent(event);
                    // 선택 영역 초기화
                    calendar.unselect();
                }else if(startDate<=info.startStr){
                    endDate = info.endStr;
                    
                    console.log(startDate);
                    console.log(endDate);
                    calendar.removeAllEvents();
                    var long = {
                        id :'longDate',
                        title: '예약일', // 이벤트 제목
                        start: startDate, // 시작 날짜
                        end: endDate, // 끝나는 날짜
                        display: 'background',
                        backgroundColor: 'green', // 배경색 설정
                    }; 
                    calendar.addEvent(long);
                    // 선택 영역 초기화
                    calendar.unselect();
                    startDate = null;
                    endDate = null;
                }else{
                    calendar.removeAllEvents();
                    startDate = null;
                    startinfo=info;
                    startDate = info.startStr;
                    var event = {
                        id :'startDate',
                        title: '시작일', // 이벤트 제목
                        start: startDate, // 시작 날짜
                        end: startDate, // 끝나는 날짜
                        backgroundColor: 'red', // 배경색 설정
                    };
                    calendar.addEvent(event);
                    // 선택 영역 초기화
                    calendar.unselect();
                }
        },
        eventClick: function(info) {
          // 이벤트를 클릭할 때 추가 작업 수행 (예: 이벤트 삭제)
          info.event.remove();
          console.log(info);
        },

      });
    
      calendar.render();
    });

    // let array = document.querySelectorAll(".fc-daygrid-day");
    // array.forEach(element => {
    //     if(!element.classList.contains('click')){
    //     element.classList.add('click')
    //     element.addEventListener('click',function(){
    //     console.log(element.dataset.date);
    //     });
    // }
    // });


// document.getElementById('calendar').addEventListener('click',function(){
//     let array = document.querySelectorAll(".fc-daygrid-day");
//     array.forEach(element => {
//         if(!element.classList.contains('click')){
//         element.classList.add('click')
//         element.addEventListener('click',function(){
//         console.log(element.dataset.date);
//         });
//     }
//     });
// })