package kellerbuch.fachlogik;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Rechnung
{
	private static int nummer = 1;
	private int id;
	private int rechnungsnr;
	private Date rechnungsdatum;
	private Kunde kunde;
	private List<Rechnungspositionen> rechnungspositionen;
	
	public Rechnung(int rechnungsnr, Date datum, Kunde k)
	{
		this.id = nummer;
		nummer++;
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
