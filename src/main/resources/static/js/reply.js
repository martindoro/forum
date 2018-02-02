function reveal(id) {
	if (document.getElementById(id).style.display == 'none') {
		document.getElementById(id).style.display = 'inline';
	} else {
		document.getElementById(id).style.display = 'none';
	}
}

function profanityCheckComment() {
	var profanities = new Array("lukas", "mato", "jakub", "matus");
	var text =  document.getElementById('textarea1').value; 
	    for (var i = 0; i < profanities.length; i++) {    	
	        if(text.toLowerCase().includes(profanities[i].toLowerCase())){
	        	text = text.toLowerCase().replace(profanities[i].toLowerCase(), "****");
	        }
	    }
	    $("#textarea1").val(text);
	}
function profanityCheckReply() {
	var profanities = new Array("lukas", "mato", "jakub", "matus");
	var text =  document.getElementById('comment1').value; 
	    for (var i = 0; i < profanities.length; i++) {    	
	        if(text.toLowerCase().includes(profanities[i].toLowerCase())){
	        	text = text.toLowerCase().replace(profanities[i].toLowerCase(), "****");
	        }
	    }
	    $("#comment1").val(text);
	}