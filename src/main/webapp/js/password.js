function togglePasswordVisibility() {
    let passwordInput = document.getElementById("password");
    let showPasswordCheckbox = document.getElementById("showPassword");
    if (showPasswordCheckbox.checked) {
        passwordInput.type = "text";
    } else {
        passwordInput.type = "password";
    }
}