package kellerbuch.fachlogik;

public class Rechnungspositionen
{
	Rechnung rechnung;
	private Weine wein;
	private int menge;
	
	public Rechnungspositionen(Weine w, int menge)
	{
		this.wein = w;
		this.menge = menge;
	}
	
	public int getMenge()
	{
		return menge;
	}
	public void setMenge(int menge)
	{
		this.menge = menge;
	}
	public Rechnung getRechnung()
	{
		return rechnung;
	}
	
	public void setRechnung(Rechnung rechnung)
	{
		this.rechnung = rechnung;
	}
	public Weine getWein()
	{
		return wein;
	}
}
