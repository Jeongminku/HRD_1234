package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.*;

public class SongDAO {
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
	
	public ArrayList<Result> getList(HttpServletRequest request) throws Exception {
		Connection conn = open();
		ArrayList<Result> songList = new ArrayList<Result>();
		String songtitle = request.getParameter("songtitle");
		String sql= "select songno, title, singer from song where title like '%"+songtitle+"%'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		try (conn; pstmt; rs;) {
			while (rs.next()) {
				Result s = new Result();
				s.setSongno(rs.getInt(1));
				s.setSongtitle(rs.getString(2));
				s.setSinger(rs.getString(3));
				
				songList.add(s);
			}
		}
		
		return songList;
	}
	
	public ArrayList<Result> getAllList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Connection conn = open();
		ArrayList<Result> allList = new ArrayList<Result>();
		String sql = "select songno, title, singer from song";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		try(conn; pstmt; rs;) {
			while (rs.next()) {
				Result s = new Result();
				s.setSongno(rs.getInt(1));
				s.setSongtitle(rs.getString(2));
				s.setSinger(rs.getString(3));
				
				allList.add(s);
			}
		}
		return allList;
		
		
	}
	
	public void insertSong(Result s) throws Exception {
		Connection conn = open();
		String sql = "insert into song (songno, title, singer, yaddress) values (?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try(conn; pstmt) {
			pstmt.setInt(1, s.getSongno());			
			pstmt.setString(2, s.getSongtitle());			
			pstmt.setString(3, s.getSinger());			
			pstmt.setString(4, s.getYaddress());
			pstmt.executeUpdate();		
			
		}
	
	}
	
	public Result select(int songno) throws Exception {
		Connection conn = open();
		Result s = new Result();
		
		String sql = "select songno, title, yaddress, singer from song where songno = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, songno);
		ResultSet rs = pstmt.executeQuery();
		
		try(conn; pstmt; rs;) {
			while (rs.next()) {
				s.setSongno(songno);
				s.setSongtitle(rs.getString(2));
				s.setYaddress(rs.getString(3));
				s.setSinger(rs.getString(4));
				
			}
			return s;
		}
	}
	
	
	
	/*
	public String search1(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Result> searchResult = new ArrayList<Result>();	
		try {
			conn = getConnection();
			String songtitle = request.getParameter("songtitle");
			String sql = "select songno, title, singer from song where title like '%"+songtitle+"%'";
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Result songList = new Result();
				songList.setSongno(rs.getInt(1));
				songList.setSongtitle(rs.getString(2));
				songList.setSinger(rs.getString(3));
				
				searchResult.add(songList);
			}
			
			request.setAttribute("list", searchResult);
			
			
			conn.close();
			ps.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "searchResult.jsp";
	}
	
	public String showlist(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Result> showlist = new ArrayList<Result>();	
		try {
			conn = getConnection();
			String sql = "select songno, title, singer from song order by songno";
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Result songList = new Result();
				songList.setSongno(rs.getInt(1));
				songList.setSongtitle(rs.getString(2));
				songList.setSinger(rs.getString(3));;
				
				showlist.add(songList);
			}
			
			request.setAttribute("showlist", showlist);
			
			
			conn.close();
			ps.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "songlist.jsp";
	}
	
	public int insert(HttpServletRequest request, HttpServletResponse response) {
		int result = 0;
		try {
			conn = getConnection();
			int songno = Integer.parseInt(request.getParameter("songno"));
			String songtitle = request.getParameter("songtitle");
			String singer = request.getParameter("singer");
			String yaddress = request.getParameter("yaddress");
			
			String sql = "insert into song (songno, title, singer, yaddress) values (?,?,?,?)";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, songno);
			ps.setString(2, songtitle);
			ps.setString(3, singer);
			ps.setString(4, yaddress);
			
			result = ps.executeUpdate();
			conn.close();
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	public String getView(int board_no) throws Exception {
		conn = getConnection();
		addSong s = new addSong();
		String sql = "select "
	}
	*/
}


