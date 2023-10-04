

document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
      initialView: 'dayGridMonth'
    });
    calendar.render();
  });



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