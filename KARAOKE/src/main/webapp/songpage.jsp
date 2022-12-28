<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
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
		<iframe width="950" height="450" src="https://www.youtube.com/embed/${}?autoplay=1&mute=1" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
		</div>
		<div>
		댓글창이 들어갈 곳
		</div>
	</div>
	<form name=songfm action=select>
	<div>
		<input type="text" placeholder="노래 코드를 입력해주세요"></input>
		<button class="btn" type="submit" onclick="fn_submit(); return false;">확인</button>
	</div>
	</form>
	<%@ include file="footer.jsp" %>
</body>
</html>