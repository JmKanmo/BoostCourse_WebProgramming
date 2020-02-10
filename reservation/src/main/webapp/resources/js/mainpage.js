let promotion = document.querySelector(".promotion");
let targets = [];
let slide_len=null;

document.addEventListener("DOMContentLoaded", function() {
	init();
	image_slide(0);
});

function init()
{
	slide_len = getListLen();
	
	for (let i = 1; i <= slide_len; i++) {
	    let elem = promotion.querySelector(`li:nth-child(${i})`);
	    elem.style.zIndex = slide_len - (i);
	    targets.push(elem);
	}	
}

function getListLen() {
    let len = 0;

    for (let i = 0; i < promotion.childNodes.length; i++) {
        if (!promotion.childNodes[i].length) len++;
    }
    return len;
}

function image_slide(idx) {
    setTimeout(() => {
        targets[idx].style.zIndex = (slide_len - 1) - idx;
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

        image_slide(idx + 1);
    }, 2000);
}
