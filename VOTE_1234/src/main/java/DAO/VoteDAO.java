package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Member;

public class VoteDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public static Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe","system","sys1234");
		return con;
	}
	
	public String search(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Member> list = new ArrayList<Member>();
		
		try {
			conn = getConnection();

			String sql = "select M.m_no,"
					+ " M.m_name,"
					+ " decode(P.p_code, 'P1', 'A정당', 'P2', 'B정당', 'P3', 'C정당', 'P4', 'D정당', 'P5', 'E정당') p_code,"
					+ " decode(M.p_school, '1', '고졸', '2', '학사', '3', '석사', '4', '박사') p_school,"
					+ " (substr(M.m_jumin,1,6) || '-' || substr(M.m_jumin,6,13)) m_jumin,"
					+ " M.m_city,"
					+ " (P.p_tel1 || '-' || P.p_tel2 || '-' || P.p_tel3 ) p_tel"
					+ " from tbl_member_202005 M, tbl_party_202005 P"
					+ " where M.p_code = P.p_code";
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Member member = new Member();
				member.setM_no(rs.getString(1));
				member.setM_name(rs.getString(2));
				member.setP_code(rs.getString(3));
				member.setP_school(rs.getString(4));
				member.setM_jumin(rs.getString(5));
				member.setM_city(rs.getString(6));
				member.setP_tel(rs.getString(7));
				
				list.add(member);
			}
			request.setAttribute("list", list);
			
			conn.close();
			ps.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		return "search.jsp";
	}
}


