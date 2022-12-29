<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/style.css" />
<script type="text/javascript" src="script.js"></script>
</head>
<body>
	<%@ include file="header.jsp" %>
	<div style="display: flex; flex-direction: row;">
		<div>
		<iframe id="iframeid" width="950" height="450" src="https://www.youtube.com/embed/${song.yaddress}?autoplay=1&mute=1" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
		</div>
		<div>
		<form name=reply action=reply>
		<input type = "hidden" name="songno" value="${song.songno}">
			<div class="main_comment">
			<table>
			<tr>
			<td> <input type="text" name="userid" placeholder="닉네임"> </td>
			<td> <input type="text" name="rep_content" placeholder= "댓글 추가..."> </td>
			<td> <button class="rep_btn" type="submit" onclick="fn_repSubmit(); return false;">댓글</button> <td>
			</tr>
			<c:forEach var="replylist" items="${replylist}">
				<tr>
					<td>${replylist.userid}</td>
					<td>${replylist.rep_content}</td>
					<td><button type="button" onclick="chkDelete(${replylist.commentno},${replylist.songno})">삭제하기</button></td>					
				</tr>
			</c:forEach>
			</table>  
			</div>
			</form>
		</div>
	</div>
	<form name=songfm action=select>
	<div>
		<input type="text" name="songno" placeholder="노래 코드를 입력해주세요"></input>
		<button class="btn" type="button" onclick="fn_submit(); return false;">확인</button>
	</div>
	</form>
	<%@ include file="footer.jsp" %>
<script>
function chkDelete(a, b) {
	
	if(confirm("댓글을 삭제하시겠습니까?123")){
		const url = location.origin;
		console.log("qwe123");
		location.href = 'delete?commentno='+a+'&songno='+b;
		
	} else{
		return false;
	}
}
</script>
</body>
</html>