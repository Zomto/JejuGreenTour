console.log(11);

let annCate= document.querySelector('#annCate').value;
if( annCate == null || annCate==""){
document.querySelector('.all').className +=" on"
console.log(annCate)
}else{
    document.querySelector('.'+annCate).className+=" on"
    console.log(annCate)
}

//공지사항 탭별 목록 검색
function goAnnListPerCate(cateCode){
    const formTag = document.querySelector('#searchForm');
    formTag.querySelector('#annCate11').value = cateCode;
    formTag.submit();

}