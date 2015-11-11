package kellerbuch.fachlogik;

public class Weine
{
	private static int nummer = 1;
	private int id;
	private String bezeichnung; // z.B. Grüner Veltliner Alte Rebe
	private int jahrgang; // z.B. 2014
	private int alkohol; // z.B. 12
	private boolean qualitaetswein;
	private String lage; // z.B. Kremstal
	private double liter; // z.B. 0,75
	private int lagerstand; // z.B. 500
	private double preis; // z.B. 5,50
	
	public Weine(String bezeichnung, int jahrgang, int alkohol,	boolean qualitaetswein, String lage, double liter,
			int lagerstand,	double preis) throws Exception
	{
		this.id = nummer;
		nummer++;
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
	
	public int getAlkohol()
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
	
	public int getJahrgang()
	{
		return jahrgang;
	}
	
	@Override
	public String toString()
	{
		return this.bezeichnung + " " + this.jahrgang;
	}
}
