package kellerbuch.fachlogik;

import java.util.ArrayList;
import java.util.List;

public class Kunde
{
//	Anrede, Firma, Nachname, Vorname, Stra�e, Hausnr, PLZ, Ort, Land, Telefonnummer, E-Mail
	private static int laufendeNr = 1;
	private int kundennr;
	private String firma;
	private String nachname;
	private String vorname;
	private String strasse;
	private String hausnummer;
	private int plz;
	private String ort;
	private String land;
	private List<Rechnung> alleRechnungen;
	
	public Kunde(String firma, String nachname, String vorname, String strasse, String hausnummer, int plz, String ort,
					String land) throws Exception
	{
		this.kundennr = laufendeNr;
		laufendeNr++;
		this.alleRechnungen = new ArrayList<Rechnung>();
		setFirma(firma);
		setNachname(nachname);
		setVorname(vorname);
		setStrasse(strasse);
		setHausnummer(hausnummer);
		setPlz(plz);
		setOrt(ort);
		setLand(land);
	}
	
	@Override
	public String toString()
	{
		if(this.firma != null)
		{
			if(this.firma.isEmpty())
			{
				return (nachname + " " + vorname);
			}
			else 
			{
				return "Firma " + firma;
			}
		}
		else
			return (nachname + " " + vorname);
	}
	
	public String getFirma()
	{
		return firma;
	}
	public void setFirma(String firma)
	{
		this.firma = firma;
	}
	
	public String getNachname()
	{
		return nachname;
	}
	public void setNachname(String nachname)
	{
		this.nachname = nachname;
	}
	
	public String getVorname()
	{
		return vorname;
	}
	public void setVorname(String vorname)
	{
		this.vorname = vorname;		
	}
	
	public String getStrasse()
	{
		return strasse;
	}
	public void setStrasse(String strasse) throws Exception
	{
		if(strasse != null)
			this.strasse = strasse;
		else
			throw new Exception("Stra�e darf nicht leer sein!");
	}
	
	public String getHausnummer()
	{
		return hausnummer;
	}
	public void setHausnummer(String hausnummer)
	{
		this.hausnummer = hausnummer;
	}
	
	public int getPlz()
	{
		return plz;
	}
	public void setPlz(int plz) throws Exception
	{
		if(plz > 999)
			this.plz = plz;
		else
			throw new Exception("PLZ muss 4-stellig sein!");
	}
	
	public String getOrt()
	{
		return ort;
	}
	public void setOrt(String ort) throws Exception
	{
		if(ort != null)
			this.ort = ort;
		else
			throw new Exception("Ort darf nicht leer sein!");
	}
	
	public String getLand()
	{
		return land;
	}
	public void setLand(String land) throws Exception
	{
		if(land != null)
			this.land = land;
		else
			throw new Exception("Land darf nicht leer sein!");
	}
	
	public int getKundennr()
	{
		return kundennr;
	}
	
	public List<Rechnung> getRechnungen()
	{
		return alleRechnungen;
	}
	
}
