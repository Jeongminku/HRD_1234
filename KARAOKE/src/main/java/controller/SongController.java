package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

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
		case "/searchsong":
			view = searchSong(request);
			break;
			
		case "/list":
			view = allList(request, response);
			break;
		
		case "/add":
			view = "add.jsp";
			break;
		
		case "/insert":
			view = insertSong(request);
			break;
			
		case "/select":
			view = select(request);
			break;
		}
		
		if(view.startsWith("redirect:/")) {
			String rview = view.substring("redirect:/".length());
			System.out.println(rview);
			response.sendRedirect(rview);
		} else {
		ctx.getRequestDispatcher("/"+ view).forward(request, response);
		
		}
	}
	
	public String searchSong(HttpServletRequest request) {
		List<Result> list;
		try {
			list = song.getList(request);
			request.setAttribute("songList", list);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("노래 검색과정에서 문제 발생");
			request.setAttribute("erorr", "노래 조회가 정상적으로 되지 않았습니다.");
		}
		return "searchResult.jsp";
	}
	
	public String allList(HttpServletRequest request, HttpServletResponse response) {
		List<Result> alllist;
		try {
			alllist = song.getAllList(request,response);
			request.setAttribute("alllist", alllist);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("노래 리스트 전체 조회에서 문제 발생");
			request.setAttribute("error", "노래 리스트가 정상적으로 로드되지 않았습니다.");
		}
		return "songlist.jsp";
		
	
	}
	
	public String insertSong(HttpServletRequest request) {
		Result s = new Result();
		try {
			BeanUtils.populate(s, request.getParameterMap());
			song.insertSong(s);
			
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("노래 추가 과정에서 문제가 발생하였습니다.");
			try {
				String encodeName = URLEncoder.encode("노래가 정상적으로 등록되지 않았습니다.", "UTF-8");
				return "redirect:/list?error="+encodeName;
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		return "redirect:/list";
	}
	
	public String select(HttpServletRequest request) {
		int songno = Integer.parseInt(request.getParameter("songno"));
		
		try {
			Result s = song.select(songno);
			request.setAttribute("song", s);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("노래코드로 불러오는 과정에서 문제 발생");
			request.setAttribute("error", "노래를 정상적으로 불러오지 못했습니다.");
		}
		return "select.jsp";
	}
}
