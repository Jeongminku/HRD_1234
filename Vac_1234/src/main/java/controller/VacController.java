package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.VacDAO;


@WebServlet("/VacController")
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
			view = "";
		}
		getServletContext().getRequestDispatcher("/"+view).forward(request, response);
			
	}
	
	
	
}
