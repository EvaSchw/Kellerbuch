package kellerbuch.fachlogik;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="k_kunden")
@NamedQuery(name = "Kunden.findAll", query = "Select k from Kunde k")
public class Kunde
{
	@Id
	@Column(name = "k_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int kundennr;
	@Column(name = "k_firma")
	private String firma;
	@Column(name = "k_nachname")
	private String nachname;
	@Column(name = "k_vorname")
	private String vorname;
	@Column(name = "k_strasse")
	private String strasse;
	@Column(name = "k_hausnr")
	private String hausnummer;
	@Column(name = "k_plz")
	private int plz;
	@Column(name = "k_ort")
	private String ort;
	@Column(name = "k_land")
	private String land;
	
	@OneToMany(mappedBy="kunde", cascade=CascadeType.REMOVE) //MappedBy = zu was das in der anderen Tabelle gehört
	private List<Rechnung> alleRechnungen;
	
	public Kunde()
	{
	}
	
	public Kunde(String firma, String nachname, String vorname, String strasse, String hausnummer, int plz, String ort,
					String land) throws Exception
	{
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
	
	public int getKundennr()
	{
		return kundennr;
	}

	public void setKundennr(int id)
	{
		this.kundennr = id;
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
			throw new Exception("Straï¿½e darf nicht leer sein!");
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
	
	public List<Rechnung> getRechnungen()
	{
		return alleRechnungen;
	}
	
}
