console.log(11);

let annCate= document.querySelector('#annCate').value;
if( annCate == null || annCate==""){
document.querySelector('.all').className +=" on"
console.log(annCate)
}else{
    document.querySelector('.'+annCate).className+=" on"
    console.log(annCate)
}