package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kellerbuch.fachlogik.Login;
import managedBean.LoginBean;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/faces/bestellung/*")
public class LoginFilter implements Filter
{

	public final static String ORIG_REQUEST = "request.original";
    /**
     * Default constructor. 
     */
    public LoginFilter() {
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
		System.out.println("Filter wurde aufgerufen!");
		
		HttpServletRequest r = (HttpServletRequest) request;
		HttpSession session = r.getSession();
		Login l = (Login)session.getAttribute(LoginBean.USER_INFO);
		System.out.println(l);
		
		if(l == null)
		{
			session.setAttribute(ORIG_REQUEST, r.getRequestURL().toString());
			request.getRequestDispatcher("/faces/login.xhtml").forward(request, response);
		}
		else
			chain.doFilter(request, response);
	}


	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
