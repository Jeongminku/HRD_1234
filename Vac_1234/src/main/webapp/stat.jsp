<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="DTO.*" %>
<%
request.setCharacterEncoding("UTF-8");
ArrayList<Status> list = new ArrayList<Status>();
list = (ArrayList<Status>)request.getAttribute("list");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel = "stylesheet" href="style.css">
<script type="text/javascript" src="script.js"></script>
</head>
<body>
	<%@ include file="topmenu.jsp" %>
	<section>
		<div class="title">병원별 접종건수 통계</div>
			<form name=frm action="booking">
				<div class="table">
					<table border=1>
						<tr>
							<th>병원코드</th>
							<th>병원명</th>
							<th>접종건수</th>
						</tr>
						<% for(Status s : list) {%>
						<tr>
							<td><%=s.getHcode() %></td>
							<td><%=s.getHname() %></td>
							<td><%=s.getHcount() %></td>
						</tr>
						<% } %>
						<tr> 
						<td></td>
						<th>총 누계</th>
						<td><%=request.getAttribute("fullcount") %></td>
						</tr>
						
						</tr>
					</table>
				</div>
			</form>	
	</section>
	<%@ include file="footer.jsp" %>	
</body>
</html>