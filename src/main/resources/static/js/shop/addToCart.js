$(document).ready(function() {
	$("#buttonAddToCart").on("click", function(e) {
		addToCart();
	});
});

function addToCart() {
	var quantity = $("#quantity" + productId).val();
	url = contextPath + "cart/add/" + productId + "/" + quantity;

	$.ajax({
		type: "POST",
		url: url,
	}).done(function(response) {
		console.log(response);
		alert(quantity + " item successfully added to your cart");
	}).fail(function(response) {
		console.log(response);
		alert("you are not logged in");
	});
}