
// ReservationConfirmManager class define
class ReservationConfirmManager {
	constructor(){
		this.emailInput = document.querySelector("#resrv_id");
		this.checkButton = document.querySelector(".login_btn");
		this.warningMsg = document.querySelector(".warning_msg");
		this.passMsg = document.querySelector(".pass_msg");
	}
	
	setSubmitListener(){
		this.emailInput.addEventListener("change", function (evt) {
			if (!(/^[0-9a-z]([-_.]?[0-9a-z])*@[0-9a-z]([-_.]?[0-9a-z])*.[a-z]{2,3}$/i).test(evt.target.value)) {
				this.checkButton.classList.add("disable");
				this.checkButton.disabled = true;
				if(evt.target.value!=""){
					this.warningMsg.style.display = "block";	
				}else{
					this.warningMsg.style.display = "none";
				}
				this.passMsg.style.display = "none";
			} else {
				 this.checkButton.classList.remove("disable");
				 this.checkButton.disabled = false;
				 this.warningMsg.style.display = "none";
				 this.passMsg.style.display = "block";
			}
		}.bind(this));	
	}
}

document.addEventListener("DOMContentLoaded", function() {
	const managerObj = new ReservationConfirmManager();
	managerObj.setSubmitListener();
});