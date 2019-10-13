
var error= $("#messageErrorCont");
var message= $("#messageError").html();
if (message!=""){
    error.removeClass("invisible");
}
else{
    error.remove();
}

