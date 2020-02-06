let submit_btn = document.querySelector(".submit_btn");

function validation() {
	let form = document.forms["form"];
	let work_input = document.forms["form"]["work_input"].value;
	let who_input = document.forms["form"]["who_input"].value;
	let priority = document.forms["form"]["priority"].value;

	if (!work_input || work_input.length > 24 || !who_input || !priority) {
		alert('잘못된입력: 다시입력하세요.');
		return false;
	} else {
		return true;
	}
}
