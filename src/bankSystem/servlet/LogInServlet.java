package bankSystem.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bankSystem.service.DepartmentService;
import bankSystem.service.ReturnMsg;
import bankSystem.service.Status;

/**
 * Servlet implementation class LogInServerlet
 */
@WebServlet("/LogInServerlet")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LogInServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		DepartmentService departmentService = new DepartmentService();
		ReturnMsg msg = departmentService.logIn(username, password);
		if(msg.getStatus().equals(Status.OK)){
			//HttpSession session = request.getSession();
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("log in successful£¬ redirecting...");
			response.sendRedirect("welcome");
		}
		else{
			request.setAttribute("msg", msg.getMsg());
			request.setAttribute("returnLink", request.getHeader("referer"));
			RequestDispatcher view = request.getRequestDispatcher("errorPage.jsp");
			view.forward(request, response);
		}
	}

}
