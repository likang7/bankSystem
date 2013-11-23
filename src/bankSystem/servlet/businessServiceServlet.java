package bankSystem.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import bankSystem.service.*;

/**
 * Servlet implementation class businessServiceServlet
 */
@WebServlet("/businessServiceServlet")
public class businessServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public businessServiceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	protected void forwardHelper(HttpServletRequest request, HttpServletResponse response, 
			String msg, String returnLink, String dispatcher) throws ServletException, IOException {
		request.setAttribute("msg", msg);
		request.setAttribute("returnLink", returnLink);
		RequestDispatcher view = request.getRequestDispatcher("errorPage.jsp");
		view.forward(request, response);
	}
	
	protected void doIndividualBusiness(HttpServletRequest request, HttpServletResponse response,
			String businesstype, String operatorId) throws ServletException, IOException {
		if(businesstype.equals("openaccount")){
			String userId = (String)request.getParameter("userid");
			String name = (String)request.getParameter("name");
			String accountType = (String)request.getParameter("type");
			double money = Double.valueOf((String)request.getParameter("money"));
			String password = (String)request.getParameter("password");
			
			ReturnMsg msg = new IndividualBusinessService().openAccount(operatorId, userId, name, accountType, money, password);
			if(msg.getStatus().equals(Status.ERROR)){//ʧ�ܣ����ʧ����Ϣ
				forwardHelper(request, response, msg.getMsg(), request.getHeader("referer"), "errorPage.jsp");
			}
			else{//�ɹ������ؿ���
				forwardHelper(request, response, "�ɹ��������ǣ�" + msg.getMsg(), 
						"business/individualBusinessService.jsp", "errorPage.jsp");
			}
		}
		else if(businesstype.equals("deposit")){
			String cardId = (String)request.getParameter("cardid");
			String password = (String)request.getParameter("password");
			double money = Double.valueOf((String)request.getParameter("money"));
			
			ReturnMsg msg = new IndividualBusinessService().deposit(operatorId, cardId, password, money);
			if(msg.getStatus().equals(Status.ERROR)){//ʧ�ܣ����ʧ����Ϣ
				forwardHelper(request, response, msg.getMsg(), request.getHeader("referer"), "errorPage.jsp");
			}
			else{//�ɹ����������
				forwardHelper(request, response, "�ɹ������Ϊ��" + msg.getMsg(), 
						"business/individualBusinessService.jsp", "errorPage.jsp");
			}				
		}
		else if(businesstype.equals("withdraw")){
			String cardId = (String)request.getParameter("cardid");
			String password = (String)request.getParameter("password");
			double money = Double.valueOf((String)request.getParameter("money"));
			
			ReturnMsg msg = new IndividualBusinessService().withdraw(operatorId, cardId, password, money);
			if(msg.getStatus().equals(Status.ERROR)){//ʧ�ܣ����ʧ����Ϣ
				forwardHelper(request, response, msg.getMsg(), request.getHeader("referer"), "errorPage.jsp");
			}
			else{//�ɹ����������
				forwardHelper(request, response, "�ɹ������Ϊ��" + msg.getMsg(), 
						"business/individualBusinessService.jsp", "errorPage.jsp");
			}
		}
		else if(businesstype.equals("query")){
			String userId = (String)request.getParameter("userid");
			String cardId = (String)request.getParameter("cardid");
			String password = (String)request.getParameter("password");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String startDateStr = (String)request.getParameter("startdate") + " 00:00:00";
			String endDateStr = (String)request.getParameter("enddate") + " 23:59:59";
			try{
				Date start = sdf.parse(startDateStr);
				Date end = sdf.parse(endDateStr);
				ReturnMsg msg = new IndividualBusinessService().query(operatorId, userId, cardId, password, start, end);
				if(msg.getStatus().equals(Status.ERROR)){//ʧ�ܣ����ʧ����Ϣ
					forwardHelper(request, response, msg.getMsg(), request.getHeader("referer"), "errorPage.jsp");
				}
				else{//�ɹ�������
					ArrayList<Log> logs = msg.getLogs();
					StringBuilder logAsStr = new StringBuilder();
					for(Log log : logs){
						logAsStr.append(log.toString()).append("<br>");
					}
					forwardHelper(request, response, "�ɹ���<br>" + logAsStr.toString(), 
							"business/individualBusinessService.jsp", "errorPage.jsp");
				}
			}catch (Exception e){
				e.printStackTrace();
			}

		}
		else if(businesstype.equals("transfer")){
			String outUserId = (String)request.getParameter("outuserid");
			String outUsername = (String)request.getParameter("outusername");
			String outCardId = (String)request.getParameter("outcardid");
			String password = (String)request.getParameter("password");
			String inCardId = (String)request.getParameter("incardid");
			String inUsername = (String)request.getParameter("inusername");
			double money = Double.valueOf((String)request.getParameter("money"));
			
			ReturnMsg msg = new IndividualBusinessService().transfer(operatorId, outUserId, 
					outCardId, password, outUsername, inCardId, inUsername, money);
			if(msg.getStatus().equals(Status.ERROR)){//ʧ�ܣ����ʧ����Ϣ
				forwardHelper(request, response, msg.getMsg(), request.getHeader("referer"), "errorPage.jsp");
			}
			else{//�ɹ����������
				forwardHelper(request, response, "�ɹ������Ϊ��" + msg.getMsg(), 
						"business/individualBusinessService.jsp", "errorPage.jsp");
			}
		}
		else if(businesstype.equals("changepasswd")){
			String userId = (String)request.getParameter("userid");
			String cardId = (String)request.getParameter("cardid");
			String oldpassword = (String)request.getParameter("oldpasswd");
			String newpassword = (String)request.getParameter("newpasswd");
			String newpasswordConfirm = (String)request.getParameter("newpasswd2");
			
			if(!newpassword.equals(newpasswordConfirm)){
				forwardHelper(request, response, "��������������벻һ�¡�", request.getHeader("referer"), "errorPage.jsp");
			}
			ReturnMsg msg = new IndividualBusinessService().changePasswd(operatorId, userId, 
					cardId, oldpassword, newpassword);
			if(msg.getStatus().equals(Status.ERROR)){//ʧ�ܣ����ʧ����Ϣ
				forwardHelper(request, response, msg.getMsg(), request.getHeader("referer"), "errorPage.jsp");
			}
			else{//�ɹ����������
				forwardHelper(request, response, "�ɹ��޸����룡", 
						"business/individualBusinessService.jsp", "errorPage.jsp");
			}				
		}
		else if(businesstype.equals("closeaccount")){
			String userId = (String)request.getParameter("userid");
			String cardId = (String)request.getParameter("cardid");
			String password = (String)request.getParameter("password");
			ReturnMsg msg = new IndividualBusinessService().closeAccount(operatorId, userId, 
					cardId, password);
			if(msg.getStatus().equals(Status.ERROR)){//ʧ�ܣ����ʧ����Ϣ
				forwardHelper(request, response, msg.getMsg(), request.getHeader("referer"), "errorPage.jsp");
			}
			else{//�ɹ����������
				forwardHelper(request, response, "�����ɹ���", 
						"business/individualBusinessService.jsp", "errorPage.jsp");
			}				
		}
		else{
			forwardHelper(request, response, "����δ֪��ҵ�����ͣ�", 
					"business/individualBusinessService.jsp", "errorPage.jsp");
		}
	}
	
	protected void doVIPBusiness(HttpServletRequest request, HttpServletResponse response,
			String businesstype, String operatorId) throws ServletException, IOException {
	
	}
	
	protected void doEnterpriseBusiness(HttpServletRequest request, HttpServletResponse response,
			String businesstype, String operatorId) throws ServletException, IOException {
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8"); 
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String usertype = (String)session.getAttribute("usertype");
		String businesstype = (String)session.getAttribute("businesstype");
		String operatorId = (String)session.getAttribute("sessionUsername");
		if(operatorId == null){
			response.sendRedirect("login.html");
			return;
		}
		
		//��������û�ҵ��
		if(usertype.equals("individual")){
			doIndividualBusiness(request, response, businesstype,operatorId);
		}
		else if(usertype.equals("vip")){
			doVIPBusiness(request, response, businesstype,operatorId);
		}
		else if(usertype.equals("enterprise")){
			doEnterpriseBusiness(request, response, businesstype,operatorId);
		}

	}
	
	public static void main(String args[]){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateAsString = "20131123";
		try{
			Date date = sdf.parse(dateAsString);
			System.out.println(date);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

}
