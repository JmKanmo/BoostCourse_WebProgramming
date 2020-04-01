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
			if(evt.target.classList.contains("ico_plus3")){
				// plus btn clicked
				let a = evt.target.closest(".clearfix").querySelector("input");
				console.log(a);
			}else{
				// minus btn clicked
			}	
		});
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