<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css">
<script type="text/javascript" src="script.js"></script>
<%@ page import="DTO.*" %>
<%
request.setCharacterEncoding("UTF-8");
Result sr = new Result();
sr = (Result)request.getAttribute("result");
String noData = (String)request.getAttribute("noData");
%>
</head>
<body>
	<%@ include file="topmenu.jsp" %>
	<section>
		<div class="title">예약번호: <%=sr.getResvno() %>의 접종예약조회</div>
			<div class="wrapper">
				<%if (noData != "N") {%>
				<table>
					<tr>
						<th>이름</th>
						<th>주민번호</th>
						<th>성별</th>
						<th>전화번호</th>
						<th>예약일자</th>
						<th>예약시간</th>
						<th>병원명</th>
						<th>대표전화</th>
						<th>병원주소</th>
						<th>백신종류</th>						
					</tr>
					<tr>
						<td><%=sr.getName() %></td>
						<td><%=sr.getJumin() %></td>
						<td><%=sr.getGender() %></td>
						<td><%=sr.getTel() %></td>
						<td><%=sr.getResvdate() %></td>
						<td><%=sr.getResvtime() %></td>
						<td><%=sr.getHospname() %></td>
						<td><%=sr.getHosptel() %></td>
						<td><%=sr.getHospaddr() %></td>
						<td><%=sr.getVac() %></td>
					</tr>
				
				</table>
				<%} else {%>
				<h2>안대지롱~</h2>
				<% } %>
			</div>
	</section>
	<%@ include file="footer.jsp" %>
</body>
</html>