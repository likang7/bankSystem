package bankSystem.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bankSystem.entity.Employee;
import bankSystem.service.DepartmentService;

/**
 * Servlet implementation class logQueryServlet
 */
@WebServlet("/logQueryServlet")
public class logQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public logQueryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8"); 
		HttpSession session = request.getSession();
		String operatorId = (String)session.getAttribute("sessionUsername");
		if(operatorId == null){
			response.sendRedirect("login.html");
			return;
		}
		
		ArrayList<Employee> employees = new DepartmentService().getUserAndSubordinatesByUsername(operatorId);
		StringBuilder results = new StringBuilder();
		for(Employee employee : employees){
			results.append(employee.toString()).append("<br>");
		}
		request.setAttribute("employees", results.toString());
		RequestDispatcher view = request.getRequestDispatcher("business/logquery.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
