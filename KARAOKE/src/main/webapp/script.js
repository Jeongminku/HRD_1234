function fn_submit(){
	var fn = document.songfm;
	
	if(fn.songno.value == ""){
		alert('검색어가 입력되지않았습니다.');
		fn.songtitle.focus();
		return false;
	}
	songfm.submit();
}

function fn_repSubmit(){
	var fn = document.reply;
	
	if(fn.nickname.value ==""){
		alert('닉네임을 입력해주세요');
		fn.nickname.focus();
		return false;
	}
	
	if(fn.reply.value == ""){
		alert('댓글 내용이 없습니다');
		fn.reply.focus();
		return false;
		
	}
	reply.submit();
}
