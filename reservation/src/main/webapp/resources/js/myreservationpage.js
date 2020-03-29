// URL parser object define
const urlParser = {
	getEmail : function() {
		let sch = location.search;
		let params = new URLSearchParams(sch);
		let email = params.get('resrv_email');
		return email;
	},
};

// Execute all functions
document.addEventListener("DOMContentLoaded", function() {
	console.log("myreservation.js load");
	console.log(urlParser.getEmail());
});