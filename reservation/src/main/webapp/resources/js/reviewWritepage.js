// urlParser define
class UrlParser {
  getResrvEmail() {
    let sch = location.search;
    let params = new URLSearchParams(sch);
    let email = params.get("resrv_email");
    return email;
  }
  
  getReservationId() {
    let sch = location.search;
    let params = new URLSearchParams(sch);
    let id = params.get("reservationid");
    return id;
  }
};

// Execute all functions
document.addEventListener("DOMContentLoaded", function () {
	const dd = new UrlParser();
	console.log(dd.getReservationId()+" "+dd.getResrvEmail());
});
