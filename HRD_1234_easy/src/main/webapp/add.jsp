<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css">
<script type="text/javascript" src="script.js"></script>
</head>
<body>
<%@ include file="topmenu.jsp" %>
		<section>
		<div class="title">홈쇼핑 회원 등록</div>
		<form name="frm" action="insert"> <!--전송하기위해서는 form 태그를 사용해야.-->
			<input type="hidden" name="GUBUN" value="add">
			<div class="wrapper">
				<table>
					<tr>
						<th>회원번호(자동발생)</th>
						<td>
						<%
						try{
						Class.forName("oracle.jdbc.OracleDriver");
						Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe", "system", "sys1234");
						Statement stmt = con.createStatement(); 	//DB 커넥셕 끝
						
						String sql = "SELECT max(custno)+1 custno FROM member_tbl_02";
						ResultSet rs = stmt.executeQuery(sql);

						if(rs.next()) {%>
						<input type="text" name="custno" value = "<%=rs.getInt(1) %>" readonly>
						<% }
						con.close();
						stmt.close();
						rs.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
						%>
						</td>
						
						
					</tr>
					<tr>
						<th>회원성명</th>
						<td><input type="text" name="custname"></td>
					</tr>
					<tr>
						<th>회원전화</th>
						<td><input type="text" name="phone" class="custphone"></td>
					</tr>
					<tr>
						<th>회원주소</th>
						<td><input type="text" name="address" class="custaddress"></td>
					</tr>
					<tr>
						<th>가입일자</th>
						<td><input type="text" name="joindate"></td>
					</tr>
					<tr>
						<th>고객등급[A:VIP,B:일반,C:직원]</th>
						<td><input type="text" name="grade"></td>
					</tr>
					<tr>
						<th>도시코드</th>
						<td><input type="text" name="city"></td>
					</tr>
					<tr>
						<td colspan="2">
						<button class="btn" type="submit" onclick="fn_submit(); return false;">등 록</button>
						<!-- onclick에서 return false가 있을 경우 href로 이동하는 액션을 취하지 않게됩니다. -->
						<button class="btn" type="button" onclick="location='list'">조 회</button>
						</td>
					</tr>
					
				</table>
			</div>
		</form>
		
	</section>
<%@ include file="footer.jsp" %>
</body>
</html>