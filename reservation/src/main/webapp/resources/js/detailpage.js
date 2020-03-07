// To parse and get productId from URL
const urlParser = {
	getProductId : function() {
		sch = location.search;
		params = new URLSearchParams(sch);
		let id = params.get('id');
		return id;
	}
}

// detailpage - upperRange
const promotionObj = {
	imageList : null,
	sequence : null,
	range : null,

	initField : function() {
		this.imageList = document.querySelector(".visual_img");
		this.sequence = document.querySelector(".sequence");
		this.range = document.querySelector(".range");
	},

	updatePromotion : function(jsonData) {
		if (jsonData["image"].length == 0) {
			this.sequence.innerText = 0;
			this.range.innerText = `/ ${0}`;
		} else {
			this.sequence.innerText = 1;
			this.range.innerText = `/ ${jsonData["etcImgCnt"] + 1}`
		}

		let template = document.querySelector("#template-promotion").innerText;
		let bindTemplate = Handlebars.compile(template);
		let ret = bindTemplate(jsonData);
		this.imageList.innerHTML = ret;
	},

	requestAjax : function(id) {
		let xhr = new XMLHttpRequest();
		let params = `promotion?productId=${id}`;

		xhr.open("GET", '/reservation/detailpage/api/' + params, true);
		xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

		xhr.addEventListener("load", function() {
			promotionObj.initField();
			promotionObj.updatePromotion(JSON.parse(this.responseText));
		});
		xhr.send();
	},

	initPromotion : function() {
		this.requestAjax(urlParser.getProductId());
	},
};

// Execute all functions
document.addEventListener("DOMContentLoaded", function() {
	// 코드작성
	promotionObj.initPromotion();
});
