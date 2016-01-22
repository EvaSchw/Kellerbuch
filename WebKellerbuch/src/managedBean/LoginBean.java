package managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import filter.LoginFilter;
import kellerbuch.fachlogik.Login;

@ViewScoped
@ManagedBean(name="loginBean")
public class LoginBean extends Login
{
	
	public final static String USER_INFO = "user.info";
	
	@ManagedProperty(value= "#{kellerbuch}")
	private WebKellerbuch kellerbuch;

	public WebKellerbuch getKellerbuch()
	{
		return kellerbuch;
	}

	public void setKellerbuch(WebKellerbuch kellerbuch)
	{
		this.kellerbuch = kellerbuch;
	}

	public String validateLogin()
	{
		System.out.println("start");
		Login l = kellerbuch.anmeldungPruefen(getUsername(), getPasswd());
		System.out.println(l);
		if(l != null)
		{
			System.out.println("session");
			HttpSession session = (HttpSession)FacesContext.getCurrentInstance()
            .getExternalContext().getSession(false);
			session.setAttribute(USER_INFO, l);
			System.out.println("ORIG_REQUEST: "+LoginFilter.ORIG_REQUEST);
			if(session.getAttribute(LoginFilter.ORIG_REQUEST) != null)
			{
				String[] str = session.getAttribute(LoginFilter.ORIG_REQUEST).toString().split("/WebKellerbuch");
				return str[1];
			}
			else
				return "index.xhtml";
		}
		else
		{
			return "error.xhtml";
		}
	}
		
	public LoginBean()
	{
		
	}
	

}
