// Promotion - variables
let promotion = null;
let targets = [];
let slideLen = 0;

// Category tab - variables
let categoryIdx = 0;
let contentsTemplates = [``,``,``,``,``,``];


// Execute all functions
document.addEventListener("DOMContentLoaded", function() {
	promotionInit();
	categoryTabInit();
	moreBtnInit();
	imageSlide();
	requestAjax(0);
});

// moreBtn initialize
function moreBtnInit(){	
	document.querySelector(".more").addEventListener("click", function(){
		requestAjax(categoryIdx, document.querySelector(".wrap_event_box").childElementCount-1);
	});
}

// Category tab variables initialize
function categoryTabInit(){
	document.querySelector(".event_tab_lst").addEventListener("click", function(evt){
		  if(evt.target.tagName==="A" || evt.target.tagName==="SPAN") {
			  contentsUpdate(evt);
			  setActiveMenu(evt.target.closest("LI"));
		  }
	});
}

// Category tab update implementation functions

function contentsUpdate(evt) {	
	let clicked_idx = parseInt(evt.target.closest("LI").getAttribute("data-category"));

	if(clicked_idx != categoryIdx){
		requestAjax(clicked_idx);	
	}
}

function requestAjax(id = 0, turn = 0){
	let xhr = new XMLHttpRequest();
	let params = "id=" + id + "&" + "turn=" + turn;

	xhr.open("GET", 'http://localhost:8080/reservation/api/products?'+params, true);
	xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

	xhr.addEventListener("load", function() {
			 update(id , JSON.parse(this.responseText), turn);
	});

	xhr.send();
}

function update(id, jsonData, turn = 0){
	let list = "";
	let moreBtn = document.querySelector(".more");
	
	document.querySelector(".event_lst_txt .pink").innerText = jsonData["productCount"] + "개";
	
	jsonData["products"].forEach(elem=>{	
		list += document.querySelector("#template-product-card").innerHTML
				.replace("{saveFileName}","./resources/" + elem["saveFileName"])
				.replace("{description}", elem["description"])
				.replace("{placeName}", elem["placeName"])
				.replace("{content}", elem["content"])
	});
	
	if(turn === 0){
		contentsTemplates[id] = list;
		document.querySelector(".wrap_event_box").innerHTML = contentsTemplates[id];	
	}else{
		document.querySelector(".wrap_event_box").removeChild(moreBtn);
		document.querySelector(".wrap_event_box").innerHTML += list;
	}

	document.querySelector(".wrap_event_box").appendChild(moreBtn);	
	
	if(document.querySelector(".wrap_event_box").childElementCount < jsonData["productCount"]){
		if(moreBtn.classList.contains("blind")){
			moreBtn.classList.remove("blind");
		}
	}else{
		moreBtn.classList.add("blind");
	}
}

function setActiveMenu(item){ 
	let inactivMenu = item.closest("UL").querySelector(`:nth-child(${categoryIdx + 1})`);
	inactivMenu.querySelector(".anchor").classList.remove("active");
	item.querySelector(".anchor").classList.add("active");
	categoryIdx = parseInt(item.getAttribute("data-category"));
}

// Promotion variables initialize
function promotionInit() {
	promotion = document.querySelector(".visual_img");
	slideLen = Math.floor(promotion.childNodes.length / 2);
	
	for (let i = 1; i <= slideLen; i++) {
		let elem = promotion.querySelector(`li:nth-child(${i})`);
		targets.push({
			pos : 0,
			idx: i,
			img : elem
		});
	}
}

// Image promotion implementation functions

function getNextIndex(index){
	if(index===0){
		return slideLen-1;
	}
	else{
		return index-1;
	}
}

function convertIndex(slideLen,index){
	if(slideLen<=1){
		return 0;
	}
	else if(slideLen>1 && slideLen<3){
		if(index == 1) 
			return 1;
		else
			return 2;
	}else{
		return index+1;
	}
}

function moveEndImage(idx){
	targets[idx]["pos"] = (slideLen-convertIndex(slideLen,targets[idx]["idx"])) * promotion.offsetWidth;
	targets[idx]["img"].style.transition = "transform 0s";
	targets[idx]["img"].style.transform = `translateX(${targets[idx]["pos"]}px)`;
}

function moveOneStepImage(idx){
	targets[idx]["img"].style.transition = "transform 0.5s";	
	targets[idx]["pos"]-= promotion.offsetWidth;			
	targets[idx]["img"].style.transform = `translateX(${targets[idx]["pos"]}px)`;		
}

function imageSlide() {
	setTimeout(() => {			
		for(let i=0, flag=false;i<targets.length;i++){				
				if(slideLen > 2){		
					let next = getNextIndex(i);	
					moveOneStepImage(i);
					
					if(targets[next]["pos"] < -targets[next]["idx"]*promotion.offsetWidth){
						moveEndImage(next);
					}
				}else{
					if(targets[i]["pos"] <= -targets[i]["idx"]*promotion.offsetWidth){
						moveEndImage(i);
						flag = true;
					}else{
						let next = getNextIndex(i);
						
						if(next!=i && targets[getNextIndex(0)]["pos"] === -targets[getNextIndex(0)]["idx"]*promotion.offsetWidth){
							moveEndImage(next);
						}
						
						moveOneStepImage(i);
						
						if(flag===true){
							moveOneStepImage(next);
						}
					}
				}
		}
	  imageSlide();
	}, 1500);
}