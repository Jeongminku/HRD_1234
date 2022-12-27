<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<section>
		<div class="title">노래검색</div>
			<form name = fvm action = "searchResult">
				<div class = "table">
					<table border=1>
						<tr>
							<th>예약번호를 입력 하시오.</th>
							<td><input type="text" name="resvno"></td>
						</tr>
						<tr>
							<td colspan="2">
							<button class="btn" type="submit" onclick="">예약조회</button>
							<button class="btn" type="button" onclick="">홈으로</button>
							</td>
						</tr>
					</table>
				</div>
		
		</form>		
	</section>
	<%@ include file="footer.jsp" %>
</body>
</html>