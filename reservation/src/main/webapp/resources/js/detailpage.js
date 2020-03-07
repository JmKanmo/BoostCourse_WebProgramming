// To parse and get productId from URL
const urlParser = {
	getProductId : function() {
		sch = location.search;
		params = new URLSearchParams(sch);
		let id = params.get('id');
		return id;
	}
}

// detailpage - upperRange
const upperRangeObj = {

	initUpperRange : function() {

	}
};

// Execute all functions
document.addEventListener("DOMContentLoaded", function() {
	// 코드작성
});
