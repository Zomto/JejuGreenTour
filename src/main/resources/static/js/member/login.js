$('.input_memberId input').click(function(){
    document.querySelector('.bi.bi-person-circle').setAttribute("fill", "#03c75a");
})
$('.input_memberPw input').click(function(){
    document.querySelector('.bi.bi-shield-lock-fill').setAttribute("fill", "#03c75a");
})

function checkInput(){
    let checkId = document.querySelector('#memberId');
    let checkPw = document.querySelector('#memberPw');

    if(checkId.value == ""){
        document.querySelector('.bi.bi-person-circle').setAttribute("fill", "#ff3f3f");
        checkId.style.borderColor = "#ff3f3f";
        checkId.placeholder = "아이디를 입력해주세요";
        return;
    } else if(checkPw.value == ""){
        document.querySelector('.bi.bi-person-circle').setAttribute("fill", "#03c75a");
        checkId.style.borderColor = "#03c75a";
        document.querySelector('.bi.bi-shield-lock-fill').setAttribute("fill", "#ff3f3f");
        checkPw.style.borderColor = "#ff3f3f";
        checkPw.style.borderTop = "1px solid #ff3f3f";
        checkPw.placeholder = "비밀번호를 입력해주세요";
        return;
    }
}
