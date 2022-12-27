package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import DAO.BoardDAO;
import DTO.Board;

@WebServlet("/")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardDAO dao; //임포트 해야
	private ServletContext ctx; //임포트 해야
	
	
	
	@Override //최초의 요청시 한번 작동하는 init()을 Override해서 사용한다.
	public void init(ServletConfig config) throws ServletException {

		super.init(config);
		//init은 서블릿 객체 생성시 딱 한번만 실행하므로 객체를 한번만 생성해 공유할 수 있습니다.
		dao = new BoardDAO();
		ctx = getServletContext(); //웹 어플리케이션의 자원관리를 하는 ServletContext를 얻어옵니다. 
			   //ServletContext : 웹어플리케이션(웹프로젝트)하나당 하나의 서블릿 콘텍스트가 생성이 된다. 웹어플의 자원을 관리하며 종료될때 소멸한다. 톰캣에서 알아서 해줌.
		
	} 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //request 객체에 저장된 한글 깨짐 방지
		doPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //request 객체에 저장된 한글 깨짐 방지
		doPro(request, response);
	}
	
	protected void doPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String context = request.getContextPath();
		String command = request.getServletPath();
		String site = null;
		
		//경로 라우팅 (경로를 찾아줌)
		switch (command) {
		case "/list":
			site = getList(request);
			break;
		
		case "/view":
			site = getView(request);
			break;
			
		case "/insert": //insert 기능임. 근데 내가 index.jsp에서 글쓰기를 눌렀다면 글쓰기 화면을 보여줘야함 -> 별도로 만들어야함.
			site = insertBoard(request);
			break;
		
		case "/write": // 글쓰기를 눌렀다면 글쓰기 화면을 보여줘야함 -> 별도로 만들어야함. -> insert
			site = "write.jsp";
			break;
			
		case "/edit": // 수정 "화면" 을 보여줌
			site = getViewForEdit(request);
			break;
			
		case "/update": //update 기능
			site = updateBoard(request);
			break;
			
		case "/delete":
			site = deleteBoard(request);
			break;
		}
		
		/* redirect와 forward 둘다 페이지를 이동하지만 차이가 있다.
		  
			redirect: URL의 변화가 O   있습니다.
					  객체의 재사용 X   안됩니다. (request, response 객체)
					  사용자의 요청 정보가 바뀌어 버림.
					  URL 주소가 바뀌면서 다시 접속되는 것을 확인할 수 있어, 클라이언트 또한 리다이렉션이 일어났음을 알 수 있다.
					  클라이언트(보통 웹브라우저를 말함)
				
				ㄴ 추천 사용 : 	DB에 변화가 생기는 요청에 사용합니다. eg) 글쓰기, 회원가입 ...  insert, update, delete 조심...  
					  
					  
			forward: URL의 변화가  X   없습니다.
					 (보안...(?))
					 사용자의 요청 정보는 유지된 채 서버 내부의 동작만 바뀜.
					 
					 객체의 재사용 O 가능합니다.   (request, response 객체)
				ㄴ 추천 사용 :	단순 조회에 사용합니다. eg)리스트보기, 검색 ... select...
					 
					 
		*/
		      //startsWith("이 글자로 시작하는지 찾는것");
		if(site.startsWith("redirect:/")) { //redirect 
			String rview = site.substring("redirect:/".length());
			System.out.println(rview);
			
			response.sendRedirect(rview);
			
		} else { //forward
			//getServletContext().getRequestDispatcher("/"+site).forward(request, response);
			ctx.getRequestDispatcher("/"+site).forward(request, response);
		}
		
	}

	
	
	
	public String getList(HttpServletRequest request) {
		List<Board> list; //임포트 해야함
		
		//list = dao.getList(); 만 썼을때 빨간줄 해결
		//getList만들때 throws Exception을 해뒀기 때문에 그냥쓰면 tryCatch or throw하라고 나온다.
		//만약에 또 throw를 하면 또 다른데서 받아줘야한다.
		//최종적으로 throw를 끝내려면 try Catch로 에러를 처리해줘야 한다.
		try {
			list = dao.getList();
			request.setAttribute("boardList", list);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("게시판 목록 생성 과정에서 문제 발생");
/* void log(String msg) : Writes the specified message to a servlet log file, usually an event log. 
 * 						  The name and type of the servlet log file is specific to the servlet container. */
			
			//사용자에게 에러메시지를 보여주기 위해서 저장(작성)해 둔 것입니다.
			request.setAttribute("error", "게시판 목록이 정상적으로 처리되지 않았습니다!!"); 

		} 
		
		return "index.jsp";
	}
	
	public String getView(HttpServletRequest request) {
		int board_no = Integer.parseInt(request.getParameter("board_no"));
	
		try {
			dao.updateViews(board_no);//조회수 증가
			Board b = dao.getView(board_no); 
			request.setAttribute("board", b);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("게시글을 가져오는 과정에서 문제 발생");
/* void log(String msg) : Writes the specified message to a servlet log file, usually an event log. 
 * 						  The name and type of the servlet log file is specific to the servlet container. */
			
			//사용자에게 에러메시지를 보여주기 위해서 저장(작성)해 둔 것입니다.
			request.setAttribute("error", "게시글을 정상적으로 불러오지 못했습니다!"); 

		} 
		
		return "view.jsp";
	}

	
	public String insertBoard(HttpServletRequest request) {
		//insert 누름 -> insertBoard 실행됨 -> request객체에는 어떤것들이 들어있는가?
		//request : view단에서 보내준 데이터들이 들어있음 (제목, 쓴이름, 내용)
		//b.setUser_id(request.getParameter("user_id")); 
		//b.setTitle(request.getParameter("title"));
		//b.setContent(request.getParameter("content"));등등 써야함,  ==> 쉽게쓰기위해서 BeanUtils 사용할것.
		
		Board b = new Board();
		try {
			BeanUtils.populate(b, request.getParameterMap()); //이렇게 쓰는구나 하고 알아둘것.
			dao.insertBoard(b);
			
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("추가 과정에서 문제가 발생하였습니다.");
			try {
				//get 방식으로 넘겨줄때 한글이 깨짐을 방지하기 위한 인코딩입니다.
				String encodeName = URLEncoder.encode("게시물이 정상적으로 등록되지 않았습니다!", "UTF-8");
				return "redirect:/list?error=" + encodeName;
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			
		}
		return "redirect:/list";
	}
	
	
	public String getViewForEdit(HttpServletRequest request) {
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		
		try {
			Board b = dao.getViewForEdit(board_no); 
			request.setAttribute("board", b);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("게시글을 가져오는 과정에서 문제 발생");
			request.setAttribute("error", "게시글을 정상적으로 불러오지 못했습니다!"); 

		} 
		
		return "edit.jsp";
	}

	
	public String updateBoard(HttpServletRequest request) {
		Board b = new Board();
		try {
			BeanUtils.populate(b, request.getParameterMap());
			dao.updateBoard(b); //만들어둔 보드객체(b)를 넘겨주고 실행합니다.
			
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("수정(update) 과정에서 문제가 발생하였습니다.");
			try {
				//get 방식으로 넘겨줄때 한글이 깨짐을 방지하기 위한 인코딩입니다.
				String encodeName = URLEncoder.encode("게시물이 정상적으로 수정(update)되지 않았습니다!", "UTF-8");
				return "redirect:/view?board_no="+ b.getBoard_no() + "&error="+ encodeName;
				
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		return "redirect:/view?board_no="+ b.getBoard_no();
	}
	
	
	public String deleteBoard(HttpServletRequest request) {
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		
		try {
			dao.deleteBoard(board_no);
		} catch(Exception e) {
			e.printStackTrace();
			ctx.log("게시글을 삭제하는 과정에서 문제 발생");
			try {
				//get 방식으로 넘겨줄때 한글이 깨짐을 방지하기 위한 인코딩입니다.
				String encodeName = URLEncoder.encode("게시물이 정상적으로 삭제(delete)되지 않았습니다!", "UTF-8");
				return "redirect:/list?error="+encodeName;
				
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		return "redirect:/list"; 
		}
}
