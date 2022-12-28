function fn_submit(){
	var fn = document.frm;
	
	if(fn.songtitle.value == ""){
		alert('검색어가 입력되지않았습니다.');
		fn.songtitle.focus();
		return false;
	}
	fn.submit();
}

