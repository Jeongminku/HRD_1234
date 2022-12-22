<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="DTO.Ranking" %>

<%
request.setCharacterEncoding("UTF-8");
ArrayList <Ranking> list = new ArrayList<Ranking>();
list = (ArrayList<Ranking>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<%@ include file="topmenu.jsp" %>
	<section>
		<div class="title">후보자 등수</div>
		<div class="wrapper">
			<table border="1" style="width: 900px">
				<tr>
					<th>후보번호</th>
					<th>성명</th>
					<th>총투표건수</th>
				</tr>
				<% for(Ranking r : list) { %>
				<tr>
					<td><%=r.getM_no() %></td>
					<td><%=r.getM_name() %></td>
					<td><%=r.getV_total() %></td>
				</tr>
 				<%} %>
			</table>
		</div>
	</section>
	<%@ include file="footer.jsp" %>
</body>
</html>