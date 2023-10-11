// 회원가입에서 주소검색 버튼 클릭 시 실행
function openPost(){
    new daum.Postcode({
        oncomplete: function(data) {
            document.querySelector('#accomAddr').value = data.roadAddress;
            // document.querySelector('#postCode').value = data.tesPostcode;
            const bname1 = data.bname1;
        }
    }).open();
}

// 회원가입 시 데이터 유효성 검사
function joinValidate(){
    // 1. 데이터 유효성 검사
    // ID 입력 여부 체크
    const inputAccomName = document.querySelector('#accomName').value
    const inputAccomAddr = document.querySelector('#accomAddr').value
    const inputAccomCate = document.querySelector('#accomCate').value

    // form태그 선택
    // form 태그 안의 요소는 name 속성으로 접근 가능
    const joinForm = document.querySelector('#joinForm');



    // form 태그 안의 name 속성이 memberId인 태그의 value
    if(joinForm.inputAccomName.value == ''){
        inputInvalidate('#id-error-div', '업소명은 필수입력!');
        return ;
    }
    if(joinForm.inputAccomAddr.value == ''){
        inputInvalidate('#id-error-div', '주소 필수입력!');
        return ;
    }
    if(joinForm.inputAccomCate.value == 'CATE_000'){
        inputInvalidate('#id-error-div', '카테고리를 선택해주세요!');
        return ;
    }






    // 2. submit 실행
    // form 태그 선택 --> submit() 함수 실행
    document.querySelector('#joinForm').submit();
}