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
class ContentManager{
	constructor(){
		this.imgList = document.querySelector(".visual_img");
		this.storeDetails = document.querySelector(".store_details");
		this.ticketBody = document.querySelector(".ticket_body");
		this.ticketPurchase = document.querySelector(".ticket_purchase");
	}
	
	initContent(productId, displayInfoId){
		this.requestAjax(productId,displayInfoId);
	}
	
	requestAjax(productId, displayInfoId){
		let xhr = new XMLHttpRequest();
		let params = `display?productId=${productId}&displayInfoId=${displayInfoId}`;

		xhr.open("GET", '/reservation/reservepage/api/' + params, true);
		xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

		xhr.addEventListener("load", function () {
			this.updateContent(JSON.parse(xhr.responseText));
			this.setEventListener();
		}.bind(this));
		xhr.send();
	}
	
	setEventListener(){
		this.ticketBody.addEventListener("click", function(evt){			
			let ticketCount = evt.target.closest(".clearfix").querySelector("input");
			let ticketPrice = evt.target.closest(".count_control").querySelector(".total_price");
			let discountedPrice = parseInt(evt.target.closest(".qty").querySelector(".product_dsc").innerText.split(' ')[0].slice(0,-1));
			
			if(evt.target.classList.contains("ico_plus3")){
				// plus btn clicked
				ticketCount.value = parseInt(ticketCount.value)+1;
				ticketPrice.innerText = parseInt(ticketPrice.innerText) + discountedPrice;
				this.ticketPurchase.querySelector("#totalCount").innerText = parseInt(this.ticketPurchase.querySelector("#totalCount").innerText) + 1;
				evt.target.closest(".clearfix").querySelector(".ico_minus3").classList.remove("disabled");
				evt.target.closest(".count_control").querySelector(".individual_price").classList.add("on_color");
				ticketCount.classList.remove("disabled");
			}else{
				// minus btn clicked
				if(parseInt(ticketCount.value)-1 < 0){
					evt.target.classList.add("disabled");
					ticketCount.classList.add("disabled");	
				}else{
					ticketCount.value = parseInt(ticketCount.value) - 1;
					ticketPrice.innerText = parseInt(ticketPrice.innerText) - discountedPrice;
					this.ticketPurchase.querySelector("#totalCount").innerText = parseInt(this.ticketPurchase.querySelector("#totalCount").innerText) - 1;
					if(parseInt(ticketPrice.innerText) <= 0){
						evt.target.closest(".count_control").querySelector(".individual_price").classList.remove("on_color");
					}
				}	
			}	
		}.bind(this));
	}
	
	updateContent(jsonData){
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

// Execute all functions
document.addEventListener("DOMContentLoaded", function() {
	const urlParser = new UrlParser();
	const contentManager = new ContentManager();
	contentManager.initContent(urlParser.getProductId(),urlParser.getDisplayInfoId());
});