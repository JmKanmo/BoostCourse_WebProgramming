// URL parser class define
class UrlParser {
  getProductId() {
    let sch = location.search;
    let params = new URLSearchParams(sch);
    let id = params.get("productId");
    return id;
  }

  getDisplayInfoId() {
    let sch = location.search;
    let params = new URLSearchParams(sch);
    let id = params.get("displayInfoId");
    return id;
  }
}

// product display content manager class define
class DisplayManager {
  constructor() {
    this.imgList = document.querySelector(".visual_img");
    this.storeDetails = document.querySelector(".store_details");
    this.ticketBody = document.querySelector(".ticket_body");
    this.ticketPurchase = document.querySelector(".ticket_purchase");
    this.hiddenInputDiv = document.querySelector("#hiddenInput_div");
    this.purchaseInfo = { priceInfo: null, totalCnt: 0, totalPrice: 0 };
    this.resrvPriceInfo = [];
  }

  initDisplayInfo(productId, displayInfoId) {
    this.requestAjax(productId, displayInfoId);
  }

  initResrvPriceInfo(priceInfo) {
    this.purchaseInfo["priceInfo"] = priceInfo;
    priceInfo.forEach((elem) => {
      this.resrvPriceInfo.push({
        id: elem.id,
        count: 0,
      });
    });
  }

