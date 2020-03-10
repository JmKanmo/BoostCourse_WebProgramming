// URL parser object define
const urlParser = {
	getProductId : function() {
		sch = location.search;
		params = new URLSearchParams(sch);
		let id = params.get('id');
		return id;
	}
}

// Execute all functions
document.addEventListener("DOMContentLoaded", function() {
	console.log(urlParser.getProductId());
});