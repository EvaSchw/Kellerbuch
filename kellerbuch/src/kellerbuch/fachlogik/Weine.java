package kellerbuch.fachlogik;

import javax.persistence.*;

@Entity
@Table (name="w_weine")
@NamedQuery(name="Weine.findAll", query="Select w from Weine w")
public class Weine
{
	@Id //kennzeichnet den eindeutigen Schlüssel
	@Column(name="w_id") //wenn Tabellenspalte anders heißt als hier
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Schlüssel automatisch generieren
	private int id;
	@Column(name="w_bezeichnung")
	private String bezeichnung; // z.B. Grüner Veltliner Alte Rebe
	@Column(name="w_jahrgang")
	private int jahrgang; // z.B. 2014
	@Column(name="w_alkohol")
	private double alkohol; // z.B. 12,5
	@Column(name="w_qualitätswein")
	private boolean qualitaetswein;
	@Column(name="w_lage")
	private String lage; // z.B. Kremstal
	@Column(name="w_liter")
	private double liter; // z.B. 0,75
	@Column(name="w_lagerstand")
	private int lagerstand; // z.B. 500
	@Column(name="w_preis")
	private double preis; // z.B. 5,50
	
	public Weine()
	{
	}
	
	public Weine(String bezeichnung, int jahrgang, int alkohol,	boolean qualitaetswein, String lage, double liter,
			int lagerstand,	double preis) throws Exception
	{
		setBezeichnung(bezeichnung);
		
		if(jahrgang >= 2000)
			this.jahrgang = jahrgang;
		else
			throw new Exception("Die Jahreszahl muss größer als 2000 sein!");
		
		if(alkohol > 9)
			this.alkohol = alkohol;
		else
			throw new Exception("Der Alkoholgehalt muss größer 9 sein!");
		
		this.qualitaetswein = qualitaetswein;
		this.lage = lage;
		this.liter = liter;
		setLagerstand(lagerstand);
		setPreis(preis);
	}
	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	} 

	public String getBezeichnung()
	{
		return bezeichnung;
	}
	
	public void setBezeichnung(String bezeichnung) throws Exception
	{
		if(bezeichnung != null)
			this.bezeichnung = bezeichnung;
		else
			throw new Exception("Bezeichnung darf nicht leer sein!");
	}
	
	public int getJahrgang()
	{
		return jahrgang;
	}
		
	public double getAlkohol()
	{
		return alkohol;
	}
	
	public String isQualitaetswein()
	{
		if(qualitaetswein)
			return "Qualitätswein";
		else
			return "Landwein";
	}
	
	public String getLage()
	{
		return lage;
	}
	
	public double getLiter()
	{
		return liter;
	}
	
	public int getLagerstand()
	{
		return lagerstand;
	}
	
	public void setLagerstand(int lagerstand) throws Exception
	{
		if(lagerstand >= 0)
			this.lagerstand = lagerstand;
		else
			throw new Exception("Lagerstand darf nicht kleiner als 0 sein!");
	}
	
	public double getPreis()
	{
		return preis;
	}
	
	public void setPreis(double preis) throws Exception
	{
		if(preis > 0)
			this.preis = preis;
		else
			throw new Exception("Preis darf nicht kleiner als 0 sein!");
	}
	
	@Override
	public String toString()
	{
		return this.bezeichnung + " " + this.jahrgang;
	}
}
