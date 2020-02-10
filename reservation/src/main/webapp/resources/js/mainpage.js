let promotion = document.querySelector(".promotion");
let targets = [];
let slideLen= 0;

document.addEventListener("DOMContentLoaded", function() {
	promotionInit();
	imageSlide(0);
});

function promotionInit()
{
	slideLen = Math.floor(promotion.childNodes.length/2);
	
	for (let i = 1; i <= slideLen; i++) {
	    let elem = promotion.querySelector(`li:nth-child(${i})`);
	    elem.style.zIndex = slideLen - (i);
	    targets.push(elem);
	}	
}

function imageSlide(idx) {
    setTimeout(() => {
        targets[idx].style.zIndex = (slideLen - 1) - idx;
        targets[idx].style.transition = "transform 0.5s";
        targets[idx].style.transform = `translateX(-${promotion.offsetWidth}px)`;

        if (idx === targets.length - 1) {
            targets[0].style.zIndex = -1;
            targets[0].style.transition = "transform 0s";
            targets[0].style.transform = `translateX(${0}px)`;
            idx = -1;
        }
        else {
            targets[idx + 1].style.transition = "transform 0s";
            targets[idx + 1].style.transform = `translateX(${0}px)`;
        }

        imageSlide(idx + 1);
    }, 1800);
}
