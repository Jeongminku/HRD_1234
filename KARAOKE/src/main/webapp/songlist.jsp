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
		<div class="title">노래검색</div>
			<div class="wrapper">
				
				<table>
					<tr>
						<th>곡 번호</th>
						<th>노래제목</th>
						<th>가수</th>
					</tr>
				<%for(Result sl : showlist) {%>
					<tr>
						<td><%=sl.getSongno() %></td>
						<td><%=sl.getSongtitle() %></td>
						<td><%=sl.getSinger() %></td>
					</tr>
				<% } %>
				</table>
				
			</div>
		
	</section>
	<%@ include file="footer.jsp" %>
</body>
</html>