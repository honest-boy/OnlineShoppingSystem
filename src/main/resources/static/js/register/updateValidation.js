function printError(elemId, hintMsg) {
	document.getElementById(elemId).innerHTML = hintMsg;
}

function validateForm() {
	var firstName = document.update.firstName.value;
	var lastName = document.update.lastName.value;
	var firstNameErr = lastNameErr = true;

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


	if ((firstNameErr || lastNameErr) == true) {
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



