function updateAccomForm(){

        inputInvalidate('#updateButton');

}
function updateAccomForm2(){

    inputInvalidate('#updateButton2');

}

function returnUpdateForm(){
    inputInvalidate2('#updateButton');
}

function returnUpdateForm2(){
    inputInvalidate2('#updateButton2');
}




function inputInvalidate(tagId){
    var element = document.querySelector(tagId);
    var parent = element.parentElement;
    element.style.display = 'none';
    element.nextElementSibling.style.display = 'block';
    parent.children[1].style.display = 'none';
    parent.children[2].style.display = 'block';
    parent.children[2].value = parent.children[1].textContent;
}

function inputInvalidate2(tagId){
    var element = document.querySelector(tagId);
    var parent = element.parentElement;
    element.style.display = 'none';
    element.previousElementSibling.style.display = 'block';
    parent.children[1].style.display = 'block';
    parent.children[1].textContent =  parent.children[2].value;
    parent.children[2].style.display = 'none';
    parent.children[3].style.display = 'block';
    parent.children[4].style.display = 'none';
}


function updateMainAccomName(accomCode, accomName, accomIntro){
    fetch('/accom/updateMainAccomName', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           'accomCode' : accomCode,
           'accomName' : accomName,
           'accomIntro' : accomIntro,
           'inputMainAccomName' : document.querySelector('#inputAccomName').value,
           'inputMainAccomIntro' : document.querySelector('#inputAccomIntro').value
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
        alert('업소의 상태가 변경되었습니다.')
        
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
        
}