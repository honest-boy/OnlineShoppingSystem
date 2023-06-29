
$(document).ready(
	function() {

		// SUBMIT FORM
		$("#update").submit(function(event) {
			// Prevent the form from submitting via the browser.
			event.preventDefault();
			ajaxPut();
		});

		function ajaxPut() {

			// PREPARE FORM DATA
			var formData = {
				id: $("#id").val(),
				firstName: $("#firstName").val(),
				lastName: $("#lastName").val(),
				email: $("#email").val(),
				password: $("#password").val(),

			}

			// DO POST
			$.ajax({
				type: "PUT",
				contentType: "application/json",
				url: "/updateUser",
				data: JSON.stringify(formData),
				success: function(response) {
					alert("profile updated");
					console.log(response);
					$(document).ajaxStop(function() {
						window.location.reload();
					});
				},
				error: function(response) {
					console.log(response);
					alert("oops something wrong !!")
				}

			});
		}

	});

