// URL parser class define
class UrlParser{
	getProductId() {
		let sch = location.search;
		let params = new URLSearchParams(sch);
		let id = params.get('productId');
		return id;
	}

	getDisplayInfoId() {
		let sch = location.search;
		let params = new URLSearchParams(sch);
		let id = params.get('displayInfoId');
		return id;
	}
};

// product display content manager class define
class DisplayManager{
	constructor(){
		this.imgList = document.querySelector(".visual_img");
		this.storeDetails = document.querySelector(".store_details");
		this.ticketBody = document.querySelector(".ticket_body");
		this.ticketPurchase = document.querySelector(".ticket_purchase");
	}
	
	initDisplayInfo(productId, displayInfoId){
		this.requestAjax(productId,displayInfoId);
	}
	
	requestAjax(productId, displayInfoId){
		let xhr = new XMLHttpRequest();
		let params = `display?productId=${productId}&displayInfoId=${displayInfoId}`;

		xhr.open("GET", '/reservation/reservepage/api/' + params, true);
		xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

		xhr.addEventListener("load", function () {
			this.updateTemplate(JSON.parse(xhr.responseText));
			this.setTicketCountListener();
		}.bind(this));
		xhr.send();
	}
	
	setTicketCountListener(){
		this.ticketBody.addEventListener("click", function(evt){
			if(evt.target.tagName === "A") {
			let ticketCount = evt.target.closest(".clearfix").querySelector("input");
			let ticketPrice = evt.target.closest(".count_control").querySelector(".total_price");
			let discountedPrice = parseInt(evt.target.closest(".qty").querySelector(".product_dsc").innerText.split(' ')[0].slice(0,-1));
			let totalTicketInfo = this.ticketPurchase.querySelector("#totalCount");
			
			if(evt.target.classList.contains("ico_plus3")){
				// plus button clicked
				ticketCount.value = parseInt(ticketCount.value)+1;
				ticketPrice.innerText = parseInt(ticketPrice.innerText) + discountedPrice;
				totalTicketInfo.innerText = parseInt(totalTicketInfo.innerText) + 1;
				evt.target.closest(".clearfix").querySelector(".ico_minus3").classList.remove("disabled");
				evt.target.closest(".count_control").querySelector(".individual_price").classList.add("on_color");
				ticketCount.classList.remove("disabled");
				totalTicketInfo.setAttribute("totalPrice", parseInt(totalTicketInfo.getAttribute("totalPrice")) + discountedPrice);
			}else{
				// minus button clicked
				if(parseInt(ticketCount.value)-1 < 0){
					evt.target.classList.add("disabled");
					ticketCount.classList.add("disabled");	
				}else{
					ticketCount.value = parseInt(ticketCount.value) - 1;
					ticketPrice.innerText = parseInt(ticketPrice.innerText) - discountedPrice;
					totalTicketInfo.innerText = parseInt(totalTicketInfo.innerText) - 1;
					totalTicketInfo.setAttribute("totalPrice", parseInt(totalTicketInfo.getAttribute("totalPrice")) - discountedPrice);
					if(parseInt(ticketPrice.innerText) <= 0){
						evt.target.closest(".count_control").querySelector(".individual_price").classList.remove("on_color");
					}
				}	
			}
		  }
		}.bind(this));
	}
	
	updateTemplate(jsonData){
		Handlebars.registerHelper("prices", function (index) {
		    return jsonData["price"][index]["price"];
		});
		Handlebars.registerHelper("priceType", function (type) {
			switch(type)
			{
			case "A": return "성인";
			case "Y": return "청소년";
			case "B": return "유아";
			case "S": return "세트1";
			}
		});
		let bindTemplate = Handlebars.compile(document.querySelector("#template-introImageList").innerText);
		this.imgList.innerHTML = bindTemplate(jsonData);
		bindTemplate = Handlebars.compile(document.querySelector("#template-displayInfo").innerText);
		this.storeDetails.innerHTML = bindTemplate(jsonData);
		bindTemplate = Handlebars.compile(document.querySelector("#template-ticketBox").innerText);
		this.ticketBody.innerHTML = bindTemplate(jsonData);
		bindTemplate = Handlebars.compile(document.querySelector("#template-ticketPurchase").innerText);
		this.ticketPurchase.innerHTML = bindTemplate(jsonData);
	}	
}

