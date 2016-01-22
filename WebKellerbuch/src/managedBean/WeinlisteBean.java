package managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import kellerbuch.fachlogik.Rechnungspositionen;
import kellerbuch.fachlogik.Weine;

@ManagedBean(name="weinliste")
@ApplicationScoped
public class WeinlisteBean implements Serializable
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value="#{kellerbuch}")
	private WebKellerbuch kellerbuch;
	
	private List<Rechnungspositionen> weinliste = new ArrayList<Rechnungspositionen>();
	
	public List<Rechnungspositionen> getWeinliste()
	{
		return this.weinliste;
	}
	
	public List<Rechnungspositionen> fuelleListe() throws Exception
	{
		for(Weine w : kellerbuch.getWeinliste()){
			if(!istInListe(w))
				this.weinliste.add(new Rechnungspositionen(w, 1));	
		}
		return this.weinliste;
	}

	private boolean istInListe(Weine w)
	{
		for(Rechnungspositionen pos : this.weinliste)
		{
			if(pos.getWein().getId() == w.getId()){
				return true;
			}
		}
		return false;
	}

	public WebKellerbuch getKellerbuch()
	{
		return kellerbuch;
	}

	public void setKellerbuch(WebKellerbuch kellerbuch)
	{
		this.kellerbuch = kellerbuch;
	}

	public void setWeinliste(List<Rechnungspositionen> weinliste)
	{
		this.weinliste = weinliste;
	}
	
	public WeinlisteBean() throws Exception
	{
//		fuelleListe();
	}

}
