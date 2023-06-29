// post user data into data base

$(document).ready(
	function() {

		// SUBMIT FORM
		$("#register").submit(function(event) {
			// Prevent the form from submitting via the browser.
			event.preventDefault();
			ajaxPost();
		});

		function ajaxPost() {

			// PREPARE FORM DATA
			var formData = {	
				firstName: $("#firstName").val(),
				lastName: $("#lastName").val(),
				email: $("#email").val(),
				password: $("#password").val(),

			}
			
			// DO POST
			$.ajax({
				type: "POST",
				contentType: "application/json",
				url: "/register",
				data: JSON.stringify(formData),
				success: function(response) {
					alert("register successful");
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
	
