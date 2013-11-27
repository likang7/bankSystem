package bankSystem.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

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
    private static final String logStatisticsPage = "logStatisticsTable.jsp";
    private static final String departmentServicePage = "business/departmentService.jsp";
    private static final String loginPage = "login.html";
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
			response.sendRedirect(loginPage);
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
						departmentServicePage, resultPage, msg.getStatus().toString());
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
							departmentServicePage, logResultPage, Status.OK.toString());
				}
				else{
					forwardHelper(request, response, "请至少选中一个雇员", request.getHeader("referer"), resultPage, Status.ERROR.toString());
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		else if(businesstype.equals("logstatistics")){
			String[] selectedEmployees = request.getParameterValues("selectedEmployee");  
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			request.setAttribute("start", (String)request.getParameter("startdate"));
			request.setAttribute("end", (String)request.getParameter("enddate"));
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
					
					//local class for help
					class Statistics{
						public int frequency = 0;
						public double out = 0;
						public double in = 0;
						@Override
						public String toString() {
							return "frequency=" + frequency
									+ ", in=" + in + ", out=" + out;
						}											
					};
					HashMap<String, Statistics> statisticsMap = new HashMap<String, Statistics>();
					Statistics all = new Statistics();
					for(Log log : logs){
						Statistics statistics;
						String operation = log.getOperation();
						if(!statisticsMap.containsKey(operation))
							statisticsMap.put(operation, new Statistics());
						statistics = statisticsMap.get(operation);
						statistics.frequency++;
						statistics.out += log.getExpenditure();
						statistics.in += log.getIncome();
						
						all.frequency++;
						all.out += log.getExpenditure();
						all.in += log.getIncome();
					}
					
					
					StringBuilder logAsStr = new StringBuilder();
					Iterator<Entry<String, Statistics>> it = statisticsMap.entrySet().iterator();
					while(it.hasNext()){
						Entry<String, Statistics> pair = it.next();
						String key = pair.getKey();
						logAsStr.append("operation=" + key + ", ");
						logAsStr.append(pair.getValue().toString());
						logAsStr.append("<br>");
					}
					logAsStr.append("All=合计, " + all.toString() + "<br>");
					
					/*for(Log log : logs){
						logAsStr.append(log.toString()).append("<br>");
					}*/
					forwardHelper(request, response, logAsStr.toString(), 
							departmentServicePage, logStatisticsPage, Status.OK.toString());
				}
				else{
					forwardHelper(request, response, "请至少选中一个雇员", request.getHeader("referer"), resultPage, Status.ERROR.toString());
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		else if(businesstype.equals("deleteemployee")){
			String username = (String)request.getParameter("username");
			ReturnMsg msg = new DepartmentService().deleteEmployee(username);
			if(msg.getStatus().equals(Status.ERROR)){
				forwardHelper(request, response, msg.getMsg(), request.getHeader("referer"), 
						resultPage, msg.getStatus().toString());
			}
			else{
				forwardHelper(request, response, "成功删除雇员", 
						departmentServicePage, resultPage, msg.getStatus().toString());
			}
		}
	}

}
