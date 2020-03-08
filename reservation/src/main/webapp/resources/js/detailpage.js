// URL parser object
const urlParser = {
	getProductId : function() {
		sch = location.search;
		params = new URLSearchParams(sch);
		let id = params.get('id');
		return id;
	}
}

// image promotion object
const promotionObj = {
	imageList : null,
	imgObject : [],
	sequence : null,
	slideLen : 0,
	prevBtn : null,
	nextBtn : null,

	initPromotion : function() {
		this.requestAjax(urlParser.getProductId());
	},

	initField : function() {
		this.imageList = document.querySelector(".visual_img");
		this.sequence = document.querySelector(".sequence");
		this.prevBtn = document.querySelector(".btn_prev");
		this.nextBtn = document.querySelector(".btn_nxt");
	},

	updatePromotion : function(jsonData) {
		if (jsonData["image"].length == 0) {
			this.sequence.innerText = 0;
			this.range.innerText = `/ ${0}`;
		} else {
			this.sequence.innerText = 1;
			document.querySelector(".range").innerText = `/ ${jsonData["etcImgCnt"] + 1}`
		}

		let template = document.querySelector("#template-promotion").innerText;
		let bindTemplate = Handlebars.compile(template);
		let ret = bindTemplate(jsonData);
		this.imageList.innerHTML = ret;
		this.slideLen = Math.floor(this.imageList.childNodes.length / 2);

		for (let i = 1; i <= this.slideLen; i++) {
			let elem = this.imageList.querySelector(`li:nth-child(${i})`);
			this.imgObject.push({
				pos : 0,
				idx : i,
				img : elem
			});
		}
	},

	requestAjax : function(id) {
		let xhr = new XMLHttpRequest();
		let params = `promotion?productId=${id}`;

		xhr.open("GET", '/reservation/detailpage/api/' + params, true);
		xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

		xhr.addEventListener("load", function() {
			promotionObj.initField();
			promotionObj.updatePromotion(JSON.parse(this.responseText));
			promotionObj.setEventListener();
		});
		xhr.send();
	},

	setEventListener : function() {
		this.prevBtn.addEventListener("click", function() {
			this.moveImages(-1);
		}.bind(promotionObj));

		this.nextBtn.addEventListener("click", function() {
			this.moveImages(1);
		}.bind(promotionObj));
	},

	getNextIndex : function(index) {
		if (index === 0) {
			return this.slideLen - 1;
		} else {
			return index - 1;
		}
	},

	convertIndex : function(slideLen, index) {
		if (slideLen <= 1) {
			return 0;
		} else if (slideLen > 1 && slideLen < 3) {
			if (index == 1)
				return 1;
			else
				return 2;
		} else {
			return index + 1;
		}
	},

	moveImages : function(sign) {
		for (let i = 0, flag = false; i < this.imgObject.length; i++) {
			if (this.slideLen > 2) {
				let next = this.getNextIndex(i);
				this.moveImageOneStep(i, sign);

				if (this.imgObject[next]["pos"] < -this.imgObject[next]["idx"]
						* this.imageList.offsetWidth) {
					this.moveImageEnd(next, sign);
				}
			} else {
				if (this.imgObject[i]["pos"] <= -this.imgObject[i]["idx"]
						* this.imageList.offsetWidth) {
					this.moveImageEnd(i, sign);
					flag = true;
				} else {
					let next = this.getNextIndex(i);

					if (next != i
							&& this.imgObject[this.getNextIndex(0)]["pos"] === -this.imgObject[this
									.getNextIndex(0)]["idx"]
									* this.imageList.offsetWidth) {
						this.moveImageEnd(next, sign);
					}

					this.moveImageOneStep(i, sign);

					if (flag === true) {
						this.moveImageOneStep(next, sign);
					}
				}
			}
		}
	},

	moveImageOneStep : function(idx, sign) {
		this.imgObject[idx]["img"].style.transition = "transform 0.5s";
		this.imgObject[idx]["pos"] -= (this.imageList.offsetWidth * sign);
		this.imgObject[idx]["img"].style.transform = `translateX(${this.imgObject[idx]["pos"]}px)`;
	},

	moveImageEnd : function(idx, sign) {
		this.imgObject[idx]["pos"] = (this.slideLen - this.convertIndex(
				this.slideLen, this.imgObject[idx]["idx"]))
				* this.imageList.offsetWidth * sign;
		this.imgObject[idx]["img"].style.transition = "transform 0s";
		this.imgObject[idx]["img"].style.transform = `translateX(${this.imgObject[idx]["pos"]}px)`;
	},
};

// Execute all functions
document.addEventListener("DOMContentLoaded", function() {
	promotionObj.initPromotion();
});
