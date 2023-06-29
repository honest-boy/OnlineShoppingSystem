// form validation for registration

function printError(elemId, hintMsg) {
	document.getElementById(elemId).innerHTML = hintMsg;
}


function validateForm() {
	var firstName = document.register.firstName.value;
	var lastName = document.register.lastName.value;
	var email = document.register.email.value;
	var password = document.register.password.value;
	var firstNameErr = lastNameErr = emailErr = passwordErr = true;

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


	if (password == "") {
		printError("passwordErr", "Please enter your password");
	} else {
		var regex = /^[a-zA-Z1-9\s]+$/;
		if (regex.test(password) === false) {
			printError("passwordErr", "please enter valid password");
		} else {
			printError("passwordErr", "");
			passwordErr = false;
		}
	}



	if ((firstNameErr || emailErr || lastNameErr || passwordErr) == true) {
		return false;
	} else {
		var dataPreview = "You've entered the following details: \n" +
			"first Name: " + firstName + "\n" +
			"last Name: " + lastName + "\n" +
			"email Address : " + email + "\n" +
			"Password: " + password + "\n";

		alert(dataPreview);

	}

};




