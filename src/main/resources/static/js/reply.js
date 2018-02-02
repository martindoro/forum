
//Function To Display Popup
function div_show() {
document.getElementById('abc').style.display = "block";
}
//Function to Hide Popup
function div_hide(){
document.getElementById('abc').style.display = "none";
}

var swear_words_arr=new Array("bad","evil","freak", "kurva");
var regex = new RegExp('\\b(' + swear_words_arr.join('|') + ')\\b', 'i' );

function validate_user_text() {
    var text = $('#textarea1').text();

    if(regex.test(text)) {
        alert("Please refrain from using offensive words"); /* + alert_text */
        $('#textarea1').focus();
        return false;
    }
}