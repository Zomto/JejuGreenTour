//전역변수
let timerstop = null
let settimerstop = null
let reatimerstop = null
$('#memberId').click(function(){
    document.querySelector('.bi.bi-person-circle').setAttribute("fill", "#03c75a");
})
$('#memberEmail').click(function(){
    document.querySelector('.bi.bi-envelope-at-fill').setAttribute("fill", "#03c75a");
})



$(function(){
    $('.tabcontent > div').hide();
    $('.tabnav a').click(function () {
      $('.tabcontent > div').hide().filter(this.hash).fadeIn();
      $('.tabnav a').removeClass('active');
      $(this).addClass('active');
      return false;
    }).filter(':eq(0)').click();
    });

// 회원가입 시 데이터 유효성 검사
function joinValidate() {
    //휴대폰 정규식표현식
    var telRegex = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
    //console.log(joinForm.memberTels[0].value);
    const tel = joinForm.memberTels[0].value + '-' + joinForm.memberTels[1].value + '-' + joinForm.memberTels[2].value;
    if (!telRegex.test(tel)) {
        inputInvalidate('#tel-error-div', '연락처 이상!');
    }
    // 2. submit 실행
    // form 태그 선택 --> submit() 함수 실행
    document.querySelector('#editMember1').submit();
}
// Validate 실패시 메세지 설정
function inputInvalidate(tagId, message) {
    document.querySelector(tagId).style.display = 'block';
    document.querySelector(tagId).textContent = message;
}
let tags = document.querySelectorAll('.inputInfo');
tags.forEach((element, idx) => {
    element.addEventListener('click', e=> {
        element.querySelector('.bi').setAttribute("fill", "#03c75a");
    });
});

let tags2 = document.querySelectorAll('.join_input');
tags2.forEach((element, idx) => {
    element.addEventListener('click', e=> {
        element.querySelector('.bi').setAttribute("fill", "#03c75a");
    });
});

let tagss = document.querySelector('.inputMem.memberId');
$('.inputMemberId').click(function(){
    tagss.style.borderColor = "#03c75a";
    document.querySelector('.bi-person-circle').setAttribute("fill", "#03c75a");
})
$('.join_input.four').click(function(){
    document.querySelector('.join_input.four').style.borderTop = "1px solid #03c75a";
    document.querySelector('.join_input.four').style.borderColor = "#03c75a";
    document.querySelector('.bi-telephone-fill').setAttribute("fill", "#03c75a");
})
$('.join_input.five').click(function(){
    document.querySelector('.join_input.five').style.borderTop = "1px solid #03c75a";
    document.querySelector('.join_input.five').style.borderLeft = "1px solid #03c75a";
    document.querySelector('.join_input.five').style.borderColor = "#03c75a";
    document.querySelector('.bi-calendar-check').setAttribute("fill", "#03c75a");
})

//비밀번호 가리기, 보이기
$(document).ready(function() {
    // '비밀번호 보이기' 버튼 클릭 시 동작
    $('#pw_off').click(function(e) {
        e.preventDefault(); // 링크 클릭 시 기본 동작 방지
        // 비밀번호 입력 필드의 type 속성을 'text'로 변경하여 비밀번호를 보여줌
        $('#memberPw').attr('type', 'text');
        // '비밀번호 보이기' 버튼을 숨김
        $(this).addClass('hide');
        // '비밀번호 가리기' 버튼을 표시
        $('#pw_on').removeClass('hide');
    });

    // '비밀번호 가리기' 버튼 클릭 시 동작
    $('#pw_on').click(function(e) {
        e.preventDefault(); // 링크 클릭 시 기본 동작 방지
        // 비밀번호 입력 필드의 type 속성을 'password'로 변경하여 비밀번호를 가림
        $('#memberPw').attr('type', 'password');
        // '비밀번호 가리기' 버튼을 숨김
        $(this).addClass('hide');
        // '비밀번호 보이기' 버튼을 표시
        $('#pw_off').removeClass('hide');
    });
});


// 이메일인증전 회원가입 버튼 비활성화 함수
function setDisabled() {
    document.querySelector('#editMember_1').disabled = true;
}


function startTimer(duration) {
    const timerElement = document.getElementById("timer");
    let timer = duration;
    let minutes, seconds;

    const updateTimer = () => {
        minutes = Math.floor((timer % (1000 * 60 * 60)) / (1000 * 60));
        seconds = Math.floor((timer % (1000 * 60)) / 1000);

        timerElement.textContent = `${minutes}:${seconds < 10 ? '0' : ''}${seconds}`;
        timer -= 1000;

        if (timer <= 0) {
            // 타이머가 종료되면 처리할 작업 추가
            timerElement.textContent = "00:00"; // 타이머가 0:00으로 표시
            // 여기에 추가 작업을 추가하실 수 있습니다.
        }
    };

    updateTimer(); // 초기값 설정

    // 1초마다 타이머 업데이트
    const timerInterval = setInterval(updateTimer, 1000);
    timerstop = timerInterval;
    // 타이머가 종료되면 interval 정지
    settimerstop= setTimeout(() => {
        clearInterval(timerInterval);
    }, duration);

}

