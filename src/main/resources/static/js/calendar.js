
document.addEventListener('DOMContentLoaded', function () {

    let setstartday = document.querySelector('#setStartDay');
    let setendday = document.querySelector('#setEndDay');
    let stayday = document.querySelector('#stayDay');
    //sql로 가져올 정보는 날짜랑 결제한 ID만필요(admin용)
    let sta = document.querySelectorAll('.start');
    let en = document.querySelectorAll('.end');

    let todaydate = new Date();
    let Year = todaydate.getFullYear();
    let Month = todaydate.getMonth();
    if (++Month / 10 < 1) {
        Month = '0' + Month;
    }
    let date = todaydate.getDate();
    if (date / 10 < 1) {
        date = '0' + date
    }
    let today = `${Year}-${Month}-${date}`;
    var eventsss = []
    for (let i = 0; i < sta.length; i++) {
        eventsss.push({
            id: 'check',
            title: 'my event',
            start: `${sta[i].value}`,
            end: `${en[i].value}`
        })
        console.log(sta[i].value)
        console.log(en[i].value)
    }

    var startDate = null;
    var startinfo = null;
    var endDate = null;
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        events: eventsss,
        initialView: 'dayGridMonth',
        selectable: true,// 날짜 선택 가능하도록 설정
        select: function (info) {
            if (today <= info.startStr) {
                let allEvents = calendar.getEvents();
                let checkEvents = [];
                for (let i = 0; i < allEvents.length; i++) {
                    if (allEvents[i].id == 'check') {
                        checkEvents.push({
                            start: `${allEvents[i].start.getFullYear()}-${allEvents[i].start.getMonth() + 1}-${allEvents[i].start.getDate()}`,
                            end: `${allEvents[i].end.getFullYear()}-${allEvents[i].end.getMonth() + 1}-${allEvents[i].end.getDate()}`
                        });
                    }
                }
                if (startDate == null) {
                    if (startinfo != null) {
                        calendar.getEventById('longDate').remove();
                        calendar.getEventById('startDate').remove();
                        calendar.getEventById('endDate').remove();
                        stayday.value = 0;
                    }
                    startinfo = info;
                    startDate = info.startStr;
                    setstartday.value = startDate;
                    setendday.value = null;
                    let safe = true;
                    for (let i = 0; i < checkEvents.length; i++) {
                        if ((checkEvents[i].start <= startDate && startDate < checkEvents[i].end)) {
                            console.log("안됨");
                            safe = false;
                            startinfo = null;
                            startDate = null;
                            setendday.value = null;
                            setstartday.value = null;
                            break;
                        }
                    }
                    if (safe) {
                        var event = {
                            id: 'startDate',
                            title: '시작일', // 이벤트 제목
                            start: startDate, // 시작 날짜
                            end: startDate, // 끝나는 날짜
                            backgroundColor: 'red', // 배경색 설정
                        };
                        calendar.addEvent(event);
                        // 선택 영역 초기화
                        calendar.unselect();
                    }

                } else if (startDate < info.startStr) {
                    endDate = info.startStr;
                    setendday.value = endDate;
                    let safe = true;
                    for (let i = 0; i < checkEvents.length; i++) {
                        if ((checkEvents[i].start < endDate && endDate < checkEvents[i].end) || (startDate <= checkEvents[i].start && checkEvents[i].start < endDate)) {
                            console.log("안됨");
                            calendar.getEventById('startDate').remove();
                            startDate = null;
                            startinfo = null;
                            endDate = null;
                            setstartday.value = null;
                            setendday.value = null;
                            safe = false;
                            break;
                        }
                    }
                    if (safe) {
                        // calendar.getEventById('startDate').remove();
                        var event = {
                            id: 'endDate',
                            title: '종료일', // 이벤트 제목
                            start: endDate, // 시작 날짜
                            end: endDate, // 끝나는 날짜
                            backgroundColor: 'red', // 배경색 설정
                        };
                        calendar.addEvent(event);
                        // 선택 영역 초기화

                        var long = {
                            id: 'longDate',
                            title: '예약일', // 이벤트 제목
                            start: startDate, // 시작 날짜
                            end: endDate, // 끝나는 날짜
                            display: 'background',
                            backgroundColor: 'green', // 배경색 설정
                        };
                        stayday.value = (new Date(endDate).getTime() / (1000 * 60 * 60 * 24)) - (new Date(startDate).getTime() / (1000 * 60 * 60 * 24))
                        calendar.addEvent(long);
                        // 선택 영역 초기화
                        calendar.unselect();
                        startDate = null;
                        endDate = null;
                    }
                } else {
                    calendar.getEventById('startDate').remove();
                    startinfo = info;
                    startDate = info.startStr;
                    setstartday.value = startDate;
                    let safe = true;
                    for (let i = 0; i < checkEvents.length; i++) {
                        if ((checkEvents[i].start <= startDate && startDate < checkEvents[i].end)) {
                            console.log("안됨");
                            startinfo = null;
                            startDate = null;
                            setstartday.value = null;
                            safe = false;
                            break;
                        }
                    }
                    if (safe) {
                        var event = {
                            id: 'startDate',
                            title: '시작일', // 이벤트 제목
                            start: startDate, // 시작 날짜
                            end: startDate, // 끝나는 날짜
                            backgroundColor: 'red', // 배경색 설정
                        };
                        calendar.addEvent(event);
                        // 선택 영역 초기화
                        calendar.unselect();
                    }
                }
            } else {
                alert("과거를 예약할 수는 없습니다.")
            }
        },
        // eventClick: function (info) {
        //     // 이벤트를 클릭할 때 추가 작업 수행 (예: 이벤트 삭제)

        //     info.event.remove();
        //     console.log(info);
        // },
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