 function ValidateSize(file) {
	        var FileSize = file.files[0].size / 1024 / 1024; // in MB
	        if (FileSize > 1) {
	            alert('File size exceeds 1 MB');
	            $(file).val(''); //for clearing with Jquery
	        } else {

	        }
	    }

document.addEventListener("DOMContentLoaded", function() {

    // JavaScript form validation

    var checkPassword = function(str)
    {
      var re = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$/;
      return re.test(str);
    };

    var checkForm = function(e)
    {
      if(this.login.value == "") {
        alert("Error: Username cannot be blank!");
        this.login.focus();
        e.preventDefault(); // equivalent to return false
        return;
      }
      re = /^\w+$/;
      if(!re.test(this.login.value)) {
        alert("Error: Username must contain only letters, numbers and underscores!");
        this.login.focus();
        e.preventDefault();
        return;
      }
      if(this.password.value != "" && this.password.value == this.password2.value) {
        if(!checkPassword(this.password.value)) {
          alert("The password you have entered is not valid!");
          this.password.focus();
          e.preventDefault();
          return;
        }
      } else {
        alert("Error: Please check that you've entered and confirmed your password!");
        this.password.focus();
        e.preventDefault();
        return;
      }
//      alert("Both username and password are VALID!");
      alert("Regisration was successfully completed!");
    };

    var myForm = document.getElementById("myForm");
    myForm.addEventListener("submit", checkForm, true);

    // HTML5 form validation

    var supports_input_validity = function()
    {
      var i = document.createElement("input");
      return "setCustomValidity" in i;
    }

    if(supports_input_validity()) {
      var usernameInput = document.getElementById("field_username");
      usernameInput.setCustomValidity(usernameInput.title);

      var pwd1Input = document.getElementById("field_pwd1");
      pwd1Input.setCustomValidity(pwd1Input.title);

      var pwd2Input = document.getElementById("field_pwd2");

      // input key handlers

      usernameInput.addEventListener("keyup", function(e) {
        usernameInput.setCustomValidity(this.validity.patternMismatch ? usernameInput.title : "");
      }, false);

      pwd1Input.addEventListener("keyup", function(e) {
        this.setCustomValidity(this.validity.patternMismatch ? pwd1Input.title : "");
        if(this.checkValidity()) {
          pwd2Input.pattern = RegExp.escape(this.value);
          pwd2Input.setCustomValidity(pwd2Input.title);
        } else {
          pwd2Input.pattern = this.pattern;
          pwd2Input.setCustomValidity("");
        }
      }, false);

      pwd2Input.addEventListener("keyup", function(e) {
        this.setCustomValidity(this.validity.patternMismatch ? pwd2Input.title : "");
      }, false);

    }

  }, false);