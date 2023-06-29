$(document).ready(function() {
	$(".minusButton").on("click", function(evt) {
		evt.preventDefault();
		decreaseQuantity($(this));
	});

	$(".plusButton").on("click", function(evt) {
		evt.preventDefault();
		increaseQuantity($(this));
	});

	$(".link-remove").on("click", function(evt) {
		evt.preventDefault();
		removeFromCart($(this));
	});

	updateTotal();
});


function removeFromCart(link) {
	url = link.attr("href");
	$.ajax({
		type: "POST",
		url: url,
		success: function(response) {
			$(document).ajaxStop(function() {
				window.location.reload();
			});
		},
	}).done(function(newSubtotal) {
		updateSubtotal(newSubtotal, productId);
		updateTotal();

	}).fail(function(response) {
		alert("item not added, something wrong");
	});

}

function decreaseQuantity(link) {
	productId = link.attr("pid");
	qtyInput = $("#quantity" + productId);
	newQty = parseInt(qtyInput.val()) - 1;

	if (newQty > 0) {
		qtyInput.val(newQty);
		updateQuantity(productId, newQty);
	}
}

function increaseQuantity(link) {
	productId = link.attr("pid");
	qtyInput = $("#quantity" + productId);
	newQty = parseInt(qtyInput.val()) + 1;

	if (newQty < 6) {
		qtyInput.val(newQty);
		updateQuantity(productId, newQty);
	}
}

function updateQuantity(productId, quantity) {
	url = contextPath + "cart/update/" + productId + "/" + quantity;

	$.ajax({
		type: "POST",
		url: url,
		success: function(response) {

		},
	}).done(function(newSubtotal) {
		updateSubtotal(newSubtotal, productId);
		updateTotal();
	}).fail(function(response) {
		alert("item not added, something wrong");
	});

}

function updateSubtotal(newSubtotal, productId) {
	$("#subtotal" + productId).text(newSubtotal);
}

function updateTotal() {
	var total = 0;

	$(".productSubtotal").each(function(index, element) {
		console.log(total);
		total = total + parseFloat(element.innerHTML);
		console.log(total);
	});
	$("#totalAmount").html(total);
	total = $("#totalAmount").text();
	$.ajax({
		type: "POST",
		url: "/cart/total/" + total,
	}).done(function(response) {

	}).fail(function(response) {

	});

}