/* user */

const registerButton = document.getElementById("registerButton");

/** 1.
 * user sign-up (register, create-user ...)
 * axios.post('/api/member')
 */
registerButton.addEventListener("click", (e) => {
    // 사용자가 입력한 정보를 userDTO 로 변환
    const userDTO = {
        // TODO : 프론트 단 유효성 검사
        name: document.getElementById("input_name").value,
        password: document.getElementById("input_password").value,
        email: document.getElementById("input_email").value,
        phone: document.getElementById("input_phone").value,
        birthDate: document.getElementById("input_birth").value || null,
    };

    // rest api call
    axios.post('/api/member', userDTO)
        .then((response) => {
            // TODO : 성공시 alert 처리
            console.log("response", response);
            location.replace('/');
        })
        .catch((error) => {
            // TODO: error 처리(ex) 메인으로 보내버리기
            console.log("error", error);
        });
});