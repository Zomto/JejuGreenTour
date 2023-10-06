// 회원가입에서 주소검색 버튼 클릭 시 실행
function openPost(){
    new daum.Postcode({
        oncomplete: function(data) {
            document.querySelector('#memberAddr').value = data.roadAddress;
        }
    }).open();
}

// 회원가입 시 데이터 유효성 검사
function joinValidate(){
    // 1. 데이터 유효성 검사
    // ID 입력 여부 체크
    const inputMemberId = document.querySelector('#memberId').value
    
    // form태그 선택
    // form 태그 안의 요소는 name 속성으로 접근 가능
    const joinForm = document.querySelector('#joinForm');

    

    // form 태그 안의 name 속성이 memberId인 태그의 value
    if(joinForm.memberId.value == ''){
        inputInvalidate('#id-error-div', 'ID는 필수입력!');
        return ;
    }
    else if(joinForm.memberId.value.length < 4){
        inputInvalidate('#id-error-div', 'ID는 4글자 이상!');
        return;
    }


    //휴대폰 정규식표현식
    var telRegex = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
    //console.log(joinForm.memberTels[0].value);
    const tel = joinForm.memberTels[0].value + '-' + joinForm.memberTels[1].value + '-' +joinForm.memberTels[2].value;
    if (!telRegex.test(tel)){
        inputInvalidate('#tel-error-div', '연락처 이상!');
    }
    //이메일 정규식표현식
    var emailRegex = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;




    // 2. submit 실행
    // form 태그 선택 --> submit() 함수 실행
    document.querySelector('#joinForm').submit();
}



// Validate 실패시 메세지 설정
function inputInvalidate(tagId, message){
    document.querySelector(tagId).style.display = 'block';
    document.querySelector(tagId).textContent = message;
}

// 화원가입 시 아이디 중복 체크
function checkId(){
    fetch('/member/checkId', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
            memberId : document.querySelector('#memberId').value
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
        if(data){
            alert('사용가능한 ID입니다.');
            document.querySelector('#join-btn').disabled = false;
        } else{
            alert('사용 불가능한 ID입니다.');
        }
        
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}

// 회원가입 버튼 비활성화 함수
function setDisabled(){
    document.querySelector('#join-btn').disabled = true;
}



// 가입 모달창이 닫힐 때 마다 실행되는 이벤트
const joinModal = document.querySelector('#join-modal')
joinModal.addEventListener('hidden.bs.modal', event => {
    document.querySelector('#joinForm').reset();
    document.querySelector('#join-btn').disabled = true;
})


// 재고관리 수정버튼

