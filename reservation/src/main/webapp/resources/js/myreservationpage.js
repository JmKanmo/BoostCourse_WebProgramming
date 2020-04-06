// URL parser class define
class UrlParser {
	getResrvEmail() {
		let sch = location.search;
		let params = new URLSearchParams(sch);
		let email = params.get('resrv_email');
		return email;
	}
}

// MyReservation HistoryManager class define
class HistoryManager {
	constructor() {
		this.urlParser = new UrlParser();
		this.summaryBoard = document.querySelector(".summary_board");
		this.historyCardList = document.querySelector(".list_cards");
	}

	requestAjax(resrvEmail) {
		let xhr = new XMLHttpRequest();
		let params = `history?resrvEmail=${resrvEmail}`;

		xhr.open("GET", '/reservation/myreservationpage/api/' + params, true);
		xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

		xhr.addEventListener("load", function () {
			this.updateTemplate(JSON.parse(xhr.responseText));
		}.bind(this));
		xhr.send();
	}

	updateTemplate(jsonData) {
		Handlebars.registerHelper("stateType", function (index) {
			switch (index) {
				case 0:
					// total history
					return jsonData["usedHistory"].length + jsonData["scheduledHistory"].length + jsonData["canceldHistory"].length;
				case 1:
					// used history
					return jsonData["usedHistory"].length;
				case 2:
					// scheduled history
					return jsonData["scheduledHistory"].length;
				case 3:
					// canceled history
					return jsonData["canceldHistory"].length;
			}
		});

		Handlebars.registerHelper("isEmpty", function (historyType) {
			if (jsonData[historyType].length === 0)
				return true;
			else
				return false;
		});

		Handlebars.registerHelper("splitDate", function (date) {
			return date.split(" ")[0];
		});

		Handlebars.registerHelper("convertPriceType", function (priceType) {
			switch (priceType) {
				case "A": return "성인";
				case "Y": return "청소년";
				case "B": return "유아";
				case "S": return "세트1";
			}
		});

		Handlebars.registerHelper("purchasePrice", function (price,cnt) {
			return price*cnt;
		});
		
		let bindTemplate = Handlebars.compile(document.querySelector("#template-summary").innerText);
		this.summaryBoard.innerHTML = bindTemplate(jsonData);
		bindTemplate = Handlebars.compile(document.querySelector("#template-history").innerText);
		this.historyCardList.innerHTML = bindTemplate(jsonData);
	}

	initReservationHistory() {
		this.requestAjax(this.urlParser.getResrvEmail());
	}
}

// Execute all functions
document.addEventListener("DOMContentLoaded", function () {
	const historyManager = new HistoryManager();
	historyManager.initReservationHistory();
});