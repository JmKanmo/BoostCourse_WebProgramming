// urlParser class define
class UrlParser {
  getResrvEmail() {
    let sch = location.search;
    let params = new URLSearchParams(sch);
    let email = params.get("resrv_email");
    return email;
  }

  getProductId() {
    let sch = location.search;
    let params = new URLSearchParams(sch);
    let id = params.get("productId");
    return id;
  }

  getReservationId() {
    let sch = location.search;
    let params = new URLSearchParams(sch);
    let id = params.get("reservationid");
    return id;
  }
}

// RatingController class define
class RatingController {
  constructor() {
    this.reviewRating = document.querySelector(".review_rating");
    this.rating = 0;
    this.visited = [];
    this.rankList = Array.prototype.slice
      .call(this.reviewRating.querySelector(".rating").childNodes)
      .filter((elem) => {
        if (elem.nodeName === "INPUT") {
          this.visited.push(false);
          return true;
        }
      });
    this.starGrade = this.reviewRating.querySelector(".star_rank");
  }

  initRatingController() {
    this.setRatingEventListener();
  }

  setRatingEventListener() {
    this.reviewRating.addEventListener(
      "click",
      function (evt) {
        if (evt.target.tagName === "INPUT") {
          let clickedRating = parseInt(evt.target.value);

          if (this.rating < clickedRating) {
            for (let i = this.rating; i < clickedRating; i++) {
              this.rankList[i].classList.add("checked");
            }
          } else if (this.rating > clickedRating) {
            for (let i = this.rating - 1; i > clickedRating - 1; i--) {
              this.rankList[i].classList.remove("checked");
              if (this.visited[i] === false) {
                this.rankList[i].classList.remove("checked");
              } else {
                this.rankList[i].click();
              }
            }
          }

          if (this.visited[clickedRating - 1] === false) {
            this.visited[clickedRating - 1] = true;
          } else {
            this.visited[clickedRating - 1] = false;
          }
          this.rating = clickedRating;
          this.starGrade.innerText = this.rating;
          if (parseInt(this.starGrade.innerText) > 0) {
            this.starGrade.classList.remove("gray_star");
          }
        }
      }.bind(this)
    );
  }

  getStarGrade() {
    return this.rating;
  }
}

// ReviewContentController class define
class ReviewContentController {
  constructor() {
    this.reviewWriteBox = document.querySelector(".review_write_info");
    this.reviewTextArea = document.querySelector(".review_textarea");
    this.reviewTextCounter = document.querySelector(".guide_review .textCount");
    this.reviewTextLimit = parseInt(
      document.querySelector(".guide_review .textLimit").innerText
    );
    this.reviewTextMin = parseInt(
      document.querySelector(".guide_review .textMin").innerText
    );
  }

  initReviewContentController() {
    this.setReviewContentEventListener();
  }

  typingText(evt) {
    if (evt.target.value.length > this.reviewTextLimit) {
      this.reviewTextArea.value = this.reviewTextArea.value.substring(
        0,
        this.reviewTextLimit
      );
      this.reviewTextCounter.innerText = this.reviewTextLimit;
      alert(`글자수가 ${this.reviewTextLimit}자가 넘습니다.`);
    } else {
      this.reviewTextCounter.innerText = evt.target.value.length;
    }
  }

  setReviewContentEventListener() {
    this.reviewWriteBox.addEventListener(
      "focus",
      function () {
        this.reviewWriteBox.classList.add("blind");
        this.reviewTextArea.classList.add("focus");
        this.reviewTextArea.focus();
      }.bind(this)
    );

    this.reviewTextArea.addEventListener(
      "blur",
      function (evt) {
        if (evt.target.value.length === 0) {
          this.reviewTextArea.classList.remove("focus");
          this.reviewWriteBox.classList.remove("blind");
        }
      }.bind(this)
    );

    this.reviewTextArea.addEventListener(
      "keydown",
      function (evt) {
        this.typingText(evt);
      }.bind(this)
    );

    this.reviewTextArea.addEventListener(
      "keyup",
      function (evt) {
        this.typingText(evt);
      }.bind(this)
    );
  }

  getReviewContent() {
    let reviewContent = this.reviewTextArea.value;

    if (reviewContent.length < 5) {
      return null;
    } else {
      return reviewContent;
    }
  }
}

// PicRegistController class define
class PicRegistController {
  constructor() {
    this.reviewPhotoBox = document.querySelector(".review_photos");
    this.delBtn = document.querySelector(".ico_del");
    this.imageFile = null;
    this.imageFileInputTag = document.querySelector(
      "#reviewImageFileOpenInput"
    );
  }

  initPicRegistController() {
    this.setPicRegistEventListener();
  }

  validImage(image) {
    const result =
      ["image/jpeg", "image/png", "image/jpg"].indexOf(image.type) > -1;
    return result;
  }

  setPicRegistEventListener() {
    this.imageFileInputTag.addEventListener(
      "change",
      function (evt) {
        const image = evt.target.files[0];
        this.imageFile = image;

        if (!this.validImage(image)) {
          alert("이미지는 jpg, png형식만 가능합니다.");
          return;
        }
        this.reviewPhotoBox.querySelector(".item").style.display = "block";
        this.reviewPhotoBox.querySelector(
          ".item_thumb"
        ).src = window.URL.createObjectURL(image);
      }.bind(this)
    );

    this.delBtn.addEventListener(
      "click",
      function (evt) {
        evt.target.closest(".item").style.display = "none";
        this.imageFile = null;
        this.imageFileInputTag.value = "";
      }.bind(this)
    );
  }
}

// ReviewFormController class define
class ReviewFormController {
  constructor() {
    this.urlParser = new UrlParser();
    this.ratingController = new RatingController();
    this.reviewContentController = new ReviewContentController();
    this.picRegistController = new PicRegistController();
    this.submitForm = document.querySelector("#reviewForm");
  }

  initReviewFormController() {
    this.ratingController.initRatingController();
    this.reviewContentController.initReviewContentController();
    this.picRegistController.initPicRegistController();
    this.initSubmitFormController();
  }

  updateHiddenInputTemplate() {
    let bindTemplate = Handlebars.compile(
      document.querySelector("#hidden-input-template").innerText
    );
    document.querySelector("#hiddenInputBox").innerHTML = bindTemplate();
    this.submitForm["resrvId"].value = this.urlParser.getReservationId();
    this.submitForm["resrvEmail"].value = this.urlParser.getResrvEmail();
    this.submitForm["productId"].value = this.urlParser.getProductId();
    this.submitForm["starGrade"].value = this.ratingController.getStarGrade();
    this.submitForm[
      "reviewContent"
    ].value = this.reviewContentController.getReviewContent();
  }

  initSubmitFormController() {
    let checkBeforeSubmit = () => {
      this.updateHiddenInputTemplate();

      if (!this.submitForm["reviewContent"].value) {
        alert(
          `텍스트는 ${this.reviewContentController.reviewTextMin}자이상 ${this.reviewContentController.reviewTextLimit}자이하로 작성하셔야합니다.`
        );
        return false;
      } else {
        alert("리뷰가 등록됩니다.");
        return true;
      }
    };

    this.submitForm.onsubmit = () => {
      return checkBeforeSubmit();
    };
  }
}

// Execute all functions
document.addEventListener("DOMContentLoaded", function () {
  const reviewFormController = new ReviewFormController();
  reviewFormController.initReviewFormController();
});
