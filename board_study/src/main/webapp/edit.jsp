<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Insert title here</title>
<link rel="stylesheet" href="./css/style.css" />
</head>
<body>
    <div class="board_wrap">
      <div class="board_title">
        <strong>자유게시판</strong>
        <p>자유게시판 입니다.</p>
      </div>
      <div class="board_write_wrap">
        <div class="board_write">
          <div class="title">
            <dl>
              <dt>제목</dt>
              <dd><input type="text" placeholder="제목 입력" value="게시판 제목 입니다." /></dd>
            </dl>
          </div>
          <div class="info">
            <dl>
              <dt>글쓴이</dt>
              <dd><input type="text" placeholder="글쓴이 입력" value="김김김" /></dd>
            </dl>
            <dl>
              <dt>비밀번호</dt>
              <dd><input type="password" placeholder="비밀번호 입력" value="1234" /></dd>
            </dl>
          </div>
          <div class="cont">
            <textarea placeholder="내용 입력">
글 내용이 들어갑니다.
글 내용이 들어갑니다.
글 내용이 들어갑니다.
글 내용이 들어갑니다.
글 내용이 들어갑니다.
글 내용이 들어갑니다.
글 내용이 들어갑니다.
글 내용이 들어갑니다.
            </textarea>
          </div>
        </div>
        <div class="bt_wrap">
          <a href="view.html" class="on">수정</a>
          <a href="view.html">취소</a>
        </div>
      </div>
    </div>
  </body>
</html>