<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/style.css" />
<script type="text/javascript" src="script.js"></script>
<%@ page import="DTO.*" %>
<%
request.setCharacterEncoding("UTF-8");
ArrayList<Result> showlist = new ArrayList<Result>();
showlist = (ArrayList<Result>)request.getAttribute("showlist");
%> 
</head>
<body>
	<%@ include file="header.jsp" %>
	<section>
		<div class="title">노래 추가하기</div>
			<div class="wrapper">
				<form name=fra action="insert">
					<table>
						<tr>
							<th>곡번호</th>
							<td><input type="text" name=songno placeholder="예시) 12345"></input></td>
						</tr>
						<tr>
							<th>노래제목</th>
							<td><input type="text" name=songtitle></input></td>
						</tr>
						<tr>
							<th>가 수</th>
							<td><input type="text" name=singer></input></td>
						</tr>
						<tr>
							<th>유튜브 주소</th>
							<td><input type="text" name=yaddress placeholder="유튜브 링크"></input></td>
						</tr>
					</table>
					<button class="btn" type="submit" onclick="fn_submit(); return false;">등록하기</button>
					<button class="btn" type="submit" onclick="fn_submit(); return false;">RESET</button>
				</form>
				
			</div>
		
	</section>
	<%@ include file="footer.jsp" %>
</body>
</html>