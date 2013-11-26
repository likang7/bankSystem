package bankSystem.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bankSystem.entity.Log;
import bankSystem.entity.Position;
import bankSystem.service.DepartmentService;
import bankSystem.service.IndividualBusinessService;
import bankSystem.service.ReturnMsg;
import bankSystem.service.Status;

/**
 * Servlet implementation class departmentServiceServlet
 */
@WebServlet("/departmentServiceServlet")
public class departmentServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String resultPage = "result.jsp";
    private static final String logResultPage = "logtable.jsp";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public departmentServiceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void forwardHelper(HttpServletRequest request, HttpServletResponse response, 
			String msg, String returnLink, String dispatcher, String status) throws ServletException, IOException {
		request.setAttribute("status", status);
		request.setAttribute("msg", msg);
		request.setAttribute("returnLink", returnLink);
		RequestDispatcher view = request.getRequestDispatcher(dispatcher);
		view.forward(request, response);
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8"); 
		HttpSession session = request.getSession();
		String usertype = (String)session.getAttribute("usertype");
		String businesstype = (String)session.getAttribute("businesstype");
		String operatorId = (String)session.getAttribute("sessionUsername");
		if(operatorId == null){
			response.sendRedirect("login.html");
			return;
		}
		
		if(businesstype.equals("addemployee")){
			String username = (String)request.getParameter("username");
			String password = (String)request.getParameter("password");
			String departmentId = (String)request.getParameter("departmentId");
			String positionStr = (String)request.getParameter("position");
			Position position;
			if(positionStr.equals("1")){
				position = Position.Supervisor;
			}
			else if(positionStr.equals("2")){
				position = Position.Manager;
			}
			else {
				position = Position.Operator;
			}
			String superiorId = (String)request.getParameter("superiorid");
			
			ReturnMsg msg = new DepartmentService().addEmployee(username, password, departmentId, position, superiorId);
			if(msg.getStatus().equals(Status.ERROR)){
				forwardHelper(request, response, msg.getMsg(), request.getHeader("referer"), 
						resultPage, msg.getStatus().toString());
			}
			else{
				forwardHelper(request, response, "成功添加用户", 
						"business/departmentService.jsp", resultPage, msg.getStatus().toString());
			}
		}
		else if(businesstype.equals("logquery")){
			String[] selectedEmployees = request.getParameterValues("selectedEmployee");  
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String startDateStr = (String)request.getParameter("startdate") + " 00:00:00";
			String endDateStr = (String)request.getParameter("enddate") + " 23:59:59";
			try{
				Date start = sdf.parse(startDateStr);
				Date end = sdf.parse(endDateStr);
				ArrayList<Log> logs = new ArrayList<Log>();
				DepartmentService ds = new DepartmentService();
				if(selectedEmployees != null){
					for(int i = 0; i < selectedEmployees.length; i++){
						logs.addAll(ds.getEmployeeLog(selectedEmployees[i], start, end));
					}
					StringBuilder logAsStr = new StringBuilder();
					for(Log log : logs){
						logAsStr.append(log.toString()).append("<br>");
					}
					forwardHelper(request, response, logAsStr.toString(), 
							"business/departmentService.jsp", logResultPage, Status.OK.toString());
				}
				else{
					forwardHelper(request, response, "请至少选中一个雇员", request.getHeader("referer"), resultPage, Status.ERROR.toString());
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		else if(businesstype.equals("statistics")){
			
		}
	}

}
