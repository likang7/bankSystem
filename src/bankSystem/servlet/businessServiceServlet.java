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
		
		//处理个人用户业务
		if(usertype.equals("individual")){
			if(businesstype.equals("openaccount")){
				String userId = (String)request.getParameter("userid");
				String name = (String)request.getParameter("name");
				String accountType = (String)request.getParameter("type");
				double money = Double.valueOf((String)request.getParameter("money"));
				String password = (String)request.getParameter("password");
				response.setContentType("text/html");
				
				ReturnMsg msg = new IndividualBusinessService().openAccount(operatorId, userId, name, accountType, money, password);
				if(msg.getStatus().equals(Status.ERROR)){//失败，输出失败信息
					request.setAttribute("msg", msg.getMsg());
					request.setAttribute("returnLink", request.getHeader("referer"));
					RequestDispatcher view = request.getRequestDispatcher("errorPage.jsp");
					view.forward(request, response);
				}
				else{//成功，返回卡号
					request.setAttribute("msg", "成功！卡号是：" + msg.getMsg());
					request.setAttribute("returnLink", "business/individualBusinessService.jsp");
					RequestDispatcher view = request.getRequestDispatcher("errorPage.jsp");
					view.forward(request, response);
				}
			}
			else if(businesstype.equals("deposit")){
				String cardId = (String)request.getParameter("cardid");
				String password = (String)request.getParameter("password");
				double money = Double.valueOf((String)request.getParameter("money"));
				
				ReturnMsg msg = new IndividualBusinessService().deposit(operatorId, cardId, password, money);
				if(msg.getStatus().equals(Status.ERROR)){//失败，输出失败信息
					request.setAttribute("msg", msg.getMsg());
					request.setAttribute("returnLink", request.getHeader("referer"));
					RequestDispatcher view = request.getRequestDispatcher("errorPage.jsp");
					view.forward(request, response);
				}
				else{//成功，返回余额
					request.setAttribute("msg", "成功！余额为：" + msg.getMsg());
					request.setAttribute("returnLink", "business/individualBusinessService.jsp");
					RequestDispatcher view = request.getRequestDispatcher("errorPage.jsp");
					view.forward(request, response);
				}
				
			}
			else if(businesstype.equals("withdraw")){
				String cardId = (String)request.getParameter("cardid");
				String password = (String)request.getParameter("password");
				double money = Double.valueOf((String)request.getParameter("money"));
				
				ReturnMsg msg = new IndividualBusinessService().withdraw(operatorId, cardId, password, money);
				if(msg.getStatus().equals(Status.ERROR)){//失败，输出失败信息
					request.setAttribute("msg", msg.getMsg());
					request.setAttribute("returnLink", request.getHeader("referer"));
					RequestDispatcher view = request.getRequestDispatcher("errorPage.jsp");
					view.forward(request, response);
				}
				else{//成功，返回余额
					request.setAttribute("msg", "成功！余额为：" + msg.getMsg());
					request.setAttribute("returnLink", "business/individualBusinessService.jsp");
					RequestDispatcher view = request.getRequestDispatcher("errorPage.jsp");
					view.forward(request, response);
				}
			}
			else if(businesstype.equals("query")){
				
			}
			else if(businesstype.equals("transfer")){
				
			}
			else if(businesstype.equals("changepasswd")){
				
			}
			else if(businesstype.equals("closeaccount")){
				
			}
			else{
				out.print("unknown business type");
			}
			//response.setContentType("text/html");
			//out.print("<br>" + usertype);
			//out.print("<br>" + businesstype);
		}
		else if(usertype.equals("vip")){
			
		}
		else if(usertype.equals("enterprise")){
			
		}

	}

}
