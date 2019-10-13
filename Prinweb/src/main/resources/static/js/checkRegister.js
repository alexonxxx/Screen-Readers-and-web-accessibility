
var form = $("#registrationForm");
var passwordInput= form.find("input[name=password]");
var error= $("#passwordCheck");
$("#passwordMessage").html("La contraseña debe contener mínimo un número y 8 carácteres");

$(passwordInput).focus(function() {
    error.removeClass("invisible");
});

$(passwordInput).blur(function() {
    error.addClass("invisible");
});

$(passwordInput).keyup(function() {

        var numbers = /[0-9]/g;
        if(passwordInput.val().match(numbers) && passwordInput.val().length >= 8) {
            error.addClass("alert-success");
            error.removeClass("alert-danger");

        } else {
            error.removeClass("alert-success");
            error.addClass("alert-danger");


        }

    });
