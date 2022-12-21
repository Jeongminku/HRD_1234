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
			view = 
			break;
	
		}
		getServletContext().getRequestDispatcher("/"+view).forward(request, response);
	}
	
	
}
