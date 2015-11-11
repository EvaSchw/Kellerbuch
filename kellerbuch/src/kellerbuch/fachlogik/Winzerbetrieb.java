package kellerbuch.fachlogik;

import java.util.*;

public class Winzerbetrieb
{
	private List<Weine> weinliste;
	private List<Kunde> kundenliste;
	private List<Rechnung> rechnungsliste;

	public Winzerbetrieb()
	{
		this.weinliste = new ArrayList<Weine>();
		this.kundenliste = new ArrayList<Kunde>();
		this.rechnungsliste = new ArrayList<Rechnung>();
	}

	public List<Weine> getWeinliste()
	{
		return weinliste;
	}

	public List<Kunde> getKundenliste()
	{
		return kundenliste;
	}
	
	public List<Rechnung> getRechnungsliste()
	{
		return rechnungsliste;
	}
	
	public void weineAnlegen(Weine wein) throws Exception
	{
		if(!weinliste.contains(wein))
			weinliste.add(wein);
		else
			throw new Exception("Wein befindet bereits in der Liste!");
	}
	
	public void kundenAnlegen(Kunde kunde) throws Exception
	{
		if(!kundenliste.contains(kunde))
			kundenliste.add(kunde);
		else
			throw new Exception("Kunde befindet bereits in der Liste!");
	}
	
	public void rechnungAnlegen(Rechnung rechnung) throws Exception
	{
		if(!rechnungsliste.contains(rechnung))
			rechnungsliste.add(rechnung);
		else
			throw new Exception("Rechnung ist bereits vorhanden!");
	}
	
	public void weineloeschen(Weine wein) throws Exception
	{
		if(weinliste.contains(wein))
			weinliste.remove(wein);
		else
			throw new Exception("Wein existiert nicht!");
	}
	
	public void kundenloeschen(Kunde kunde) throws Exception
	{
		if(kundenliste.contains(kunde))
			kundenliste.remove(kunde);
		else
			throw new Exception("Kunde existiert nicht!");
	}
}