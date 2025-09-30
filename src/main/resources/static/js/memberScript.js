let idFlag = false;
let passFlag = false;
let pass2Flag = false;
let nameFlag = false;
let phoneFlag = false;
let emailFlag = false;
let emailChkFlag = false;

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

//이메일인증
const emailInput = document.getElementById("email");
const emailVerifyBtn = document.getElementById("emailVerifyBtn");
const emailCodeGroup = document.getElementById("emailCodeGroup");
const emailCodeInput = document.getElementById("emailCode");
const emailCodeVerifyBtn = document.getElementById("emailCodeVerifyBtn");
const emailCodeHint = document.getElementById("emailCodeHint");

emailVerifyBtn.addEventListener("click", function() {
    const email = emailInput.value.trim();
    if(!email) return alert("이메일을 입력해주세요.");

    fetch("/api/auth/email", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email })
    })
    .then(() => {
        alert("인증코드가 발송되었습니다!");
        emailCodeGroup.style.display = "block";
    })
    .catch(error => alert("메일 발송 실패: " + error));
});

emailCodeVerifyBtn.addEventListener("click", function() {
	const email = emailInput.value.trim();
    const inputCode = emailCodeInput.value.trim();
    if(!inputCode) return alert("인증번호 입력 필요");

    fetch("/api/auth/email/verify", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
		body: JSON.stringify({ 
		    email: email,
		    inputCode: inputCode 
			})
    })
    .then(res => res.text())
    .then(result => {
        if(result === "success") {
            emailCodeInput.disabled = true;
            emailCodeVerifyBtn.disabled = true;
            emailCodeHint.style.display = "none";
            emailChkFlag = true;
			document.getElementById("inputCodeHidden").value = emailCodeInput.value.trim();

        } else {
            emailCodeHint.textContent = "인증번호가 일치하지 않습니다.";
            emailCodeHint.style.display = "block";
        }
    })
    .catch(() => {
        emailCodeHint.textContent = "서버 오류";
        emailCodeHint.style.display = "block";
    });
});
// 모든 항목 체크
function submitbtn() {
	
	if (idFlag && passFlag && pass2Flag && nameFlag && phoneFlag && emailFlag && emailChkFlag) {
		document.querySelector('.signup-form').submit();
		alert("회원가입 완료!")
	} else {
		alert("형식이 일치하지 않는 항목이 있습니다");
	}
}