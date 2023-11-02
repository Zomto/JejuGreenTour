
document.addEventListener('DOMContentLoaded', function () {

    let setstartday = document.querySelector('#setStartDay');
    let setendday = document.querySelector('#setEndDay');
    let stayday = document.querySelector('#stayDay');
    document.querySelector('#setdayuse').value = 'N';
    //sql로 가져올 정보는 날짜랑 결제한 ID만필요(admin용)
    let sta = document.querySelectorAll('.start');
    let en = document.querySelectorAll('.end');
    let dayuse = document.querySelectorAll('.dayuse');

    let checkin = document.querySelector('.checkin');
    let checkout = document.querySelector('.checkout');
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
            start: sta[i].value,
            end: en[i].value
        })
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
            info.startStr = info.startStr + 'T' + checkin.value;
            if (today <= info.startStr) {
                let allEvents = calendar.getEvents();
                let checkEvents = [];
                for (let i = 0; i < allEvents.length; i++) {
                    if (allEvents[i].id == 'check') {
                        checkEvents.push({
                            start: eventsss[i].start.split('T')[0],//`${allEvents[i].start.getFullYear()}-${allEvents[i].start.getMonth() + 1}-${allEvents[i].start.getDate()}`
                            end: eventsss[i].end.split('T')[0]//`${allEvents[i].end.getFullYear()}-${allEvents[i].end.getMonth() + 1}-${allEvents[i].end.getDate()}`
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
                            setendday.value = '';
                            setstartday.value = '';
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
                    info.startStr = info.startStr.split('T')[0] + 'T' + checkout.value;
                    endDate = info.startStr;
                    setendday.value = endDate;
                    let safe = true;

                    for (let i = 0; i < checkEvents.length; i++) {
                        // if (dayuse[i] == 'Y') {

                        // }
                        //else {
                        if ((sta[i].value < endDate && endDate < en[i].value) || (startDate <= sta[i].value && sta[i].value < endDate)) {

                            console.log("안됨");
                            calendar.getEventById('startDate').remove();
                            startDate = null;
                            startinfo = null;
                            endDate = null;
                            setstartday.value = '';
                            setendday.value = '';
                            safe = false;
                            break;
                        }
                        // }
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
                            start: startDate.split('T')[0], // 시작 날짜
                            end: endDate.split('T')[0], // 끝나는 날짜
                            display: 'background',
                            backgroundColor: 'green', // 배경색 설정
                        };
                        stayday.value = Math.ceil((new Date(endDate).getTime() / (1000 * 60 * 60 * 24)) - (new Date(startDate).getTime() / (1000 * 60 * 60 * 24)))
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



// divcalendar.addEventListener('click',function(){
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


function turndayuse(tag) {
    let divcalendar = document.querySelector("#calendar");
    divcalendar.innerHTML = null;
    let setstartday = document.querySelector('#setStartDay');
    let setendday = document.querySelector('#setEndDay');
    let stayday = document.querySelector('#stayDay');
    document.querySelector('#setdayuse').value = 'Y';
    const renttime = document.querySelector('.renttime').value;
    setstartday.value = null;
    setendday.value = null;
    stayday.value = 0;
    let setday = 0;
    let sta = document.querySelectorAll('.start');
    let en = document.querySelectorAll('.end');
    let dayuse = document.querySelectorAll('.dayuse');
    let checkin = document.querySelector('.checkin');
    let checkout = document.querySelector('.checkout');

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
        if (sta[i].value.split("T")[0] == en[i].value.split("T")[0]) {
            eventsss.push({
                id: 'check',
                title: '대실-' + renttime + '시간',
                start: sta[i].value,
                end: en[i].value
            })
        } else if (dayuse[i].value == 'Y') {
            eventsss.push({
                id: 'check',
                title: '대실-심야',
                start: sta[i].value,
                end: en[i].value
            })
        }
        else {
            let starr = sta[i].value.split("T");
            let starr2 = starr[0].split("-");
            //console.log(starr2[0] + '-' + starr2[1] + '-' + (Number(starr2[2]) + 1) + 'T' + checkin.value)
            //console.log(en[i].value.split("T")[0] + 'T' + checkout.value)
            eventsss.push({
                id: 'check',
                title: '숙박',
                start: starr2[0] + '-' + starr2[1] + '-' + (Number(starr2[2]) + 1) + 'T' + checkin.value,
                end: en[i].value.split("T")[0] + 'T' + checkout.value
            })
        }

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
                        if (dayuse[i].value == 'Y') {
                            checkEvents.push({
                                start: eventsss[i].start,//`${allEvents[i].start.getFullYear()}-${allEvents[i].start.getMonth() + 1}-${allEvents[i].start.getDate()}`
                                end: eventsss[i].end//`${allEvents[i].end.getFullYear()}-${allEvents[i].end.getMonth() + 1}-${allEvents[i].end.getDate()}`
                            });
                        } else {

                        }
                    }
                }
                if (startDate != null) {
                    calendar.getEventById('startDate').remove();
                    startDate = null;
                }
                if (startDate == null) {
                    startinfo = info;
                    startDate = info.startStr;
                    setstartday.value = startDate;
                    setendday.value = startDate;
                    let candayuse = Number(checkout.value.split(':')[0]);
                    let dayusecheckin = (candayuse + 1) / 10 >= 1 ? (candayuse + 1) : "0" + (candayuse + 1)
                    startDate = startDate + 'T' + dayusecheckin + ':00:00';
                    let safe = true;
                    for (let i = 0; i < checkEvents.length; i++) {

                        if ((checkEvents[i].start <= startDate && startDate < checkEvents[i].end) || (checkEvents[i].start == checkEvents[i].end && checkEvents[i].start <= startDate && startDate <= checkEvents[i].end)) {//

                            console.log("안됨");
                            safe = false;
                            startinfo = null;
                            startDate = null;
                            setendday.value = '';
                            setstartday.value = '';
                            break;
                        }


                    }
                    if (safe) {
                        let dayusecheckout = (candayuse + 1 + Number(renttime)) / 10 >= 1 ? (candayuse + 6) : "0" + (candayuse + 6)
                        stayday.value = 1;
                        setday = renttime
                        var event = {
                            id: 'startDate',
                            title: '대실-' + renttime, // 이벤트 제목
                            start: startDate, // 시작 날짜
                            end: startDate + 'T' + dayusecheckout + ':00:00', // 끝나는 날짜
                            backgroundColor: 'red', // 배경색 설정
                        };
                        setStartDay.value = startDate.split('T')[0] + 'T' + (Number(checkout.value.split(':')[0]) + 1) + ':00:00';
                        setEndDay.value = startDate.split('T')[0] + 'T' + (Number(checkout.value.split(':')[0]) + 1 + Number(renttime)) + ':00:00'
                        // setEndDay.value =endDate.split('T')[0] +(checkin.value.split(':')[0]-1)+'00:00';
                        calendar.addEvent(event);
                        // 선택 영역 초기화
                        calendar.unselect();
                    }
                }
            } else {
                alert("과거를 예약할 수는 없습니다.")
            }
        },
        eventClick: function (info) {
            // 이벤트를 클릭할 때 추가 작업 수행 (예: 이벤트 삭제)
            if (info.event.id == 'startDate') {

                console.log(Number(checkout.value.split(':')[0]) + Number(setday) + Number(renttime))
                if (Number(checkin.value.split(':')[0]) > Number(checkout.value.split(':')[0]) + Number(setday) + Number(renttime)) {
                    info.event.setProp('title', '대실-' + (Number(renttime) + Number(setday)))
                    setday = Number(setday) + Number(renttime);
                    stayday.value = Number(stayday.value) + 1;
                    setEndDay.value = startDate.split('T')[0] + 'T' + (Number(checkout.value.split(':')[0]) + 1 + Number(setday)) + ':00:00'
                }
            }
        },
    });
    calendar.render();


}







