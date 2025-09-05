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