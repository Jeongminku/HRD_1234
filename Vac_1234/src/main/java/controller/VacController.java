package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.VacDAO;


@WebServlet("/")
public class VacController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public VacController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPro(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPro(request, response);
	}

	protected void doPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String context = request.getContextPath();
		String command = request.getServletPath();
		String view = null;
		System.out.println(context + ", " + command);
		
		VacDAO vac = new VacDAO();
		switch(command) {
		case "/home":
			view = "index.jsp";
			break;
			
		case "/book":
			view = "add.jsp";
			break;
		
		case "/booking":
			int result = vac.book(request,response);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if (result == 1) {
				out.println("<script>");
				out.println("alert('예약이 성공적으로 되었습니다!'); location.href= '" + context + "'; ");
				out.println("</script>");
				out.flush();
			} else {
				out.println("<script>");
				out.println("alert('예약 번호가 입력되지 않았습니다.'); location.href= '" + context + "'; ;");
				out.println("</script>");
				out.flush();
			}
			break;
			
		case "/reserveList":
			view = "reserveSearch.jsp";
			break;
		
		case "/search":
			view = vac.search(request, response);
			break;
		}
		
		
		getServletContext().getRequestDispatcher("/" + view).forward(request, response);
	}
	
	
	
}