function turnreserv(tag) {
    let divcalendar = document.querySelector("#calendar");
    divcalendar.innerHTML = null;


    let setstartday = document.querySelector('#setStartDay');
    let setendday = document.querySelector('#setEndDay');
    let stayday = document.querySelector('#stayDay');
    stayday.value = 0;
    document.querySelector('#setdayuse').value = 'N';
    //sql로 가져올 정보는 날짜랑 결제한 ID만필요(admin용)
    let sta = document.querySelectorAll('.start');
    let en = document.querySelectorAll('.end');
    let dayuse = document.querySelectorAll('.dayuse');

    let checkin = document.querySelector('.checkin');
    let checkout = document.querySelector('.checkout');
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
            start: sta[i].value,
            end: en[i].value
        })
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
            info.startStr = info.startStr + 'T' + checkin.value;
            if (today <= info.startStr) {
                let allEvents = calendar.getEvents();
                let checkEvents = [];
                for (let i = 0; i < allEvents.length; i++) {
                    if (allEvents[i].id == 'check') {
                        checkEvents.push({
                            start: eventsss[i].start.split('T')[0],//`${allEvents[i].start.getFullYear()}-${allEvents[i].start.getMonth() + 1}-${allEvents[i].start.getDate()}`
                            end: eventsss[i].end.split('T')[0]//`${allEvents[i].end.getFullYear()}-${allEvents[i].end.getMonth() + 1}-${allEvents[i].end.getDate()}`
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
                            setendday.value = '';
                            setstartday.value = '';
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
                    info.startStr = info.startStr.split('T')[0] + 'T' + checkout.value;
                    endDate = info.startStr;
                    setendday.value = endDate;
                    let safe = true;

                    for (let i = 0; i < checkEvents.length; i++) {
                        // if (dayuse[i] == 'Y') {

                        // }
                        //else {
                        if ((sta[i].value < endDate && endDate < en[i].value) || (startDate <= sta[i].value && sta[i].value < endDate)) {

                            console.log("안됨");
                            calendar.getEventById('startDate').remove();
                            startDate = null;
                            startinfo = null;
                            endDate = null;
                            setstartday.value = '';
                            setendday.value = '';
                            safe = false;
                            break;
                        }
                        // }
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
                            start: startDate.split('T')[0], // 시작 날짜
                            end: endDate.split('T')[0], // 끝나는 날짜
                            display: 'background',
                            backgroundColor: 'green', // 배경색 설정
                        };

                        stayday.value = Math.ceil((new Date(endDate).getTime() / (1000 * 60 * 60 * 24)) - (new Date(startDate).getTime() / (1000 * 60 * 60 * 24)))
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
                            setstartday.value = '';
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
}

function checksubmit() {

    let basketCode = document.querySelector('#basketCode');
    fetch('/buy/setBasketCode', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
        })
    })
        .then((response) => {
            if (!response.ok) {
                alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
                return;
            }

            return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
            //return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!
            basketCode.value = data;
            waitsubmit()
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err => {
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
}
function waitsubmit(){
    
    let setstartday = document.querySelector('#setStartDay');
    let setendday = document.querySelector('#setEndDay');
    let memberId = document.querySelector('#memberId');
    if ((setstartday.value != null && setstartday.value != '') && (setendday.value != null && setendday.value != '')) {
        if (memberId != null && memberId.value != '') {
            document.querySelector('#calendarform').submit();
        } else {
            if (confirm('로그인 하시겠습니까?')) {
                location.href = '/member/loginForm';
            }
        }
    } else {
        alert('날짜를 제대로 서택해 주세요')
    }

}