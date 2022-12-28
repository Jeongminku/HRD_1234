package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.*;
import DTO.*;


@WebServlet("/")
public class SongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private SongDAO song;
    private ServletContext ctx;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		song = new SongDAO();
		ctx = getServletContext();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPro(request, response);
	}

	protected void doPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String context = request.getContextPath();
		String command = request.getServletPath();
		String view = null;
		System.out.println(context + ", " + command);
		

		switch(command) {
		case "/home":
			view = "index.jsp";
			break;
		case "/search":
			view = "search.jsp";
			break;
		case "/getList":
			view = getList(request);
			break;
		}
		getServletContext().getRequestDispatcher("/"+ view).forward(request, response);
	}
	
	public String getList(HttpServletRequest request) {
		List<Result> list;
		try {
			list = song.getList(request);
			request.setAttribute("songList", list);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("노래 조회과정에서 문제 발생");
			request.setAttribute("erorr", "노래 조회가 정상적으로 되지 않았습니다.");
		}
		return "searchResult.jsp";
	}
}

