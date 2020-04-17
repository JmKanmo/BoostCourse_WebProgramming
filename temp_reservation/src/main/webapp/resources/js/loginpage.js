function validateForm() {
	emailInput = document.querySelector("#resrv_id");
	warningMsg = document.querySelector(".warning_msg");

	if (!(/^[0-9a-z]([-_.]?[0-9a-z])*@[0-9a-z]([-_.]?[0-9a-z])*.[a-z]{2,3}$/i)
		.test(emailInput.value)) {
		warningMsg.style.display = "block";
		return false;
	} else {
		this.warningMsg.style.display = "none";
		return true;
	}
}
