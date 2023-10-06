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
    const inputAccomName = document.querySelector('#accomName').value

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