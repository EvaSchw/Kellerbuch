package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.*;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.swing.JOptionPane;

import kellerbuch.fachlogik.Login;
import kellerbuch.fachlogik.Rechnungspositionen;
import kellerbuch.fachlogik.Weine;
import kellerbuch.fachlogik.Winzerbetrieb;
import kellerbuch.persistence.WinzerbetriebDB;

@ApplicationScoped
@ManagedBean(name="kellerbuch", eager=true)
public class WebKellerbuch extends WinzerbetriebDB implements Converter
{
	
	public WebKellerbuch() throws Exception
	{
		super();
	}

	@Override
	public Object getAsObject(FacesContext fc, UIComponent ui, String id)
	{
		if(id != null)
			return findeWein(Integer.valueOf(id));
		return null;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent ui, Object o)
	{
		Weine w = (Weine) o;
		return w.getBezeichnung();
	}
	
	@Override
	public List<Weine> getWeinliste() throws Exception
	{
		return super.getWeinliste();
	}
	
	
	public Login anmeldungPruefen(String user, String pw)
	{
		try
		{
			for(Login l: getLoginliste())
			{
				if(l.getUsername().equals(user) && l.getPasswd().equals(pw))
				{
					return l;
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return null;
	}
}
