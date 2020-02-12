// Promotion - variables
let promotion = document.querySelector(".visual_img");
let targets = [];
let slideLen = 0;

// Category tab - variables
let categoryObj = {
		idx : 0,
		cnt : 0,
}

// Execute all functions
document.addEventListener("DOMContentLoaded", function() {
	promotionInit();
	categoryTabInit();
	imageSlide();
});


// Category tab variables initialize
function categoryTabInit(){
	let category_tab = document.querySelector(".event_tab_lst");
	
	category_tab.addEventListener("click", function(evt){
		  if(evt.target.tagName==="A" || evt.target.tagName==="SPAN") {
			  contentsUpdate(evt);
		  }
	});
}

// Category tab update implementation functions

function contentsUpdate(evt) {
	setActiveMenu(evt.target.closest("LI"));
	console.log(document.querySelector("template-product-card"));
}

function setActiveMenu(item){ 
	let inactivMenu = item.closest("UL").querySelector(`:nth-child(${categoryObj["idx"] + 1})`);
	inactivMenu.querySelector(".anchor").classList.remove("active");
	item.querySelector(".anchor").classList.add("active");
	categoryObj["idx"] = parseInt(item.getAttribute("data-category"));
}

// Promotion variables initialize
function promotionInit() {
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