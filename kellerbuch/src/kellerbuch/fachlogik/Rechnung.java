package kellerbuch.fachlogik;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="r_rechnungen")
@NamedQuery(name = "Rechnung.findAll", query = "Select r from Rechnung r")
public class Rechnung
{
	@Id
	@Column(name = "r_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "r_nr")
	private int rechnungsnr;
	@Column(name = "r_datum")
	private Date rechnungsdatum;
	@Column(name = "r_k_id")
	@ManyToOne
	private Kunde kunde;
	
	@OneToMany(mappedBy = "rechnung", cascade= CascadeType.REMOVE)
	private List<Rechnungspositionen> rechnungspositionen;
	
	public Rechnung()
	{
	}
	
	public Rechnung(int rechnungsnr, Date datum, Kunde k)
	{
		this.rechnungsnr = rechnungsnr;
		this.rechnungsdatum = datum;
		this.kunde = k;
		this.rechnungspositionen = new ArrayList<Rechnungspositionen>();
	}
	
	@Override
	public String toString()
	{
		return "Rechnung " + this.rechnungsnr;
	}
	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
	
	public int getRechnungsnr()
	{
		return rechnungsnr;
	}
	
	public String getRechnungsdatum()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		return sdf.format(rechnungsdatum);
	}
	
	public void neueRechungsposition(Rechnungspositionen pos) throws Exception{
		if(pos != null){
			if(!rechnungspositionen.contains(pos)){
			 	if(rechnungspositionen.add(pos)){
			 		pos.setRechnung(this);
			 	} else {
			 		throw new Exception("Ein unbekannter Fehler ist aufgetreten! Die Rechnungsposition konnte nicht hinzugefügt werden!");
			 	}
			} else {
				throw new IllegalArgumentException("Rechungspositon bereits hinzugefügt!");
			}
		} else {
			throw new NullPointerException("Rechungsposition darf nicht leer sein!");
		}
	}
	
	public List<Rechnungspositionen> getRechnungspositionen(){
		return rechnungspositionen;
	}
	
	public Kunde getKunde()
	{
		return kunde;
	}
}
