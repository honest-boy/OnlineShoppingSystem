function printError(elemId, hintMsg) {
	document.getElementById(elemId).innerHTML = hintMsg;
}


function validateForm() {
	var firstName = document.address.firstName.value;
	var lastName = document.address.lastName.value;
	var address1 = document.address.address1.value;
	var address2 = document.address.address2.value;
	var city = document.address.city.value;
	var email = document.address.email.value;
	
	var firstNameErr = lastNameErr = address1Err = address2Err  = cityErr =  emailErr = true;

	// Validate name
	if (firstName == "") {
		printError("firstNameErr", "Please enter your first name");
	} else {
		var regex = /^[a-zA-Z\s]+$/;
		if (regex.test(firstName) === false) {
			printError("firstNameErr", "Please enter a valid first name");
		} else {
			printError("firstNameErr", "");
		}
	}

	if (lastName == "") {
		printError("lastNameErr", "Please enter your last name");
	} else {
		var regex = /^[a-zA-Z\s]+$/;
		if (regex.test(lastName) === false) {
			printError("lastNameErr", "Please enter a valid last name");
		} else {
			printError("lastNameErr", "");
			lastNameErr = false;
		}
	}
	
	// address 1
	
	if (address1 == "") {
		printError("address1Err", "Please enter address");
	} else {
		var regex = /^[a-zA-Z0-9\s]+$/;
		if (regex.test(address1) === false) {
			printError("address1Err", "Please enter valid address");
		} else {
			printError("address1Err", "");
		}
	}
	
	// address2
	
	if (address2 == "") {
		printError("address2Err", "Please enter address");
	} else {
		var regex = /^[a-zA-Z0-9\s]+$/;
		if (regex.test(address2) === false) {
			printError("address2Err", "Please enter valid address");
		} else {
			printError("address2Err", "");
		}
	}
	
	
	// city
	
	if (city == "") {
		printError("cityErr", "Please enter city");
	} else {
		var regex = /^[a-zA-Z\s]+$/;
		if (regex.test(city) === false) {
			printError("cityErr", "Please enter valid city");
		} else {
			printError("cityErr", "");
		}
	}


	// Validate email address
	if (email == "") {
		printError("emailErr", "Please enter your email address");
	} else {
		// Regular expression for basic email validation
		var regex = /^\S+@\S+\.\S+$/;
		if (regex.test(email) === false) {
			printError("emailErr", "Please enter a valid email address");
		} else {
			printError("emailErr", "");
			emailErr = false;
		}
	}



	if ((firstNameErr || lastNameErr || address1Err || address2Err || cityErr || emailErr) == true) {
		return false;
	} else {
		var dataPreview = "You've entered the following details: \n" +
			"first Name: " + firstName + "\n" +
			"last Name: " + lastName + "\n" +
			"email Address : " + email + "\n" ;

		alert(dataPreview);

	}

};
