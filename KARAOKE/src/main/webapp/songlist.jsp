<%@page import="java.util.ArrayList"%>
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
<%@ page import="DTO.*" %>
</head>
<body>
	<%@ include file="header.jsp" %>
	<section>
		<div class="title">노래검색</div>
			<div class="wrapper">
				
				<table>
					<tr>
						<th>곡 번호</th>
						<th>노래제목</th>
						<th>가수</th>
					</tr>
				<c:forEach var="songlist" items="${alllist}">
					<tr>
						<td>${songlist.songno}</td>
						<td>${songlist.songtitle}</td>
						<td>${songlist.singer}</td>
					</tr>
				</c:forEach>
				</table>
				
			</div>
		
	</section>
	<%@ include file="footer.jsp" %>
</body>
</html>