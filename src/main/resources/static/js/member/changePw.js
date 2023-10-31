function changePw() {
    let pw = document.querySelector('#InputchangePw').value;
    let pwCheck = document.querySelector('#InputchangePwCheck').value;

    if (pw === '') {
        inputInvalidate('.err-div', '비밀번호를 입력해주세요!');
        return;
    } else if (pwCheck === '') {
        inputInvalidate('.err-div', '비밀번호를 확인해주세요!');
        return;
    } else if (pw !== pwCheck) {
        inputInvalidate('.err-div', '비밀번호가 일치하지 않습니다!');
        return;
    } else {
        // 비밀번호 일치, 폼 제출
        document.querySelector('#changePwForm').submit();
        alert('회원 정보가 변경되었습니다.\n다시 로그인 해주세요.');
    }window.close();
}
