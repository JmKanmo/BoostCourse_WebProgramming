// URL parser object define
const urlParser = {
	getProductId : function() {
		let sch = location.search;
		let params = new URLSearchParams(sch);
		let id = params.get('id');
		return id;
	},
};

// user comment object define
const commentObj = {
	initComment : function() {
		this.requestAjax(urlParser.getProductId());
	},

	updateCommentGrade : function(JSONData) {
		document.querySelector(".graph_value").style.width = `${JSONData["avgAndCnt"]["scoreAvg"]*20}%`;
		document.querySelector(".user_comment_grade").innerText = JSONData["avgAndCnt"]["scoreAvg"];
		document.querySelector(".join_count .green").innerText = `${JSONData["avgAndCnt"]["reviewCnt"]}건`;
	},

	updateUserComment : function(JSONData) {
		let slicedData = JSONData["review"].reduce(function(init_val, cur_val,
				idx, arr) {
			if (idx >= 3) {
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
			"review" : []
		});

		let template = document.querySelector("#template-comment").innerText;
		let bindTemplate = Handlebars.compile(template);
		let ret = bindTemplate(slicedData);
		let userCommentList = document.querySelector(".list_short_review");
		userCommentList.innerHTML = ret;
	},

	requestAjax : function(id) {
		let xhr = new XMLHttpRequest();
		let params = `review?productId=${id}`;

		xhr.open("GET", '/reservation/detailpage/api/' + params, true);
		xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

		xhr.addEventListener("load", function() {
			// 코드작성
			commentObj.updateCommentGrade(JSON.parse(this.responseText));
			commentObj.updateUserComment(JSON.parse(this.responseText));
		});
		xhr.send();
	}
};

// Execute all functions
document.addEventListener("DOMContentLoaded", function() {
	commentObj.initComment();
});