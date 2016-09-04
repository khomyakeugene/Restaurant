/**
 * Created by Yevhen on 04.09.2016.
 */
/**
 * charCode [48,57] 	Numbers 0 to 9
 * keyCode 46  			"delete"
 * keyCode 9  			"tab"
 * keyCode 13  			"enter"
 * keyCode 116 			"F5"
 * keyCode 8  			"backscape"
 * keyCode 37,38,39,40	Arrows
 * keyCode 10			(LF)
 */
function validate_int(myEvent) {
    dato = !!((myEvent.charCode >= 48 && myEvent.charCode <= 57) || myEvent.keyCode == 9 ||
    myEvent.keyCode == 10 || myEvent.keyCode == 13 || myEvent.keyCode == 8 || myEvent.keyCode == 116 ||
    myEvent.keyCode == 46 || (myEvent.keyCode <= 40 && myEvent.keyCode >= 37));

    return dato;
}

function phone_number_mask() {
    var myMask = "(___) ___-____";
    var myInputField = document.getElementById("employeePhoneNumber");
    var myText = "";
    var myNumbers = [];
    var myOutPut = "";
    var theLastPos = 1;
    myText = myInputField.value;
    //get numbers
    for (var i = 0; i < myText.length; i++) {
        if (!isNaN(myText.charAt(i)) && myText.charAt(i) != " ") {
            myNumbers.push(myText.charAt(i));
        }
    }
    //write over mask
    for (var j = 0; j < myMask.length; j++) {
        if (myMask.charAt(j) == "_") { //replace "_" by a number
            if (myNumbers.length == 0)
                myOutPut = myOutPut + myMask.charAt(j);
            else {
                myOutPut = myOutPut + myNumbers.shift();
                theLastPos = j + 1; //set caret position
            }
        } else {
            myOutPut = myOutPut + myMask.charAt(j);
        }
    }
    document.getElementById("employeePhoneNumber").value = myOutPut;
    document.getElementById("employeePhoneNumber").setSelectionRange(theLastPos, theLastPos);
}

document.getElementById("employeePhoneNumber").onkeypress = validate_int;
document.getElementById("employeePhoneNumber").onkeyup = phone_number_mask;