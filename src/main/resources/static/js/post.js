const createPostBtn = document.getElementById("postRegisterBtn") || null;

/**
 * post create-post
 * fetch 사용
 */
if (createPostBtn) {
    createPostBtn.addEventListener("click", (e) => {
        e.preventDefault();

        const addPostRequest = {
            title: document.getElementById("title").value,
            content: document.getElementById("content").value,
            attachment: document.getElementById("attachment").value || null,
            ctgId: 10,
            // ctgId: document.getElementById("ctgId").value || null,
            userId: 4,
        };
        // axios.post('/api/post', addPostRequest)
        //     .then((res) => console.log(res))
        //     .catch((err) => console.log(err));

            fetch("/api/post", {
                method: "POST",
                mode : "cors",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(addPostRequest)
            })
                .then((res) => {
                    if(res.status == 201){
                        console.log("res", res);

                        return res.json();
                    } else {
                        throw new Error("게시글 생성에 실패했습니다.")
                    }
                    console.log(res);
                    // location.replace('/post/post-list');
                })
                .then((data) => {
                    console.log("data", data);
                    alert("게시글이 생성되었습니다 !");
                    // location.replace('/post/post-list');
                })
                .catch((error) => console.log(error))
        });

}

