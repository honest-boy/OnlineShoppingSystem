$(document).ready(
	function() {

		// SUBMIT FORM
		$("#address").submit(function(event) {
			// Prevent the form from submitting via the browser.
			event.preventDefault();
			ajaxPost();
		});

		function ajaxPost() {

			// PREPARE FORM DATA
			var formData = {
				id :$("#id").val(),
				firstName: $("#firstName").val(),
				lastName: $("#lastName").val(),
				country: $("#country").val(),
				address1: $("#address1").val(),
				address2: $("#address2").val(),
				pincode: $("#pincode").val(),
				city: $("#city").val(),
				phone: $("#phone").val(),
				email: $("#email").val(),
				userId: $("#userId").val(),

			}
			
			// DO POST
			$.ajax({
				type: "POST",
				contentType: "application/json",
				url: "/cart/addAddress",
				data: JSON.stringify(formData),
				success: function(response) {
					alert("address added successful");
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
	
	
	