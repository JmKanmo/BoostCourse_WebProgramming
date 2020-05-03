// URL parser class define
class UrlParser {
  getResrvEmail() {
    let sch = location.search;
    let params = new URLSearchParams(sch);
    let email = params.get("resrv_email");
    return email;
  }
}

// MyReservation HistoryManager class define
class HistoryManager {
  constructor() {
    this.urlParser = new UrlParser();
    this.summaryBoard = document.querySelector(".summary_board");
    this.historyCardList = document.querySelector(".list_cards");
    this.popup = document.querySelector(".popup_booking_wrapper");
  }

  setPopupClickListener() {
    this.popup.addEventListener(
      "click",
      function (evt) {
        if (evt.target.tagName === "I") {
          this.popup.style.display = "none";
        } else if (
          evt.target.tagName === "A" ||
          evt.target.tagName === "SPAN"
        ) {
          if (evt.target.innerText === "예") {
            if (
              evt.target.closest(".pop_bottom_btnarea").getAttribute("id") ===
              "cancel"
            ) {
              let reservationInfoId = evt.target
                .closest("div")
                .getAttribute("reservationid");
              this.cancelReservation(reservationInfoId);
              this.popup.style.display = "none";
            } else if (
              evt.target.closest(".pop_bottom_btnarea").getAttribute("id") ===
              "review"
            ) {
              let resrvEmail = this.urlParser.getResrvEmail();
              let resrvId = evt.target
                .closest(".btn_green")
                .getAttribute("reservationid");
              location.href = `/reservation/reviewWrite?resrv_email=${resrvEmail}&reservationid=${resrvId}`;
            }
          } else if (evt.target.innerText === "아니오") {
            this.popup.style.display = "none";
          }
        }
      }.bind(this)
    );
  }

  setButtonClickListener() {
    this.historyCardList.addEventListener(
      "click",
      function (evt) {
        // click cancel button
        if (evt.target.closest(".booking_cancel")) {
          if (evt.target.closest("#cancelScope")) {
            this.requestHistoryById(
              evt.target.closest("#cancelScope").getAttribute("reservationid"),
              "cancel-template-popup"
            );
            this.popup.style.display = "block";
          } else {
            this.requestHistoryById(
              evt.target.closest("#reviewScope").getAttribute("reservationid"),
              "review-template-popup"
            );
            this.popup.style.display = "block";
          }
        }
      }.bind(this)
    );
  }

  cancelReservation(reservationInfoId) {
    let xhr = new XMLHttpRequest();
    let params = `reservations?reservationInfoId=${reservationInfoId}`;

    xhr.open("PUT", "/reservation/myreservationpage/api/" + params, true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

    xhr.addEventListener(
      "load",
      function () {
        this.requestHistoryInfoByEmail(this.urlParser.getResrvEmail());
      }.bind(this)
    );
    xhr.send();
  }

  requestHistoryInfoByEmail(resrvEmail) {
    let xhr = new XMLHttpRequest();
    let params = `reservations?resrvEmail=${resrvEmail}`;

    xhr.open("GET", "/reservation/myreservationpage/api/" + params, true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

    xhr.addEventListener(
      "load",
      function () {
        this.updateHistoryTemplate(JSON.parse(xhr.responseText));
      }.bind(this)
    );
    xhr.send();
  }

  requestHistoryById(reservationId, popupType) {
    let xhr = new XMLHttpRequest();
    let params = `reservations/id?reservationId=${reservationId}`;

    xhr.open("GET", "/reservation/myreservationpage/api/" + params, true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

    xhr.addEventListener(
      "load",
      function () {
        this.updatePopupTemplate(JSON.parse(xhr.responseText), popupType);
      }.bind(this)
    );
    xhr.send();
  }

  updatePopupTemplate(jsonData, popupType) {
    let bindTemplate = Handlebars.compile(
      document.querySelector(`#${popupType}`).innerText
    );
    this.popup.innerHTML = bindTemplate(jsonData);
  }

  updateHistoryTemplate(jsonData) {
    Handlebars.registerHelper("stateType", function (index) {
      switch (index) {
        case 0:
          // total history
          return (
            jsonData["usedHistory"].length +
            jsonData["scheduledHistory"].length +
            jsonData["canceldHistory"].length
          );
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
      if (jsonData[historyType].length === 0) return true;
      else return false;
    });

    Handlebars.registerHelper("splitDate", function (date) {
      return date.split(" ")[0];
    });

    Handlebars.registerHelper("convertPriceType", function (priceType) {
      switch (priceType) {
        case "A":
          return "성인";
        case "Y":
          return "청소년";
        case "B":
          return "유아";
        case "S":
          return "세트";
        case "D":
          return "장애인";
        case "C":
          return "지역주민";
        case "E":
          return "얼리버드";
        case "V":
          return "VIP석";
        case "R":
          return "R석";
        case "B":
          return "B석";
        case "S":
          return "S석";
        case "D":
          return "평일";
      }
    });

    Handlebars.registerHelper("purchasePrice", function (price, cnt) {
      return price * cnt;
    });

    let bindTemplate = Handlebars.compile(
      document.querySelector("#template-summary").innerText
    );
    this.summaryBoard.innerHTML = bindTemplate(jsonData);
    bindTemplate = Handlebars.compile(
      document.querySelector("#template-history").innerText
    );
    this.historyCardList.innerHTML = bindTemplate(jsonData);
  }

  initReservationHistory() {
    this.requestHistoryInfoByEmail(this.urlParser.getResrvEmail());
    this.setButtonClickListener();
    this.setPopupClickListener();
  }
}

// Execute all functions
document.addEventListener("DOMContentLoaded", function () {
  const historyManager = new HistoryManager();
  historyManager.initReservationHistory();
});
