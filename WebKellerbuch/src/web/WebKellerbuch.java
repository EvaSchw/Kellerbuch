package web;

import java.util.List;

import javax.faces.bean.*;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import kellerbuch.fachlogik.Weine;
import kellerbuch.fachlogik.Winzerbetrieb;
import kellerbuch.persistence.WinzerbetriebDB;

@ApplicationScoped
@ManagedBean(name="kellerbuch", eager=true)
public class WebKellerbuch extends WinzerbetriebDB implements Converter
{
	
	int anzahl;
	
	public int getAnzahl()
	{
		return anzahl;
	}

	public void setAnzahl(int anzahl)
	{
		this.anzahl = anzahl;
	}

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
		setAnzahl(1);
		return super.getWeinliste();
	}
}
