function checkcansle() {
    document.querySelectorAll('input[name="subAccomCates"]').forEach(element => {
        element.checked = false;
    });
    document.querySelector('#noting').checked = true;
}
function notingcheck(tag) {
    console.log(tag)
    if(tag.value == document.querySelector('input[value="금연"]').value)
    {
        document.querySelector('input[value="흡연_O"]').checked = false;
    }
    if(tag.value == document.querySelector('input[value="흡연_O"]').value)
    {
        document.querySelector('input[value="금연"]').checked = false;
    }
    if(tag.value == document.querySelector('input[value="반려동물_O"]').value)
    {
        document.querySelector('input[value="반려동물_X"]').checked = false;
    }
    if(tag.value == document.querySelector('input[value="반려동물_X"]').value)
    {
        document.querySelector('input[value="반려동물_O"]').checked = false;
    }
    document.querySelector('#noting').checked = false;
}

function candayuse(tag){
    let rent = document.querySelector('select[name="accomStartTime"]').value - Number(document.querySelector('select[name="accomEndTime"]').value) - 2;
    if (tag.checked && rent < 1) {
        alert("체크인 체크아웃 가능 시간을 수정해 주세요")
        tag.checked=false;
    }
}
function dayusecheck(tag){
    let dayusecan = document.querySelector('.rent')
    if(!dayusecan.checked){
        alert("대실을 체크해 주세요")
        tag.value=0;
    }
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
            alert('대실 요금을 입력해주세요')
        return;
    }
    let rent = document.querySelector('select[name="accomStartTime"]').value - Number(document.querySelector('select[name="accomEndTime"]').value) - 2;
    let dayusecan = document.querySelector('.rent')
    if (dayusecan.checked && rent < 1) {
        alert("체크인 체크아웃 가능 시간을 수정해 주세요")
        return;
    }

    let file = document.querySelector('input[type="file"]')
    if (file.value == "") {
        alert("이미지를 추가해 주세요")
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