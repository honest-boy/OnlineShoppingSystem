
$(document).ready(
	function() {
		$("#product").submit(function(event) {
			event.preventDefault();
			ajaxPost();
		});
		function ajaxPost() {
			var category = $("#category").val();
			var data = new FormData($("#product")[0]);
			data.append('category', category);

			$.ajax({
				type: 'POST',
				enctype: 'multipart/form-data',
				url: "/admin/addProduct",
				data: data,
				processData: false,
				contentType: false,
				cache: false,
				success: function(response) {
					alert("stored  successfully");
					console.log(response);
					$(document).ajaxStop(function() {
						window.location.reload();
					});

				},
				error: function(response) {
					console.log(response);
					alert("Oops! something went wrong.");
				}
			});
		}
	});


$(document).ready(function() {
	$("#getProduct").submit(function(event) {
		event.preventDefault();
		fetchProduct();
	});
});


$(document).ready(function() {
	fetchProduct();
});

function fetchProduct() {
	var keyword = $("#keyword").val();
	var tableData = "";
	$.ajax({
		type: "GET",
		url: "/admin/getProducts",
		data: { keyword: keyword },
		success: function(response) {
			console.log(response);

			response.forEach(function(item, index) {
				tableData += '<tr>' +
					'<td id = "slNo' + item.id + '">' + (index + 1) + '</td>' +
					'<td id = "name' + item.id + '">' + item.name + '</td>' +
					'<td id = "categoryName' + item.id + '">' + item.category.name + '</td>' +
					'<td id = "quantity' + item.id + '">' + item.quantity + '</td>' +
					'<td id = "price' + item.id + '">' + item.price + '</td>' +
					'<td id = "preview' + item.id + '">' + '<img src="/productImages/' + item.imageName + '"+"height="100px" width="100px"">' + '</td>' +
					'<td id = "update' + item.id + '">' + '<a href="/admin/product/update/' + item.id + '"+" class = "btn btn-warning btn-md "">' + 'Update' + '</td>' +
					'<td>' +
					'<button type = "button" id = "deleteButton' + item.id + '" class = "btn btn-danger btn-md deleteButton" onclick = "deleteProduct(' + item.id + ')">Delete</button>' +
					'</td>' +
					'</tr>';
			});
			$("#product-table>tbody").html(tableData);
		},
		error: function(response) {
			console.log(response);
		}
	});
}


function deleteProduct(id) {
	if (confirm("are you want to delete ?")) {
		console.log(id);
		$.ajax({
			type: "DELETE",
			url: "/admin/deleteProduct",
			data: { id: id },
			success: function(response) {
				console.log(response);
				fetchProduct();
			},
			error: function(response) {
				console.log(response);
				alert("oops something went to wrong");
			}
		});
	} else {
		console.log();
	}
}





