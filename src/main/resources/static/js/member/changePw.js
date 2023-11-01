let pwBlock = document.querySelector('#InputchangePw');
    let pwCheckBlock = document.querySelector('#InputchangePwCheck');
    let pw = document.querySelector('#InputchangePw').value;
    let pwCheck = document.querySelector('#InputchangePwCheck').value;

function closePopup(){
    if(confirm('창을 닫으시겠습니까?')){
        window.close();
    }
}
pwBlock.addEventListener('click',function(){
    pwBlock.type = "password";
    pwBlock.value = '';
    document.querySelector('.bi.bi-shield-lock-fill').setAttribute("fill", "#03c75a");
});

pwCheckBlock.addEventListener('click',function(){
    pwCheckBlock.type = "password";
    pwCheckBlock.value = '';
    document.querySelector('.bi.bi-shield-lock').setAttribute("fill", "#03c75a");
});

function changePw() {
    let pwBlock = document.querySelector('#InputchangePw');
    let pwCheckBlock = document.querySelector('#InputchangePwCheck');
    let pw = document.querySelector('#InputchangePw').value;
    let pwCheck = document.querySelector('#InputchangePwCheck').value;

    if (pw === '') {
        pwBlock.placeholder = "비밀번호를 입력해 주세요."
        pwBlock.style.borderColor = "#ff3f3f";
        document.querySelector('.bi.bi-shield-lock-fill').setAttribute("fill", "#ff3f3f");
        return;
    } else if (pwCheck === '') {
        pwCheckBlock.placeholder = "비밀번호를 확인 해 주세요."
        pwCheckBlock.style.borderColor = "#ff3f3f";
        document.querySelector('.bi.bi-shield-lock').setAttribute("fill", "#ff3f3f");
        return;
    } else if (pw !== pwCheck) {
        pwCheckBlock.type = "text"
        pwCheckBlock.value = "비밀번호가 일치하지 않습니다."
        pwCheckBlock.style.borderColor = "#ff3f3f";
        document.querySelector('.bi.bi-shield-lock').setAttribute("fill", "#ff3f3f");
        return;
    } else {
        fetch('/member/changePw', { //요청경로
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            //컨트롤러로 전달할 데이터
            body: new URLSearchParams({
               "memberId" : document.querySelector('#inputMemberId').value,
               "memberPw" : document.querySelector('#InputchangePw').value
            })
        })
        .then((response) => {
            if(!response.ok){
                alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
                return ;
            }
        
            return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
            //return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!
            if (confirm("회원 정보가 변경되었습니다.\n 창을 닫으시겠습니까?")) {
                window.close();
            }
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err=>{
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
    }
}
