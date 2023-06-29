// for payment using rozorpay

const paymentStart = () => {
	console.log("payment started");
	var amount = $("#payment_field").val();
	console.log(amount);
	if (amount == "" || amount == null) {
		swal("Failed !!", "amount is required !!", "error");
		return;
	}

	$.ajax({
		url: "/cart/create_order",
		data: JSON.stringify({ amount: amount, info: "order_request" }),
		contentType: "application/json",
		type: "POST",
		dataType: "json",
		success: function(response) {

			//invoked when success

			console.log(response);
			if (response.status == "created") {

				//open payment form

				let options = {
					key: "rzp_test_uK7UVURCzPUR6d",
					amount: response.amount,
					currency: "INR",
					name: "online shopping",
					description: "testing",
					image: "", // here will paste our website url image path
					order_id: response.id,
					handler: function(response) {
						console.log(response.razorpay_payment_id);
						console.log(response.razorpay_order_id);
						console.log(response.razorpay_signature);
						console.log("payment successful !!");
						swal("Good job!", "congrates !! Payment successful !!", "success");
						
						var cOrderId = response.razorpay_order_id;
						// after successful payment automatic remove data from cart

						$.ajax({
							url: "/cart/deleteAll",
							type: "POST",
							success: function(result) {
								console.log("order successfull")
							}
						});
						
						$.ajax({
							type: "POST",
							url: "/cart/order",
							data:{cOrderId:cOrderId},
							success: function(result) {
							}
						});

					},

					// need to fill details

					prefill: {
						name: "",
						email: "",
						contact: "",
					},
					notes: {
						address: "Online shopping ",
					},
					theme: {
						color: "#3399cc",
					},
				};

				let rzp = new Razorpay(options);

				rzp.on("payment.failed", function(response) {
					console.log(response.error.code);
					console.log(response.error.description);
					console.log(response.error.source);
					console.log(response.error.step);
					console.log(response.error.reason);
					console.log(response.error.metadata.order_id);
					console.log(response.error.metadata.payment_id);

					swal("Failed !!", "Oops payment failed !!", "error");
				});
				rzp.open();

			}
		},
		error: function(error) {
			//invoked when error
			console.log(error);
			alert("something went wrong !!")
		}

	});

}