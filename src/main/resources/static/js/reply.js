function reveal(id) {
	if (document.getElementById(id).style.display == 'none') {
		document.getElementById(id).style.display = 'inline';
	} else {
		document.getElementById(id).style.display = 'none';
	}
}