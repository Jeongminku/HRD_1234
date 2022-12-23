package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Member;
import DTO.Ranking;
import DTO.Vote;

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
	
	public int insertVote(HttpServletRequest request, HttpServletResponse response) {
		
		int result = 0;
		try {
			conn = getConnection();
			//request.getParameter(String name)
		
			String v_jumin = request.getParameter("v_jumin");
			String v_name = request.getParameter("v_name");
			String m_no = request.getParameter("m_no");
			String v_time = request.getParameter("v_time");
			String v_area = request.getParameter("v_area");
			String v_confirm = request.getParameter("v_confirm");
			
			String sql = "INSERT INTO TBL_VOTE_202005 VALUES(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, v_jumin);
			ps.setString(2, v_name);
			ps.setString(3, m_no);
			ps.setString(4, v_time);
			ps.setString(5, v_area);
			ps.setString(6, v_confirm);
			
			result = ps.executeUpdate();
			conn.close();
			ps.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	

	public String listcheck(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Vote> list = new ArrayList<Vote>();
		
		try {
			conn = getConnection();
			
			String sql = "select v_name,"
					+ " '19'||substr(v_jumin,1,2)||'년'||substr(v_jumin,3,2)||'월'||substr(v_jumin,5,2)||'일생' v_jumin,"
					+ " '만'|| (to_number(to_char(sysdate,'yyyy')) - to_number('1900'+substr(v_jumin,1,2))) ||'세' v_age, "
					+ " decode(substr(v_jumin,7,1), '1', '남', '2', '여') v_gender, "
					+ " m_no,"
					+ " substr(v_time,1,2)||':'||substr(v_time,3,2) v_time, "
					+ " decode(v_confirm, 'Y', '확인', 'N', '미확인') v_confirm "
					+ " from tbl_vote_202005"
					+ " where v_area = '제1투표장'";
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
						
			while(rs.next()) {
				Vote vote = new Vote();
				vote.setV_name(rs.getString(1));
				vote.setV_jumin(rs.getString(2));
				vote.setV_age(rs.getString(3));
				vote.setV_gender(rs.getString(4));
				vote.setM_no(rs.getString(5));
				vote.setV_time(rs.getString(6));
				vote.setV_confirm(rs.getString(7));
				
				list.add(vote);
			}
			request.setAttribute("list", list);
			
			conn.close();
			ps.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "listcheck.jsp";
		
	}
	
	public String ranking(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Ranking> list = new ArrayList<Ranking>();
		try {
			conn = getConnection();
			String sql = "select m.m_no, "
					+ " m.m_name,"
					+ " count(v.m_no) v_total"
					+ " from tbl_vote_202005 v, tbl_member_202005 m "
					+ " where m.m_no = v.m_no and v.v_confirm = 'Y' "
					+ " group by m.m_no, m.m_name "
					+ " order by count(v.m_no) desc";
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Ranking rank = new Ranking();
				rank.setM_no(rs.getString(1));
				rank.setM_name(rs.getString(2));
				rank.setV_total(rs.getString(3));
				
				list.add(rank);
			
				
			
			}
			request.setAttribute("list", list);
			conn.close();
			ps.close();
			rs.close();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return "rankinglist.jsp";
	}
}

