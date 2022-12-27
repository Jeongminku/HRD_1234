function chkForm() {
	var f = document.frm; //폼 태그
	
	if(f.title.value == '') {
		alert("제목을 입력해주십시오.");
		return false;
	}
	if(f.user_id.value == '') {
		alert("아이디를 입력해주십시오.");
		return false;
	}
	
	f.submit(); //폼태그 전송
}

function chkDelete(board_no) {
	const result = confirm("삭제하시겠습니까?"); //삭제하시겠습니까 확인(true리턴) 취소(false리턴) 나오는 버튼이 confirm
	
	if(result) {
		const url = location.origin; //url을 불러오는것. 
		//loaction.origin: 프로토콜과 도메인 까지 가져옴 "http://localhost:8082" 이런느낌. 
		//이동시킬 최종 주소는 http://localhost:8082/board/delete?board_no= board_no  로
		location.href = url + "/board_study/delete?board_no="+ board_no; //페이지 이동.
	} else {
		return false;
	}
	
}
