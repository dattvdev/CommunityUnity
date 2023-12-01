/* 
 * To change this license header, choose License Headers in Project Properties.
 * mtuong.xyz
 * and open the template in the editor.
 */


function checkPasswordMatch() {
    var newPassword = document.getElementById("newpass").value;
    var confirmNewPassword = document.getElementById("repass").value;
    var errorElement = document.getElementById("error_message");

    // Check if passwords match
    if (newPassword !== confirmNewPassword) {
        errorElement.innerHTML = "Passwords does not match!";
    } else {
        errorElement.innerHTML = "";
    }
}

function checkStrong() {
    var newPassword = document.getElementById("newpass").value;
    var confirmNewPassword = document.getElementById("repass").value;
    var errorElement = document.getElementById("error_message");

    if (isStrongPassword(newPassword)) {
        errorElement.innerHTML = ""; // Clear the error message if passwords match and password is strong
    } else {
        errorElement.innerHTML = "Password is not strong.EX: Abc123@";
    }


}

// Function to check if a password is strong
function isStrongPassword(password) {
    // Check if the password has at least 6 characters
    if (password.length < 6) {
        return false;
    }

    // Check for strong password criteria
    // Criteria: At least one uppercase letter, one lowercase letter, one digit, and one special character
    var uppercaseRegex = /[A-Z]/;
    var lowercaseRegex = /[a-z]/;
    var digitRegex = /\d/;
    var specialCharRegex = /[!@#$%^&*()_+{}[\]:;<>,.?~\\-]/;

    if (!uppercaseRegex.test(password) || !lowercaseRegex.test(password) || !digitRegex.test(password) || !specialCharRegex.test(password)) {
        return false;
    }

    // If all conditions are met, the password is strong
    return true;
}

function checkEmail() {
    var email = document.getElementById("email").value;
    var errorElement = document.getElementById("error_message");
    email = email.trim();
    if (isEmail(email)) {
        errorElement.innerHTML = "";
    } else {
        errorElement.innerHTML = "Invalid email address";
    }

}
function isEmail(email) {

    var emailRegex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;

    if (!emailRegex.test(email)) {
        return false;
    }
    // If all conditions are met, the password is strong
    return true;
}

function checkUserNAme() {
    var username = document.getElementById("username").value;
    var errorElement = document.getElementById("error_message");
    var specialCharRegex = /[!@#$%^&*()_+{}[\]:;<>,.?~\\-]/;
    username = username.trim();




    if (username === "") {
        errorElement.innerHTML = "Username cannot be empty.";

    } else if (/\s+/g.test(username)) {
        errorElement.innerHTML = "Username cannot consist of only spaces";

    } else if (specialCharRegex.test(username)) {
        errorElement.innerHTML = "Username cannot consist of special chars";

    } else {

        errorElement.innerHTML = "";

    }

}

function checkUserNAmeLogin() {
    var username = document.getElementById("username1").value;
    var errorElement = document.getElementById("error_message1");
    var specialCharRegex = /[!@#$%^&*()_+{}[\]:;<>,.?~\\-]/;
    username = username.trim();




    if (username === "") {
        errorElement.innerHTML = "Username cannot be empty.";

    } else if (/\s+/g.test(username)) {
        errorElement.innerHTML = "Username cannot consist of only spaces";

    } else if (specialCharRegex.test(username)) {
        errorElement.innerHTML = "Username cannot consist of special chars";

    } else {

        errorElement.innerHTML = "";

    }

}


function lowerCase() {
    let x = document.getElementById("username");
    x.value = x.value.toLowerCase().trim();
}
function lowerCase1() {
    let x = document.getElementById("username1");
    x.value = x.value.toLowerCase().trim();
}
function trimInput() {
    let x = document.getElementById("email");
    x.value = x.value().trim();
}