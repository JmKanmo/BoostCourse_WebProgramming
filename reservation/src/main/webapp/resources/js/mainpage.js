// Promotion - variables
let promotion = null;
let targets = [];
let slideLen = 0;

// Category tab - variables
let categoryIdx = 0;


// moreBtn initialize
function initMorebtn() {
	document.querySelector(".more").addEventListener("click", function () {
		requestAjax(categoryIdx, getProductListCount());
	});
}

// Category tab variables initialize
function initCategoryTab() {
	document.querySelector(".event_tab_lst").addEventListener("click", function (evt) {
		if (evt.target.tagName === "A" || evt.target.tagName === "SPAN") {
			checkIdx(evt);
			setActiveMenu(evt.target.closest("LI"));
		}
	});
}

// Category tab & event list update implementation functions

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

// update products list
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

// change active category tab
function setActiveMenu(item) {
	let inactivMenu = item.closest("UL").querySelector(`:nth-child(${categoryIdx + 1})`);
	inactivMenu.querySelector(".anchor").classList.remove("active");
	item.querySelector(".anchor").classList.add("active");
	categoryIdx = parseInt(item.getAttribute("data-category"));
}

// Promotion variables initialize
function initPromotion() {
	promotion = document.querySelector(".visual_img");
	slideLen = Math.floor(promotion.childNodes.length / 2);

	for (let i = 1; i <= slideLen; i++) {
		let elem = promotion.querySelector(`li:nth-child(${i})`);
		targets.push({
			pos: 0,
			idx: i,
			img: elem
		});
	}
}

// Image promotion implementation functions

function getNextIndex(index) {
	if (index === 0) {
		return slideLen - 1;
	}
	else {
		return index - 1;
	}
}

function convertIndex(slideLen, index) {
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
}

function moveImageEnd(idx) {
	targets[idx]["pos"] = (slideLen - convertIndex(slideLen, targets[idx]["idx"])) * promotion.offsetWidth;
	targets[idx]["img"].style.transition = "transform 0s";
	targets[idx]["img"].style.transform = `translateX(${targets[idx]["pos"]}px)`;
}

function moveImageOneStep(idx) {
	targets[idx]["img"].style.transition = "transform 0.5s";
	targets[idx]["pos"] -= promotion.offsetWidth;
	targets[idx]["img"].style.transform = `translateX(${targets[idx]["pos"]}px)`;
}

function slideImage() {
	setTimeout(() => {
		for (let i = 0, flag = false; i < targets.length; i++) {
			if (slideLen > 2) {
				let next = getNextIndex(i);
				moveImageOneStep(i);

				if (targets[next]["pos"] < -targets[next]["idx"] * promotion.offsetWidth) {
					moveImageEnd(next);
				}
			} else {
				if (targets[i]["pos"] <= -targets[i]["idx"] * promotion.offsetWidth) {
					moveImageEnd(i);
					flag = true;
				} else {
					let next = getNextIndex(i);

					if (next != i && targets[getNextIndex(0)]["pos"] === -targets[getNextIndex(0)]["idx"] * promotion.offsetWidth) {
						moveImageEnd(next);
					}

					moveImageOneStep(i);

					if (flag === true) {
						moveImageOneStep(next);
					}
				}
			}
		}
		slideImage();
	}, 1500);
}

// Execute all functions
document.addEventListener("DOMContentLoaded", function () {
	initPromotion();
	initCategoryTab();
	initMorebtn();
	slideImage();
	requestAjax(0);
});
