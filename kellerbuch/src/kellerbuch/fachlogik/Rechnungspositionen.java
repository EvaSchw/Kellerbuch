package kellerbuch.fachlogik;

import javax.persistence.*;

@Entity
@Table(name="rp_rechnungspositionen")
@NamedQuery(name="Rechnungspositionen.findAll", query="select rp from Rechnungspositionen rp")
public class Rechnungspositionen
{
	//Klärung wie genau erstellen (ob überhaupt id benötigt wird) (TeeshopCopy Bestellung und BestellungPK)
	@Id
	private int id;
	@ManyToOne
	private Rechnung rechnung;
	private Weine wein;
	private int menge;
	
	public Rechnungspositionen()
	{
	}
	
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
