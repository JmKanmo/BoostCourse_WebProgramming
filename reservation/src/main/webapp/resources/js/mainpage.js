// product event tab component object define
const eventTabObj = {
  categoryIdx: 0,

  updateCategoryTap: function (jsonData) {
    let categoryTab = document.querySelector(".event_tab_lst");
    let template = document.querySelector("#template-category-tab").innerText;
    let bindTemplate = Handlebars.compile(template);
    let ret = bindTemplate(jsonData);
    categoryTab.innerHTML += ret;
  },

  setMorebtnEvent: function () {
    document.querySelector(".more").addEventListener(
      "click",
      function () {
        this.requestAjax(
          "products",
          this.categoryIdx,
          this.getProductListCount()
        );
      }.bind(eventTabObj)
    );
  },

  setCategoryEvent: function () {
    document.querySelector(".event_tab_lst").addEventListener(
      "click",
      function (evt) {
        if (evt.target.tagName === "A" || evt.target.tagName === "SPAN") {
          this.callAjax(evt);
          this.setActiveMenu(evt.target.closest("LI"));
        }
      }.bind(eventTabObj)
    );
  },

  initEventTab: function () {
    this.requestAjax("categories");
    this.setMorebtnEvent();
    this.setCategoryEvent();
    this.requestAjax("products");
  },

  getProductListCount: function () {
    return document.querySelector(".wrap_event_box").childElementCount * 2 - 2;
  },

  callAjax: function (evt) {
    let clickedIdx = parseInt(
      evt.target.closest("LI").getAttribute("data-category")
    );

    if (clickedIdx != this.categoryIdx) {
      this.requestAjax("products", clickedIdx);
    }
  },

  requestAjax: function (target, id = 0, turn = 0, limit = 4) {
    let xhr = new XMLHttpRequest();
    let params = `${target}?id=${id}&turn=${turn}&limit=${limit}`;

    xhr.open("GET", "/reservation/mainpage/api/" + params, true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

    xhr.addEventListener("load", function () {
      switch (target) {
        case "products":
          eventTabObj.updateContents(JSON.parse(this.responseText), turn);
          break;

        case "categories":
          eventTabObj.updateCategoryTap(JSON.parse(this.responseText));
          break;
      }
    });

    xhr.send();
  },

  setActiveMenu: function (item) {
    let inactivMenu = item
      .closest("UL")
      .querySelector(`:nth-child(${this.categoryIdx + 1})`);
    inactivMenu.querySelector(".anchor").classList.remove("active");
    item.querySelector(".anchor").classList.add("active");
    this.categoryIdx = parseInt(item.getAttribute("data-category"));
  },

  updateContents: function (jsonData, turn) {
    let templates = this.getTemplate(jsonData);
    let moreBtn = document.querySelector(".more");

    document.querySelector(".event_lst_txt .pink").innerText =
      jsonData["productCount"] + "개";

    if (turn === 0) {
      document.querySelector(".wrap_event_box").innerHTML = templates;
    } else {
      document.querySelector(".wrap_event_box").removeChild(moreBtn);
      document.querySelector(".wrap_event_box").innerHTML += templates;
    }

    document.querySelector(".wrap_event_box").appendChild(moreBtn);

    if (
      jsonData["productCount"] > 0 &&
      this.getProductListCount() < jsonData["productCount"]
    ) {
      if (moreBtn.classList.contains("blind")) {
        moreBtn.classList.remove("blind");
      }
    } else {
      moreBtn.classList.add("blind");
    }
  },

  getTemplate: function (jsonData) {
    let slicedData = [
      {
        products: jsonData["products"].slice(0, 2),
      },
      {
        products: jsonData["products"].slice(2, 4),
      },
    ];

    let template = document.querySelector("#template-product-list").innerText;
    let bindTemplate = Handlebars.compile(template);
    let retData = [bindTemplate(slicedData[0]), bindTemplate(slicedData[1])];
    return retData[0] + retData[1];
  },
};

// image promotion component object define
const promotionObj = {
  imageList: null,
  imgObject: [],
  slideLen: 0,

  initPromotion: function () {
    this.requestAjax();
    this.slideImageLeft();
  },

  initField: function () {
    this.slideLen = Math.floor(this.imageList.childNodes.length / 2);

    for (let i = 1; i <= this.slideLen; i++) {
      let elem = this.imageList.querySelector(`li:nth-child(${i})`);
      this.imgObject.push({
        pos: 0,
        idx: i,
        img: elem,
      });
    }
  },

  updatePromotion: function (jsonData) {
    this.imageList = document.querySelector(".visual_img");
    let template = document.querySelector("#template-promotion").innerText;
    let bindTemplate = Handlebars.compile(template);
    let ret = bindTemplate(jsonData);
    this.imageList.innerHTML = ret;
  },

  requestAjax: function () {
    let xhr = new XMLHttpRequest();

    xhr.open("GET", "/reservation/mainpage/api/promotions", true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

    xhr.addEventListener("load", function () {
      promotionObj.updatePromotion(JSON.parse(this.responseText));
      promotionObj.initField();
    });

    xhr.send();
  },

  getPrevIndex: function (index) {
    if (index === 0) return this.slideLen - 1;
    else return index - 1;
  },

  getNextIndex: function (index) {
    if (index === this.slideLen - 1) return 0;
    else return index + 1;
  },

  moveImageEnd: function (idx, sign) {
    if (sign === -1) {
      this.imgObject[idx]["pos"] =
        (this.slideLen - this.imgObject[idx]["idx"]) *
        this.imageList.offsetWidth;
    } else {
      this.imgObject[idx]["pos"] =
        -this.imgObject[idx]["idx"] * this.imageList.offsetWidth;
    }
    this.imgObject[idx]["img"].style.transition = "transform 0s";
    this.imgObject[idx][
      "img"
    ].style.transform = `translateX(${this.imgObject[idx]["pos"]}px)`;
  },

  moveImageOneStep: function (idx, sign) {
    this.imgObject[idx]["img"].style.transition = "transform 0.5s";
    if (sign === -1) {
      this.imgObject[idx]["pos"] -= this.imageList.offsetWidth;
    } else {
      this.imgObject[idx]["pos"] += this.imageList.offsetWidth;
    }
    this.imgObject[idx][
      "img"
    ].style.transform = `translateX(${this.imgObject[idx]["pos"]}px)`;
  },

  slideImageRight: function () {
    setTimeout(() => {
      for (let i = this.imgObject.length - 1, flag = false; i >= 0; i--) {
        if (i === this.slideLen - 1 && this.imgObject[i]["pos"] === 0) {
          this.moveImageEnd(i, 1);
          continue;
        }

        let next = this.getPrevIndex(i);

        if (
          !flag &&
          this.imgObject[next]["pos"] ===
            (this.slideLen - this.imgObject[next]["idx"]) *
              this.imageList.offsetWidth
        ) {
          this.moveImageEnd(next, 1);
          flag = true;
          continue;
        }

        if (flag) {
          this.moveImageOneStep(i + 1, 1);
          this.moveImageOneStep(i, 1);
          flag = false;
        } else {
          this.moveImageOneStep(i, 1);
        }

        if (i === 0 && this.imgObject[i]["pos"] === 0) continue;

        if (
          this.imgObject[next]["pos"] <=
          -this.imgObject[next]["idx"] * this.imageList.offsetWidth
        ) {
          this.moveImageOneStep(next, 1);
        }
      }
      this.slideImageRight();
    }, 1500);
  },

  slideImageLeft: function () {
    setTimeout(() => {
      let leftOut = -1,
        leftEnd = -1;

      for (let i = 0; i < this.slideLen; i++) {
        if (
          this.imgObject[i]["pos"] ===
          -this.imgObject[i]["idx"] * this.imageList.offsetWidth
        ) {
          leftOut = i;
        }
        if (
          this.imgObject[i]["pos"] ===
          -(this.imgObject[i]["idx"] - 1) * this.imageList.offsetWidth
        ) {
          leftEnd = i;
        }
      }

      if (leftOut >= 0) {
        this.moveImageEnd(leftOut, -1);
      }

      while (true) {
        if (
          this.imgObject[leftEnd]["pos"] ===
          (this.slideLen - this.imgObject[leftEnd]["idx"]) *
            this.imageList.offsetWidth
        ) {
          this.moveImageOneStep(leftEnd, -1);
          break;
        }
        this.moveImageOneStep(leftEnd, -1);
        leftEnd = this.getNextIndex(leftEnd);
      }
      this.slideImageLeft();
    }, 1500);
  },
};

// Execute all functions
document.addEventListener("DOMContentLoaded", function () {
  promotionObj.initPromotion();
  eventTabObj.initEventTab();
});
