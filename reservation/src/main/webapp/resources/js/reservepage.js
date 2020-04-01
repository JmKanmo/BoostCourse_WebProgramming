// URL parser object define
const urlParser = {
	getProductId : function() {
		let sch = location.search;
		let params = new URLSearchParams(sch);
		let id = params.get('id');
		return id;
	},

	getDisplayInfoId : function() {
		let sch = location.search;
		let params = new URLSearchParams(sch);
		let id = params.get('displayInfoId');
		return id;
	}
};

// Execute all functions
document.addEventListener("DOMContentLoaded", function() {
	console.log(urlParser.getDisplayInfoId());
	console.log(urlParser.getProductId());
});