function verifyCode() {
    var emailRegex = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;

    if (emailRegex.test(document.querySelector('#memberEmail').value + document.querySelector('#email_host').value)) {

        if(timerstop != null){
            console.log('이전 타이머 있음');
            clearTimeout(timerstop);
            console.log(timerstop);
        }
        document.querySelector('#member_mail').value = (document.querySelector('#memberEmail').value + document.querySelector('#email_host').value)
        clearTimeout(settimerstop);
        clearTimeout(reatimerstop);
        clearInterval(timerstop);
        document.getElementById("timer").textContent = "03:00";
        const additionalInputDiv = document.getElementById("additionalInput");
        document.querySelector('.inputMemEmail').style.borderBottom = "0px solid #dadada"
        document.querySelector('.inputMemEmail').style.borderRadius = "0px"
        additionalInputDiv.style.display = "block";
        startTimer(180000);
        document.querySelector('#verify_code').value = null;
        fetch('/member/verifyCode', { //요청경로
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            //컨트롤러로 전달할 데이터
            body: new URLSearchParams({
                email: document.querySelector('#memberEmail').value+document.querySelector('#email_host').value
            })
        })
            .then((response) => {
                if (!response.ok) {
                    alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
                    return;
                }

                return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
                // return response.json(); //나머지 경우에 사용
            })
            //fetch 통신 후 실행 영역
            .then((data) => {//data -> controller에서 리턴되는 데이터!
                alert("인증메일이 발송 되었습니다! 메일함을 확인해주세요!")
                confirmCode = data;
                reatimerstop= setTimeout(() => {
                    confirmCode = null
                }, 180000)

            })
    } else {
        alert("이메일을 다시 확인 해주세요!")
        const additionalInputDiv = document.getElementById("additionalInput");
            additionalInputDiv.style.display = "none";
    }
}

// function checkCode() {
//     let document.querySelector('#editMember_1') = edit
//     if (confirmCode == null) {
//         document.querySelector('.alertbox').innerHTML="유효하지 않은 코드 입니다."
//         document.querySelector('.alertbox').style.color='red';
//     } else {
//         let inputCode = document.querySelector('#verify_code').value
//         if (inputCode == confirmCode) {
//             alert("인증되었습니다! 절차를 진행해주세요!")
//             document.querySelector('#editMember_1').disabled = false
//             const additionalInputDiv = document.getElementById("additionalInput");
//             additionalInputDiv.style.display = "none";
            
//         } else {
//             document.querySelector('.alertbox').innerHTML="인증번호를 정확하게 입력해주세요"
//             document.querySelector('.alertbox').style.color='red';
//         }
//     }

// }
function checkCode() {
    let edit = document.querySelector('#editMember_1');
    if (confirmCode == null) {
        document.querySelector('.alertbox').innerHTML = "유효하지 않은 코드 입니다.";
        document.querySelector('.alertbox').style.color = 'red';
    } else {
        let inputCode = document.querySelector('#verify_code').value;
        if (inputCode == confirmCode) {
            alert("인증되었습니다! 절차를 진행해주세요!");
            edit.disabled = false; // 버튼 활성화
            const additionalInputDiv = document.getElementById("additionalInput");
            additionalInputDiv.style.display = "none";

            // 클릭 이벤트를 감지하여 알람 띄우기
            edit.addEventListener('click', function okEdit() {
                alert("수정이 완료되었습니다!");
            });
        } else {
            document.querySelector('.alertbox').innerHTML = "인증번호를 정확하게 입력해주세요";
            document.querySelector('.alertbox').style.color = 'red';
        }
    }
}

let confirmCode = null

function changePw() {
    let pw = document.querySelector('#InputchangePw');
    let pwCheck = document.querySelector('#InputchangePwCheck');
    
    if (pw.value == '') {
      pw.placeholder = "비밀번호를 입력 해 주세요.";
      return;
    } else if (pwCheck.value == '') {
      pwCheck.placeholder = "비밀번호를 확인 해 주세요.";
      return;
    } else if (pw.value != pwCheck.value) {
        pwCheck.value = '';
      pwCheck.placeholder = "비밀번호가 일치하지 않습니다";
      return;
    } else{
        alert('회원 정보가 변경되었습니다.\n다시 로그인 해주세요');
        document.querySelector('#editMember2').submit();
    }

  }
  
// Validate 실패시 메세지 설정
function inputInvalidate(tagId, message){
    document.querySelector(tagId).style.display = 'block';
    document.querySelector(tagId).textContent = message;
}

// let svgIcon = document.querySelector
// $('.join_input').click(function(){
//     $(this).find('.bi').setAttribute("fill", "#03c75a");
// });
