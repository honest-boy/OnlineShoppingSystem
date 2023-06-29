// for validation

// Defining a function to display error message
function printError(elemId, hintMsg) {
    document.getElementById(elemId).innerHTML = hintMsg;
}


// Defining a function to validate form 
function validateForm() {
    // Retrieving the values of form elements 
    var name = document.product.name.value;
    var price = document.product.price.value;
    var description = document.product.description.value;
    
  
	// Defining error variables with a default value
    var nameErr = priceErr = descriptionErr = true;
    
    // Validate name
    if(name == "") {
        printError("nameErr", "Please enter product name");
    } else {
        var regex = /^[a-zA-Z\s]{2,25}$/;                
        if(regex.test(name) === false) {
            printError("nameErr", "Please enter a valid name");
        } else {
            printError("nameErr", "");
            nameErr = false;
        }
    }
    
    if(price == 0) {
        printError("priceErr", "Please enter product price");
    } else {
        var regex = /^[1-9\d]{1,4}$/;                
        if(regex.test(price) === false) {
            printError("priceErr", "Please enter a valid price");
        } else {
            printError("priceErr", "");
            priceErr = false;
        }
    }
    
    if(description == "") {
        printError("descriptionErr", "Please enter description");
    } else {
        var regex = /^[a-zA-Z0-9\s]{2,150}$/;                
        if(regex.test(description) === false) {
            printError("descriptionErr", "Please enter a valid description");
        } else {
            printError("descriptionErr", "");
            descriptionErr = false;
        }
    }
 
    // Prevent the form from being submitted if there are any errors
    if((nameErr || priceErr || descriptionErr) == true) {
       return false;
    } else {
        // Creating a string from input data for preview
        var dataPreview = "Product Name: " + name + "\n" 
        					"price: " +price ;
        
        // Display input data in a dialog box before submitting the form
        alert(dataPreview);
    }
};

