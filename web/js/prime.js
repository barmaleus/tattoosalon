
        /** Registration js*/

function checkPass() {
    //Store the password field objects into variables ...
    var pass1 = document.getElementById('pass1');
    var pass2 = document.getElementById('pass2');
    //Store the Confimation Message Object ...
    var message = document.getElementById('confirmMessage');
    //Set the colors we will be using ...
    var goodColor = "#66cc66";
    var badColor = "#ff6666";
    //Compare the values in the password field
    //and the confirmation field
    if(pass1.value === pass2.value){
        //The passwords match.
        //Set the color to the good color and inform
        //the user that they have entered the correct password
        pass2.style.backgroundColor = goodColor;
        message.style.color = goodColor;
        message.innerHTML = "Passwords Match"
    }else{
        //The passwords do not match.
        //Set the color to the bad color and
        //notify the user.
        pass2.style.backgroundColor = badColor;
        message.style.color = badColor;
        message.innerHTML = "Passwords Do Not Match!"
    }
}

// validates text only at login.jsp, registration.jsp
function Validate(txt) {
    txt.value = txt.value.replace(/[^a-zA-Z0-9-'.]+/g, '');
}

        // validates text only at registration.jsp (name and surname)
        function validate_name(txt) {
            txt.value = txt.value.replace(/[^a-zA-Z0-9а-яА-Я-'.]+/g, '');
        }

// validate email
function email_validate(email) {
    var regMail = /^([_a-zA-Z0-9-]+)(\.[_a-zA-Z0-9-]+)*@([a-zA-Z0-9-]+\.)+([a-zA-Z]{2,3})$/;

    if(regMail.test(email) === false)
    {
        document.getElementById("status").innerHTML    = "<span class='warning'>Email address is not valid yet.</span>";
    }
    else
    {
        document.getElementById("status").innerHTML	= "<span class='valid'>Thanks, you have entered a valid Email address!</span>";
    }
}

// validates birth date (minimum years old, date 'max' parameter)
function maxdate_validate() {
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0!
    var yyyy = (today.getFullYear() - 16); // Client can get tattoo since 16 years.
    if(dd<10){
        dd='0'+dd
    }
    if(mm<10){
        mm='0'+mm
    }
    today = yyyy+'-'+mm+'-'+dd;
    document.getElementById("birth").setAttribute("max", today);
}

// validates text only when adding publications (title and text itself) mailing to admin
function validate_add_publication(txt) {
    txt.value = txt.value.replace(/[^,.!?+:;*()%=_@а-яА-Яa-zA-Z0-9-'\s\n\r.]+/g, '');     // &,<,>,",',\ at least are prohibited
}
