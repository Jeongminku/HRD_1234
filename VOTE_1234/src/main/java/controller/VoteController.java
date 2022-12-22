package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.VoteDAO;

@WebServlet("/")
public class VoteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public VoteController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPro(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPro(request,response);
	}

	protected void doPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String context = request.getContextPath();
		String command = request.getServletPath();
		String view = null;	
		System.out.println(context + " , " + command);
		
		VoteDAO vote = new VoteDAO();
		switch (command) {
		case "/home":
			view = "index.jsp";
			break;
		case "/search":
			view = vote.search(request, response);
			break;
		case "/vote":
				int result = vote.insertVote(request, response);
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				if (result == 1) {
					out.println("<script>");
					out.println("alert('투표하기 정보가 정상적으로 등록 되었습니다!'); location.href= '" + context + "'; ");
							  //println("alert('내부'); location.href= '주소';");
					out.println("</script>");
					out.flush(); //안쓰면 스택 오버플로우
				} else {
					out.println("<script>");
					out.println("alert('등록 실패!'); location.href= '"+ context +"'; ");
					out.println("</script>");
					out.flush();
				}
				break;
		case "/voteGo":
			view = "voteGo.jsp";
			break;
			
		case "/listcheck":
			view = vote.listcheck(request, response);
			break;
			
		case "/rankinglist":
			view = vote.ranking(request, response);
			break;
		}
		getServletContext().getRequestDispatcher("/"+view).forward(request, response);
	}
	
	
}
