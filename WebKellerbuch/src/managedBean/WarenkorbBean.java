package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import kellerbuch.fachlogik.Rechnungspositionen;
import kellerbuch.fachlogik.Weine;

@ManagedBean(name="warenkorb")
@SessionScoped
public class WarenkorbBean
{
	private List<Rechnungspositionen> warenListe;
	
	public WarenkorbBean()
	{
		warenListe = new ArrayList<Rechnungspositionen>();
	}

	public List<Rechnungspositionen> getWarenListe()
	{
		return warenListe;
	}

	public void setWarenListe(List<Rechnungspositionen> warenListe)
	{
		this.warenListe = warenListe;
	}

	public void neu(Rechnungspositionen neu) throws Exception
	{
		if(neu != null)
		{
			if(!warenListe.contains(neu))
			{
				warenListe.add(neu);
			}
			else
			{
				throw new Exception("Rechnungsposition wurde bereits hinzugefügt");
			}
		}
		else
		{
			throw new Exception("Rechnungsposition darf nicht leer sein");
		}
	}
	
	public void loschen(Rechnungspositionen pos) throws Exception
	{
		if(istinListe(pos))
			warenListe.remove(pos);
		else
			throw new Exception("Diese Position ist nicht vorhanden!");
	}
	
	public boolean istinListe(Rechnungspositionen pos)
	{
		for(Rechnungspositionen p: warenListe)
		{
			if(p.getWein().getId() == pos.getWein().getId())
			{
				return true;
			}
		}
		return false;
	}
	
	public void neu(Weine wein, int anzahl) throws Exception
	{
		if(wein != null)
		{
			Rechnungspositionen r = new Rechnungspositionen(wein, anzahl);
			if(!warenListe.contains(r))
			{
				warenListe.add(r);
			}
			else
			{
				throw new Exception("Rechnungsposition wurde bereits hinzugefügt");
			}
		}
		else
		{
			throw new Exception("Rechnungsposition darf nicht leer sein");
		}
		
		//Überprüfung, ob richtig speichert!!!
		System.out.println("--- START WARENKORB ---");
		System.out.println("Warenkorb: ");
		for(Rechnungspositionen pos : warenListe)
			System.out.println("Wein: "+pos.getWein()+ " - Menge: " +pos.getMenge());
		System.out.println("--- ENDE WARENKORB ---");
	}
}