  requestAjax(productId, displayInfoId) {
    let xhr = new XMLHttpRequest();
    let params = `display?productId=${productId}&displayInfoId=${displayInfoId}`;

    xhr.open("GET", "/reservation/reservepage/api/" + params, true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

    xhr.addEventListener(
      "load",
      function () {
        this.initResrvPriceInfo(JSON.parse(xhr.responseText)["price"]);
        this.updateTemplate(JSON.parse(xhr.responseText));
      }.bind(this)
    );
    xhr.send();
  }

  updateTemplate(jsonData) {
    Handlebars.registerHelper("priceType", function (type) {
      switch (type) {
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

    let bindTemplate = Handlebars.compile(
      document.querySelector("#template-introImageList").innerText
    );
    this.imgList.innerHTML = bindTemplate(jsonData);
    bindTemplate = Handlebars.compile(
      document.querySelector("#template-displayInfo").innerText
    );
    this.storeDetails.innerHTML = bindTemplate(jsonData);
    bindTemplate = Handlebars.compile(
      document.querySelector("#template-ticketBox").innerText
    );
    this.ticketBody.innerHTML = bindTemplate(jsonData);
    bindTemplate = Handlebars.compile(
      document.querySelector("#template-ticketPurchase").innerText
    );
    this.ticketPurchase.innerHTML = bindTemplate(jsonData);
    bindTemplate = Handlebars.compile(
      document.querySelector("#template-hiddenInput").innerText
    );
    this.hiddenInputDiv.innerHTML = bindTemplate(jsonData);
  }
}

// reservation manager class define
class ReservationManager extends DisplayManager {
  constructor() {
    super();
    this.urlParser = new UrlParser();
    this.resrvAgreement = document.querySelector(".section_booking_agreement");
    this.resrvForm = document.forms["resrvForm"];
    this.resrvInputs = [
      ["name", /^[가-힣a-zA-Z]+$/, "name_wrap", "reservationName"],
      ["tel", /^\d{2,3}-\d{3,4}-\d{4}$/, "tel_wrap", "reservationTel"],
      [
        "email",
        /^[0-9a-z]([-_.]?[0-9a-z])*@[0-9a-z]([-_.]?[0-9a-z])*.[a-z]{2,3}$/i,
        "email_wrap",
        "reservationEmail",
      ],
    ];
    this.resrvBtn = document.querySelector(".bk_btn");
  }

  setTicketCountListener() {
    this.ticketBody.addEventListener(
      "click",
      function (evt) {
        if (evt.target.tagName === "A") {
          let ticketCount = evt.target
            .closest(".clearfix")
            .querySelector("input");
          let ticketPrice = evt.target
            .closest(".count_control")
            .querySelector(".total_price");
          let discountedPrice = this.purchaseInfo["priceInfo"][
            evt.target.closest(".qty").id
          ]["discountedPrice"];
          let totalTicketCount = this.ticketPurchase.querySelector(
            "#totalCount"
          );
          let totalTicketPrice = this.ticketPurchase.querySelector(
            "#totalPrice"
          );

          if (evt.target.classList.contains("ico_plus3")) {
            // plus button clicked
            ticketCount.value = parseInt(ticketCount.value) + 1;
            ticketPrice.innerText =
              parseInt(ticketPrice.innerText) + discountedPrice;
            evt.target
              .closest(".clearfix")
              .querySelector(".ico_minus3")
              .classList.remove("disabled");
            evt.target
              .closest(".count_control")
              .querySelector(".individual_price")
              .classList.add("on_color");
            ticketCount.classList.remove("disabled");
            this.purchaseInfo["totalCnt"] = this.purchaseInfo["totalCnt"] + 1;
            this.purchaseInfo["totalPrice"] =
              this.purchaseInfo["totalPrice"] + discountedPrice;
            this.resrvPriceInfo[evt.target.closest(".qty").id].count++;
          } else {
            // minus button clicked
            if (parseInt(ticketCount.value) - 1 < 0) {
              evt.target.classList.add("disabled");
              ticketCount.classList.add("disabled");
            } else {
              if (
                this.purchaseInfo["totalCnt"] - 1 >= 0 &&
                this.purchaseInfo["totalPrice"] - discountedPrice >= 0
              ) {
                ticketCount.value = parseInt(ticketCount.value) - 1;
                ticketPrice.innerText =
                  parseInt(ticketPrice.innerText) - discountedPrice;
                this.purchaseInfo["totalCnt"] =
                  this.purchaseInfo["totalCnt"] - 1;
                this.resrvPriceInfo[evt.target.closest(".qty").id].count--;
                this.purchaseInfo["totalPrice"] =
                  this.purchaseInfo["totalPrice"] - discountedPrice;
                if (parseInt(ticketPrice.innerText) <= 0) {
                  evt.target
                    .closest(".count_control")
                    .querySelector(".individual_price")
                    .classList.remove("on_color");
                }
              }
            }
          }
          totalTicketCount.innerText = parseInt(this.purchaseInfo["totalCnt"]);
          totalTicketPrice.innerText = parseInt(
            this.purchaseInfo["totalPrice"]
          );
          this.validationCheck();
        }
      }.bind(this)
    );
  }

  setRegularExpCheckListener() {
    this.resrvInputs.forEach((input) => {
      this.resrvForm[input[0]].addEventListener(
        "change",
        function (evt) {
          if (!input[1].test(evt.target.value)) {
            setTimeout(() => {
              evt.target
                .closest(`.${input[2]}`)
                .querySelector(".warning_msg").style.visibility = "hidden";
            }, 1000);
            evt.target
              .closest(`.${input[2]}`)
              .querySelector(".warning_msg").style.visibility = "visible";
            this.resrvForm[input[0]].value = "";
            this.resrvForm[input[3]].value = "";
          } else {
            this.resrvForm[input[0]].value = evt.target.value;
            this.resrvForm[input[3]].value = evt.target.value;
          }
          this.validationCheck();
        }.bind(this)
      );
    });
  }

  setHiddenDataInput() {
    this.resrvForm["productId"].value = this.urlParser.getProductId();
    this.resrvForm["displayInfoId"].value = this.urlParser.getDisplayInfoId();
    this.resrvForm["reservationDate"].value = document.querySelector(
      "#resrvDate"
    ).innerText;

    for (let i = 0; i < this.resrvPriceInfo.length; i++) {
      this.resrvForm[
        `reservationPrice[${i}].productPriceId`
      ].value = this.resrvPriceInfo[i]["id"];
      this.resrvForm[
        `reservationPrice[${i}].count`
      ].value = this.resrvPriceInfo[i]["count"];
    }
  }

  submitReservationData() {
    this.setHiddenDataInput();
    let queryString = $("form[name = resrvForm]").serialize();
    let params = `reservations?${queryString}`;
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "/reservation/reservepage/api/" + params, true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.addEventListener("load", function () {
      alert("등록 완료");
      window.location.href = "/reservation/main";
    });
    xhr.send();
  }

  validationCheck() {
    let flag = true;

    this.resrvInputs.some((input) => {
      if (
        this.resrvForm[input[0]].value != this.resrvForm[input[3]].value ||
        this.resrvForm[input[3]].value == ""
      ) {
        flag = false;
        return true;
      }
    });

    if (
      this.purchaseInfo["totalCnt"] !=
        parseInt(this.ticketPurchase.querySelector("#totalCount").innerText) ||
      this.purchaseInfo["totalPrice"] !=
        parseInt(this.ticketPurchase.querySelector("#totalPrice").innerText)
    ) {
      flag = false;
    }

    if (
      this.purchaseInfo["totalCnt"] <= 0 ||
      this.purchaseInfo["totalPrice"] <= 0 ||
      document.querySelector(".chk_agree").value === "off"
    ) {
      flag = false;
    }

    for (
      let i = 0, j = 0, nodes = this.ticketBody.childNodes;
      i < nodes.length;
      i++
    ) {
      if (nodes[i].tagName === "DIV") {
        if (nodes[i].id != j++) {
          flag = false;
          break;
        }
      }
    }

    if (!flag) {
      this.resrvBtn.closest("div").classList.add("disable");
    } else {
      this.resrvBtn.closest("div").classList.remove("disable");
    }
  }

  setReserveBtnClickListener() {
    this.resrvBtn.addEventListener(
      "click",
      function () {
        if (
          this.resrvBtn.closest("div").classList.contains("disable") != true
        ) {
          this.submitReservationData();
        }
      }.bind(this)
    );
  }

  setAgreementClickListener() {
    this.resrvAgreement.addEventListener(
      "click",
      function (evt) {
        if (evt.target.tagName === "INPUT") {
          // check box clicked
          if (
            evt.target.closest("div").querySelector(".chk_agree").value ===
            "off"
          ) {
            evt.target.closest("div").querySelector(".chk_agree").value = "on";
          } else {
            evt.target.closest("div").querySelector(".chk_agree").value = "off";
          }
          this.validationCheck();
        } else if (evt.target.tagName === "I") {
          // agreement view clicked
          let agreement = evt.target.closest(".agreement");

          if (!agreement.classList.contains("open")) {
            agreement.classList.add("open");
          } else {
            agreement.classList.remove("open");
          }
        }
      }.bind(this)
    );
  }

  initReservationForm() {
    this.initDisplayInfo(
      this.urlParser.getProductId(),
      this.urlParser.getDisplayInfoId()
    );
    this.setTicketCountListener();
    this.setRegularExpCheckListener();
    this.setAgreementClickListener();
    this.setReserveBtnClickListener();
  }
}

// Execute all functions
document.addEventListener("DOMContentLoaded", function () {
  const reservationManager = new ReservationManager();
  reservationManager.initReservationForm();
});
