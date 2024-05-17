console.log("boardComment in");
console.log(bnoVal);

document.getElementById('cmtAddBtn').addEventListener('click',()=>{
    const cmtText = document.getElementById('cmtText').value;
    const cmtWriter = document.getElementById('cmtWriter').innerText;
    if(cmtText == null || cmtText == ''){
        alert("댓글을 입력해주세요.");
        document.getElementById('cmtText').focus();
        return false;
    }else{
        let cmtData={
            bno : bnoVal,
            writer : document.getElementById('cmtWriter').innerText,
            content : cmtText
        };
        // 전송
        postCommentToServer(cmtData).then(result => {

            if(result == '1'){
                alert('댓글 등록 성공!!');
            }
            //화면에 뿌리기
            spreadCommentList(bnoVal);
        })
    }

});

async function postCommentToServer(cmtData){
    try {
        const url = "/comment/post";
        const config={
            method:"post",
            headers:{
                "content-type":"application/json; charset=utf-8"
            },
            body:JSON.stringify(cmtData)
        };

        const resp = await fetch(url, config);
        const result = await resp.text(); 
        return result;
    } catch (error) {
        console.log(error);
    }
};

