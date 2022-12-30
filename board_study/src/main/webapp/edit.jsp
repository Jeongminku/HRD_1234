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
</head>
<body>
	<%@ include file="header.jsp" %>
	<section>
		<div class="title">노래 추가하기</div>
			<div class="wrapper">
				<form name=fra method="post" action="update?songno=${song.songno}">
					<div class="title"> 
						<dl>
							<dt></dt>
							<dd>
								<input type="text" 
							</dd>
						</dl>
					</div>
					
				</form>
				
			</div>
		
	</section>
	<%@ include file="footer.jsp" %>
</body>
</html>