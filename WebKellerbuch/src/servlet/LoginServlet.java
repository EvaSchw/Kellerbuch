package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kellerbuch.fachlogik.Login;
import managedBean.WebKellerbuch;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private final static String USER_INFO = "user.info";
	
	private WebKellerbuch kellerbuch;

	public LoginServlet() throws Exception
	{
		super();
		kellerbuch = new WebKellerbuch();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		String username = request.getParameter("user");
		String password = request.getParameter("password");
		
		Login l = kellerbuch.anmeldungPruefen(username, password);
		
		if(l != null) //Login erfolgreich
		{
			// User-Instanz der Session mitgeben
			HttpSession session = request.getSession();
			session.setAttribute(USER_INFO, l); // = MAP(key, value)
			
			// Forward: im Server wird auf eine andere geleitet
			request.getRequestDispatcher("index.html").forward(request, response);
		}
		else
			// Redirect geht zum Browser, dieser ruft dann die Seite login.html auf
			response.sendRedirect("login.html");
		
	}
}
