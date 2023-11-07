



document.addEventListener('DOMContentLoaded', function () {
    let itemdetail = document.querySelectorAll('.itemdetail');
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'timeGridDay,listWeek,dayGridMonth'
        },
        //   initialDate: '2023-01-12', today
        editable: true,
        selectable: true,
        eventDragStop: function (info) {
            console.log(info.event);
        },
        eventResize: function (eventResizeInfo) {
            console.log(eventResizeInfo.event.end)
        },
        select: function (arg) {
            console.log(itemdetail)
            if (confirm(arg.start.getFullYear() + ':' + arg.start.getMonth() + ':' + arg.start.getDate() + ' 에 일정을 추가 하시겠습니까?')) {
                
                let startday=arg.start.getFullYear();
                if(arg.start.getMonth()/10 <= 0){
                    startday+='-0'+(arg.start.getMonth()+1)
                }
                else{
                    startday+='-'+(arg.start.getMonth()+1)
                }
                if(arg.start.getDate()/10 <= 0){
                    startday+='-0'+arg.start.getDate()
                }else{
                    startday+='-'+arg.start.getDate()
                }
                console.log(startday+'T08:00:00')
                console.log(Date(startday+'T08:00:00'))
                
                for (let i = 0; i < itemdetail.length; i++) {
                    
                    if (itemdetail[i].dataset.name == " "){
                        console.log('ddd');
                    }else{
                        switch (i) {
                            case 0:
                                calendar.addEvent({
                                    title: itemdetail[i].dataset.name,
                                    id : itemdetail[i].dataset.addr,
                                    start: (startday+'T08:00:00'),
                                    end: (startday+'T09:00:00'),
                                    groupId :'Breakfast'
                                })
                                break;
                            case 1:
                                calendar.addEvent({
                                    title: itemdetail[i].dataset.name,
                                    id : itemdetail[i].dataset.addr,
                                    start: (startday+'T09:00:00'),
                                    end: (startday+'T10:00:00'),
                                    groupId :'tour1'
                                })
                                break;
                            case 2:
                                calendar.addEvent({
                                    title: itemdetail[i].dataset.name,
                                    id : itemdetail[i].dataset.addr,
                                    start: (startday+'T12:00:00'),
                                    end: (startday+'T13:00:00'),
                                    groupId :'Lunch'
                                })

                                break;
                            case 3:
                                calendar.addEvent({
                                    title: itemdetail[i].dataset.name,
                                    id : itemdetail[i].dataset.addr,
                                    start: (startday+'T15:00:00'),
                                    end: (startday+'T16:00:00'),
                                    groupId :'tour2'
                                })

                                break;

                            default:
                                calendar.addEvent({
                                    title: itemdetail[i].dataset.name,
                                    id : itemdetail[i].dataset.addr,
                                    start: (startday+'T18:00:00'),
                                    end: (startday+'T19:00:00'),
                                    groupId :'Dinner'
                                })
                                break;
                        }
                    }
                }





            }
            calendar.unselect()
        },
        eventClick: function (arg) {
            if (confirm('Are you sure you want to delete this event?')) {
                arg.event.remove()
            }
        },
        events: [
            {
                groupId: 'aaaa',
                start: '2023-11-11',
                end: '2023-11-13',
                display: 'background',
                backgroundColor: "yellow"
            },
            {
                groupId: 'Breakfast',
                startTime: '6:00', // a start time (10am in this example)
                endTime: '9:00', // an end time (6pm in this example)
                backgroundColor: 'red',//각 식사 관광 이벤트 색을 탁하게 
                display: 'background',
            },
            {
                groupId: 'Lunch',
                startTime: '12:00', // a start time (10am in this example)
                endTime: '15:00', // an end time (6pm in this example)
                backgroundColor: 'green',//각 식사 관광 이벤트 색을 탁하게 
                display: 'background',
            },
            {
                groupId: 'Dinner',
                startTime: '18:00', // a start time (10am in this example)
                endTime: '23:00', // an end time (6pm in this example)
                backgroundColor: '#000066',//각 식사 관광 이벤트 색을 탁하게 
                display: 'background',
            },
            {
                groupId: 'tour1',
                startTime: '9:00', // a start time (10am in this example)
                endTime: '12:00', // an end time (6pm in this example)
                backgroundColor: '#663300',//각 식사 관광 이벤트 색을 탁하게 
                display: 'background',
            },
            {
                groupId: 'tour2',
                startTime: '15:00', // a start time (10am in this example)
                endTime: '18:00', // an end time (6pm in this example)
                backgroundColor: '#663300',//각 식사 관광 이벤트 색을 탁하게 
                display: 'background',
            },
        ]
    })

    calendar.render();


});



