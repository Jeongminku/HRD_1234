package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Member;

public class MemberDAO {
	Connection conn = null; //db와의 connection
	PreparedStatement pstmt = null; //쿼리문 실행담당
	ResultSet rs = null; //결과 담는 부분
	
	//DB와 연결하는 메소드입니다.
	public static Connection getConnection() throws Exception{
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1524:xe", "system", "sys1234");
		return con;
	}
	
	//insert
	public String insert(HttpServletRequest request, HttpServletResponse response) {
		int custno = Integer.parseInt(request.getParameter("custno"));
		String custname = request.getParameter("custname");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String joindate = request.getParameter("joindate");
		String grade = request.getParameter("grade");
		String city = request.getParameter("city");
		int result = 0;
		
		try {
			conn = getConnection(); //db연결
			//String sql = "쿼리문 작성"
			String sql = "insert into member_tbl_02 values(?,?,?,?,to_date(?,'YYYY-MM-DD'),?,?)"; //번호,성명,번호,주소,가입일,등급,도시
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, custno); //setInt(x번째물음표에, 넣을데이터)
			pstmt.setString(2, custname);
			pstmt.setString(3, phone);
			pstmt.setString(4, address);
			pstmt.setString(5, joindate);
			pstmt.setString(6, grade);
			pstmt.setString(7, city);
			
			//insert, update, delete: 성공한 레코드의 개수를 반환합니다.
			result = pstmt.executeUpdate(); // 수행결과로 Int 타입의 값을 반환합니다.  INSERT / DELETE / UPDATE 관련 구문에서는 반영된 레코드의 건수를 반환합니다.
			
			System.out.println(result);
			
			pstmt.close();//리소스이므로 사용했으면 닫기.	
			conn.close(); //리소스이므로 사용했으면 닫기.
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			return "add";			
	}
	//회원목록 조회, 수정파트
	public String selectAll(HttpServletRequest request, HttpServletResponse response) { 
			ArrayList<Member> list = new ArrayList<Member>(); //Member 엔티티클래스(DTO)를 담는 역할.
			try {
				conn = getConnection();
				//String sql = "쿼리문 작성"
				//쿼리문으로 해결할 수 있는것은 엔간해선 전부 뒷단(쿼리문)에서 해결해야합니다.  ex) if문을 써서 A -> VIP 이렇게 하면 안되고 뒷단에서 해야한다는 것입니다.
				//DECODE 를 사용해 봅시다  A = VIP 이런식으로 씀.  오라클할때 배웠다는데요?
				String sql = "select custno, custname, phone, address, TO_CHAR(joindate, 'YYYY-MM-DD') joindate,";
				sql+= "DECODE(grade, 'A', 'VIP', 'B', '일반', '직원') grade, city from member_tbl_02, order by custno asc"; //DECODE(컬럼명, 'N1값', '다시표현할 N1값', 'N2라면', '일반N2', '나머지는 이렇게')
				
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					Member member = new Member();
					member.setCustno(rs.getInt(1));
					member.setCustname(rs.getString(2));
					member.setPhone(rs.getString(3));
					member.setAddress(rs.getString(4));
					member.setJoindate(rs.getString(5));
					member.setGrade(rs.getString(6));
					member.setCity(rs.getString(7));
					
					list.add(member); //ArrayList의 이름: list 에 add 합니다.
				}
				
				request.setAttribute("list", list); //Member객체의 값을 저장한 list를 list라는 key값에 저장.
				
				conn.close();
				pstmt.close();
				rs.close();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return "list.jsp";
	}
	
}
