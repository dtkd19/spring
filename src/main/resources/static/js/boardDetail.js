console.log("boardDetail.js in!!!~");

document.getElementById('listBtn').addEventListener('click', () => {
    // list로 이동 
    location.href="/board/list";
});

document.getElementById('modBtn').addEventListener('click', () => {
    // title, content 의 readonly를 해지 readOnly = true = false
    document.getElementById('t').readOnly = false;
    document.getElementById('c').readOnly = false;

    // modBtn delBtn 삭제
    document.getElementById('modBtn').remove();
    document.getElementById('delBtn').remove();

    // modBtn => submit 버튼으로 변경 추가
    let modBtn = document.createElement('button'); // <button></button>
    modBtn.setAttribute('type','submit'); // <button type="submit"></button>
    modBtn.setAttribute('id','regBtn');
    modBtn.classList.add('btn', 'btn-outline-primary');
    modBtn.innerText="submit";  // <button type="submit" class="btn btn-outline-primary">submit</button>

    // form 태그의 자식 요소로 추가 - form 가장 마지막에 추가됨.
    document.getElementById('modForm').appendChild(modBtn);

    let fileDelBtn = document.querySelectorAll(".file-x");
    console.log(fileDelBtn);
    for(let delBtn of fileDelBtn){
        delBtn.disabled = false;
    };

    document.getElementById('trigger').disabled = false;

});

document.getElementById('delBtn').addEventListener('click', () => {
    let bnoVal = document.getElementById('n').value;
    location.href=`/board/delete?bno=${bnoVal}`;
});


document.addEventListener('click', (e) => {
    if(e.target.classList.contains('file-x')){
        let uuid = e.target.dataset.uuid;
        console.log(uuid);
        let li = e.target.closest('li');

        removeFileToServer(uuid).then(result => {
            if(result == "1"){
                li.remove();
                alert("파일삭제 성공!!");
            } else{
                alert("파일삭제 실패!!");
            }
        })
    }
})

async function removeFileToServer(uuid) {
    try {
        const url = ("/board/file/"+ uuid)
        const config = {
            method : 'delete'
        }
        const resp = await fetch(url, config); 
        const result = await resp.text();
        return result;

    } catch (error) {
        console.log(error);
    }
}
