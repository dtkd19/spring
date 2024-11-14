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
    modBtn.classList.add('btn', 'btn-outline-primary');
    modBtn.innerText="submit";  // <button type="submit" class="btn btn-outline-primary">submit</button>

    // form 태그의 자식 요소로 추가 - form 가장 마지막에 추가됨.
    document.getElementById('modForm').appendChild(modBtn);
});

document.getElementById('delBtn').addEventListener('click', () => {
    let bnoVal = document.getElementById('n').value;
    location.href=`/board/delete?bno=${bnoVal}`;
});