package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MemberDAO;

@WebServlet("/")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MemberController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPro(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPro(request,response);
	}

	protected void doPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String context = request.getContextPath(); //Context =웹어플 하나당 servletcontext가 한개 생성된다. 톰캣에서 생성되는녀석. 톰캣의 context path를 가져옵니다. 웹어플리케이션의 자원을 관리하고, 종료되면 사라진다.
												   //Servers>Tomcat v9.0> server.xml의 156번째 가면 확인 가능.
												   //톰캣의 context Path를 가져온다. (server.xml 156행의 path="/HRD_1234")
		String command = request.getServletPath(); //파일명만 가져온다(인터넷 주소 경로의 마지막 파일명을 가져옴)
		String site = null;
		System.out.println(context + ", " + command);
		
		MemberDAO member = new MemberDAO();
		
		switch (command) {
		case "/home":
			site = "index.jsp";
			break;
		case "/insert":
			site = member.insert(request, response);
			break;
		case "/list":
			site = member.selectAll(request, response);
			break;
		case "/add":
			site = member.nextCustno(request, response);
			break;
		case "/modify":
			site = member.modify(request, response);
			break;
		case "/result":
			site = member.selectResult(request,response);
			break;
		case "/update":
			int result1 = member.update(request, response);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			if(result1 == 1) {//업데이트 성공
				out.print("<script>");   				  //location.href= '/HRD_1234';
				out.print("alert('회원수정이 완료 되었습니다!'); location.href='" + context + "';");
				out.print("</script>");
				out.flush();
			} else {
				out.print("<script>");   				  //location.href= '/HRD_1234';
				out.print("alert('수정실패!!'); location.href='" + context + "';");
				out.print("</script>");
				out.flush();
			}
			break;
			
		case "/delete":
			int result2 = member.delete(request, response);
			response.setContentType("text/html; charset=UTF-8");
			out = response.getWriter();
			
			if(result2 == 1) {//업데이트 성공
				out.print("<script>");   				  //location.href= '/HRD_1234';
				out.print("alert('회원정보가 삭제되었습니다!'); location.href='" + context + "';");
				out.print("</script>");
				out.flush();
			} else {
				out.print("<script>");   				  //location.href= '/HRD_1234';
				out.print("alert('삭제실패!!'); location.href='" + context + "';");
				out.print("</script>");
				out.flush();
			}
			break;
		}
		
		getServletContext().getRequestDispatcher("/" + site).forward(request, response);
	}
}
