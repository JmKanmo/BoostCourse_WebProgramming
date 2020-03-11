// URL parser object define
const urlParser = {
	getProductId: function () {
		sch = location.search;
		params = new URLSearchParams(sch);
		let id = params.get('id');
		return id;
	},

	getDisplayInfoId: function () {
		sch = location.search;
		params = new URLSearchParams(sch);
		let id = params.get('displayInfoId');
		return id;
	}
};

// image promotion object define
const promotionObj = {
	imageList: null,
	imgObject: [],
	sequence: null,
	slideLen: 0,
	prevBtn: null,
	nextBtn: null,

	initPromotion: function () {
		this.requestAjax(urlParser.getProductId());
	},

	initField: function () {
		this.prevBtn = document.querySelector(".btn_prev");
		this.nextBtn = document.querySelector(".btn_nxt");
		this.slideLen = Math.floor(this.imageList.childNodes.length / 2);

		for (let i = 1; i <= this.slideLen; i++) {
			let elem = this.imageList.querySelector(`li:nth-child(${i})`);
			this.imgObject.push({
				pos: 0,
				idx: i,
				img: elem
			});
		}
	},

	updatePromotion: function (jsonData) {
		this.imageList = document.querySelector(".visual_img");
		this.sequence = document.querySelector(".sequence");

		if (jsonData["image"].length == 0) {
			this.sequence.innerText = 0;
			this.range.innerText = `/ 0`;
		} else {
			this.sequence.innerText = 1;
			document.querySelector(".range").innerText = `/ ${jsonData["etcImgCnt"] + 1}`
		}

		let template = document.querySelector("#template-promotion").innerText;
		let bindTemplate = Handlebars.compile(template);
		let ret = bindTemplate(jsonData);
		this.imageList.innerHTML = ret;
	},

	requestAjax: function (id) {
		let xhr = new XMLHttpRequest();
		let params = `promotion?productId=${id}`;

		xhr.open("GET", '/reservation/detailpage/api/' + params, true);
		xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

		xhr.addEventListener("load", function () {
			promotionObj.updatePromotion(JSON.parse(this.responseText));
			promotionObj.initField();
			promotionObj.setEventListener();
		});
		xhr.send();
	},

	setEventListener: function () {
		this.prevBtn.addEventListener("click", function () {
			this.slideImageLeft(-1);
			this.updateSequence(-1);
		}.bind(promotionObj));

		this.nextBtn.addEventListener("click", function () {
			this.slideImageRight(1);
			this.updateSequence(1);
		}.bind(promotionObj));

		if (this.slideLen <= 1) {
			this.prevBtn.style.display = "none";
			this.nextBtn.style.display = "none";
		}
	},

	updateSequence: function (sign) {
		let n = parseInt(this.sequence.innerText);

		if (sign === 1) {
			if (n + 1 > this.slideLen)
				n = 1;
			else
				n++;
		} else {
			if (n - 1 === 0)
				n = this.slideLen;
			else
				n--;
		}
		this.sequence.innerText = `${n}`;
	},

	getPrevIndex: function (index) {
		if (index === 0)
			return this.slideLen - 1;
		else
			return index - 1;
	},

	getNextIndex: function (index) {
		if (index === this.slideLen - 1)
			return 0;
		else
			return index + 1;
	},

	moveImageEnd: function (idx, sign) {
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

	moveImageOneStep: function (idx, sign) {
		this.imgObject[idx]["img"].style.transition = "transform 0.5s";
		if (sign === -1) {
			this.imgObject[idx]["pos"] -= this.imageList.offsetWidth;
		} else {
			this.imgObject[idx]["pos"] += this.imageList.offsetWidth;
		}
		this.imgObject[idx]["img"].style.transform = `translateX(${this.imgObject[idx]["pos"]}px)`;
	},

	slideImageRight: function (param) {
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

	slideImageLeft: function (param) {
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

// event content Object define
const eventContentObj = {
	initEventContent: function () {
		this.requestAjax(urlParser.getProductId());
	},

	updateContent: function (content) {
		document.querySelector(".store_details .dsc").innerText = content;
	},

	setEventListener: function () {
		$("._open").click(function () {
			$(".store_details").removeClass("close3");
			$("._open").css("display", "none");
			$("._close").css("display", "block");
		});

		$("._close").click(function () {
			$(".store_details").addClass("close3");
			$("._open").css("display", "block");
			$("._close").css("display", "none");
		});
	},

	requestAjax: function (id) {
		let xhr = new XMLHttpRequest();
		let params = `product?productId=${id}`;

		xhr.open("GET", '/reservation/detailpage/api/' + params, true);
		xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

		xhr
			.addEventListener(
				"load",
				function () {
					let content = JSON.parse(this.responseText)["product"][0]["content"];
					eventContentObj.updateContent(content);
					eventContentObj.setEventListener();
				});
		xhr.send();
	}
};

// user reserve,comment object define
const reservationObj = {
	initReservation: function () {
		this.requestAjax(urlParser.getProductId());
	},

	setEventListener: function () {
		let bkBtn = document.querySelector(".bk_btn");
		let reviewMoreBtn = document.querySelector(".btn_review_more");

		bkBtn
			.addEventListener(
				"click",
				function () {
					location.href = `/reservation/reserve?id=${urlParser.getProductId()}`;
				});

		reviewMoreBtn
			.addEventListener(
				"click",
				function () {
					console.log(urlParser.getProductId());
					location.href = `/reservation/review?id=${urlParser.getProductId()}`;
				});
	},

	updateCommentGrade: function (JSONData) {
		document.querySelector(".graph_value").style.width = `${JSONData["avgAndCnt"]["scoreAvg"] * 20}%`;
		document.querySelector(".user_comment_grade").innerText = JSONData["avgAndCnt"]["scoreAvg"];
		document.querySelector(".join_count .green").innerText = `${JSONData["avgAndCnt"]["reviewCnt"]}건`;
	},

	updateUserComment: function (JSONData) {
		let slicedData = JSONData["review"].reduce(function (init_val, cur_val,
			idx) {
			if (idx < 3) {
				if (cur_val["fileURL"] === "") {
					cur_val["blind"] = "blind";
				} else {
					cur_val["blind"] = "";
				}
				cur_val["score"] = cur_val["score"].toFixed(1);
				init_val["review"].push(cur_val);
			}
			return init_val;
		}, {
			"review": []
		});

		let template = document.querySelector("#template-comment").innerText;
		let bindTemplate = Handlebars.compile(template);
		let ret = bindTemplate(slicedData);
		let userCommentList = document.querySelector(".list_short_review");
		userCommentList.innerHTML = ret;
	},

	requestAjax: function (id) {
		let xhr = new XMLHttpRequest();
		let params = `review?productId=${id}`;

		xhr.open("GET", '/reservation/detailpage/api/' + params, true);
		xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

		xhr.addEventListener("load", function () {
			reservationObj.setEventListener();
			reservationObj.updateCommentGrade(JSON.parse(this.responseText));
			reservationObj.updateUserComment(JSON.parse(this.responseText));
		});
		xhr.send();
	}
};

// tab description obj
const descTabObj = {
	descTab: null,
	detailDescription: null,
	detailLocation: null,
	inDescription: null,
	storeLocation: null,

	initField: function () {
		this.descTab = document.querySelector(".info_tab_lst");
		this.detailDescription = document.querySelector(".detail_area_wrap");
		this.detailLocation = document.querySelector(".detail_location");
		this.inDescription = document.querySelector(".detail_info_lst .in_dsc");
		this.storeLocation = document.querySelector(".store_location");
	},

	setActiveTab: function (anchor) {
		if (anchor.getAttribute("id") === "detailDesc") {
			document.querySelector("#pathDesc").classList.remove("active");
		} else {
			document.querySelector("#detailDesc").classList.remove("active");
		}
		anchor.classList.add("active");
	},

	updateDescription: function (param) {
		if (param === "상세정보") {
			this.detailLocation.classList.add("hide");
			this.detailDescription.classList.remove("hide");
			this.requestAjax("product", { "type": "productId", "id": urlParser.getProductId() });
		} else {
			this.detailDescription.classList.add("hide");
			this.detailLocation.classList.remove("hide");
			this.requestAjax("display", { "type": "displayInfoId", "id": urlParser.getDisplayInfoId() });
		}
	},

	updateTemplate(target, jsonData) {
		if (target === "product") {
			this.inDescription.innerText = jsonData["product"][0]["content"];
		} else {
			let template = document.querySelector("#template-display").innerText;
			let bindTemplate = Handlebars.compile(template);
			let ret = bindTemplate(jsonData);
			if (document.querySelector(".store_info") === null) {
				this.storeLocation.insertAdjacentHTML("afterend", ret);
			}
		}
	},

	requestAjax: function (target, param) {
		let xhr = new XMLHttpRequest();
		let params = `${target}?${param["type"]}=${param["id"]}`;

		xhr.open("GET", '/reservation/detailpage/api/' + params, true);
		xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

		xhr.addEventListener("load", function () {
			descTabObj.updateTemplate(target, JSON.parse(this.responseText));
		});
		xhr.send();
	},

	setEventListener: function () {
		this.descTab.addEventListener("click", function (evt) {
			if (evt.target.tagName === "SPAN") {
				let anchor = evt.target.closest("A");
				if (anchor.classList.contains("active") === false) {
					this.setActiveTab(anchor);
					this.updateDescription(evt.target.innerText);
				}
			}
		}.bind(descTabObj));
	},

	initDescTab: function () {
		this.initField();
		this.setEventListener();
		this.requestAjax("product", { "type": "productId", "id": urlParser.getProductId() });
	}
};

// Execute all functions
document.addEventListener("DOMContentLoaded", function () {
	promotionObj.initPromotion();
	eventContentObj.initEventContent();
	reservationObj.initReservation();
	descTabObj.initDescTab();
});
