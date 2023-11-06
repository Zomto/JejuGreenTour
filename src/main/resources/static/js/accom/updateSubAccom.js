
function changesoldOut(tag) {
    if (confirm("해당 호실을 상태를 변경하시겠습니까?")) {
        fetch('/accom/subAccomUpdate', { //요청경로
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            //컨트롤러로 전달할 데이터
            body: new URLSearchParams({
                // 데이터명 : 데이터값
                subAccomCode: tag.dataset.subAccomCode,
                soldOut: tag.dataset.soldOut

            })
        })
            .then((response) => {
                if (!response.ok) {
                    alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
                    return;
                }
                return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
                //return response.json(); //나머지 경우에 사용
            })
            //fetch 통신 후 실행 영역
            .then((data) => {//data -> controller에서 리턴되는 데이터!
                if (tag.dataset.soldOut == 'Y') {
                    tag.parentNode.parentNode.innerHTML = `
                    예약 종료
                <div>
                    <label for="sold">판매하기</label>
                    <input id="sold" type="checkbox" onclick="changesoldOut(this)"
                data-sub-accom-Code="${tag.dataset.subAccomCode}"
                data-sold-out="N">
                </div>
                <input type="button" value="삭제하기" data-sub-accom-Code="${tag.dataset.subAccomCode}"
                onclick="subaccomdelete(this)">
                    `
                } else {
                    tag.parentNode.parentNode.innerHTML = `
                    판매중입니다.
                <div>
                    <label for="sold">판매취소</label>
                    <input id="sold" type="checkbox" onclick="changesoldOut(this)"
                data-sub-accom-Code="${tag.dataset.subAccomCode}"
                data-sold-out="Y">
                </div>
                <input type="button" value="삭제하기" data-sub-accom-Code="${tag.dataset.subAccomCode}"
                onclick="subaccomdelete(this)">
                    `
                }
            })
            //fetch 통신 실패 시 실행 영역
            .catch(err => {
                alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
                console.log(err);
            });
    }
}


function subaccomdelete(tag) {
    if (confirm("해당 호실을 삭제 하시겠습니까?")) {
        fetch('/accom/subAccomUpdate', { //요청경로
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            //컨트롤러로 전달할 데이터
            body: new URLSearchParams({
                // 데이터명 : 데이터값
                subAccomCode: tag.dataset.subAccomCode
            })
        })
            .then((response) => {
                if (!response.ok) {
                    alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
                    return;
                }

                return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
                //return response.json(); //나머지 경우에 사용
            })
            //fetch 통신 후 실행 영역
            .then((data) => {//data -> controller에서 리턴되는 데이터!
                tag.parentNode.parentNode.remove();
            })
            //fetch 통신 실패 시 실행 영역
            .catch(err => {
                alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
                console.log(err);
            });
    }
}

function changePrice(tag) {

    if (tag.dataset.priceName == 'reservationPrice') {
        if (confirm('숙박요금을 변경하시겠습니까?')) {

            fetch('/accom/subAccomUpdate', { //요청경로
                method: 'POST',
                cache: 'no-cache',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
                },
                //컨트롤러로 전달할 데이터
                body: new URLSearchParams({
                    // 데이터명 : 데이터값
                    subAccomCode: tag.dataset.subAccomCode,
                    reservationPrice: tag.parentNode.querySelector('input[type="number"]').value
                })
            })
                .then((response) => {
                    if (!response.ok) {
                        alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
                        return;
                    }

                    return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
                    //return response.json(); //나머지 경우에 사용
                })
                //fetch 통신 후 실행 영역
                .then((data) => {//data -> controller에서 리턴되는 데이터!
                    alert("요금이 변경되었습니다.")
                })
                //fetch 통신 실패 시 실행 영역
                .catch(err => {
                    alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
                    console.log(err);
                });
        }
    } else {
        if (confirm('대실요금을 변경하시겠습니까?')) {
fetch('/accom/subAccomUpdate', { //요청경로
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            //컨트롤러로 전달할 데이터
            body: new URLSearchParams({
                // 데이터명 : 데이터값
                subAccomCode: tag.dataset.subAccomCode,
                dayusePrice: tag.parentNode.querySelector('input[type="number"]').value

            })
        })
            .then((response) => {
                if (!response.ok) {
                    alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
                    return;
                }

                return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
                //return response.json(); //나머지 경우에 사용
            })
            //fetch 통신 후 실행 영역
            .then((data) => {//data -> controller에서 리턴되는 데이터!
                alert("요금이 변경되었습니다.")
            })
            //fetch 통신 실패 시 실행 영역
            .catch(err => {
                alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
                console.log(err);
            });
        }
    }
}