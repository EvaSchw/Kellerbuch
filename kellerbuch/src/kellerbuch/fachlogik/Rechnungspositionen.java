package kellerbuch.fachlogik;

import javax.persistence.*;

@Entity
@Table(name="rp_rechnungspositionen")
@NamedQuery(name="Rechnungspositionen.findAll", query="select rp from Rechnungspositionen rp")
public class Rechnungspositionen
{
	//Kl�rung wie genau erstellen (ob �berhaupt id ben�tigt wird)
	@Id
	@Column(name="rp_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="rp_r_id")
	private Rechnung rechnung;
	
	@ManyToOne
	@JoinColumn(name="rp_w_id")
	private Weine wein;
	
	@Column(name="rp_menge")
	private int menge;
	
	public Rechnungspositionen()
	{
	}
	
	public Rechnungspositionen(Weine w, int menge)
	{
		this.wein = w;
		this.menge = menge;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public void setId(int id)
	{
		this.id = id;
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
