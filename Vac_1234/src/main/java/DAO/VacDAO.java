package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.*;
import java.util.*;
public class VacDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public static Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe", "system", "sys1234");
		return con;
	}
	
	public int book(HttpServletRequest request, HttpServletResponse response) {
		int result = 0;
		try {
			conn = getConnection();
			String resvno = request.getParameter("resvno");
			String jumin = request.getParameter("jumin");
			String vcode = request.getParameter("vcode");
			String hospcode = request.getParameter("hospcode");
			String resvdate = request.getParameter("resvdate");
			String resvtime = request.getParameter("resvtime");
			String sql = "insert into tbl_vaccresv_202108 values (?, ?, ?, ?, ?, ?)";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, resvno);
			ps.setString(2, jumin);
			ps.setString(3, vcode);
			ps.setString(4, hospcode);
			ps.setString(5, resvdate);
			ps.setString(6, resvtime);
			
			result = ps.executeUpdate();
			conn.close();
			ps.close();
					
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}
	
	public String search(HttpServletRequest request, HttpServletResponse response) {
		Result searchResult = new Result();
		try {
			conn = getConnection();
			String resvno = request.getParameter("resvno");
			
			String sql = "select j.pname, " 
					+ " j.jumin, "
					+ " decode(substr(j.jumin,8,1), '1', '남성', '2', '여성'),"
					+ " j.tel," 
					+ " v.resvdate," 
					+ " v.resvtime," 
					+ " h.hospname," 
					+ " h.hosptel,"
					+ " h.hospaddr,"
					+ " v.vcode"
					+ " from tbl_jumin_201808 j "
					+ " join tbl_vaccresv_202108 v "
					+ " on v.jumin = j.jumin "
					+ " join tbl_hosp_202108 h "
					+ " on v.hospcode = h.hospcode"
					+ " where resvno =" + resvno;
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			
			if(rs.next()) {
				searchResult.setName(rs.getString(1));
				searchResult.setJumin(rs.getString(2));
				searchResult.setGender(rs.getString(3));
				searchResult.setTel(rs.getString(4));
				searchResult.setResvdate(rs.getString(5));
				searchResult.setResvtime(rs.getString(6));
				searchResult.setHospname(rs.getString(7));
				searchResult.setHosptel(rs.getString(8));
				searchResult.setHospaddr(rs.getString(9));
				searchResult.setVac(rs.getString(10));
			}
			else {
				request.setAttribute("noData", "N");
			
//			else {
//				conn.close();
//				ps.close();
//				rs.close();
//				return "에러.jsp";
//				
			}
			
			request.setAttribute("result", searchResult);
			searchResult.setResvno(resvno);
			
			conn.close();
			ps.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "searchResult.jsp";
//		if(searchResult.getName()==null) {
//			return "index.jsp";
//		} else {
//			return "searchResult.jsp";	
//		}
		
		
	}
}