// reservation manager class define
class ReservationManager extends DisplayManager{
		constructor(){
			super();
			this.resrvForm = document.forms["resrvForm"];
			this.resrvAgreement = document.querySelector(".section_booking_agreement");
			this.resrvInputs = [["name", /^[가-힣a-zA-Z]+$/, "name_wrap", "resrvName"],
		 		  ["tel", /^\d{2,3}-\d{3,4}-\d{4}$/, "tel_wrap", "resrvTel"],
		 		  ["email", /^[0-9a-z]([-_.]?[0-9a-z])*@[0-9a-z]([-_.]?[0-9a-z])*.[a-z]{2,3}$/i, "email_wrap", "resrvEmail"]];
			this.resrvBtn = document.querySelector(".bk_btn");
		}
		
		setRegularExpCheckListener(){		
			this.resrvInputs.forEach((input)=>{
				this.resrvForm[input[0]].addEventListener("change", function (evt) {
		            if (!(input[1]).test(evt.target.value)) {
		            	setTimeout(() => {
		            		evt.target.closest(`.${input[2]}`).querySelector(".warning_msg").style.visibility="hidden";
		            	  }, 1000);
		            	evt.target.closest(`.${input[2]}`).querySelector(".warning_msg").style.visibility="visible";
		            	this.resrvForm[input[0]].value = "";
		            	this.resrvForm[input[3]].value = "";
		            }else{
		            	this.resrvForm[input[0]].value = evt.target.value;
		            	this.resrvForm[input[3]].value = evt.target.value;
		            } 
		        }.bind(this));			
			});		
		}
		
		setValidationCheckListener(){
			this.resrvBtn.addEventListener("click", function(){
				let flag = true;
				
				if(this.resrvBtn.closest("div").classList.contains("disable")!=true){
					this.resrvInputs.some((input)=>{
						if(this.resrvForm[input[0]].value != this.resrvForm[input[3]].value || this.resrvForm[input[3]].value == ""){
							alert('예매정보가 올바르지않거나 비어있습니다.');
							flag = false;
							return true;
						}
					});
					
					// 티켓개수 & 가격 유효성 검사 코드작성
							
					if(flag){
						alert('등록완료');
						// hidden input에 값 대입 & 서버로 정보전송코드작성
					}
				}
			}.bind(this));
		}
		
		setAgreementClickListener(){
			this.resrvAgreement.addEventListener("click", function(evt){
				if(evt.target.tagName === "INPUT"){
					// check box clicked
					if(evt.target.closest("div").querySelector(".chk_agree").value === "off"){
						evt.target.closest("div").querySelector(".chk_agree").value = "on";
						this.resrvBtn.closest("div").classList.remove("disable");
					}else{
						evt.target.closest("div").querySelector(".chk_agree").value = "off";
						this.resrvBtn.closest("div").classList.add("disable");
					}
				}else if(evt.target.tagName === "I"){
					// agreement view clicked
				}
			}.bind(this));
		}
		
		initReservationForm(){
			this.setRegularExpCheckListener();
			this.setAgreementClickListener();
			this.setValidationCheckListener();
		}
}

// Execute all functions
document.addEventListener("DOMContentLoaded", function() {
	const urlParser = new UrlParser();
	const reservationManager = new ReservationManager();
	reservationManager.initDisplayInfo(urlParser.getProductId(),urlParser.getDisplayInfoId());
	reservationManager.initReservationForm();
});