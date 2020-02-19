let categoryIdx = 0;



function initMorebtn() {
	document.querySelector(".more").addEventListener("click", function () {
		requestAjax(categoryIdx, getProductListCount());
	});
}


function initCategoryTab() {
	document.querySelector(".event_tab_lst").addEventListener("click", function (evt) {
		if (evt.target.tagName === "A" || evt.target.tagName === "SPAN") {
			checkIdx(evt);
			setActiveMenu(evt.target.closest("LI"));
		}
	});
}



function getProductListCount() {
	return (document.querySelector(".wrap_event_box").childElementCount * 2) - 2;
}

function checkIdx(evt) {
	let clicked_idx = parseInt(evt.target.closest("LI").getAttribute("data-category"));

	if (clicked_idx != categoryIdx) {
		requestAjax(clicked_idx);
	}
}

function requestAjax(id = 0, turn = 0) {
	let xhr = new XMLHttpRequest();
	let params = "id=" + id + "&" + "turn=" + turn;

	xhr.open("GET", 'http://localhost:8080/reservation/api/products?' + params, true);
	xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

	xhr.addEventListener("load", function () {
		updateContents(JSON.parse(this.responseText), turn);
	});

	xhr.send();
}


function updateContents(jsonData, turn) {
	let templates = getTemplate(jsonData);
	let moreBtn = document.querySelector(".more");

	document.querySelector(".event_lst_txt .pink").innerText = jsonData["productCount"] + "개";

	if (turn === 0) {
		document.querySelector(".wrap_event_box").innerHTML = templates;
	} else {
		document.querySelector(".wrap_event_box").removeChild(moreBtn);
		document.querySelector(".wrap_event_box").innerHTML += templates;
	}

	document.querySelector(".wrap_event_box").appendChild(moreBtn);

	if (jsonData["productCount"] > 0 && getProductListCount() < jsonData["productCount"]) {
		if (moreBtn.classList.contains("blind")) {
			moreBtn.classList.remove("blind");
		}
	} else {
		moreBtn.classList.add("blind");
	}
}

function getTemplate(jsonData) {
	let cardTemplate = null;
	let itemList = "", cardList = "";

	for (let i = 0; i < jsonData["products"].length; i++) {
		itemList += document.querySelector("#template-card-item").innerHTML
			.replace("{saveFileName}", "./resources/" + jsonData["products"][i]["saveFileName"])
			.replace("{description}", jsonData["products"][i]["description"])
			.replace("{placeName}", jsonData["products"][i]["placeName"])
			.replace("{content}", jsonData["products"][i]["content"]);

		if (i % 2 != 0) {
			cardTemplate = document.querySelector("#template-product-card").innerHTML;
			cardTemplate = cardTemplate.replace("{item}", itemList);
			cardList += cardTemplate;
			itemList = "";
		}
	}

	if (itemList != "") {
		cardTemplate = document.querySelector("#template-product-card").innerHTML;
		cardTemplate = cardTemplate.replace("{item}", itemList);
		cardList += cardTemplate;
	}
	return cardList;
}


function setActiveMenu(item) {
	let inactivMenu = item.closest("UL").querySelector(`:nth-child(${categoryIdx + 1})`);
	inactivMenu.querySelector(".anchor").classList.remove("active");
	item.querySelector(".anchor").classList.add("active");
	categoryIdx = parseInt(item.getAttribute("data-category"));
}

const promotionObj = {
	imageList: null,
	imgObject: [],
	slideLen: 0,

	initPromotion: function () {
		this.imageList = document.querySelector(".visual_img");
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

	getNextIndex: function (index) {
		if (index === 0) {
			return this.slideLen - 1;
		}
		else {
			return this.index - 1;
		}
	},

	convertIndex: function (slideLen, index) {
		if (slideLen <= 1) {
			return 0;
		}
		else if (slideLen > 1 && slideLen < 3) {
			if (index == 1)
				return 1;
			else
				return 2;
		} else {
			return index + 1;
		}
	},

	slideImage: function () {
		setTimeout(() => {
			for (let i = 0, flag = false; i < this.imgObject.length; i++) {
				if (this.slideLen > 2) {
					let next = this.getNextIndex(i);
					this.moveImageOneStep(i);

					if (this.imgObject[next]["pos"] < -this.imgObject[next]["idx"] * this.imageList.offsetWidth) {
						this.moveImageEnd(next);
					}
				} else {
					if (this.imgObject[i]["pos"] <= -this.imgObject[i]["idx"] * this.imageList.offsetWidth) {
						this.moveImageEnd(i);
						flag = true;
					} else {
						let next = this.getNextIndex(i);

						if (next != i && this.imgObject[this.getNextIndex(0)]["pos"] === -this.imgObject[this.getNextIndex(0)]["idx"] * this.imageList.offsetWidth) {
							this.moveImageEnd(next);
						}

						this.moveImageOneStep(i);

						if (flag === true) {
							this.moveImageOneStep(next);
						}
					}
				}
			}
			this.slideImage();
		}, 1500);
	}
};

// Execute all functions
document.addEventListener("DOMContentLoaded", function () {
	promotionObj.initPromotion();
	promotionObj.slideImage();
	initCategoryTab();
	initMorebtn();
	requestAjax(0);
});
