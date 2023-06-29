// for validation

// Defining a function to display error message
function printError(elemId, hintMsg) {
    document.getElementById(elemId).innerHTML = hintMsg;
}

function submitForm(){
	document.getElementById("postcategory").submit(); 
}

// Defining a function to validate form 
function validateForm() {
    // Retrieving the values of form elements 
    var name = document.postcategory.name.value;
  
	// Defining error variables with a default value
    var nameErr = true;
    
    // Validate name
    if(name == "") {
        printError("nameErr", "Please enter category name");
    } else {
        var regex = /^[a-zA-Z\s]+$/;                
        if(regex.test(name) === false) {
            printError("nameErr", "Please enter a valid name");
        } else {
            printError("nameErr", "");
            nameErr = false;
        }
    }
 
    // Prevent the form from being submitted if there are any errors
    if(nameErr == true) {
       return false;
    } else {
        // Creating a string from input data for preview
        var dataPreview = "Category Name: " + name + "\n" ;
        
        // Display input data in a dialog box before submitting the form
        alert(dataPreview);
		submitForm();
    }
};

