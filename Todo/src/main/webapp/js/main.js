var todo_group = [];
var doing_group = [];

function init() {
	for (let i = 0; i < document.querySelector("#todo").querySelectorAll(
			".move_btn").length; i++) {
		todo_group
				.push({
					btn : document.querySelector("#todo").querySelectorAll(
							".move_btn")[i],
					id : document.querySelector("#todo").querySelectorAll(
							".move_btn")[i].getAttribute("data-id"),
					idx : i,
					type : "TODO"
				});
	}

	for (let i = 0; i < document.querySelector("#doing").querySelectorAll(
			".move_btn").length; i++) {
		doing_group
				.push({
					btn : document.querySelector("#doing").querySelectorAll(
							".move_btn")[i],
					id : document.querySelector("#doing").querySelectorAll(
							".move_btn")[i].getAttribute("data-id"),
					idx : i,
					type : "DOING"
				});
	}
}

function set_eventListener(todo, doing) {
	for (let i = 0; i < todo_group.length; i++) {
		todo_group[i]["btn"].addEventListener("click", function() {
			ajax(todo_group[i]["id"], todo_group[i]["type"],
					todo_group[i]["idx"], todo, i);
		}, false);
	}

	for (let i = 0; i < doing_group.length; i++) {
		doing_group[i]["btn"].addEventListener("click", function() {
			ajax(doing_group[i]["id"], doing_group[i]["type"],
					doing_group[i]["idx"], doing, i);
		}, false);
	}
}

function todo(i) {
	mediate_idx(todo_group[i]["type"], todo_group[i]["idx"]);

	if (todo_group[i]["type"] === "TODO") {
		todo_group[i]["idx"] = document.querySelector("#doing")
				.querySelectorAll(".move_btn").length;
		todo_group[i]["type"] = "DOING";
	} else {
		todo_group[i]["idx"] = document.querySelector("#done")
				.querySelectorAll(".item").length;
		todo_group[i]["type"] = "DONE";
	}

}

function doing(i) {
	mediate_idx(doing_group[i]["type"], doing_group[i]["idx"]);

	doing_group[i]["idx"] = document.querySelector("#done").querySelectorAll(
			".item").length;
	doing_group[i]["type"] = "DONE";
}

function mediate_idx(type, idx) {
	switch (type) {
	case "TODO": {
		for (let i = 0; i < todo_group.length; i++) {
			if (todo_group[i]["type"] === "TODO" && todo_group[i]["idx"] > idx) {
				todo_group[i]["idx"]--;
			}
		}
		break;
	}
	case "DOING": {
		for (let i = 0; i < todo_group.length; i++) {
			if (todo_group[i]["type"] === "DOING" && todo_group[i]["idx"] > idx) {
				todo_group[i]["idx"]--;
			}
		}
		for (let i = 0; i < doing_group.length; i++) {
			if (doing_group[i]["type"] === "DOING"
					&& doing_group[i]["idx"] > idx) {
				doing_group[i]["idx"]--;
			}
		}
		break;
	}
	}
}

function moveCard(idx, type) {
	switch (type) {
	case "TODO": {
		document.querySelector("#doing").appendChild(
				document.querySelector("#todo").querySelectorAll(".item")[idx]);
		break;
	}
	case "DOING": {
		document.querySelector("#done")
				.appendChild(
						document.querySelector("#doing").querySelectorAll(
								".item")[idx]);

		document.querySelector("#done").querySelectorAll(".item")[document
				.querySelector("#done").querySelectorAll(".item").length - 1]
				.querySelector(".move_btn").style.display = "none";
		break;
	}
	}
}

function ajax(id, type, idx, func, i) {
	let xhr = new XMLHttpRequest();
	let params = "id=" + id + "&" + "type=" + type;

	xhr.open("POST", '/mavenweb/TodoTypeServlet', true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

	xhr.addEventListener("load", function() {
		if (xhr.responseText === "success") {
			func(i);
			moveCard(idx, type);
		}
	});

	xhr.send(params);
}


window.addEventListener('DOMContentLoaded', (event) => {
    init();
    set_eventListener(todo, doing);
});