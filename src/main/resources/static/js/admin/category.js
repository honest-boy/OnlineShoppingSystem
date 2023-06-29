// for post category in to database

$(document).ready(
	function() {
		$("#postcategory").submit(function(event) {
			event.preventDefault();
			ajaxPost();
		});
		function ajaxPost() {
			var formData = {
				name: $("#name").val()

			}
			$.ajax({
				type: "POST",
				contentType: "application/json",
				url: "/admin/addCategory",
				data: JSON.stringify(formData),
				dataType: 'json',
				success: function(response) {
					$(document).ajaxStop(function() {
						console.log(response);
						window.location.reload();
					});
					fetchCategory();
				},

				error: function(response) {
					console.log(response);
				}
			});
		}
	})


$(document).ready(function() {
	$("#getCategory").submit(function(event) {
		event.preventDefault();
		fetchCategory();
	});
});


$(document).ready(function() {
	fetchCategory();
})


// get categories from database

function fetchCategory() {

	var keyword = $("#keyword").val();
	var tableData = "";
	$.ajax({
		type: "GET",
		url: "/admin/getCategories",
		data: { keyword: keyword },
		success: function(response) {
			console.log(response);

			response.forEach(function(item,index) {
				tableData += '<tr>' +
					'<td id = "slNo' + item.id + '">' + (index+1) + '</td>' +
					'<td id = "name' + item.id + '">' + item.name + '</td>' +
					'<td>' +
					'<button type = "button" id = "editButton' + item.id + '" class = "btn btn-warning btn-md editButton" onclick = "editCategory(' + item.id + ')">Update</button>&nbsp;' +
					'<button type = "button" id = "updateButton' + item.id + '" style = "display:none;" class = "btn btn-success btn-md updateButton" onclick = "updateCategory(' + item.id + ')">Save</button>' +
					'</td>' +
					'<td>' +
					'<button type = "button" id = "deleteButton' + item.id + '" class = "btn btn-danger btn-md deleteButton" onclick = "deleteCategory(' + item.id + ')">Delete</button>' +
					
					'</td>' +
					'</tr>';
			});
			$("#category-table>tbody").html(tableData);
		},
		error: function(response) {
			console.log(response);
		}
	});

}

// delete category from database

function deleteCategory(categoryId) {


	if (confirm("are you want to delete ?")) {
		$.ajax({
			type: "DELETE",
			url: "/admin/deleteCategory",
			data: { id: categoryId },
			success: function(response) {
				console.log(response);
				fetchCategory();
			},
			error: function(response) {
				console.log(response);
				alert("cannot delete directly category while inside this category product will present");
			}

		});
	} else {
		console.log();
		fetchCategory();
	}

}


// edit category


function editCategory(categoryId) {
	if (confirm("are you want to update ?")) {
		document.getElementById("editButton" + categoryId).style.display = "none";
		document.getElementById("updateButton" + categoryId).style.display = "block";

		var name = document.getElementById("name" + categoryId);  //  td nameid 
		var name_data = name.innerHTML;
		console.log(name_data)

		name.innerHTML = "<input type='text' id='name_text" + categoryId + "' value='" + name_data + "'>";
	} else {
		console.log();
		fetchCategory();
	}


}

// after update save category in to database

function updateCategory(categoryId) {

	var name = $('#name_text' + categoryId).val();
	
	$.ajax({
		type: "PUT",
		url: "/admin/updateCategory",
		data: { id: categoryId, name: name },
		success: function(response) {
			console.log(response);
			fetchCategory();
		},
		error: function(response) {
			console.log(response);
		}

	});
}

// for validation

function printError(elemId, hintMsg) {
	document.getElementById(elemId).innerHTML = hintMsg;
}

function submitForm() {
	document.getElementById("postcategory").submit();
}

// Defining a function to validate form 
function validateForm() {
	// Retrieving the values of form elements 
	var name = document.postcategory.name.value;

	// Defining error variables with a default value
	var nameErr = true;

	// Validate name
	if (name == "") {
		printError("nameErr", "Please enter category name");
	} else {
		var regex = /^[a-zA-Z\s]+$/;
		if (regex.test(name) === false) {
			printError("nameErr", "Please enter a valid name");
		} else {
			printError("nameErr", "");
			nameErr = false;
		}
	}

	// Prevent the form from being submitted if there are any errors
	if (nameErr == true) {
		return false;
	} else {
		// Creating a string from input data for preview
		var dataPreview = "You've entered the following details: \n" +
			"Full Name: " + name + "\n";

		// Display input data in a dialog box before submitting the form
		alert(dataPreview);
		submitForm();
	}
};




