
document.addEventListener('DOMContentLoaded', function () {
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
            var title = prompt('Event Title:');
            if (title) {
              calendar.addEvent({
                title: title,
                start: arg.start,
                end: arg.end,
                allDay: arg.allDay
              })
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
            groupId : '',
            title: 'Conference',
            start: '2023-01-11',
            end: '2023-01-13'
        },
        {
            groupId : '',
            startTime: '10:00', // a start time (10am in this example)
            endTime: '18:00', // an end time (6pm in this example)
            background: ''//각 식사 관광 이벤트 색을 탁하게 
          },
        ]
    })

    calendar.render();


});



