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
		this.prevBtn = document.querySelector(".btn_prev");
		this.nextBtn = document.querySelector(".btn_nxt");
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

	updatePromotion : function(jsonData) {
		this.imageList = document.querySelector(".visual_img");
		this.sequence = document.querySelector(".sequence");

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
	},

	requestAjax : function(id) {
		let xhr = new XMLHttpRequest();
		let params = `promotion?productId=${id}`;

		xhr.open("GET", '/reservation/detailpage/api/' + params, true);
		xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

		xhr.addEventListener("load", function() {
			promotionObj.updatePromotion(JSON.parse(this.responseText));
			promotionObj.initField();
			promotionObj.setEventListener();
		});
		xhr.send();
	},

	setEventListener : function() {
		this.prevBtn.addEventListener("click", function() {
			this.slideImageLeft(-1);
		}.bind(promotionObj));

		this.nextBtn.addEventListener("click", function() {
			this.slideImageRight(1);
		}.bind(promotionObj));
	},

	getPrevIndex : function(index) {
		if (index === 0)
			return this.slideLen - 1;
		else
			return index - 1;
	},

	getNextIndex : function(index) {
		if (index === this.slideLen - 1)
			return 0;
		else
			return index + 1;
	},

	moveImageEnd : function(idx, sign) {
		if (sign === -1) {
			this.imgObject[idx]["pos"] = (this.slideLen - this.imgObject[idx]["idx"])
					* this.imageList.offsetWidth;
		} else {
			this.imgObject[idx]["pos"] = -this.imgObject[idx]["idx"]
					* this.imageList.offsetWidth;
		}
		this.imgObject[idx]["img"].style.transition = "transform 0s";
		this.imgObject[idx]["img"].style.transform = `translateX(${this.imgObject[idx]["pos"]}px)`;
	},

	moveImageOneStep : function(idx, sign) {
		this.imgObject[idx]["img"].style.transition = "transform 0.5s";
		if (sign === -1) {
			this.imgObject[idx]["pos"] -= this.imageList.offsetWidth;
		} else {
			this.imgObject[idx]["pos"] += this.imageList.offsetWidth;
		}
		this.imgObject[idx]["img"].style.transform = `translateX(${this.imgObject[idx]["pos"]}px)`;
	},

	slideImageRight : function(param) {
		let leftOut = -1;

		for (let i = 0; i < this.slideLen; i++) {
			if (this.imgObject[i]["pos"] === (-this.imgObject[i]["idx"] * this.imageList.offsetWidth)) {
				leftOut = i;
			}
		}

		if (leftOut >= 0) {
			this.moveImageEnd(leftOut, -param);
		}

		for (let i = this.imgObject.length - 1, flag = false; i >= 0; i--) {
			if (i === this.slideLen - 1 && this.imgObject[i]["pos"] === 0) {
				this.moveImageEnd(i, param);
				continue;
			}

			let next = this.getPrevIndex(i);

			if (!flag
					&& this.imgObject[next]["pos"] === (this.slideLen - this.imgObject[next]["idx"])
							* this.imageList.offsetWidth) {
				this.moveImageEnd(next, param);
				flag = true;
				continue;
			}

			if (flag) {
				this.moveImageOneStep(i + 1, param);
				this.moveImageOneStep(i, param);
				flag = false;
			} else {
				this.moveImageOneStep(i, param);
			}

			if (i === 0 && this.imgObject[i]["pos"] === 0)
				continue;

			if (this.imgObject[next]["pos"] <= -this.imgObject[next]["idx"]
					* this.imageList.offsetWidth) {
				this.moveImageOneStep(next, param);
			}
		}
	},

	slideImageLeft : function(param) {
		let leftOut = -1, leftEnd = -1;

		for (let i = 0; i < this.slideLen; i++) {
			if (this.imgObject[i]["pos"] === (-this.imgObject[i]["idx"] * this.imageList.offsetWidth)) {
				leftOut = i;
			}
			if (this.imgObject[i]["pos"] === (-(this.imgObject[i]["idx"] - 1) * this.imageList.offsetWidth)) {
				leftEnd = i;
			}
		}

		if (leftOut >= 0) {
			this.moveImageEnd(leftOut, param);
		}

		while (true) {
			if (this.imgObject[leftEnd]["pos"] === (this.slideLen - this.imgObject[leftEnd]["idx"])
					* this.imageList.offsetWidth) {
				this.moveImageOneStep(leftEnd, param);
				break;
			}
			this.moveImageOneStep(leftEnd, param);
			leftEnd = this.getNextIndex(leftEnd);
		}
	}
};

// Execute all functions
document.addEventListener("DOMContentLoaded", function() {
	promotionObj.initPromotion();
});
