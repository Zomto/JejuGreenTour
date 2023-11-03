function readURL(input) {
    let box = document.querySelector('.imgLookBox');
    box.innerHTML = "";
    let a = 0;
    for (const file of input.files) {

        console.log(file);
        var fileName = file.name;
        document.querySelector('.upload-name').value = fileName + " 외 " + a + "개";
        var reader = new FileReader();
        reader.onload = function (e) {
            const imgElement = document.createElement("img");
            imgElement.className = "imgSlideList";
            imgElement.src = e.target.result;
            imgElement.alt = '';

            box.appendChild(imgElement);
        };
        a++;
        reader.readAsDataURL(file);
    };

}