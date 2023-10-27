//전역변수
let timerstop = null
let settimerstop = null
let reatimerstop = null



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
                document.querySelector('.alertbox').innerHTML="인증메일이 발송 되었습니다! 메일함을 확인해주세요!"
                document.querySelector('.alertbox').style.color='black';
        
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
function findId() {
    const memberMail = document.getElementById("member_mail").value;
    const mailTail = document.querySelector('#email_host').value;
    console.log(memberMail);
    console.log(mailTail);

    fetch('/member/findId', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           "member_mail1" : document.querySelector('#memberEmail').value+document.querySelector('#email_host').value
        })
    })
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }
    
        //return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        console.log(data);
        let resultDiv = document.querySelector('.result');
        for (const data1 of data) {
            console.log(data1.memberId);
            var resultEmail = document.createElement("input");
                resultEmail.className = "resultEmail";
                resultEmail.value = data1.memberId;
                resultDiv.appendChild(resultEmail);
        }
        resultDiv.style = "display : block";
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}


function checkCode() {
    if (confirmCode == null) {
        document.querySelector('.alertbox').innerHTML="유효하지 않은 코드 입니다."
        document.querySelector('.alertbox').style.color='red';
    } else {
        let inputCode = document.querySelector('#verify_code').value
        if (inputCode == confirmCode) {
            alert("인증되었습니다! 가입절차를 진행해주세요!")
            const additionalInputDiv = document.getElementById("additionalInput");
            additionalInputDiv.style.display = "none";
            document.querySelector('#verifyButton').style = "display : none";
            document.querySelector('#findIdButton').style = "display : block";
        } else {
            document.querySelector('.alertbox').innerHTML="인증번호를 정확하게 입력해주세요"
            document.querySelector('.alertbox').style.color='red';
        }
    }

}
let confirmCode = null

function changePw(){
    let pw = document.querySelector('#memberPw').value;
    let pwCheck = document.querySelector('#memberPwCheck').value;
    if(pw == ''){
        inputInvalidate('.err-div', '비밀번호를 입력해주세요!');
        return;
    } else{
        if(pwCheck == ''){
            inputInvalidate('.err-div', '비밀번호를 확인해주세요!');
            return;
        } else{
            if(pw == pwCheck){
                document.querySelector('#editMember2').submit();
                alert('회원 정보가 변경되었습니다.\n다시 로그인 해주세요')
            } else{
                inputInvalidate('.err-div', '비밀번호 다름!');
                return ;
            }
        }
    }
}

// Validate 실패시 메세지 설정
function inputInvalidate(tagId, message){
    document.querySelector(tagId).style.display = 'block';
    document.querySelector(tagId).textContent = message;
}


