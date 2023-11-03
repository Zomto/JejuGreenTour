function checkcansle() {
    document.querySelectorAll('input[name="subAccomCates"]').forEach(element => {
        element.checked = false;
    });
    document.querySelector('#noting').checked = true;
}
function notingcheck() {

    document.querySelector('#noting').checked = false;
}


function insertsubaccom() {

    let name = document.querySelector('#subAccomName').value
    if (name == null || name == '') {
        alert('방이름을 입력해주세요')
        return;
    }


    let intro = document.querySelector('#subAccomIntro').value
    if (intro == null || intro == "") {
        alert('방의 간략한 소개를 입력해주세요')
        return;
    }


    let reservPrice = document.querySelector('#reservationPrice').value
    if (reservPrice == null || reservPrice == 0) {
        alert('숙박 요금을 입력해주세요')
        return;
    }

    let candayuse = document.querySelector('.rent');
    let dayuse = document.querySelector('#dayusePrice').value;
    if (candayuse.checked && (dayuse == null || dayuse == 0)) {
        candayuse.parentNode.innerHTML +=
            alert('대실 요금을 입력해주세요')
        return;
    }

    document.querySelector('form[id="joinForm"]').submit()

}


function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            document.getElementById('preview').src = e.target.result;
        };
        reader.readAsDataURL(input.files[0]);
    } else {
        document.getElementById('preview').src = "";
    }
}