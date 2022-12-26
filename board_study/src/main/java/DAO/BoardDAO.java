package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.Board;


public class BoardDAO {
	final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    
    //메소드 방식    , open()메소드는 데이터 베이스와의 연결 수행 메소드입니다.
    public Connection open( ) {
    	Connection conn = null;
    	try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, "test", "test1234");
		} catch (Exception e) {
			e.printStackTrace();
			
		}
    	
    	return conn; //데이터 베이스의 연결객체를 리턴합니다.
    	
    }
    
    //게시판 리스트 가져오기.					//throws 해버림
    public ArrayList<Board> getList() throws Exception {
    	Connection conn = open(); //메소드 가져오기
    	ArrayList <Board> boardList = new ArrayList<>();
    	
    	String sql = "select BOARD_NO, title, USER_ID, TO_CHAR(reg_date, 'yyyy.mm.dd') reg_date, views from BOARD";
    	PreparedStatement pstmt = conn.prepareStatement(sql); //쿼리문 등록 -> 컴파일 합니다.
    	ResultSet rs = pstmt.executeQuery(); //쿼리문 실행 -> DB데이터 베이스 결과 저장
    	
    	/*
    	//리소스 자동 닫기사용 전에 쓰던 방식
    	try {
    		
    	} catch(Exception e) {
    		
    	} finally {
    		conn.close();
    		pstmt.close();
    		rs.close();
    	}
    	*/
    	
    	//리소스 자동 닫기(try-with-resource 구문)
    	try (conn; pstmt; rs) {
    		while (rs.next()) {
    			Board b = new Board();
    			b.setBoard_no(rs.getInt(1));
    			b.setTitle(rs.getString(2));
    			b.setUser_id(rs.getString(3));
    			b.setReg_date(rs.getString(4));
    			b.setViews(rs.getInt(5));
    			
    			boardList.add(b);
    		}
    	}
    	
    	return boardList;    	
    	
    }


    //
    //게시판 내용 가져오기.
	public Board getView(int board_no) throws Exception {
    	Connection conn = open();
    	Board b = new Board();
		
		String sql = "select BOARD_NO, title, USER_ID, TO_CHAR(reg_date, 'yyyy.mm.dd') reg_date, views, content from BOARD where board_no = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql); //쿼리문 등록 -> 컴파일 합니다.
    	pstmt.setInt(1, board_no); //첫번째 물음표에 매개변수로 받아온 board_no 값을 넣습니다.
		ResultSet rs = pstmt.executeQuery(); //쿼리문 실행 -> DB데이터 베이스 결과 저장
    	
		try(conn; pstmt; rs;) {
    		while (rs.next()) {
    			b.setBoard_no(board_no);
    			b.setTitle(rs.getString(2));
    			b.setUser_id(rs.getString(3));
    			b.setReg_date(rs.getString(4));
    			b.setViews(rs.getInt(5));
    			b.setContent(rs.getString(6));
    		}
    		return b;
		}
	}
	
	
	//
	//조회수 증가시키기
	public void updateViews(int board_no) throws Exception {
		Connection conn = open(); //메소드 가져오기
    	
    	String sql = "update board set views = (views + 1) where board_no = ?";
    	PreparedStatement pstmt = conn.prepareStatement(sql); //쿼리문 등록 -> 컴파일 합니다.
    	
    	try (conn; pstmt){
    		pstmt.setInt(1, board_no);
    		pstmt.executeUpdate();
    	} 
	}
	
	
	public void insertBoard(Board b) throws Exception {
		Connection conn = open();
		String sql = "insert into board(board_no, user_id, title, content, reg_date, views) values(board_seq.nextval, ?, ?, ?, sysdate, 0)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try(conn; pstmt){
			pstmt.setString(1, b.getUser_id());
			pstmt.setString(2, b.getTitle());
			pstmt.setString(3, b.getContent());
			pstmt.executeUpdate();
			
		}
				
	}
	
	
}


