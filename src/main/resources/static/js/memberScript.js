let idFlag = false;
let passFlag = false;
let pass2Flag = false;
let nameFlag = false;
let phoneFlag = false;
let emailFlag = false;

let idregex = /(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{5,20}/;
let passregex = /^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9!@#$%^&*]{8,20}$/;
let nameregex = /^[A-Za-z0-9가-힣]{2,10}$/;
let phoneregex = /^\d{11}$/;
let emailregex = /^[A-Za-z0-9]+@[^\s@]+\.[^\s@]{2,}/;
//아이디 실시간 체크
function idcheck() {
	let idval = document.getElementById('userid').value;
	let idhint = document.getElementById('id-hint');

	if (!idregex.test(idval)) {
		idhint.style.color = "red";
	} else {
		idhint.style.color = "black";
		idFlag = true;
	}
}
document.getElementById('userid').addEventListener('focusout', idcheck);

//비밀번호 실시간 체크
function passcheck() {
	let passval = document.getElementById('password').value;
	let passhint = document.getElementById('password-hint');

	if (!passregex.test(passval)) {
		passhint.style.color = "red"
	} else {
		passhint.style.color = "black"
		passFlag = true;
	}
}
document.getElementById('password').addEventListener('focusout', passcheck);

//비밀번호 확인 체크
function pass2check() {
	let passval = document.getElementById('password').value;
	let passval2 = document.getElementById('password2').value;
	let passhint = document.getElementById('password2-hint');

	if (passval != passval2) {
		passhint.style.display = "block"
	} else {
		passhint.style.display = "none"
		pass2Flag = true;
	}
}
document.getElementById('password2').addEventListener('focusout', pass2check);
document.getElementById('password').addEventListener('focusout', pass2check);

//이름 실시간 체크
function namecheck() {
	let nameval = document.getElementById('name').value;
	let namehint = document.getElementById('name-hint');

	if (!nameregex.test(nameval)) {
		namehint.style.color = "red";
	} else {
		namehint.style.color = "black";
		nameFlag = true;
	}
}
document.getElementById('name').addEventListener('focusout', namecheck);

//번호 실시간 체크
function phonecheck() {
	let phoneval = document.getElementById('phone').value;
	let phonehint = document.getElementById('phone-hint');

	if (!phoneregex.test(phoneval)) {
		phonehint.style.color = "red";
	} else {
		phonehint.style.color = "black";
		phoneFlag = true;
	}
}
document.getElementById('phone').addEventListener('focusout', phonecheck);

//이메일 실시간 체크
function emailcheck() {
	let emailval = document.getElementById('email').value
	let emailhint = document.getElementById('email-hint');

	if (!emailregex.test(emailval)) {
		emailhint.style.display = "block"
	} else {
		emailhint.style.display = "none"
		emailFlag = true;
	}
}
document.getElementById('email').addEventListener('focusout', emailcheck);


function submitbtn() {

	if (idFlag && passFlag && pass2Flag && nameFlag && phoneFlag && emailFlag) {
		document.querySelector('.signup-form').submit();
		alert("회원가입 완료!")
	} else {
		alert("형식이 일치하지 않는 항목이 있습니다");
	}
}

//이메일 인증
const emailInput = document.getElementById("email");
const emailVerifyBtn = document.getElementById("emailVerifyBtn");
const emailCodeGroup = document.getElementById("emailCodeGroup");
const emailCodeInput = document.getElementById("emailCode");
const emailCodeVerifyBtn = document.getElementById("emailCodeVerifyBtn");
let serverCode = null; // 서버에서 받은 인증코드 (임시 저장, 실제 구현 시 세션/DB 저장 필요)

emailVerifyBtn.addEventListener("click", function() {
    const email = emailInput.value.trim();
    if (!email) {
        alert("이메일을 입력해주세요.");
        return;
    }

    // 이메일 인증 코드 요청
    fetch("/api/auth/email", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email: email })
    })
    .then(response => response.text())
    .then(result => {
        alert("인증코드가 발송되었습니다!");
        emailCodeGroup.style.display = "block";
        // 서버 코드 예시는 JS에서 임시로 저장 (실제 구현은 서버 DB/세션 사용)
        serverCode = "123456"; 
    })
    .catch(error => {
        alert("메일 발송 실패: " + error);
    });
});

// 인증번호 확인 버튼 클릭 시
emailCodeVerifyBtn.addEventListener("click", function() {
    const inputCode = emailCodeInput.value.trim();
    if (inputCode === serverCode) {
        emailCodeInput.disabled = true;
        emailCodeVerifyBtn.disabled = true;
    } else {
        document.getElementById("emailCodeHint").style.display = "block";
    }
});