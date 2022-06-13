const preview = document.getElementById('preview');
function previewFile(file) {
    const reader = new FileReader();

    reader.onload = function(e) {
        const imageUrl = e.target.result;
        const img = document.createElement("img");
        img.src = imageUrl;
        img.style = "height:100px; weight:100px";
        preview.appendChild(img);
    }

    reader.readAsDataURL(file);
}

const fileInput = document.getElementById('file');

const handleFileSelect = () => {

    while(preview.lastChild){
        preview.removeChild(preview.lastChild);
    }

    const files = fileInput.files;

    for (let i=0; i<files.length; i++){
        // 5枚以上アップロードされた場合は、打ち切ってメッセージを出す
        if (i == 5) {

            const div = document.createElement("div");
            div.innerText = "写真は5枚までしかアップロードできません";
            preview.appendChild(div);
            break;                        
        }

        previewFile(files[i]);
    }
}
fileInput.addEventListener('change', handleFileSelect);