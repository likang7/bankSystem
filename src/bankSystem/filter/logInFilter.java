package bankSystem.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class logInFilter
 */
@WebFilter("/logInFilter")
public class logInFilter implements Filter {

    /**
     * Default constructor. 
     */
    public logInFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest)request;
		RequestDispatcher dispatcher = request.getRequestDispatcher("/management/login.jsp");
		HttpSession session = req.getSession(true);
		
		String username = (String)session.getAttribute("sessionUsername");
		if(username == null || username.equals("")){
			String url = req.getRequestURI();
			if(url != null && !url.equals("") && url.indexOf("login") < 0){
				dispatcher.forward(request, response);
				HttpServletResponse res = (HttpServletResponse) response;  
				res.setHeader("Cache-Control", "no-store");
				res.setDateHeader("Expires",0);  
				res.setHeader("Pragma","no-cache"); 
				return;
			}
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
