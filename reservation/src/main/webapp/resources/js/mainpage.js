
let imageList = null;
let imgObject = [];
let slideLen = 0;


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


function initPromotion() {
	imageList = document.querySelector(".visual_img");
	slideLen = Math.floor(imageList.childNodes.length / 2);

	for (let i = 1; i <= slideLen; i++) {
		let elem = imageList.querySelector(`li:nth-child(${i})`);
		imgObject.push({
			pos: 0,
			idx: i,
			img: elem
		});
	}
}


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
	imgObject[idx]["pos"] = (slideLen - convertIndex(slideLen, imgObject[idx]["idx"])) * imageList.offsetWidth;
	imgObject[idx]["img"].style.transition = "transform 0s";
	imgObject[idx]["img"].style.transform = `translateX(${imgObject[idx]["pos"]}px)`;
}

function moveImageOneStep(idx) {
	imgObject[idx]["img"].style.transition = "transform 0.5s";
	imgObject[idx]["pos"] -= imageList.offsetWidth;
	imgObject[idx]["img"].style.transform = `translateX(${imgObject[idx]["pos"]}px)`;
}

function slideImage() {
	setTimeout(() => {
		for (let i = 0, flag = false; i < imgObject.length; i++) {
			if (slideLen > 2) {
				let next = getNextIndex(i);
				moveImageOneStep(i);

				if (imgObject[next]["pos"] < -imgObject[next]["idx"] * imageList.offsetWidth) {
					moveImageEnd(next);
				}
			} else {
				if (imgObject[i]["pos"] <= -imgObject[i]["idx"] * imageList.offsetWidth) {
					moveImageEnd(i);
					flag = true;
				} else {
					let next = getNextIndex(i);

					if (next != i && imgObject[getNextIndex(0)]["pos"] === -imgObject[getNextIndex(0)]["idx"] * imageList.offsetWidth) {
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
