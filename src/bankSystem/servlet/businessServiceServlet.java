package bankSystem.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		PrintWriter out = response.getWriter();
		String usertype = (String)request.getSession().getAttribute("usertype");
		String businesstype = (String)request.getSession().getAttribute("businesstype");
		if(usertype.equals("individual")){
			if(businesstype.equals("openaccount")){
				//ReturnMsg msg = new IndividualBusinessService().openAccount(operatorId, userId, name, accountType, money, password);
			}
			else if(businesstype.equals("deposit")){
				
			}
			else if(businesstype.equals("withdraw")){
				
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
			response.setContentType("text/html");
			out.print("<br>" + usertype);
			out.print("<br>" + businesstype);
		}
		else if(usertype.equals("vip")){
			
		}
		else if(usertype.equals("enterprise")){
			
		}

	}

}
